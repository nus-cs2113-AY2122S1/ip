import java.util.Scanner;

public class Duke {
    private static Task[] tasks;
    private static int taskCount;
    /* Lines for formatting purposes */
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Prints text within two horizontal lines.
     *
     * @param text String to be printed.
     */
    public static void printWithLines(String text) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(text);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints welcome message upon running MARK.
     */
    public static void printHelloMessage() {
        String helloMessage = "Hello! I'm MARK\n" + "What can I do for you?";
        printWithLines(helloMessage);
    }

    /**
     * Prints exit message upon terminating MARK.
     */
    public static void printByeMessage() {
        String byeMessage = "You've terminated MARK. Have a good day!";
        printWithLines(byeMessage);
     }

    /**
     * Reads in a string, identifies the first word and adds new task according to the type of task indicated by it.
     *
     * @param task a todo, deadline or event to be added.
     */
    public static void addTask(String task) throws DukeException {
        /*handles todo tasks */
        if (task.startsWith("todo")) {
            if (task.substring(4).isEmpty()) {
                throw new DukeException("Description of todo cannot be empty.");
            }

            tasks[taskCount] = new ToDo(task.replaceFirst("^todo", "").trim());

        /*handles deadline tasks*/
        } else if (task.startsWith("deadline")) {
            if (!task.contains("/by")) {
                throw new DukeException("Deadline task requires a /by property.");
            }
            tasks[taskCount] = new Deadline(task.substring(0, task.indexOf("/by"))
                    .replaceFirst("^deadline", "").trim(),
                    task.substring(task.indexOf("/by") + "/by".length()).trim());

        /*handles event tasks*/
        } else if (task.startsWith("event")) {
            if (!task.contains("/at")) {
                throw new DukeException("Event task requires a /at property.");
            }
            tasks[taskCount] = new Event(task.substring(0, task.indexOf("/at"))
                    .replaceFirst("^event", "").trim(),
                    task.substring(task.indexOf("/at") + "/at".length()).trim());
        }

        if (taskCount == 0) {
            printWithLines("I've added this task:\n" + tasks[taskCount].toString() + "\n" + "You have " + 1 + " task in the list.");
        } else {
            printWithLines("I've added this task:\n" + tasks[taskCount].toString() + "\n" + "You have " + (taskCount + 1) + " tasks in the list.");
        }
        /* increments after adding a task */
        taskCount++;
    }

    /**
     * Lists down tasks that have been added, displaying their description and their status.
     */
    public static void listTasks() {
        String taskList = "Your list of tasks:\n";

        if (taskCount == 0) {
            printWithLines("No tasks listed!");
            return;
        }

        for (int i = 0; i < taskCount; i++) {
            taskList = taskList.concat((i + 1) + ". " + tasks[i].toString() + "\n");
        }

        // erase last newline character
        taskList = taskList.substring(0, taskList.length() - 1);

        printWithLines(taskList);
    }

    /**
     * Identify a task in the list and marks it as completed.
     *
     * @param input a String to be parsed to identify a task number in the list.
     */
    public static void setTaskDone(String input) throws DukeException {
        int taskNumber = Integer.parseInt(input.replace("done ", "")) - 1;

        if (taskNumber > taskCount - 1) {
            throw new DukeException("Task number " + (taskNumber + 1) + " is invalid!\nEnter a valid task number.");
        }

        Task chosenTask = tasks[taskNumber];
        chosenTask.setDone();
        printWithLines("Task has been marked as done:\n"
            + chosenTask.getStatusIcon()
            + " "
            + chosenTask.description);
    }


    public static void selectCommand(String input) throws DukeException {
        String inputCommand = input.trim().split(" ")[0];
        String inputData = input.replaceFirst(inputCommand, "").trim();

        switch (inputCommand){
        case "todo": case "deadline": case "event":
            addTask(input);
            break;
        case "list":
            listTasks();
            break;
        case "done":
            setTaskDone(inputData);
            break;
        case "bye":
            printByeMessage();
            System.exit(0);
            break;
        default:
            printWithLines("Your input is invalid.");
            break;
        }
    }

    public static void main(String[] args) {
        tasks = new Task[100];
        taskCount = 0;

        printHelloMessage();

        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();

        while (true) {
            try{
                selectCommand(line);
            } catch (DukeException x) {
                printWithLines(x.toString());
            }

            line = in.nextLine();
        }
    }
}
