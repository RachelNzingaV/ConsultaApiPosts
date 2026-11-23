import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ejercicio1GetHttpClient {

    public static void main(String[] args) {
        String urlString = "https://jsonplaceholder.typicode.com/users/1";

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
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
                System.out.println("Error en la petición HTTP. Código: " + respuesta.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al enviar la petición con HttpClient: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}