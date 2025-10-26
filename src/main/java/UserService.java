import Model.RespostaAPI;
import Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class UserService {
    private List<User> users;
    private Requests requests;


    //ja cria um servico de request automatico
    public UserService() {
        this.requests = new Requests();
    }

    //aqui vai setar os users
    //tenho que botar as mesmas excessoes que o outro metodo importa
    public void setUsers() throws IOException, InterruptedException {
        //faz request para criar json
        String path = requests.request(RequestConfig.SALVAR_JSON);

        //desserializa e passa para objeto
        ObjectMapper objectMapper = new ObjectMapper();
        RespostaAPI resp = objectMapper.readValue(new File(path), RespostaAPI.class);

        //seta isso como users
        this.users = resp.getResults();
    }


    // Proporcao de genero (numero e estatistica);
    public void genderProportion() {
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

        System.out.println("Percentage males: " + ((x1[0] / users.size()) * 100) + "%, amount: " + x[0] +
                "\n Percentage females: " + ((x1[1] / users.size()) * 100) + "%, amount: " + x[1]);

    }

    //Retorna quais as diferentes idades, em ordem, qual a porcentagem que cada um ocupa, (filter, ordem, map(x -> x/pessoas.size())
    public void diferentAges() {
        //estou mapeando idades unicas para quantas vezes aparecem
        Map<Integer, Long> x = this.users.stream()
                //transforma uma stream de users para uma stream de idades
                .map(user -> user.getDob().getAge())
                //vai transformar a stream em estrultura de dados agrupando por key e value
                .collect(Collectors.groupingBy(
                        //key idades unicas, pq map n tem keys repitidas
                        idade -> idade,
                        //conta quantas vezes cada idade aparece
                        Collectors.counting()
                ));
        // para cada entrada chave e valou no meu conjunto de pares faca algo
        for(Map.Entry<Integer,Long> entry : x.entrySet()){
            double porcentagem = ((entry.getValue()*100.0)/ users.size());
            System.out.print("Age: " + entry.getKey() + ", frequency: "+ porcentagem + "%\n");
        }
    }
    //Retorna nome das pessoas até ter uma que o nome n tem 'a' e qual foi o primeiro que nao deu match
    public void firstNoAName(){
        List<String> names = this.users.stream()
                .map(user -> user.getName().getFirst())
                .takeWhile(name -> name.contains("a"))
                .collect(Collectors.toList());

        Optional noAName = this.users.stream()
                .map(user -> user.getName().getFirst())
                .filter(name -> !name.contains("a"))
                .findFirst();

        for(String name : names){
            System.out.println(name);
        }
        //usar o get "desembrulha o valor contido no optional"
        System.out.println("No a name: " + noAName.get());
    }
    //Retorna a primeira pessoa que morar no Brazil
    public void livesInBrazil(){
        Optional brazilian = this.users.stream()
                .filter(userCountry -> userCountry.getLocation().getCountry().equals("Brazil"))
                .findFirst();

        System.out.println("First brazilian: " + brazilian.get());
    }
    //Retorna 5 mais novos e 5 mais velhos

    public void oldTier(){
        User[] oldest = this.users.stream()
                //com .reverseOrder so funcionaria se user implementasse comparable
                //.sorted(Comparator.reverseOrder())
                //por isso precisa falar qual campo vamos comparar
                //as vezes compilador se confunde entao tem que deixar explicito
                //estou dizendo quero comparar users porem baseados no em Date que seria o u.getDob().getDate()
                //nao preciso inverter a lista pois segue a ordem natural, que em datas é do mais velho para o mais novo
                .sorted(Comparator.<User, Date>comparing(u -> u.getDob().getDate()))
                .limit(5)
                // i -> new User[i]
                //basicamente explica que esse to Arrays vai se transformar em User
                //pois o toArray padrao converte para um Object[]
                .toArray(User[]::new);
                //mudei o nome da pasta pois o compilador nao sabia se era o package User ou a classe

        User[] newest = this.users.stream()
                .sorted(Comparator.<User, Date>comparing(u -> u.getDob().getDate()).reversed())
                .limit(5)
                .toArray(User[]::new);

        System.out.println("Top 5 oldest users");
        for(User u : oldest) {
            System.out.println(u.getName() + " Birth: " + u.getDob());
        }

        System.out.println("\nTop 5 newest users");
        for(User u : newest){
            System.out.println(u.getName()+ " Birth: " + u.getDob());

        }

    }

    //Retorna todos os nomes que se repetem
    public void mostCommomNames(){
        Map<String, Long> commom =  this.users.stream()
                .map(u -> u.getName().getFirst())
                .collect(Collectors.groupingBy(
                        name -> name,
                        Collectors.counting()
                ));
        for(Map.Entry<String, Long> entry : commom.entrySet()){
            if(entry.getValue() > 3){
                System.out.println("The name "+entry.getKey() + " appears " + entry.getValue() + " times");
            }
        }
    }

    //Retorna apenas os brasileiros nascidos apos anos 2000
    public void brazilianGenZ(){
        List<User> Genz = this.users.stream()
                .filter(u -> u.getLocation().getCountry().equals("Brazil"))
                //pega apenas a partir de 1900
                .filter(u -> u.getDob().getDate().getYear() >= 100)
                .collect(Collectors.toList());

        System.out.println("Brazilian gen z");
        for(User u : Genz){
            System.out.println(u.getName() + " Birth: " + u.getDob());
        }
    }

    //Retorna quantos homens entre 18 e 25 anos e qual a proporcao deles no todo
    public void maleAgeGroup(){
        List<User> male = this.users.stream()
                .filter(u -> u.getGender().equals("female"))
                .filter(u -> u.getDob().getAge()>= 18 && u.getDob().getAge()<=25)
                .collect(Collectors.toList());

        double totalMales = (double) this.users.stream()
                        .filter(u -> u.getGender().equals("male"))
                        .count();
        System.out.println("Male in this age group: " + male.size());
        System.out.println("That means " + String.format("%.2f",((male.size()*100.0)/totalMales)) +"% of total male users");
    }

    //Retorna das pessoas de uma localidade quantas sao mulheres com faixa de idade 19-30
    public void womanAgeLocality(String Country){
        List<User> womans = this.users.stream()
                .filter(u -> u.getLocation().getCountry().equals(Country))
                .filter(u -> u.getGender().equals("female"))
                .filter(u -> u.getDob().getAge()>= 19 && u.getDob().getAge()<=30)
                .collect(Collectors.toList());

        double numberOfWomans = (double) this.users.stream()
                .filter(u -> u.getGender().equals("female"))
                .count();
        System.out.println("Woman age locality: " + womans.size());
        System.out.println("That means " + String.format("%.2f",(womans.size()*100.0)/numberOfWomans) +"% of total woman users");
    }
}