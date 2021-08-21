import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String intro = "Top of the morning my good sir, what can I do for you on this fine day?";
        String farewell = "I bid you farewell my good man. Good Bye.";
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        System.out.println(intro);
        String input;
        do {
            input = sc.nextLine();
            input = input.stripTrailing();
            input = input.stripLeading();
            if (input.equals("end")) {
                System.out.println(farewell);
                sc = null;
                manager = null;
            } else if (input.equals("list")) {
                TaskManager.printList();
            } else {
                TaskManager.addTask(input);
            }
        } while (!(input.equals("end")));
    }
}
