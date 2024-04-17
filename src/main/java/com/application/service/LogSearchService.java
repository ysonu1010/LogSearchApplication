package com.application.service;

import com.application.model.SearchRequestBody;
import com.application.model.SearchResponseBody;
import org.springframework.stereotype.Service;

@Service
abstract public class LogSearchService {

    abstract public SearchResponseBody search(SearchRequestBody body);
}
