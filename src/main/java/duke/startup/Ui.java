package duke.startup;

import java.util.Scanner;

public class Ui {
    public static void printLine() {
        System.out.println("                 ...                 ");
    }

    public static void sayHi(String username) {
        printLine();
        System.out.println("Hello " + username + "!" + "\n" + "I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void sayGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you  again soon!");
        printLine();
    }

    public static void printWrongCommand() {
        System.out.println("Oops, please enter a proper command!");
    }

    public static void printOneKeyword() {
        System.out.println("Please enter one, and only one keyword: ");
    }

    public static void printMatchingTasksAlert() {
        System.out.println("Here are the matching tasks in your list: ");
    }
    public static String readCommand() {
        Scanner in = new Scanner(System.in);
        return  in.nextLine();
    }
}
