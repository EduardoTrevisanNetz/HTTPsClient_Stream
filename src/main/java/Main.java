import java.io.IOException;

public class Main{
    private static UserService userService = new UserService();
    public static void main(String[] args) throws IOException, InterruptedException {
        userService.setUsers();
        System.out.println("\n");
        userService.genderProportion();
        System.out.println("\n");
        userService.diferentAges();
        System.out.println("\n");
        userService.firstNoAName();
        System.out.println("\n");
        userService.livesInBrazil();
        System.out.println("\n");
        userService.oldTier();
        System.out.println("\n");
        userService.duplicateNames();
        System.out.println("\n");
        userService.brazilianGenZ();
        System.out.println("\n");
        userService.maleAgeGroup();
        System.out.println("\n");
        userService.womanAgeLocality("United States");
    }
}
