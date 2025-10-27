import java.net.http.*;
import java.net.*;
import java.io.IOException;
import java.io.FileWriter;

// fazer metodo request chamar outros requests privados
//cada request ser independente e poder passar url e pa
//tirar da main as coisas e ter metodos só pra requests
public class Requests {
    private static final String url = "https://randomuser.me/";
    private static final int urlInput = 1000;
    //Esse que vai criar e fazer as requisicoes http
    private static HttpClient client = HttpClient.newHttpClient();
    private RequestSaver requestSaver;
    private String archivePath = "requests";

    public Requests() {
        this.requestSaver = new RequestSaver();
    }
    //esconder os metodos que fazem algo do externo, responsabilidade unica
    public String request(RequestConfig config)throws  IOException, InterruptedException {
        String dados = requestString();

        return switch (config){
            case N_SALVAR -> dados;
            case SALVAR_JSON -> requestSaver.Save(dados, archivePath, ".json");
            case SALVAR_CSV ->  requestSaver.Save(dados, archivePath, ".csv");
            case SALVAR_TXT ->  requestSaver.Save(dados, archivePath, ".txt");
        };
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
}
