package User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String gender;
    private Name name;
    private DateofBirth dob;
    private String email;
    private Location location;

 @Override
    public String toString() {
        return "Nome: "+ name + " (" + gender + "), " +
                "Email: " + email + ",\nDate of Birth: " + dob + "\n" +
                "Endereço: " + location;
    }
}