import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_______________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("_______________________________________________________");
        System.out.println("Your instructions, my Liege.");
        while (true) {
            Scanner myObj = new Scanner(System.in);
            String userInput = myObj.nextLine();
            if (Objects.equals(userInput, "bye")) {
                System.out.println("_______________________________________________________\n" + "Bye! Hope to see you again soon." + "\n" + "_______________________________________________________\n");
                break;
            }
            System.out.println("_______________________________________________________\n" + userInput + "\n" + "_______________________________________________________\n");
        }
    }
}
