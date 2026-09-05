package com.movieapp.movie_backend;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class tmdbTest {
    public static void main(String[] args) throws Exception {
        String url = "https://api.themoviedb.org/3/search/movie?api_key=6e0837d0d7414a0c8118eea8edb4bcf1&query=Batman";

        HttpClient client = HttpClient.newHttpClient(); // no forced version, defaults to TLS 1.3 capable

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
