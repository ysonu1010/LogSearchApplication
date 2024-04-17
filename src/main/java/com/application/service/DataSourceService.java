package com.application.service;

import com.application.model.daos.BaseDataSourceRequest;
import com.application.model.daos.BaseDataSourceResponse;

public interface DataSourceService {
    BaseDataSourceResponse getData(BaseDataSourceRequest path);
}
