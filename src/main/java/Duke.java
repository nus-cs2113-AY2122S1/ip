import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;


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
        System.out.println("I can do the following: Echo, List");
        System.out.println(line);

        String userChoice = scannerObj.nextLine();


        while (!userChoice.equalsIgnoreCase("echo") && !userChoice.equalsIgnoreCase("list")) {
            System.out.println("Invalid input, please try again. You may choose from: List, Echo");
            userChoice = scannerObj.nextLine();
        }
        switch (userChoice.toLowerCase()) {
            case "echo":
                while (true) {
                    System.out.println("Copy cat, copy cat");
                    String userInput = scannerObj.nextLine();
                    if (userInput.equalsIgnoreCase("bye")) break;
                    else echo(userInput);
                }
                break;

            case "list":
                ArrayList<String> tasks = new ArrayList<>();
                while (true) {
                    System.out.println("To view your list, enter 'list'. To add to your list just type what you would like to add.");
                    String userInput = scannerObj.nextLine();
                    if (userInput.equalsIgnoreCase("bye")) break;
                    else list(userInput, tasks);
                }
                break;

            default:
                break;
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void echo(String input) {
        System.out.println(input);
    }

    public static void list(String input, ArrayList<String> tasks) {
        if (input.equalsIgnoreCase("list")) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        } else {
            tasks.add(input);
            System.out.println("added: " + input);
        }
    }

}
