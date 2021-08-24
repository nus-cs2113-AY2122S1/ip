import java.util.Scanner;

public class Duke {

    public static void greeting() {
        line();
        System.out.println("Hey! I'm Lizzy the Lizard!");
        System.out.println("What can I do for you?");
        System.out.println("");
    }

    public static void exit() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void line() {
        for (int i = 0; i < 40; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    /*
    Function that handles task tracking, task display and exit. Boolean return value
    tells Main whether to continue execution of the program, or to exit.
     */
    public static void addTask(Scanner in) {

        String tasks[] = new String[100];
        int taskIndex = 0;
        String line = in.nextLine();
        while (!line.equalsIgnoreCase("bye")) {
            line();
            if (line.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskIndex; i++) {
                    System.out.printf("%d. %s%n", i+1, tasks[i]);
                }
            } else {
                tasks[taskIndex] = line;
                System.out.println("Added: " + tasks[taskIndex]);
                taskIndex++;
            }
            line();
            line = in.nextLine();
        }

        exit();
    }

    public static void main(String[] args) {

        String lizText = "      ___                   ___           ___           ___     \n" +
                "     /\\__\\      ___        /\\  \\         /\\  \\         |\\__\\    \n" +
                "    /:/  /     /\\  \\       \\:\\  \\        \\:\\  \\        |:|  |   \n" +
                "   /:/  /      \\:\\  \\       \\:\\  \\        \\:\\  \\       |:|  |   \n" +
                "  /:/  /       /::\\__\\       \\:\\  \\        \\:\\  \\      |:|__|__ \n" +
                " /:/__/     __/:/\\/__/ _______\\:\\__\\ _______\\:\\__\\     /::::\\__\\\n" +
                " \\:\\  \\    /\\/:/  /    \\::::::::/__/ \\::::::::/__/    /:/~~/~   \n" +
                "  \\:\\  \\   \\::/__/      \\:\\~~\\~~      \\:\\~~\\~~       /:/  /     \n" +
                "   \\:\\  \\   \\:\\__\\       \\:\\  \\        \\:\\  \\        \\/__/      \n" +
                "    \\:\\__\\   \\/__/        \\:\\__\\        \\:\\__\\                  \n" +
                "     \\/__/                 \\/__/         \\/__/                  \n";

        String lizLogo = "                      ____...---...___\n" +
                "___.....---\"\"\"                .                   \"\"--..____\n" +
                "     .                  .            .\n" +
                " .             _.--._       /|\n" +
                "        .    .'()..()`.    / /\n" +
                "            ( `-.__.-' )  ( (    .\n" +
                "   .         \\        /    \\ \\\n" +
                "       .      \\      /      ) )        .\n" +
                "            .' -.__.- `.-.-'_.'\n" +
                " .        .'  /-____-\\  `.-'       .\n" +
                "          \\  /-.____.-\\  /-.\n" +
                "           \\ \\`-.__.-'/ /\\|\\|           .\n" +
                "          .'  `.    .'  `.\n" +
                "          |/\\/\\|    |/\\/\\|";
        System.out.println("Howdy! It's\n" + lizText + lizLogo);
        Scanner in = new Scanner(System.in);
        greeting();
        addTask(in);


    }
}
