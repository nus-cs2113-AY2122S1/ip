import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<Task>();
        System.out.println("What can I do for you today homie");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("See you later alligator");
                break;
            }

            switch (input) {
            case "list":
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + "."
                            + "[" + tasks.get(i).getStatusIcon() + "] "
                            + tasks.get(i).taskDescription);
                }
                break;

            default :
                if (input.contains("done")) {
                    int taskIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    tasks.get(taskIndex - 1).markAsDone();
                    break;
                }

                Task newTask = new Task (input);
                tasks.add(newTask);
                System.out.println("added: " + input);
            }
        }
    }
}
