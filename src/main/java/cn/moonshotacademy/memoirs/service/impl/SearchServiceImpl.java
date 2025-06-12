package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.service.SearchService;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.typesense.api.Client;
import org.typesense.api.Configuration;
import org.typesense.api.FieldTypes;
import org.typesense.api.exceptions.ObjectNotFound;
import org.typesense.model.CollectionAlias;
import org.typesense.model.CollectionAliasSchema;
import org.typesense.model.CollectionResponse;
import org.typesense.model.CollectionSchema;
import org.typesense.model.Field;
import org.typesense.model.FieldEmbed;
import org.typesense.model.FieldEmbedModelConfig;
import org.typesense.model.ImportDocumentsParameters;
import org.typesense.model.IndexAction;
import org.typesense.model.SearchParameters;
import org.typesense.model.SearchResult;
import org.typesense.resources.Node;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    private final Configuration configuration;
    private final Configuration importConfiguration;
    private final Client client;
    private final Client importClient;
    private final CollectionSchema storySchema;

    public SearchServiceImpl() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(
                new Node(
                        "http", // For Typesense Cloud use https
                        "localhost", // For Typesense Cloud use xxx.a1.typesense.net
                        "8108" // For Typesense Cloud use 443
                        ));

        configuration = new Configuration(nodes, Duration.ofSeconds(2), "xyz");
        importConfiguration = new Configuration(nodes, Duration.ofMinutes(5), "xyz");
        client = new Client(this.configuration);
        importClient = new Client(this.importConfiguration);
        storySchema =
                new CollectionSchema()
                        .name("stories")
                        .addFieldsItem(new Field().name("story").type(FieldTypes.STRING).locale("zh"))
                        .addFieldsItem(
                                new Field().name("tags").type(FieldTypes.STRING_ARRAY).locale("zh").facet(true))
                        .addFieldsItem(new Field().name("articleId").type(FieldTypes.INT32).index(false))
                        .addFieldsItem(
                                new Field()
                                        .name("embedding")
                                        .type(FieldTypes.FLOAT_ARRAY)
                                        .embed(
                                                new FieldEmbed()
                                                        .from(List.of("story"))
                                                        .modelConfig(
                                                                new FieldEmbedModelConfig()
                                                                        .modelName("ts/multilingual-e5-small")
                                                                        .indexingPrefix("passage: ")
                                                                        .queryPrefix("query: "))));
    }

    @Override
    public SearchResult searchStories(
            String q, Optional<Integer> snippetLength, Optional<Boolean> preciseSearch) {
        boolean isPrecise = preciseSearch.isPresent() && preciseSearch.get();
        String queryBy = isPrecise ? "story,tags" : "story,tags,embedding";
        SearchParameters searchParameters =
                new SearchParameters()
                        .q(q)
                        .queryBy(queryBy)
                        .sortBy("_text_match:desc")
                        .stopwords("stopword_set1");
        if (snippetLength.isPresent()) {
            searchParameters.setHighlightAffixNumTokens(snippetLength.get());
        } else {
            searchParameters.setHighlightFullFields("story");
        }
        try {
            return client.collections("stories").documents().search(searchParameters);
        } catch (Exception e) {
            log.error("Error when searching stories", e);
            throw new BusinessException(ExceptionEnum.SEARCH_ERROR);
        }
    }

    @Override
    public SearchResult searchTeller(String q) {
        SearchParameters searchParameters =
                new SearchParameters().q(q).queryBy("name").sortBy("_text_match:desc").infix("always");
        try {
            return client.collections("narrator").documents().search(searchParameters);
        } catch (Exception e) {
            log.error("Error when searching narrator", e);
            throw new BusinessException(ExceptionEnum.SEARCH_ERROR);
        }
    }

    @Override
    public SearchResult searchUser(String q) {
        SearchParameters searchParameters =
                new SearchParameters().q(q).queryBy("name").sortBy("_text_match:desc").infix("always");
        try {
            return client.collections("user").documents().search(searchParameters);
        } catch (Exception e) {
            log.error("Error when searching narrator", e);
            throw new BusinessException(ExceptionEnum.SEARCH_ERROR);
        }
    }

    @Override
    public void syncStories(List<ArticleEntity> stories) throws Exception {
        String newName;
        try {
            CollectionAlias currentAlias = client.aliases("stories").retrieve();
            newName = currentAlias.getCollectionName().equals("stories_1") ? "stories_2" : "stories_1";
        } catch (ObjectNotFound e) {
            newName = "stories_1";
        }

        CollectionResponse[] collectionList = client.collections().retrieve();
        for (CollectionResponse collectionResponse : collectionList) {
            if (collectionResponse.getName().equals(newName)) {
                client.collections(newName).delete();
                break;
            }
        }
        client.collections().create(storySchema.name(newName));

        ArrayList<HashMap<String, Object>> documentList = new ArrayList<>();

        for (ArticleEntity articleEntity : stories) {
            HashMap<String, Object> document = new HashMap<>();
            document.put("story", articleEntity.getText());
            document.put("tags", articleEntity.getTagList().stream().map(tag -> tag.getName()).toArray());
            document.put("articleId", articleEntity.getId());

            documentList.add(document);
        }

        ImportDocumentsParameters importDocumentsParameters =
                new ImportDocumentsParameters().action(IndexAction.CREATE);

        importClient.collections(newName).documents().import_(documentList, importDocumentsParameters);

        CollectionAliasSchema collectionAliasSchema =
                new CollectionAliasSchema().collectionName(newName);

        client.aliases().upsert("stories", collectionAliasSchema);
    }
}
