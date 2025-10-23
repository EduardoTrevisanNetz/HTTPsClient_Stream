import java.net.http.*;
import java.net.*;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;

public class Requisicoes{
    public static void main(String[] args) throws IOException, InterruptedException {
        //Esse que vai criar e fazer as requisicoes http
        HttpClient client = HttpClient.newHttpClient();

        //lista de registros para guardar as respostas
        List<HttpResponse<String>> registros = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            // Essa é a requisicao
            HttpRequest request = HttpRequest.newBuilder()
                    // to fazendo uma request na uri (Uniform Resource Indentifier)
                    // e juntando com o numero de id que vai ser o i do meu for
                    .uri(URI.create("https://randomuser.me/api/?results=2"))
                    //se eu nao falo nada ja usa GET como padrao
                    .GET()
                    .build();

            //HttpResponse significa a resposta que eu vou ter da minha requisição
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            registros.add(response);
        }
        System.out.println(registros.size());

        FileWriter fw = new FileWriter("registros.json");
        for(HttpResponse<String> response: registros){
            fw.write(response.body() + "\n");
        }
    }
}
