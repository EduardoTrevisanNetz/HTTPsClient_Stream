package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private Street street;
    private String city;
    private String state;
    private String country;
    private String postcode;

    @Override
    public String toString() {
        return  street + ",\n          City: "
                + city + ",\n          State: "
                + state +",\n          Country: "
                + country + " (" + postcode + ")";
    }
}