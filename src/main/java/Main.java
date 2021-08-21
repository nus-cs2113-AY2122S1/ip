import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner in = new Scanner(System.in);
        duke.greet();

        String command = in.nextLine();
        while (!command.equalsIgnoreCase("bye")) {
            duke.echo(command);
            command = in.nextLine();
        }
        duke.exit();
    }
}
