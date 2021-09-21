package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String INDENT = "    │ ";
    private static int taskCount = 0; //todo how to do away with taskCount and taskCompleted?
    private static int taskCompleted = 0;
    protected static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Ui.greetUser();
        engageUser();
        Ui.byeUser();
    }

    //Move to taskManager //////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Adds inputs from user to list[] to keep track of user's tasks, deadlines, and events.
     *
     * @param taskName Name of task from user.
     * @param taskType Type of task from user.
     * @param taskDetails Time/date of event/deadline.
     */
    public static void addTask(String taskName, String taskType, String taskDetails) {

        try {
            switch (taskType) {
            case "todo":
                addTodo(taskName);
                break;
            case "deadline":
                addDeadline(taskName, taskDetails);
                break;
            case "event":
                addEvent(taskName, taskDetails);
                break;
            default:
                return;
            }
            addTaskConfirmation();
        } catch (DukeException e) {
            Ui.printMissingTextError();
        }
    }
    public static void addTodo(String taskName) throws DukeException {
        if (isEmpty(taskName)) {
            throw new DukeException("todo name missing.");
        }
        tasks.add(new Todo(taskName));
    }
    public static void addDeadline(String taskName, String taskDetails) throws DukeException {
        if (isEmpty(taskName)) {
            throw new DukeException("deadline name missing.");
        }
        tasks.add(new Deadline(taskName, taskDetails));

    }
    public static void addEvent(String taskName, String taskDetails) throws DukeException {
        if (isEmpty(taskName)) {
            throw new DukeException("event name missing.");
        }
        tasks.add(new Event(taskName, taskDetails));

    }

    /**
     * Prints confirmation to user of added task and updates taskCount number
     */
    public static void addTaskConfirmation() {
        taskCount++;
        int taskPending = taskCount - taskCompleted;
        String plural = (taskPending) == 1 ? "" : "s";

        Ui.printTopLine();
        Ui.printAddedTask(tasks, plural, taskPending);
        Ui.printBottomLine();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Marks tasks in tasks[] as done, only if they exist or has not been completed.
     *
     * @param userInput String from user to be converted into a number that is associated with a task.
     */
    public static void markTaskAsDone(String userInput) {
        int taskNumber;
        boolean isExists;
        try {
            //abstraction here with method with "throws DukeException"
            taskNumber = Integer.parseInt(userInput) - 1;
            isExists = taskNumber >= 0 && taskNumber < taskCount;

            Ui.printTopLine();
            if (!isExists) {
                System.out.println(INDENT + "Wha- Hey! Task does not exist!");
            } else if (tasks.get(taskNumber).getDoneStatus()) {
                System.out.println(INDENT + "Dude... you've done the task already.");
            } else {
                taskCompleted++;
                tasks.get(taskNumber).setAsDone();
                System.out.println(INDENT + "About time. I've mark that task as done:");
                System.out.println(INDENT + "[" + tasks.get(taskNumber).getStatusIcon() + "]"
                        + tasks.get(taskNumber).getTaskName());
            }

            Ui.printBottomLine();

        } catch (NumberFormatException e){
            Ui.printNumberFormatError();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printMissingTextError();
        }
    }

    /**
     * Checks whether there exist an input after user input the command word.
     *
     * @param input Expected input from user after the command word taskType is typed.
     * @return true if input is empty, false if input is not empty.
     */
    public static boolean isEmpty(String input) {
        return input.equals("");
    }

    /**
     * Engages user base on what the user has typed and executes a corresponding command.
     */
    public static void engageUser() {
        Scanner text = new Scanner(System.in);
        String taskType;
        String taskName;
        String taskDetails = "";

        String userInput;
        String[] words = new String[0];
        boolean isExit = false;

        do {
            taskType = text.next().toLowerCase();

            switch (taskType) {
            case "bye":
                isExit = true;
                break;
            case "hello":
            case "hi":
            case "yo":
                Ui.mockUser();
                break;
            case "list":
                Ui.printList(tasks, taskCount);
                break;
            case "done":
                userInput = text.nextLine();
                if (userInput.equals("")) {
                    Ui.printMissingTextError();
                } else {
                    words = userInput.split(" ");
                    userInput = words[1];
                    markTaskAsDone(userInput);
                }
                break;
            case "todo":
                taskName = text.nextLine();
                addTask(taskName, taskType, taskDetails);
                break;
            case "deadline":
            case "event":
                userInput = text.nextLine();

                if (taskType.equals("deadline")) {
                    words = userInput.split(" /by ");
                } else if (taskType.equals("event")) {
                    words = userInput.split(" /at ");
                }

                taskName = words[0];
                taskDetails = words[1];
                addTask(taskName, taskType, taskDetails);
                break;
            default:
                userInput = text.nextLine();
                Ui.printMissingTaskTypeError();
                break;
            }
        } while (!isExit);
    }

}
