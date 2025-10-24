import User.RespostaAPI;
import User.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;
    private Requests requests;


    //ja cria um servico de request automatico
    public UserService(){ this.requests = new Requests(); }

    //aqui vai setar os users
    //tenho que botar as mesmas excessoes que o outro metodo importa
    public void setUsers(String paths)throws IOException, InterruptedException {
        //faz request para criar json
        String path = requests.requestJson(paths);

        //desserializa e passa para objeto
        ObjectMapper objectMapper = new ObjectMapper();
        RespostaAPI resp = objectMapper.readValue(new File(path), RespostaAPI.class);

        //seta isso como users
        this.users = resp.getResults();
    }


    // Proporcao de genero (numero e estatistica);
    public void proporcaoGenero() {
        long[] x = new long[2];
        x[0] = this.users.stream()
                .filter(p -> p.getGender().equals("male"))
                .count();

        x[1] = this.users.stream()
                .filter(p -> p.getGender().equals("female"))
                .count();

        double[] x1 = new double[2];
        x1[0] = (double) x[0];
        x1[1] = (double) x[1];

        System.out.println("Percentage males: " + ((x1[0] / users.size()) * 100) + "%, amount: " + x1[0] +
                "\n Percentage females: " + ((x1[1] / users.size()) * 100) + "%, amount" + x1[1]);

    }
}
    //Retorna quais as diferentes idades, em ordem, qual a porcentagem que cada um ocupa, (filter, ordem, map(x -> x/pessoas.size())

    //Retorna nome das pessoas até ter uma que o nome n tem 'a' e qual foi o primeiro que nao deu match

    //Retorna a primeira pessoa que morar no Brazil

    //Retorna 5 mais novos e 5 mais velhos

    //Retorna todos os nomes que se repetem

    //Retorna apenas os brasileiros nascidos apos anos 2000

    //Retorna quantos homens entre 18 e 25 anos e qual a proporcao deles no todo

    //Retorna das pessoas de uma localidade quantas sao mulheres com faixa de idade 19-30

    //Retorna
