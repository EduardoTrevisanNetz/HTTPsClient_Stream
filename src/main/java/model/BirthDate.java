package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BirthDate {
    @JsonProperty("date")
    private Date birthDate;
    private int age;

    @Override
    public String toString() {
        return  birthDate +", " + age + " years old";}
}
