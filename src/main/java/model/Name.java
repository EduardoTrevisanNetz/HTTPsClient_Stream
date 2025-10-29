package model;

import lombok.Data;

@Data
public class Name {
    private String title;
    private String first;
    private String last;

    @Override
    public String toString() {
        return title + " " + first + " " + last;}
}
