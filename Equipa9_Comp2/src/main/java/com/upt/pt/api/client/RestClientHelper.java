package com.upt.pt.api.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public final class RestClientHelper {

    private static final String BASE_URL = "http://localhost:8080";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    private RestClientHelper() {}

    public static HttpResponse<String> get(String path) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .GET()
                .build();
        HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
        return resp;
    }

    public static HttpResponse<String> post(String path, String jsonBody) throws IOException, InterruptedException {
        HttpRequest.Builder b = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .header("Content-Type", "application/json");
        if (jsonBody != null && !jsonBody.isBlank()) {
            b = b.POST(HttpRequest.BodyPublishers.ofString(jsonBody));
        } else {
            b = b.POST(HttpRequest.BodyPublishers.noBody());
        }
        HttpResponse<String> resp = CLIENT.send(b.build(), HttpResponse.BodyHandlers.ofString());
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
        return resp;
    }

    public static HttpResponse<String> put(String path, String jsonBody) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
        return resp;
    }

    public static HttpResponse<String> delete(String path) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .DELETE()
                .build();
        HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
        return resp;
    }
}
