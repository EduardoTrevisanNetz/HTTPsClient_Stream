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

// fazer metodo request chamar outros requests privados
//cada request ser independente e poder passar url e pa
//tirar da main as coisas e ter metodos só pra requests
public class Requests {
    private static final String url = "https://randomuser.me/";
    private static final int urlInput = 2500;
    //Esse que vai criar e fazer as requisicoes http
    private static HttpClient client = HttpClient.newHttpClient();

    //esconder os metodos que fazem algo do externo, responsabilidade unica
    public String request(RequestConfig config)throws  IOException, InterruptedException {
        String dados = requestString();

        switch (config){
            case N_SALVAR:
                return dados;
            case SALVAR_JSON:
                return requestJson(dados, "requests");
            case SALVAR_CSV:
                return requestCsv(dados, "requests");
            case SALVAR_TXT:
                return requestTxt(dados, "requests");
        }
        return "Especificar a request";
    }

    private String requestString() throws IOException, InterruptedException {
        // Essa é a requisicao qual a ordem que eu to mandando pra api
        HttpRequest request = HttpRequest.newBuilder()
                // to fazendo uma request na uri (Uniform Resource Indentifier)
                // e juntando com o numero de id que vai ser o i do meu for
                .uri(URI.create(url + "api/?results=" + urlInput))
                //se eu nao falo nada ja usa GET como padrao
                .GET()
                .build();

        //HttpResponse significa a resposta que eu vou ter da minha requisição
        HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());

        return res.body();
    }

    private String requestJson(String dados, String path) throws IOException, InterruptedException {
        try (FileWriter file = new FileWriter(path + ".json")) {
            file.write(dados);
        }

        return path + ".json";
    }

    private String requestCsv(String dados, String path) throws IOException, InterruptedException {
        try (FileWriter file = new FileWriter(path + ".csv")) {
            file.write(dados);
        }

        return path + ".csv";
    }

    private String requestTxt(String dados, String path) throws IOException, InterruptedException {
        try (FileWriter file = new FileWriter(path + ".txt")) {
            file.write(dados);
        }

        return path + ".txt";
    }
}
