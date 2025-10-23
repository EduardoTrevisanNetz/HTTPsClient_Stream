import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String gender;
    private String title;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private Endereco endereco;

    // Construtor
    public User(String gender, String title, String firstName, String lastName, int age, String email, Endereco endereco) {
        this.gender = gender;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.endereco = endereco;
    }

    // Getters e Setters
    public String getGender() { return gender; }
    public String getTitle() { return title; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public Endereco getEndereco() { return endereco; }

    public void setGender(String gender) { this.gender = gender; }
    public void setTitle(String title) { this.title = title; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAge(int age) { this.age = age; }
    public void setEmail(String email) { this.email = email; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    @Override
    public String toString() {
        return title + " " + firstName + " " + lastName + " (" + gender + "), " +
                "Email: " + email + ", Idade: " + age + "\n" +
                "Endereço: " + endereco;
    }
}