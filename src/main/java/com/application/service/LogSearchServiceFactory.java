package com.application.service;

import com.application.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogSearchServiceFactory {
    public AwsS3Service awsS3Service;

    @Autowired
    public LogSearchServiceFactory(AwsS3Service awsS3Service) {
        this.awsS3Service = awsS3Service;
    }

    public LogSearchService getService(DataSource dataSource){
        switch (dataSource) {
            case AWS_S3 -> {
                return new AwsS3DataSourceGrepService(awsS3Service);
            }
            default -> throw new IllegalArgumentException("Invalid enum value: " + dataSource);
        }
    }
}
