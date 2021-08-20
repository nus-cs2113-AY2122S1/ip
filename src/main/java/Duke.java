import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
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
            System.out.println(horizontalLine);
            System.out.println(userInput);
            System.out.println(horizontalLine);

        }
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
