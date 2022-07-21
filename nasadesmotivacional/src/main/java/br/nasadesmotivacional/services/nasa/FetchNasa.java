package br.nasadesmotivacional.services.nasa;

import java.io.IOException;
import java.net.http.*;
import java.net.URI;
import java.util.*;

import com.google.gson.Gson;

import br.nasadesmotivacional.services.twitter.ResponseNasa;

public class FetchNasa {
  private static FetchNasa instance;
  private static final String NASA_API_KEY = "DEMO_KEY";// "CmD9tirYzJ8uTVAxuZdIIiHAtW4xCyHWgpfYTsTD";
  private static final String NASA_API_URL = "https://api.nasa.gov/planetary/apod";

  private FetchNasa() {
  }

  public static FetchNasa getInstance() {
    if (instance == null) {
      instance = new FetchNasa();
    }
    return instance;
  }

  public ResponseNasa requestJSON(String url) throws IOException, InterruptedException {

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
    HttpRequest.newBuilder(URI.create(url)).GET().build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    String body = response.body();

    return new Gson().fromJson(body, ResponseNasa.class);
  }

  public ResponseNasa requestJSON() throws IOException, InterruptedException {
    String url = NASA_API_URL + "?api_key=" + NASA_API_KEY + "&thumbs=true";
    return requestJSON(url);
  }

}