package User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String gender;
    private Name name;
    private DateofBirth dob;
    private String email;
    private Location location;

    // Getters e Setters
    public String getGender() { return gender; }
    public Name getName() {return name;}
    public DateofBirth getDob() { return dob; }
    public String getEmail() { return email; }
    public Location getLocation() { return location; }

    public void setGender(String gender) { this.gender = gender; }
    public void setName(Name name) { this.name = name; }
    public void setAge(int age) { this.dob = dob; }
    public void setEmail(String email) { this.email = email; }
    public void setLocation(Location location) {this.location = location;}

    @Override
    public String toString() {
        return "Nome: "+ name + " (" + gender + "), " +
                "Email: " + email + ",\nDate of Birth: " + dob + "\n" +
                "Endereço: " + location;
    }
}