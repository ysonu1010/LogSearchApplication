package com.application.service;

import com.application.model.SearchRequestBody;
import com.application.model.SearchResponseBody;
import com.application.model.daos.AwsS3Request;
import com.application.model.daos.AwsS3Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class AwsS3DataSourceGrepService extends LogSearchService {
    private final AwsS3Service awsS3Service;
    @Autowired
    public AwsS3DataSourceGrepService(AwsS3Service awsS3Service){
        this.awsS3Service = awsS3Service;
    }
    public SearchResponseBody search(SearchRequestBody body) {
        List<String> paths = getS3paths(body.getFrom(), body.getTo());
        List<String> matchedLines = new ArrayList<>();

        for(String path: paths) {
            AwsS3Request awsS3Request = AwsS3Request.builder()
                    .path(path)
                    .build();
            AwsS3Response s3Response = awsS3Service.getData(awsS3Request);

            Pattern pattern = Pattern.compile(body.getSearchKeyword());
            File[] files = s3Response.getFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                Matcher matcher = pattern.matcher(line);
                                if (matcher.find()) {
                                    matchedLines.add(line);
                                }
                            }
                        } catch (IOException e) {
                            log.error(e.getMessage());
                            throw new RuntimeException();
                        }
                    }
                }
            } else {
                log.info("No files found in the directory");
            }
        }

        return SearchResponseBody.builder()
                .matchedLines(matchedLines)
                .build();
    }

    private List<String> getS3paths(LocalDateTime from, LocalDateTime to){
        boolean mock = true; // mocked to fetch files from project directory
        String prefix = !mock ? "bucket-name-based-prefix" : "src/main/java/com/application/service/mocks/logs_";
        List<String> dates = getDatesBetween(from, to);
        return dates.stream().map(date-> prefix + date).toList();
    }

    List<String> getDatesBetween(LocalDateTime from, LocalDateTime to) {
        LocalDateTime current = from;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        List<String> dates = new ArrayList<>();

        while (!current.isAfter(to)) {
            String fromDate = current.format(format);
            dates.add(fromDate);
            current = current.plusDays(1);
        }
        return dates;
    }
}
