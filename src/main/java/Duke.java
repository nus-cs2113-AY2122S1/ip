import java.util.Scanner;

public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String ADD_TASK_MSG = "Got it. I've added this task: ";
    private static final String ERROR_MSG = "Sorry, I didn't quite get what you want me to do :(";
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        showHelloGreeting();
        executeResponses();
        showByeGreeting();
    }

    private static void executeResponses() {
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int index = 0;
        String text;
        text = in.nextLine();
        while (!text.equals("bye")) {
            System.out.println(LINE);
            String[] words = text.split(" ");

            switch (words[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < index; i++) {
                        Task currentTask = tasks[i];
                        System.out.println((i + 1) + "." + tasks[i].toString());
                    }
                    break;
                case "done":
                    int taskNum = Integer.parseInt(words[words.length - 1]);
                    tasks[taskNum - 1].setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[x] " + tasks[taskNum - 1].getDescription());
                    break;
                case "todo":
                    String[] todoTaskInfo = extractInfo(text, "todo");
                    tasks[index] = new Todo(todoTaskInfo[0]);
                    System.out.println(ADD_TASK_MSG);
                    index++;
                    System.out.println("Now you have " + index + " tasks in the list.");
                    break;
                case "deadline":
                    String[] deadlineTaskInfo = extractInfo(text, "deadline");
                    tasks[index] = new Deadline(deadlineTaskInfo[0], deadlineTaskInfo[1]);
                    System.out.println(ADD_TASK_MSG);
                    index++;
                    System.out.println("Now you have " + index + " tasks in the list.");
                    break;
                case "event":
                    String[] eventTaskInfo = extractInfo(text, "event");
                    tasks[index] = new Event(eventTaskInfo[0], eventTaskInfo[1]);
                    System.out.println(ADD_TASK_MSG);
                    index++;
                    System.out.println("Now you have " + index + " tasks in the list.");
                    break;
                default:
                    System.out.println(ERROR_MSG);
                    break;
            }
            System.out.println(LINE);
            text = in.nextLine();
        }
    }

    private static void showByeGreeting() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void showHelloGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE);
        System.out.println(" Hello! I'm Duke\n" +
                " What can I do for you?");
        System.out.println(LINE);
    }

    private static String[] extractInfo(String taskString, String taskType) {
        String[] taskInfo = new String[2];
        int slashPos = taskString.indexOf('/');
        switch (taskType) {
            case "todo":
                taskInfo[0] = taskString.substring(5);
                break;
            case "deadline":
                taskInfo[0] = taskString.substring(9, slashPos - 1);
                taskInfo[1] = taskString.substring(slashPos + 4);
                break;
            case "event":
                taskInfo[0] = taskString.substring(6, slashPos - 1);
                taskInfo[1] = taskString.substring(slashPos + 4);
                break;
            default:
                break;
        }
        return taskInfo;
    }
}
