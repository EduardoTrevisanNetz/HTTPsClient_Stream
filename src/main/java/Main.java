import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException, InterruptedException{
        UserPrinterService printer = new UserPrinterService();
        printer.printAll("United States");
    }
}