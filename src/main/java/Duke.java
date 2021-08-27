import java.util.Scanner;

public class Duke {

    public static final String EXIT_PROGRAM = "bye";
    public static final String LIST_TASK = "list";
    public static final String MARK_DONE = "done";
    public static final String TODO_TASK = "todo";
    public static final String DEADLINE_TASK = "deadline";
    public static final String EVENT_TASK = "event";

    public static String getTaskDetails(String[] words) {
        String taskName = "";
        for (int i = 1; i < words.length; i++) {
            taskName += words[i];
            if (i != words.length - 1) {
                taskName += " ";
            }
        }
        return taskName;
    }

    public static void greet() {
        Display.printSeparatingLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    public static void goodbye() {
        Display.printSeparatingLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        Display.printSeparatingLine();
    }

    public static void interact() {
        String line;
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        Boolean isStillInteracting = true;
        while (isStillInteracting) {
            line = in.nextLine();
            String[] words = line.trim().split(" ");

            switch (words[0]) {
            case LIST_TASK:
                taskManager.listTask();
                break;
            case MARK_DONE:
                int taskNumber = Integer.parseInt(words[1]) - 1;
                taskManager.markTaskAsCompleted(taskNumber);
                break;
            case EXIT_PROGRAM:
                isStillInteracting = false;
                break;
            case TODO_TASK:
                taskManager.addTodoTask(getTaskDetails(words));
                break;
            case DEADLINE_TASK:
                taskManager.addDeadlineTask(getTaskDetails(words));
                break;
            case EVENT_TASK:
                taskManager.addEventTask(getTaskDetails(words));
                break;
            default:
                break;
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        interact();
        goodbye();
    }
}
