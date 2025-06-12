package cn.moonshotacademy.memoirs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.typesense.model.SearchResult;

@Data
@AllArgsConstructor
public class SearchResultDto {
    private SearchResult stories;
    private SearchResult tellers;
    private SearchResult users;
}
