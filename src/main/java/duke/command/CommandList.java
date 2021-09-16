package duke.command;
import duke.DukeException;
import duke.task.Task;
import duke.task.Deadlines;
import duke.task.Todo;
import duke.task.Events
        ;

import java.util.ArrayList;

public class CommandList {
    private static final int CMD_NOT_FOUND = 0;
    private static final int CMD_TODO = 1;
    private static final int CMD_EVENT = 2;
    private static final int CMD_DEADLINE = 3;
    private static final int CMD_LIST = 4;
    private static final int CMD_DONE = 5;
    private static final int CMD_TERMINATE = 6;
    private static final int CMD_DELETE = 7;
    private static final int FIVE = 5;
    private static final int SEVEN = 7;
    private static final String border = "____________________________________________________________\n";

    protected int command;
    protected int taskCount = 0;

    public CommandList() {

        this.command = CMD_NOT_FOUND;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void addTaskMessage(Task task) {
        System.out.println(border);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(border);
    }
    public void removeTaskMessage(Task task) {
        System.out.println(border);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(border);
    }

    public void addEvent(ArrayList<Task> items, String description, String time) {
        Events newEvent = new Events(description, time);
        items.add(taskCount, newEvent);
        taskCount++;
        addTaskMessage(newEvent);
    }

    public void addDeadline(ArrayList<Task> items, String description, String by) {
        Deadlines newDeadline = new Deadlines(description, by);
        items.add(taskCount, newDeadline);
        taskCount++;
        addTaskMessage(newDeadline);
    }

    public void addTodo(ArrayList<Task> items, String description) {
        Todo newTodo = new Todo(description);
        items.add(taskCount, newTodo);
        taskCount++;
        addTaskMessage(newTodo);
    }
    public void removeItem(ArrayList<Task> items, int taskNum) {
        removeTaskMessage(items.get(taskNum));
        items.remove(taskNum);
    }

    public static void printEndMessage() {
        System.out.println(border);
        System.out.println("chat again next time!\n" + border);
    }

    public void printErrorMessage(DukeException error) {
        error.printError(error.getErrorType());
    }

    public void executeCommand(ArrayList<Task> items, DukeException error, String input) {

        switch (command) {
            case CMD_TODO:
                addTodo(items, input.replace("todo", "").trim());
                break;
            case CMD_EVENT:
                addEvent(items, input.split("/at")[1].trim(), input.split("/at")[0].trim().replace("event", "").trim());
                break;
            case CMD_DEADLINE:
                addDeadline(items, input.split("/by")[1].trim(), input.split("/by")[0].trim().replace("deadline", "").trim());
                break;
            case CMD_LIST:
                int j = 1;
                System.out.println(border);
                System.out.println("Here are the task in your list:");
                for (Task item : items) {
                    if (item != null) {
                        System.out.print(j + ".");
                        System.out.println(item);
                        j++;
                    }
                }
                break;
            case CMD_DONE:
                int dividerPosition = input.indexOf(" ") + 1;
                int endPosition = input.length();
                if (endPosition > FIVE) {
                    String num = input.substring(dividerPosition, endPosition);
                    int taskNum = Integer.parseInt(num) - 1;
                    items.get(taskNum).markDone();
                    System.out.println(border + "Nice! task is done " + '\n' + border);
                }
                break;
            case CMD_TERMINATE:
                printEndMessage();
                break;
            case CMD_DELETE:
                dividerPosition = input.indexOf(" ") + 1;
                endPosition = input.length();
                if (endPosition > SEVEN) {
                    String num = input.substring(dividerPosition, endPosition);
                    int taskNum = Integer.parseInt(num) - 1;
                    removeItem(items, taskNum);
                }
                break;
            default:
                printErrorMessage(error);
                break;
        }
    }
}


