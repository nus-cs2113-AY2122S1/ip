import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String farewell = "I bid you farewell my good man. Good Bye.";
        printWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        String[] listOfInputs;
        String input;
        do {
            input = sc.nextLine();
            input = input.stripTrailing();
            input = input.stripLeading();

            listOfInputs = input.split(" ");
            if (listOfInputs[0].equals("end")) {
                System.out.println(farewell);
                sc = null;
                manager = null;
            } else if (listOfInputs[0].equals("list")) {
                TaskManager.printList();
            } else if (listOfInputs[0].equals("done")) {
                int taskIndex = Integer.parseInt(listOfInputs[1]);
                TaskManager.markTaskAsDone(taskIndex);
            } else {
                TaskManager.addTask(input);
            }
        } while (!(input.equals("end")));
    }

    private static void printWelcomeMessage() {
        String intro = "Top of the morning my good sir, what can I do for you on this fine day?";
        System.out.println(intro);
    }
}
