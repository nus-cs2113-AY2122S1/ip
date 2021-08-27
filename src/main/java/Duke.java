import java.util.Scanner;

public class Duke {

    public static final String COMMAND_EXIT_PROGRAM = "bye";
    public static final String COMMAND_MARK_DONE = "done";
    public static final String COMMAND_LIST_TASK = "list";
    public static final String COMMAND_TODO_TASK = "todo";
    public static final String COMMAND_DEADLINE_TASK = "deadline";
    public static final String COMMAND_EVENT_TASK = "event";
    public static final int COMMAND_INDEX = 0;
    public static final int TASK_INDEX = 1;

    public static final String SEPARATOR = " ";

    public static String getTaskDetails(String[] words) {
        String taskName = "";
        for (int i = TASK_INDEX; i < words.length; i++) {
            taskName += words[i];
            taskName += SEPARATOR;
        }
        return taskName.trim();
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
            String[] words = line.trim().split(SEPARATOR);

            switch (words[COMMAND_INDEX]) {
            case COMMAND_LIST_TASK:
                taskManager.listTask();
                break;
            case COMMAND_MARK_DONE:
                int taskNumber = Integer.parseInt(words[TASK_INDEX]) - 1;
                taskManager.markTaskAsCompleted(taskNumber);
                break;
            case COMMAND_EXIT_PROGRAM:
                isStillInteracting = false;
                break;
            case COMMAND_TODO_TASK:
                taskManager.addTodoTask(getTaskDetails(words));
                break;
            case COMMAND_DEADLINE_TASK:
                taskManager.addDeadlineTask(getTaskDetails(words));
                break;
            case COMMAND_EVENT_TASK:
                taskManager.addEventTask(getTaskDetails(words));
                break;
            default:
                Error.displayInvalidCommandError();
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
