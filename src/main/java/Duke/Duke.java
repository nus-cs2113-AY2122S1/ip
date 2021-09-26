package Duke;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Ui.printLogo();
        Ui.greeting();
        String command;
        Scanner in = new Scanner(System.in);
        boolean flag = true;
        do{
            command = in.nextLine();
            flag = Parser.parse(command);
        }while (flag);
    }
}
