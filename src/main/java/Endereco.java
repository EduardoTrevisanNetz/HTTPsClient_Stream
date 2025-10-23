import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
class Endereco {
    private String street;
    private int streetNumber;
    private String city;
    private String state;
    private String country;
    private String postcode;

    // Construtor
    public Endereco(String street, int streetNumber, String city, String state, String country, String postcode) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
    }

    // Getters e Setters
    public String getStreet() { return street; }
    public int getStreetNumber() { return streetNumber; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getCountry() { return country; }
    public String getPostcode() { return postcode; }

    public void setStreet(String street) { this.street = street; }
    public void setStreetNumber(int streetNumber) { this.streetNumber = streetNumber; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setCountry(String country) { this.country = country; }
    public void setPostcode(String postcode) { this.postcode = postcode; }

    @Override
    public String toString() {
        return streetNumber + " " + street + ", " + city + ", " + state + ", " + country + " (" + postcode + ")";
    }
}