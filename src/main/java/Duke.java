import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Response.greet();

        String input;
        do {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();

            Response.parseInput(input);
        } while (!input.equals("bye"));

    }
}
