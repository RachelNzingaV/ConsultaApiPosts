import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class Ejercicio1Get {

    public static void main(String[] args) {
        String urlString = "https://jsonplaceholder.typicode.com/users/1";
        HttpURLConnection conexion = null;
        BufferedReader lector = null;

        try {
            URL url = URI.create(urlString).toURL();
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            int codigoRespuesta = conexion.getResponseCode();
            if (codigoRespuesta >= 200 && codigoRespuesta <= 299) {
                lector = new BufferedReader(
                        new InputStreamReader(conexion.getInputStream(), "UTF-8")
                );

                StringBuilder cuerpoJson = new StringBuilder();
                String linea;
                while ((linea = lector.readLine()) != null) {
                    cuerpoJson.append(linea).append(System.lineSeparator());
                }

                System.out.println(cuerpoJson.toString());
            } else {
                System.out.println("Error en la petición HTTP. Código: " + codigoRespuesta);
            }

        } catch (IOException e) {
            System.out.println("Error al realizar la petición GET: " + e.getMessage());
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
