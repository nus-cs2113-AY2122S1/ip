package duke;

/**
 * A class the display the Ui messages to users.
 */
public class Ui {
    public static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        greet();
    }

    public static void greet() {
        lineSeparator();
        System.out.println("Hello! I am your memo keeper Duke! \nWhat can I do for you?");
        lineSeparator();
    }

    public static void echo(String userInput) {
        lineSeparator();
        System.out.println(userInput);
        lineSeparator();
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again, don't forget to complete your task!");
    }

    public static void lineSeparator() {
        System.out.println("-*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*-");
    }
}
