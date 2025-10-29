package service;

import config.RequestConfig;
import model.APIResponse;
import model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class UserService {
    private List<User> users;
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void loadUsers() throws IOException, InterruptedException {
        this.users = repository.getAllUsers();
    }

    public Long getUserQuantityByGender(String gender) {
        return this.users.stream()
                .filter(u -> u.getGender().equals(gender))
                .count();
    }

    public Map<String, Long> countUsersByGender() {
       return this.users.stream()
               .map(User::getGender)
               .collect(Collectors.groupingBy(
                       gender -> gender,
                       Collectors.counting()
               ));
    }

    public <T> Map<T, Double> getPercentageInUsers(Map<T, Long> quantityMap) {
        return quantityMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (entry.getValue() * 100.0) / users.size()
                ));
    }

    public Map<Integer, Long> countUsersByAge() {
        return this.users.stream()
                .map(User::getAge)
                .collect(Collectors.groupingBy(
                        age -> age,
                        Collectors.counting()
                ));
    }

    public List<String> getNamesUntilNoAName(){
        return this.users.stream()
                .map(User::getFirstName)
                .takeWhile(name -> name.contains("a"))
                .collect(Collectors.toList());
    }

    public Optional<String> firstNoAName(){
        return this.users.stream()
                .map(User::getFirstName)
                .filter(name -> !name.contains("a"))
                .findFirst();
    }

    public Optional<User> getFirstBrazilianUser(){
        return this.users.stream()
                .filter(user -> user.getCountry().equals("Brazil"))
                .findFirst();
    }

    public List<User> getOldestUsers(int quantity){
        return this.users.stream()
                .sorted(Comparator.<User, Date>comparing(User::getDateBirthDate))
                .limit(quantity)
                .collect(Collectors.toList());
    }

    public List<User> getYoungestUsers(int quantity){
        return this.users.stream()
                .sorted(Comparator.<User, Date>comparing(User::getDateBirthDate)
                        .reversed())
                .limit(quantity)
                .collect(Collectors.toList());
    }

    public Map<String, Long> getUsersMostCommonNames(){
        return this.users.stream()
                .map(User::getFirstName)
                .collect(Collectors.groupingBy(
                        name -> name,
                        Collectors.counting()
                ));
    }

    public List<User> getBrazilianUsers(){
        return this.users.stream()
                .filter(user -> user.getCountry().equals("Brazil"))
                .collect(Collectors.toList());
    }

    public List<User> getGenZUsers(List<User> users){
        return this.users.stream()
                .filter(user -> user.getYearOfBirth() >= 100)
                .collect(Collectors.toList());
    }

    public List<User> usersMaleAgeGroup(int minAge, int maxAge){
        return this.users.stream()
                .filter(user -> user.getGender().equals("male"))
                .filter(user -> user.getAge()>= minAge && user.getAge()<= maxAge)
                .collect(Collectors.toList());
    }

    public List<User> getWomenInAgeGroupFromCountry(String country, int minAge, int maxAge) {
        return this.users.stream()
                .filter(user -> user.getCountry().equals(country))
                .filter(user -> user.getGender().equals("female"))
                .filter(user -> user.getAge() >= minAge && user.getAge() <= maxAge)
                .collect(Collectors.toList());
    }
}