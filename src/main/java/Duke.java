import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String description;
        ArrayList<Task> toDoList = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        System.out.println("\tHello! I'm Duke \uD83D\uDE04");
        System.out.println("\tWhat can I do for you?");
        description = in.nextLine();
        while (!description.equals("bye")) {
            if (description.startsWith("done ")) {
                System.out.println("\tNice! \uD83D\uDC4D I've marked this task as done:");
                Task task = toDoList.get(Integer.parseInt(description.substring(5))-1);
                task.markAsDone();
                System.out.println("\t\t[" + task.getStatusIcon() + "] " + task.getDescription());
            } else if (description.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i=0; i<toDoList.size(); i++) {
                    Task task = toDoList.get(i);
                    System.out.println("\t" + (i+1) + "." + "[" + task.getStatusIcon() + "] " + task.getDescription());
                }
            } else {
                System.out.println("\tAdded: " + description);
                toDoList.add(new Task(description));
            }
            description = in.nextLine();
        }
        System.out.println("\tBye. Hope to see you again soon! \uD83D\uDC4B");
    }
}
