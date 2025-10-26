package Model;

import lombok.Data;

import java.util.Date;


@Data
public class DateofBirth {
    private Date date;
    private int age;

    @Override
    public String toString() {
        return  date +", " + age + " years old";}
}
