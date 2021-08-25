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
        Information input = new Information();
        int counter = 0;
        while (true) {
            Scanner myObj = new Scanner(System.in);
            String userInput = myObj.nextLine();
            if (Objects.equals(userInput, "bye")) {
                System.out.println("_______________________________________________________\n" + "Farewell, my Lord." + "\n" + "_______________________________________________________\n");
                break;
            } else if (Objects.equals(userInput, "list")) {
                for (int i = 0; i < 100; i++) {
                    if (input.List[i] == null) {
                        break;
                    } else {
                        System.out.println((i+1) + ".[" + input.List[i].getStatusIcon() + "] " + input.List[i].description);
                    }
                }
                System.out.println("_______________________________________________________");
                continue;
            } else if (Objects.equals(userInput, "done")) {
                System.out.println("Select which task has been completed");
                int whichTask = myObj.nextInt();
                if (whichTask < 0) {
                    System.out.println("Index out of bounds");
                } else {
                    input.List[whichTask - 1].markAsDone();
                    System.out.println("OK! That task has been marked as complete");
                }
                continue;
            }
            input.List[counter] = new Task(userInput);
            System.out.println("_______________________________________________________\n" + userInput + "\n" + "_______________________________________________________\n");
            counter++;
        }
    }
}
