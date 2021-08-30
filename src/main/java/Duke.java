import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // Checks if a string is a positive numeric value
    public static int isPositiveNumeric(String str) {
        try {
            return Integer.parseInt(str);
        } catch(NumberFormatException e){
            return -1;
        }
    }

    //
    public static String taskToString(Task task) {
            return "[" + task.getType() + "][" + (task.isDone() ? "X" : " ") + "] " + task.getTask() + (task.getType() == 'T' ? "" : " (" + (task.getType() == 'D' ? "by: " : "at: ")+ task.getTrail() + ")");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "________________________________________";
        ArrayList<Task> taskList = new ArrayList<>();
        String curCommand = "";

        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        while (!curCommand.equals("bye")) {
            curCommand = input.nextLine();
            System.out.println(line);

            String[] commands = curCommand.split(" ", 2);
            if (commands.length > 1) {
                switch (commands[0]) {
                    case "done":
                        int doneIndex = isPositiveNumeric(commands[1]);
                        if (doneIndex > 0 && doneIndex <= taskList.size()) {
                            taskList.get(doneIndex - 1).setDone(true);
                        }
                        System.out.println(doneIndex > 0 && doneIndex <= taskList.size()
                                ? "Nice! I've marked this task as done:\n " + taskToString(taskList.get(doneIndex - 1))
                                : "Formatting error");
                        break;
                    case "todo":
                        taskList.add(new Task(commands[1], "", 'T', false));
                        System.out.println("Got it. I've added this task:\n " +
                                taskToString(taskList.get(taskList.size() - 1)) +
                                "\nNow you have " + taskList.size() + " tasks in the list.");
                        break;
                    case "deadline":
                        String[] deadin = commands[1].split(" /by ", 2);
                        if (deadin.length > 1) {
                            taskList.add(new Task(deadin[0], deadin[1], 'D', false));
                            System.out.println("Got it. I've added this task:\n " +
                                    taskToString(taskList.get(taskList.size() - 1)) +
                                    "\nNow you have " + taskList.size() + " tasks in the list.");
                        }
                        else {
                            System.out.println("Formatting error");
                        }
                        break;
                    case "event":
                        String[] evin = commands[1].split(" /at ", 2);
                        if (evin.length > 1) {
                            taskList.add(new Task(evin[0], evin[1], 'E', false));
                            System.out.println("Got it. I've added this task:\n " +
                                    taskToString(taskList.get(taskList.size() - 1)) +
                                    "\nNow you have " + taskList.size() + " tasks in the list.");
                        }
                        else {
                            System.out.println("Formatting error");
                        }
                        break;
                    default:
                        System.out.println("Formatting error");
                }
            }
            else {
                switch (curCommand) {
                    case "list":
                        System.out.println("Here are the tasks in your list");
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println((i + 1) + "." + taskToString(taskList.get(i)));
                        }
                        break;
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    default:
                        System.out.println("Formatting error");
                }
            }
            System.out.println(line);
        }

    }
}
