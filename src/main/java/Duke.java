import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner input = new Scanner(System.in);
        String command;

        while (true) {
            command = input.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                TaskManager.list();
            } else if (command.startsWith("done")) {
                int taskNo = Integer.parseInt(command.split(" ")[1]);
                TaskManager.mark(taskNo - 1);
            } else if (command.startsWith("todo")) {
                String todo = command.replaceFirst("todo ", "");
                TaskManager.todo(todo);
            } else if (command.startsWith("deadline")) {
                String [] parsedInput = command.replaceFirst("deadline ", "").split("/by ");
                String deadlineName = parsedInput[0];
                String dueDate = parsedInput[1];
                TaskManager.deadline(deadlineName, dueDate);
            } else if (command.startsWith("event")) {
                String [] parsedInput = command.replaceFirst("event ", "").split("/at ");
                String eventName = parsedInput[0];
                String timeslot = parsedInput[1];
                TaskManager.event(eventName, timeslot);
            }
            else {
                TaskManager.add(command);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
