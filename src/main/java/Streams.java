import User.RespostaAPI;
import User.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Streams {
    //java obriga a tratar excessoes verificadas/conhecidas
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper =  new  ObjectMapper();
        RespostaAPI respostas = mapper.readValue(new File("registros.json"), RespostaAPI.class);
        List<User> pessoas = respostas.getResults();

        for (User user : pessoas) {
            System.out.println(user);
            System.out.println();
        }
    }

    // Proporcao de genero (numero e estatistica);
    public static int[] proporcaoGenero(List<User> pessoas){
        long[] x = new long[2];
        x[0] = pessoas.stream()
                .filter(p -> p.getGender() == "male")
                .count();

        x[1] = pessoas.stream()
                        .filter(p -> p.getGender() == "female")
                        .count();

        System.out.println("Percentage males: "+ ((x[0]/pessoas.size()/100) + "%, amount: "+ x[0] +
                            "\n Percentage females: "+ ((x[1]/pessoas.size()/100) + "%, amount"+ x[1]);

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
}