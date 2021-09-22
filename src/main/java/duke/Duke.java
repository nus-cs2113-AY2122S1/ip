package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private static int taskCount = 0; //todo how to do away with taskCount and taskCompleted?
    private static int taskCompleted = 0;
    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";

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
    public static void addTask(String taskType, String taskName, String taskDetails) {

        try {
            switch (taskType) {
            case TODO:
                addTodo(false, taskName);
                break;
            case DEADLINE:
                addDeadline(false, taskName, taskDetails);
                break;
            case EVENT:
                addEvent(false, taskName, taskDetails);
                break;
            default:
                return;
            }
            addTaskConfirmation();
        } catch (DukeException e) {
            Ui.printMissingTextError();
        }
    }

    public static void addTaskFromFile(String taskType, String taskIsDone, String taskName, String taskDetails) {
        boolean isDone = false;
        if (taskIsDone.equals("1")) {
            isDone = true;
        } else if (taskIsDone.equals("0")) {
            isDone = false;
        } else {
            //todo throw exception
        }

        try {
            switch (taskType) {
            case TODO:
                addTodo(isDone, taskName);
                break;
            case DEADLINE:
                addDeadline(isDone, taskName, taskDetails);
                break;
            case EVENT:
                addEvent(isDone, taskName, taskDetails);
                break;
            default:
                return;
            }
            System.out.println("Tasks added from save file.");
            //replace addTaskConfirmation() with something else
        } catch (DukeException e) {
            Ui.printMissingTextError();
        }
    }

    public static void addTodo(boolean isDone, String taskName) throws DukeException {
        if (isEmpty(taskName)) {
            throw new DukeException("todo name missing.");
        }
        tasks.add(new Todo(isDone, taskName));
    }
    public static void addDeadline(boolean isDone, String taskName, String taskDetails) throws DukeException {
        if (isEmpty(taskName)) {
            throw new DukeException("deadline name missing.");
        }
        tasks.add(new Deadline(isDone, taskName, taskDetails));

    }
    public static void addEvent(boolean isDone, String taskName, String taskDetails) throws DukeException {
        if (isEmpty(taskName)) {
            throw new DukeException("event name missing.");
        }
        tasks.add(new Event(isDone, taskName, taskDetails));

    }

    /**
     * Prints confirmation to user of added task and updates taskCount number
     */
    public static void addTaskConfirmation() {
        taskCount++;
        int taskPending = taskCount - taskCompleted;
        String isPlural = (taskPending) == 1 ? "" : "s";

        Ui.printTopLine();
        Ui.printAddedTask(tasks, isPlural, taskPending);
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
                Ui.printTaskDoesNotExist();
            } else if (tasks.get(taskNumber).getDoneStatus()) {
                Ui.printTaskAlreadyDone();
            } else {
                taskCompleted++;
                tasks.get(taskNumber).setAsDone();
                Ui.printTaskMarkedAsDone(tasks, taskNumber);
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
        String taskIsDone = "0";
        String taskName;
        String taskDetails = "";

        String userInput;
        String[] words = new String[0];
        boolean isExit = false;

        do {
            taskType = text.next().toLowerCase();

            switch (taskType) {
            case "bye":
                FileManager.saveTaskToFile(tasks);
                isExit = true;
                break;
            case "hello":
            case "hi":
            case "yo":
                Ui.mockUser();
                break;
            case "list":
                Ui.printList(tasks);
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
                taskName = text.nextLine().substring(1); //.substring(1) to remove whitespace before taskName
                taskType = TODO;
                addTask(taskType, taskName, taskDetails);
                break;
            case "deadline":
            case "event":
                userInput = text.nextLine().substring(1);

                if (taskType.equals("deadline")) {
                    words = userInput.split(" /by ");
                    taskType = DEADLINE;
                } else if (taskType.equals("event")) {
                    words = userInput.split(" /at ");
                    taskType = EVENT;
                }
                taskName = words[0];
                taskDetails = words[1];
                addTask(taskType, taskName, taskDetails);
                break;
            default:
                userInput = text.nextLine();
                Ui.printWrongTaskTypeError(taskType, userInput);
                break;
            }
        } while (!isExit);
    }

}
