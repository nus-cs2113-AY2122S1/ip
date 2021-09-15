package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.Task;
import java.util.ArrayList;

import java.util.Scanner;

public class Duke {
    static final String LINE_SEPARATOR = "     _______________________\n";
    static final String SPACING = "     ";
    static final String CANNOT_IDENTIFY =
            "I am sorry, but I do not know what do you mean. " +
                    "Please key in a valid input.";
    static final String GREETINGS = LINE_SEPARATOR
            + SPACING + "Hello! I'm Duke\n"
            + SPACING + "What can I do for you?\n"
            + LINE_SEPARATOR;
    static final String NO_ARGUMENT_1 = "The ";
    static final String NO_ARGUMENT_2 = " command is incomplete.";

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();
        int taskNum = 0;
        System.out.println(GREETINGS);
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            switch (line.split(" ")[0]) {
            case "list":
                list(taskNum, taskList);
                break;
            case "done":
                try {
                    int currentTask = Integer.parseInt(line.split(" ")[1]) - 1;
                    taskList.get(currentTask).markAsDone();
                    String update = LINE_SEPARATOR
                            + SPACING + "Nice! I've marked this task as done: \n"
                            + SPACING + taskList.get(currentTask).toString() + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(update);
                } catch (IndexOutOfBoundsException e) {
                    String error = LINE_SEPARATOR
                            + SPACING + NO_ARGUMENT_1 + "done" + NO_ARGUMENT_2 + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(error);
                }
                break;
            case "deadline":
                try {
                    String taskName = line.substring("deadline ".length(), line.indexOf("/by "));
                    String taskDdl = line.substring(line.indexOf("/by ") + "/by ".length());
                    taskList.add(new Deadline(taskName, taskDdl));
                    taskNum += 1;
                    String response = LINE_SEPARATOR
                            + SPACING + "added: " + taskList.get(taskList.size() - 1) + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(response);
                } catch (StringIndexOutOfBoundsException e) {
                    String error = LINE_SEPARATOR
                            + SPACING + NO_ARGUMENT_1 + "deadline" + NO_ARGUMENT_2 + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(error);
                }

                break;
            case "event":
                try {
                    String taskName = line.substring("event ".length(), line.indexOf("/at "));
                    String taskTime = line.substring(line.indexOf("/at ") + "/at ".length());
                    taskList.add(new Event(taskName, taskTime));
                    String response = LINE_SEPARATOR
                            + SPACING + "added: " + taskList.get(taskList.size() - 1) + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(response);
                } catch (StringIndexOutOfBoundsException e) {
                    String error = LINE_SEPARATOR
                            + SPACING + NO_ARGUMENT_1 + "event" + NO_ARGUMENT_2 + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(error);
                }

                break;
            case "todo":
                try {
                    String taskName = line.substring("todo ".length());
                    taskList.add(new ToDo(taskName));
                    taskNum += 1;
                    String response = LINE_SEPARATOR
                            + SPACING + "added: " + taskList.get(taskList.size() - 1) + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(response);
                } catch (StringIndexOutOfBoundsException e) {
                    String error = LINE_SEPARATOR
                            + SPACING + NO_ARGUMENT_1 + "todo" + NO_ARGUMENT_2 + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(error);
                }
                break;
            case "delete":
                try {
                    int currentTask = Integer.parseInt(line.split(" ")[1]) - 1;
                    String update = LINE_SEPARATOR
                            + SPACING + "Noted! I've removed this task: \n"
                            + SPACING + taskList.get(currentTask) + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(update);
                    taskList.remove(currentTask);
                } catch (IndexOutOfBoundsException e) {
                    String error = LINE_SEPARATOR
                            + SPACING + NO_ARGUMENT_1 + "delete" + NO_ARGUMENT_2 + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(error);
                }
                break;
            default:
                String response = LINE_SEPARATOR
                        + SPACING + CANNOT_IDENTIFY + "\n"
                        + LINE_SEPARATOR;
                System.out.println(response);
            }
        }

        String Bye = LINE_SEPARATOR
                + SPACING + "Bye. Hope to see you again soon!\n"
                + LINE_SEPARATOR;
        System.out.println(Bye);
    }


    public static void list(int taskNum, ArrayList<Task> taskList ) {
        String listOutput = LINE_SEPARATOR;
        for (int i = 0; i < taskList.size(); i++) {
            listOutput += SPACING + (i + 1) + ". "
                    + taskList.get(i).toString() + "\n";
        }
        listOutput += LINE_SEPARATOR;
        System.out.println(listOutput);

    }

}
