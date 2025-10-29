package service;

import config.RequestConfig;
import utils.SaveUtils;

import java.net.http.*;
import java.net.*;
import java.io.IOException;

public class Requests {
    private static final String url = "https://randomuser.me/";
    private static final String archivePath = "requests";
    private static final int urlInput = 1000;
    private static final HttpClient client = HttpClient.newHttpClient();
    private final SaveUtils saver;

    public Requests() {
        this.saver = new SaveUtils();
    }

    public String request(RequestConfig config) throws  IOException, InterruptedException {
        String data = requestString();

        switch (config) {
            case DONT_SAVE:
                return data;
            case SAVE_JSON:
                return saver.save(data, archivePath, ".json");
            case SAVE_CSV:
                return saver.save(data, archivePath, ".csv");
            case SAVE_TXT:
                return saver.save(data, archivePath, ".txt");
        }

        return null;
    }

    private String requestString() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "api/?results=" + urlInput))
                .GET()
                .build();

        HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());

        return res.body();
    }
}
