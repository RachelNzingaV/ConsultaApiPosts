import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ejercicio1RickAndMorty {

    public static void main(String[] args) {
        String urlPersonajes = "https://rickandmortyapi.com/api/character";

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(urlPersonajes))
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = cliente.send(
                    peticion,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (respuesta.statusCode() >= 200 && respuesta.statusCode() <= 299) {
                System.out.println(respuesta.body());
            } else {
                System.out.println("La petición no fue correcta. Código HTTP: " + respuesta.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al consultar The Rick and Morty API: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
