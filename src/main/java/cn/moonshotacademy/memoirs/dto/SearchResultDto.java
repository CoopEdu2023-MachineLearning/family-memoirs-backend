package cn.moonshotacademy.memoirs.dto;

import org.typesense.model.SearchResult;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchResultDto {
    private SearchResult stories;
    private SearchResult tellers;
    private SearchResult users;
}
