import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100]; // Store up to 100 tasks.
    private static int tasksCounter = 0;

    public static void printWelcome() {
        String welcomeMessage = "____________________________________________________________" + System.lineSeparator()
                + "Hello! I'm your friendly taskbot, John!" + System.lineSeparator()
                + "Please type @help for a list of commands. How can I help?" + System.lineSeparator()
                + "____________________________________________________________";
        System.out.println(welcomeMessage);
    }

    public static void printHelp() {
        String helpMessage = "____________________________________________________________" + System.lineSeparator()
                + "todo <task> - Adds a todo task." + System.lineSeparator()
                + "deadline <task> /by <due date> - Adds a deadline task." + System.lineSeparator()
                + "event <task> /at <when> - Adds an event task." + System.lineSeparator()
                + "list - Lists all tasks recorded." + System.lineSeparator()
                + "done <task number> - Marks selected task number as done with an X." + System.lineSeparator()
                + "bye - Exits the taskbot." + System.lineSeparator()
                + "____________________________________________________________";
        System.out.println(helpMessage);
    }

    public static void printAddedTodo(String todoDescription) {
        String addedMessage = "____________________________________________________________" + System.lineSeparator()
                + "Alright! I've successfully added this task:" + System.lineSeparator()
                + "[T]" + "[ " + "] " + todoDescription + System.lineSeparator()
                + "You now have " + tasksCounter + " tasks in the list!" + System.lineSeparator()
                + "____________________________________________________________" + System.lineSeparator();
        System.out.println(addedMessage);
    }

    public static void printAddedDeadline(String deadlineDescription, String deadlineBy) {
        String addedMessage = "____________________________________________________________" + System.lineSeparator()
                + "Alright! I've successfully added this task:" + System.lineSeparator()
                + "[D]" + "[ " + "] " + deadlineDescription + "(by: " + deadlineBy + ")" + System.lineSeparator()
                + "You now have " + tasksCounter + " tasks in the list!" + System.lineSeparator()
                + "____________________________________________________________" + System.lineSeparator();
        System.out.println(addedMessage);
    }

    public static void printAddedEvent(String eventDescription, String eventAt) {
        String addedMessage = "____________________________________________________________" + System.lineSeparator()
                + "Alright! I've successfully added this task:"  + System.lineSeparator()
                + "[E]" + "[ " + "] " + eventDescription + "(at: " + eventAt + ")" + System.lineSeparator()
                + "You now have " + tasksCounter + " tasks in the list!" + System.lineSeparator()
                + "____________________________________________________________" + System.lineSeparator();
        System.out.println(addedMessage);
    }

    public static void printList() {
        System.out.println("___________________________________________________________" + System.lineSeparator()
                + "As requested, here are the tasks in your list:");
        if (tasksCounter == 0) {
            System.out.println("There are no tasks recorded!");
        }
        for (int i = 0; i < tasksCounter; i++) {
            String taskType = tasks[i].getType();
            String byOrAt = "";

            if (taskType.equals("D")) {
                byOrAt = "by: ";
            }
            else if (taskType.equals("E")) {
                byOrAt = "at: ";
            }

            if (byOrAt.equals("")) {
                System.out.println(i + 1 + ". "
                        + "[" + taskType + "]"
                        + "[" + tasks[i].getStatusIcon() + "] "
                        + tasks[i].description);
            }
            else {
                System.out.println(i + 1 + ". "
                        + "[" + taskType + "]"
                        + "[" + tasks[i].getStatusIcon() + "] "
                        + tasks[i].description + "(" + byOrAt + tasks[i].getWhen() + ")");
            }
        }
        System.out.println("____________________________________________________________");
    }

    public static void printExit() {
        String exitMessage = "____________________________________________________________" + System.lineSeparator()
                + "Thanks for talking with me, see you soon!" + System.lineSeparator()
                + "____________________________________________________________";
        System.out.println(exitMessage);
    }

    public static int filterTaskNum(String doneTask) {
        String[] words = doneTask.split(" ");
        if (words.length > 1) { // simple check to see if task number has not been input
            return Integer.parseInt(words[1]);
        }
        return -1;
    }

    public static void markTaskDone(int numOfDone) {
        if (numOfDone == -1) { // Error case
            System.out.println("Missing task number to remove. Please try again.");
        }
        else if ((numOfDone - 1 >= 0) && (tasks[numOfDone - 1] != null)) {
            tasks[numOfDone - 1].markAsDone();
            System.out.println("___________________________________________________________" + System.lineSeparator()
                    + "Great work! I've marked this task as done:" + System.lineSeparator()
                    + "[" + tasks[numOfDone - 1].getType() + "]" + "[" + tasks[numOfDone - 1].getStatusIcon() + "] "
                    + tasks[numOfDone - 1].description + System.lineSeparator()
                    + "___________________________________________________________");
        }
        else {
            System.out.println("That is not a valid task number! Please try again.");
        }
    }

    public static void addTask(Task t) {
        tasks[tasksCounter] = t;
        tasksCounter++;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;

        printWelcome();
        line = in.nextLine();

        // while input is not "bye", keep taking inputs.
        while (!line.equals("bye")) {
            if (line.equals("list")) { // print List when "list" command
                printList();
            }
            else if (line.equals ("@help")) { // print help commands when "@help" command
                printHelp();
            }
            else if (line.contains("done")) { // mark task as Done when "done" command
                int taskNum = filterTaskNum(line);
                markTaskDone(taskNum);
            }
            else if (line.contains("todo")) { // add a todo when "todo" command
                String todoDescription = line.substring(5);
                addTask(new Todo(todoDescription));
                printAddedTodo(todoDescription);
            }
            else if (line.contains("deadline")) { // add a deadline when "deadline" command
                int byPos = line.indexOf("/by");
                if (byPos == -1) { // throw error if missing /by parameter
                    System.out.println("ERROR! Did you forget /by ?");
                    break;
                }
                int lastPos = line.length();
                String deadlineDescription = line.substring(9, byPos); // get description from input
                String deadlineBy = line.substring(byPos + 4, lastPos); // get deadline when from input
                addTask(new Deadline(deadlineDescription, deadlineBy));
                printAddedDeadline(deadlineDescription,deadlineBy);
            }
            else if (line.contains("event")) { // add an event
                int atPos = line.indexOf("/at");
                if (atPos == -1) { // throw error if missing /at parameter
                    System.out.println("ERROR! Did you forget /at ?");
                    break;
                }
                int lastPos = line.length();
                String eventDescription = line.substring(6, atPos); // get description from input
                String eventAt = line.substring(atPos + 4, lastPos); // get event when from input
                addTask(new Event(eventDescription, eventAt));
                printAddedEvent(eventDescription,eventAt);
            }
            else { // throw error when no commands are found in input
                System.out.println("Unrecognized command! Please try again, or type @help for a list of commands.");
            }
            line = in.nextLine();
        }
        printExit();
    }
}
