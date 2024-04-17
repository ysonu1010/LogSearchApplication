package com.application.model;

import com.application.model.daos.BaseDataSourceResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SearchResponseBody {
    private List<String> matchedLines;
}
