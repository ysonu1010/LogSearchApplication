package com.application.controller;

import com.application.DataSource;
import com.application.model.SearchRequestBody;
import com.application.model.SearchResponseBody;
import com.application.service.LogSearchService;
import com.application.service.LogSearchServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log-search")
public class LogSearchController {

    final LogSearchService logSearchService;

    @Autowired
    public LogSearchController(LogSearchServiceFactory logSearchServiceFactory) {
        this.logSearchService = logSearchServiceFactory.getService(DataSource.AWS_S3);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SearchResponseBody> searchLogs(@RequestBody SearchRequestBody body) {
        if(body == null || body
                .getSearchKeyword() == null || body.getFrom() == null || body.getTo() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        try {
            return ResponseEntity.ok().body(logSearchService.search(body));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(SearchResponseBody.builder()
                            .matchedLines(null)
                            .build());
        }
    }
}
