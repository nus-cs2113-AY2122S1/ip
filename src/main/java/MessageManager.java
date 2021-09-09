public class MessageManager {

    // Prints newest task
    public static void printNewTask(Task task, int size) {
        System.out.println("Got it. I've added this task:\n " +
                taskToString(task) +
                "\nNow you have " + size + " tasks in the list.");
    }

    // Converts task to string
    public static String taskToString(Task task) {
        return "[" + task.getType() + "][" + (task.isDone() ? "X" : " ") + "] "
                + task.getTask() + (task.getType() == 'T' ? "" : " (" + (task.getType() == 'D' ? "by: " : "at: ")+ task.getTrail() + ")");
    }

    // Prints help message
    public static void printHelp(String command) {
        switch (command) {
            case "done":
                System.out.println("done:\n Marks a specific task as done.");
                System.out.println("Format:\n done (index)");
                break;
            case "todo":
                System.out.println("todo:\n Adds a task without any date/time attached to it.");
                System.out.println("Format:\n todo (task)");
                break;
            case "deadline":
                System.out.println("deadline:\n Adds a task that needs to be done before a specific date/time.");
                System.out.println("Format:\n deadline (task) /by (date/time)");
                break;
            case "event":
                System.out.println("event:\n Adds a task that starts at a specific time and ends at a specific time.");
                System.out.println(" Format:\n event (task) /at (date/time)");
                break;
            case "list":
                System.out.println("list:\n Shows all tasks.");
                break;
            case "bye":
                System.out.println("bye:\n Terminates the program.");
                break;
            case "help":
                System.out.println("Type help (command) to show more.\nAvailable commands: done, todo, deadline, event, list, bye, help (displays this message)");
                break;
            default:
                System.out.println("Formatting error");
        }
    }

}
