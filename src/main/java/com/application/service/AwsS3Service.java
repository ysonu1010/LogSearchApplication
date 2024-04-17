package com.application.service;

import com.amazonaws.services.s3.AmazonS3;
import com.application.model.daos.AwsS3Response;
import com.application.model.daos.BaseDataSourceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class AwsS3Service implements DataSourceService {
    AmazonS3 awsClient;

    @Autowired
    public AwsS3Service(AmazonS3 amazonS3) {
        this.awsClient = amazonS3;
    }

    @Override
    public AwsS3Response getData(BaseDataSourceRequest input) {
        /**
         * TODO - write aws client - GET
         */

        // Using stored mock files from a project directory
        String directoryPath = input.getPath();
        File directories = new File(directoryPath);
        File[] files = directories.listFiles();
        return AwsS3Response.builder()
                .files(files)
                .build();
    }
}
