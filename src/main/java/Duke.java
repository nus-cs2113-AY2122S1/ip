import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner input = new Scanner(System.in);
        String command;
        do {
            command = input.nextLine();
            System.out.println(command);
        } while (!command.equalsIgnoreCase("Bye"));

        System.out.println("Bye. Hope to see you again soon!");
    }
}
