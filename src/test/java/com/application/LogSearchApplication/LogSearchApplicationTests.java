package com.application.LogSearchApplication;

import com.application.model.SearchRequestBody;
import com.application.model.SearchResponseBody;
import com.application.service.AwsS3DataSourceGrepService;
import com.application.service.AwsS3Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class LogSearchApplicationTests {
	AwsS3DataSourceGrepService awsS3DataSourceGrepService;
	SearchRequestBody searchRequestBody;

	@BeforeEach
	void setup() {
		AwsS3Service awsS3CRUD = new AwsS3Service(null);
		String from = "2023-11-25T12:30:00";
		String to = "2023-11-26T12:30:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		this.awsS3DataSourceGrepService = new AwsS3DataSourceGrepService(awsS3CRUD);

		searchRequestBody = new SearchRequestBody("micro",
				LocalDateTime.parse(from, formatter),
				LocalDateTime.parse(to, formatter));
	}

	@Test
	void testGrepService() {
		SearchResponseBody response = awsS3DataSourceGrepService.search(searchRequestBody);
		Assertions.assertNotNull(response);
	}
}
