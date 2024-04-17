# LogSearchApplication

## Launching the Spring Boot Application

To launch the Spring Boot application, follow these steps:

1. Clone the repository to your local machine.
2. Navigate to the root directory of the project.
3. Run the following command to build the application:
```code
mvn clean install
```
4. Once the build is successful, run the following command to start the application:

``` code
java -jar target/LogSearchApplication-0.0.1-SNAPSHOT.jar
```

The application will start, and you can access it at `http://localhost:8080`.

## cURL for log search Endpoint

### Endpoint: [POST] `/log-search/search`

You can use the following cURL command to access the `/log-search/search` endpoint:

```bash
curl --location 'http://localhost:8080/log-search/search' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data '{
    "searchKeyword": "micro",
    "from": "2023-11-25T12:30:00",
    "to": "2023-11-26T12:30:00"
}'
```

## Response
Upon accessing the /log-search/search endpoint, you will receive a JSON response with the following structure:

```json 
{
  "matchedLines": [
    "2023-11-25 00:02:39.345 DEBUG className:200 - 01:txt :- Developed the sample microservice",
    "2023-11-25 00:03:39.345 DEBUG className:200 - 01:txt :- This microservice serves the log search capability",
    "2023-11-25 00:02:39.345 DEBUG className:200 - 02:txt :- Developed the sample microservice",
    "2023-11-25 00:03:39.345 DEBUG className:200 - 02:txt :- This microservice serves the log search capability"
  ]
}
```



## Mock Details
- Project contains mocked log files in path com/application/service/mocks
- We read this path instead of aws/azure data source for log filter input

