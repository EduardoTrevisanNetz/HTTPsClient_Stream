package User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
class Location {
    private Street street;
    private String city;
    private String state;
    private String country;
    private String postcode;

    // Getters e Setters
    public Street getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getCountry() { return country; }
    public String getPostcode() { return postcode; }

    public void setStreet(Street street) { this.street = street; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setCountry(String country) { this.country = country; }
    public void setPostcode(String postcode) { this.postcode = postcode; }

    @Override
    public String toString() {
        return  street + ",\n          City: "
                + city + ",\n          State: "
                + state +",\n          Country: "
                + country + " (" + postcode + ")";
    }
}