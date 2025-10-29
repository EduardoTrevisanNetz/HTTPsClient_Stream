package model;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String gender;
    private Name name;
    @JsonProperty("dob")
    private BirthDate birthDate;
    private String email;
    private Location location;

    public String getCountry(){
        return location.getCountry();
    }

    public int getYearOfBirth(){
        return birthDate.getBirthDate().getYear();
    }

    public Date getDateBirthDate(){
        return birthDate.getBirthDate();
    }

    public int getAge(){
        return birthDate.getAge();
    }

    public String getFirstName(){
        return name.getFirst();
    }

 @Override
    public String toString() {
        return "Name: "+ name + " (" + gender + "), " +
                "Email: " + email + ",\nBirth date: " + birthDate + "\n" +
                "Address: " + location;
    }
}