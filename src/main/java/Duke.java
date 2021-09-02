import java.util.Scanner;

public class Duke {
    public static String EXIT_CMD = "bye";
    public static String LIST_CMD = "list";
    public static String DONE_CMD = "done";
    public static String TODO_CMD = "todo";
    public static String DEADLINE_CMD = "deadline";
    public static String EVENT_CMD = "event";

    public static void main(String[] args) {
        greetUserOnStart();
        Scanner input = new Scanner(System.in);

        while (true) {
            String command = input.nextLine();

            // parse and handle commands
            if (command.equals(EXIT_CMD)) {
                break;
            } else if (command.equals(LIST_CMD)) {
                TaskManager.listTasks();
            } else if (command.startsWith(DONE_CMD)) {
                String parsedInput = command.split(" ")[1];
                int taskNo = Integer.parseInt(parsedInput);
                TaskManager.markTaskNoAsDone(taskNo - 1);
            } else if (command.startsWith(TODO_CMD)) {
                String parsedInput = command.replaceFirst(TODO_CMD, "");
                String todo = parsedInput.strip();
                TaskManager.addTodo(todo);
            } else if (command.startsWith(DEADLINE_CMD)) {
                String [] parsedInput = command.replaceFirst(DEADLINE_CMD, "").split("/by ");
                String deadlineTitle = parsedInput[0].strip();
                String deadlineDue = parsedInput[1].strip();
                TaskManager.addDeadline(deadlineTitle, deadlineDue);
            } else if (command.startsWith(EVENT_CMD)) {
                String [] parsedInput = command.replaceFirst(EVENT_CMD, "").split("/at ");
                String eventTitle = parsedInput[0].strip();
                String eventTime = parsedInput[1].strip();
                TaskManager.addEvent(eventTitle, eventTime);
            } else {
                // handle invalid command
                System.out.println("Invalid command! Please enter a valid command");
            }
        }

        greetUserOnEnd();
    }

    public static void greetUserOnStart() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void greetUserOnEnd() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
