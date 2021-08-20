import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        ArrayList<String> taskList = new ArrayList<>();
        String horizontalLine = "------------------------------------------------------";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String userInput = "";

        System.out.println("Hello from\n" + logo);
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Duke!\n" + "What can I do for you?");
        System.out.println(horizontalLine);

        while(!userInput.equalsIgnoreCase("bye")) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();

            if (!userInput.contains("list") && !userInput.contains("bye")) {
                System.out.println(horizontalLine);
                System.out.println("Added: " + userInput);
                System.out.println(horizontalLine);
                taskList.add(userInput);
            } else if (userInput.contains("list")) {
                int i = 1;
                for (String task : taskList) {
                    System.out.println(i + ". " +task);
                    i++;
                }
                System.out.println(horizontalLine);
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
