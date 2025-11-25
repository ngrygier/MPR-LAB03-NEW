package service;
import com.google.gson.*;
import exception.ApiException;
import model.Pracownik;
import model.Stanowisko;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.*;
import java.io.IOException;

public class ApiService {
    //pobieranie z API
    public static void fetchFromAPI() throws IOException, InterruptedException, ApiException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .build();


        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Gson gson = new Gson();
            JsonObject post = gson.fromJson(response.body(), JsonObject.class);

            //tworzenie nowego rpogramisty
            String pelneImie = post.get("name").getAsString();
            String email = post.get("email").getAsString();
            String companyName = post.get("company.name").getAsString();
            String[] imieINazwisko = pelneImie.split(" ");
            String imie = imieINazwisko[0];
            String nazwisko = imieINazwisko[1];

            new Pracownik(imie, nazwisko, companyName, email, Stanowisko.PROGRAMISTA);

        } else {
            System.out.println("Błąd HTTP: " + response.statusCode());
        }
    }
}
