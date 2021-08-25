import java.util.Arrays;
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
        String[] storage = new String[100];
        int counter = 0;
        while (true) {
            Scanner myObj = new Scanner(System.in);
            String userInput = myObj.nextLine();
            if (Objects.equals(userInput, "bye")) {
                System.out.println("_______________________________________________________\n" + "Farewell, my Lord." + "\n" + "_______________________________________________________\n");
                break;
            } else if (Objects.equals(userInput, "list")) {
                for (int i = 0; i < 100; i++) {
                    if (storage[i] == null) {
                        break;
                    } else {
                        System.out.println((i+1) + "." + storage[i]);
                    }
                }
                continue;
            }
            storage[counter] = userInput;
            System.out.println("_______________________________________________________\n" + userInput + "\n" + "_______________________________________________________\n");
            counter++;
        }
    }
}
