import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // list to store all tasks
        String[] tasks = new String[100];
        int tasksCounter = 0;
        String friday = "  __      _     _             \n"
                + " / _|    (_)   | |            \n"
                + "| |_ _ __ _  __| | __ _ _   _ \n"
                + "|  _| '__| |/ _` |/ _` | | | |\n"
                + "| | | |  | | (_| | (_| | |_| |\n"
                + "|_| |_|  |_|\\__,_|\\__,_|\\__, |\n"
                + "                         __/ |\n"
                + "                        |___/ \n";
        String dashes = "____________________________________________________________\n";

        // greet user
        System.out.print(dashes);
        System.out.println(friday);
        System.out.println("Initiating FRIDAY");
        System.out.println("Hello Mr Stark, how may I be of assistance to you today");
        System.out.print(dashes);

        // Scan in user input of tasks
        String userInput = "";
        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.equals("list")) {
                System.out.print(dashes);
                System.out.println("Listing all tasks for today.");
                for (String task : tasks) {
                    if (task == null) {
                        break;
                    }
                    System.out.print(">");
                    System.out.println(task);
                }
                System.out.print(dashes);
                continue;
            }
            /*
            String echo = "____________________________________________________________\n"
                    + userInput + "\n"
                    + "____________________________________________________________\n";
             */
            // add to list
            tasks[tasksCounter++] = userInput;
            System.out.print(dashes);
            System.out.println("Very well, adding task " + userInput);
            System.out.print(dashes);
        }

        String exit = "____________________________________________________________\n"
                + "Powering Off now. Good Bye Mr Stark.\n"
                + "____________________________________________________________\n";
        System.out.println(exit);
    }
}
