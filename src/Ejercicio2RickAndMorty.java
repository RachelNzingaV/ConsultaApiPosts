import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class Ejercicio2RickAndMorty {

    public static void main(String[] args) {
        String urlPersonajes = "https://rickandmortyapi.com/api/character";
        HttpURLConnection conexion = null;
        BufferedReader lector = null;

        try {
            URL url = URI.create(urlPersonajes).toURL();
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            int codigoRespuesta = conexion.getResponseCode();
            if (codigoRespuesta >= 200 && codigoRespuesta <= 299) {
                lector = new BufferedReader(
                        new InputStreamReader(conexion.getInputStream(), "UTF-8")
                );

                StringBuilder respuesta = new StringBuilder();
                String linea;
                while ((linea = lector.readLine()) != null) {
                    respuesta.append(linea).append(System.lineSeparator());
                }

                System.out.println(respuesta.toString());
            } else {
                System.out.println("La petición no fue correcta. Código HTTP: " + codigoRespuesta);
            }

        } catch (IOException e) {
            System.out.println("Error de conexión o lectura: " + e.getMessage());
        } finally {
            if (lector != null) {
                try {
                    lector.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar BufferedReader: " + e.getMessage());
                }
            }
            if (conexion != null) {
                conexion.disconnect();
            }
        }
    }
}
