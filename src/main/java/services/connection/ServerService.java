package services.connection;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Map;


public class ServerService {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    private HttpClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T makeGetRequest(String uri,
                            Map<String, String> headers,
                            Class<T> serverResponse)  {
        client = HttpClient.newBuilder().build();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(uri))
                    .headers(headers.entrySet().stream()
                            .map(header -> new String[]{header.getKey(), header.getValue()})
                            .flatMap(Arrays::stream)
                            .toArray(String[]::new))
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());


            return objectMapper.readValue(
                    response.body(),
                    serverResponse
            );

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(ANSI_RED +"Request ERROR"+ ANSI_RESET);
        }
    }

    public <T> T makePostRequest(String uri,
                             Map<String, String> headers,
                             Object body,
                             Class<T> serverResponse) {
        client = HttpClient.newBuilder().build();
        try {
            String requestBody = objectMapper.writeValueAsString(body);
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .uri(URI.create(uri))
                    .headers(headers.entrySet().stream()
                            .map(header -> new String[]{header.getKey(), header.getValue()})
                            .flatMap(Arrays::stream)
                            .toArray(String[]::new))
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(
                    response.body(),
                    serverResponse
            );

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(ANSI_RED +"Response ERROR"+ ANSI_RESET);
        }
    }

//    List<User> getGetRequest(){
//        UsersResponse response = (UsersResponse) getRequest("https://mag-contacts-api.herokuapp.com/users", UsersResponse.class);
//        if ("ok".equals(response.getStatus())) {
//            return response.getUsers();
//        } else {
//            throw new RuntimeException("ERROR");
//        }
//    }
}
