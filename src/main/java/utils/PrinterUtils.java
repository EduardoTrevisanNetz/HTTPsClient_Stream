package utils;

import model.User;
import service.UserService;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.Optional;

public class PrinterUtils {
    private final UserService userService;

    public PrinterUtils(UserService userService)throws IOException, InterruptedException{
        this.userService = userService;
    }

    public void printGenderProportion() {
        Map<String, Long> genderNumber = userService.countUsersByGender();
        Map<String, Double> genderPercentage = userService.getPercentageInUsers(genderNumber);

        for(Map.Entry<String, Long> entry : genderNumber.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue() + ", this corresponds to "+genderPercentage.get(entry.getKey())+ "% of the users");
        }
    }

    public void printDifferentAges(){
        Map<Integer, Double> percentage = userService.getPercentageInUsers(userService.countUsersByAge());
        for(Map.Entry<Integer,Double> entry : percentage.entrySet()){
            System.out.print("Age: " + entry.getKey() + ", percentage: "+ entry.getValue() + "%\n");
        }
    }

    public void printNoAName(){
        List<String> names = userService.getNamesUntilNoAName();
        Optional<String> noAName = userService.firstNoAName();

        for(String name : names){
            System.out.println(name);
        }
        if(noAName.isPresent()){
            System.out.println("First name without A: " + noAName.get());
        }else{
            System.out.println("No name without A found");
        }
    }

    public void printfirstBrazilian(){
        Optional<User> firstBrazilian = userService.getFirstBrazilianUser();
        if(firstBrazilian.isPresent()){
            System.out.println("First brazilian: " + firstBrazilian.get());
        }
    }

    public void printTierList(){
        List<User> olderUsers= userService.getOldestUsers(5);
        List<User> youngestUsers = userService.getYoungestUsers(5);

        System.out.println("Top 5 oldest users");
        for(User user : olderUsers) {
            System.out.println(user.getName() + " Birth: " + user.getDateBirthDate());
        }

        System.out.println("\nTop 5 newest users");
        for(User user : youngestUsers) {
            System.out.println(user.getName()+ " Birth: " + user.getDateBirthDate());

        }
    }

    public void printMostCommonNames(){
        Map<String, Long> common =  userService.getUsersMostCommonNames();
        for(Map.Entry<String, Long> entry : common.entrySet()){
            if(entry.getValue() > 3){
                System.out.println("The name "+entry.getKey() + " appears " + entry.getValue() + " times");
            }
        }
    }

    public void printBrazilianGenZ(){
        List<User> genZ = userService.getGenZUsers(userService.getBrazilianUsers());
        System.out.println("Brazilian gen z");
        for(User u: genZ){
            System.out.println(u.getName() + " Born in: " + (u.getYearOfBirth()+1900));
        }
    }

    public void printMaleAgeGroup() {
        List<User> maleAgeGroup = userService.usersMaleAgeGroup(17, 25);
        long maleSize = userService.getUserQuantityByGender("male");
        System.out.println("Male in this age group: " + maleAgeGroup.size());
        System.out.println("That means " + String.format("%.2f", ((maleAgeGroup.size() * 100.0) / maleSize)) + "% of total male users");
    }

    public void printWomanAgeLocality(String country){
        List<User> womanAgeLocality = userService.getWomenInAgeGroupFromCountry(country, 19, 30);
        long womanSize = userService.getUserQuantityByGender("female");
        System.out.println("Woman age locality: " + womanAgeLocality.size());
        System.out.println("That means " + String.format("%.2f",(womanAgeLocality.size()*100.0)/womanSize) +"% of total woman users");
    }

    public void printAll(String country){
        printGenderProportion();
        System.out.println();
        printDifferentAges();
        System.out.println();
        printNoAName();
        System.out.println();
        printfirstBrazilian();
        System.out.println();
        printTierList();
        System.out.println();
        printMostCommonNames();
        System.out.println();
        printBrazilianGenZ();
        System.out.println();
        printMaleAgeGroup();
        System.out.println();
        printWomanAgeLocality(country);
    }
}

