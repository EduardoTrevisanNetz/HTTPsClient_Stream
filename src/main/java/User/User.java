
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String gender;
    private Name name;
    private int age;
    private String email;
    private Location location;

    // Getters e Setters
    public String getGender() { return gender; }
    public Name getName() {return name;}
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public Location getLocation() { return location; }

    public void setGender(String gender) { this.gender = gender; }
    public void setName(Name name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setEmail(String email) { this.email = email; }
    public void setLocation(Location location) {this.location = location;}

    @Override
    public String toString() {
        return "Nome: "+ name + " (" + gender + "), " +
                "Email: " + email + ", Idade: " + age + "\n" +
                "Endereço: " + location;
    }
}