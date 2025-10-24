import java.io.IOException;

public class Main{
    private static UserService userService = new UserService();
    public static void main(String[] args) throws IOException, InterruptedException {
        userService.setUsers("registros.json");
        userService.proporcaoGenero();
    }
}
