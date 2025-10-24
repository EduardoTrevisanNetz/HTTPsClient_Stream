import User.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.io.File;
import java.net.http.*;
import java.net.*;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;
import User.RespostaAPI;


//tirar da main as coisas e ter metodos só pra requests
public class Requests {
    //java obriga a tratar excessoes verificadas/conhecidas
    public static void main(String[] args) throws IOException, InterruptedException {
        //Esse que vai criar e fazer as requisicoes http
        HttpClient client = HttpClient.newHttpClient();

        // Essa é a requisicao qual a ordem que eu to mandando pra api
        HttpRequest request = HttpRequest.newBuilder()
                // to fazendo uma request na uri (Uniform Resource Indentifier)
                // e juntando com o numero de id que vai ser o i do meu for
                .uri(URI.create("https://randomuser.me/api/?results=100"))
                //se eu nao falo nada ja usa GET como padrao
                .GET()
                .build();

        //HttpResponse significa a resposta que eu vou ter da minha requisição
        HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());

        try (FileWriter file = new FileWriter("registros.json")) {
            file.write(res.body());
        }
    }
}
