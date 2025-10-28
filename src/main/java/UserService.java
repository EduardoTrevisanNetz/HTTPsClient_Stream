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
    public long[] genderNumber() {
        long[] x = new long[2];
        x[0] = this.users.stream()
                .filter(p -> p.getGender().equals("male"))
                .count();

        x[1] = this.users.stream()
                .filter(p -> p.getGender().equals("female"))
                .count();

        return x;
    }
    public double[] genderPercentage(long[] numberPerGender){
        double[] percentages = new double[2];
        percentages[0] = (double)  ((numberPerGender[0]*100.0)/ users.size());
        percentages[1] = (double)  ((numberPerGender[1]*100.0)/ users.size());

        return percentages;
    }

    //Retorna quais as diferentes idades, em ordem, qual a porcentagem que cada um ocupa, (filter, ordem, map(x -> x/pessoas.size())
    public Map<Integer, Long> diferentAges() {
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
        return x;
    }
    public List<Double> agesPercentage(Map<Integer, Long> agesQuantity){
        List<Double> percentages = new ArrayList<>();
        for(Map.Entry<Integer,Long> entry : agesQuantity.entrySet()){
            double percentage = ((entry.getValue()*100.0)/ users.size());
            percentages.add(percentage);
        }
        return percentages;
    }


    //Retorna nome das pessoas até ter uma que o nome n tem 'a' e qual foi o primeiro que nao deu match
    public List<String> untilAName(){
        List<String> names = this.users.stream()
                .map(user -> user.getName().getFirst())
                .takeWhile(name -> name.contains("a"))
                .collect(Collectors.toList());
        return names;
    }

    public Optional<String> firstNoAName(){
        return this.users.stream()
                .map(user -> user.getName().getFirst())
                .filter(name -> !name.contains("a"))
                .findFirst();
    }
    //Retorna a primeira pessoa que morar no Brazil
    public Optional<User> firstBrazilian(){
        return this.users.stream()
                .filter(userCountry -> userCountry.getLocation().getCountry().equals("Brazil"))
                .findFirst();
    }
    //Retorna 5 mais novos e 5 mais velhos

    public User[] oldTier(){
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

        return oldest;
    }

    public User[] newTier(){
        User[] newest = this.users.stream()
                .sorted(Comparator.<User, Date>comparing(u -> u.getDob().getDate()).reversed())
                .limit(5)
                .toArray(User[]::new);

        return newest;
    }

    //Retorna todos os nomes que se repetem
    public Map<String, Long> mostCommomNames(){
        Map<String, Long> commom =  this.users.stream()
                .map(u -> u.getName().getFirst())
                .collect(Collectors.groupingBy(
                        name -> name,
                        Collectors.counting()
                ));
      return commom;
    }

    //Retorna apenas os brasileiros nascidos apos anos 2000
    public List<User> brazilianGenZ(){
        List<User> genZ = this.users.stream()
                .filter(u -> u.getLocation().getCountry().equals("Brazil"))
                //pega apenas a partir de 1900
                .filter(u -> u.getDob().getDate().getYear() >= 100)
                .collect(Collectors.toList());

       return genZ;
    }

    //Retorna quantos homens entre 18 e 25 anos e qual a proporcao deles no todo
    public List<User> maleAgeGroup(){
        List<User> maleGroup = this.users.stream()
                .filter(u -> u.getGender().equals("female"))
                .filter(u -> u.getDob().getAge()>= 18 && u.getDob().getAge()<=25)
                .collect(Collectors.toList());

        return maleGroup;
    }

    //Retorna das pessoas de uma localidade quantas sao mulheres com faixa de idade 19-30
    public List<User> womanAgeLocality(String Country) {
        List<User> womans = this.users.stream()
                .filter(u -> u.getLocation().getCountry().equals(Country))
                .filter(u -> u.getGender().equals("female"))
                .filter(u -> u.getDob().getAge() >= 19 && u.getDob().getAge() <= 30)
                .collect(Collectors.toList());

        return womans;
    }
}