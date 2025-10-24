package User;

import lombok.Data;

@Data
public class Street {
    private int number;
    private String name;

    @Override
    public String toString() {
        return name  + " User.Street , " + number;}
}
