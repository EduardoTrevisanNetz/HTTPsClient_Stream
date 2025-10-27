import Model.User;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class UserPrinterService {
    private UserService userService;
    public UserPrinterService()throws IOException, InterruptedException{
        this.userService = new UserService();
        userService.setUsers();
    }


    public void printAll(String country){
        printGenderProportion();
        System.out.println();
        printDiferentAges();
        System.out.println();
        printNoAName();
        System.out.println();
        printfirstBrazilian();
        System.out.println();
        printTierList();
        System.out.println();
        printMostCommomNames();
        System.out.println();
        printBrazilianGenZ();
        System.out.println();
        printMaleAgeGroup();
        System.out.println();
        printWomanAgeLocality(country);
    }

    public void printGenderProportion(){
        long[] genderNumber = userService.genderNumber();
        double[] genderPercentage = userService.genderPercentage(genderNumber);
        System.out.println("Percentage males: " + genderPercentage[0] + "%, amount: " + genderNumber[0] +
                "\n Percentage females: " + genderPercentage[1] + "%, amount: " + genderNumber[1]);
    }

    public void printDiferentAges(){
        Map<Integer,Long> diferentAges = userService.DiferentAges();
        List<Double> percentage = userService.AgesPercentage(diferentAges);
        // para cada entrada chave e valou no meu conjunto de pares faca algo
        for(Map.Entry<Integer,Long> entry : diferentAges.entrySet()){
            System.out.print("Age: " + entry.getKey() + ", percentage: "+ percentage + "%\n");
        }
    }

    public void printNoAName(){
        List<String> names = userService.untilAName();
        Optional noAName = userService.firstNoAName();

        for(String name : names){
            System.out.println(name);
        }
        //usar o get "desembrulha o valor contido no optional"
        System.out.println("No a name: " + noAName.get());
    }

    public void printfirstBrazilian(){
        Optional brazilian = userService.firstBrazilian();
        System.out.println("First brazilian: " + brazilian.get());
    }

    public void printTierList(){
        User[] oldest = userService.oldTier();
        User[] newest = userService.newTier();

        System.out.println("Top 5 oldest users");
        for(User u : oldest) {
            System.out.println(u.getName() + " Birth: " + u.getDob());
        }

        System.out.println("\nTop 5 newest users");
        for(User u : newest){
            System.out.println(u.getName()+ " Birth: " + u.getDob());

        }
    }

    public void printMostCommomNames(){
        Map<String, Long> commom =  userService.mostCommomNames();
        for(Map.Entry<String, Long> entry : commom.entrySet()){
            if(entry.getValue() > 3){
                System.out.println("The name "+entry.getKey() + " appears " + entry.getValue() + " times");
            }
        }
    }

    public void printBrazilianGenZ(){
        List<User> genZ = userService.brazilianGenZ();
        System.out.println("Brazilian gen z");
        for(User u: genZ){
            System.out.println(u.getName() + " Born in: " + (u.getDob().getDate().getYear()+1900));
        }
    }

    public void printMaleAgeGroup() {
        List<User> maleAgeGroup = userService.maleAgeGroup();
        long maleSize = userService.genderNumber()[0];
        System.out.println("Male in this age group: " + maleAgeGroup.size());
        System.out.println("That means " + String.format("%.2f", ((maleAgeGroup.size() * 100.0) / maleSize)) + "% of total male users");
    }

    public void printWomanAgeLocality(String country){
        List<User> womanAgeLocality = userService.womanAgeLocality(country);
        long womanSize = userService.genderNumber()[1];
        System.out.println("Woman age locality: " + womanAgeLocality.size());
        System.out.println("That means " + String.format("%.2f",(womanAgeLocality.size()*100.0)/womanSize) +"% of total woman users");
    }
}

