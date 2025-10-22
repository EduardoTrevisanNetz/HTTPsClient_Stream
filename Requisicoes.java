import java.net.http.*;
import java.net.*;
import java.io.IOException;

public class Streams{
    public static void main(String[] args) throws IOException, InterruptedException {
        //Esse que vai criar e fazer as requisicoes http
        HttpClient client = HttpClient.newHttpClient();
        // Essa é a requisicao

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                //se eu nao falo nada ja usa GET como padrao
                .GET()
                .build();

        //HttpResponse significa a resposta que eu vou ter da minha requisição
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
