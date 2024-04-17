package com.application.model.daos;

import lombok.Builder;
import lombok.Getter;

import java.io.File;
import java.util.List;

@Builder
@Getter
public class AwsS3Request extends BaseDataSourceRequest{
    String path;
}
