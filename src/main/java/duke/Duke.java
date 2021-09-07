package duke;

import java.util.Scanner;

public class Duke {
    private static void greet() {
        System.out.println("Welcome to Duke! What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        greet();
        //initialise variables to scan user input
        String userInput;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                exit();
                break;
            } else if (userInput.startsWith("list")) {
                TaskManager.listTasks();
            } else {
                TaskManager.handleRequest(userInput);
            }
        }
    }
}
