import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Guardar_Requisicoes {
    public static void main(String[] args)throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        List<HttpResponse<String>> registros = new ArrayList<>();
        for (int i = 2; i < 102; i++) {
            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.gamerpower.com/api/giveaway?id=" + i))
                    .build();

            HttpResponse<String> response = client.send(requisicao, HttpResponse.BodyHandlers.ofString());
            registros.add(response);

        }
        System.out.println("Registros:" + registros.size());
        for(HttpResponse<String> registro : registros){
            System.out.println(registro.statusCode());
            System.out.println(registro.body());
            System.out.println();
            System.out.println();
        }
    }
}
