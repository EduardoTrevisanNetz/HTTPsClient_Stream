import utils.PrinterUtils;

import java.io.IOException;
import service.UserService;
import repository.ApiUserRepository;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        var repo = new ApiUserRepository(); // pega da API
        UserService userService = new UserService(repo);
        userService.loadUsers();
        PrinterUtils printer = new PrinterUtils(userService);
        printer.printAll("United States");
    }
}