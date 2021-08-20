import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        String line = "________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);

        while (true) {
            String userInput = scannerObj.nextLine();
            if (userInput.equalsIgnoreCase("bye")) break;
            echo(userInput);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void echo(String input) {
        System.out.println(input);
    }

}
