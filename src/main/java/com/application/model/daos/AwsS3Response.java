package com.application.model.daos;

import lombok.Builder;
import lombok.Getter;

import java.io.File;

@Builder
@Getter
public class AwsS3Response extends BaseDataSourceResponse{
    File[] files;
}
