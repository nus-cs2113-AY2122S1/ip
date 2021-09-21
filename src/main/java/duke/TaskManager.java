package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskManager {

    protected static int taskCount = 0; //can be replaced with tasks.size()
    protected static int taskCompleted = 0; //encapsulate in type Task?
    protected static ArrayList<Task> tasks = new ArrayList<>();

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
    public static void doneTask(String userInput) {
        int taskNumber;
        boolean isExists;
        try {
            //todo abstraction here with a TaskManager method with `throws DukeException`
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
                Ui.printTaskDone(tasks, taskNumber);
            }
            Ui.printBottomLine();

        } catch (NumberFormatException e){
            Ui.printNumberFormatError();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printMissingTextError();
        }
    }

    public static void deleteTask(String userInput) {
        int taskNumber;
        boolean isExists;

        try {
            //todo abstraction here with a TaskManager method with `throws DukeException`
            taskNumber = Integer.parseInt(userInput) - 1;
            isExists = taskNumber >= 0 && taskNumber < taskCount;

            Ui.printTopLine();
            if (!isExists) {
                Ui.printTaskDoesNotExist();
            } else if (tasks.get(taskNumber).getDoneStatus()) {
                Ui.printTaskAlreadyDone();
            } else {
                taskCount--;
                Ui.printTaskDeleted(tasks, taskNumber);
                tasks.remove(tasks.get(taskNumber));
            }
            Ui.printBottomLine();

        } catch (NumberFormatException e){
            Ui.printNumberFormatError();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printMissingTextError();
        }
    }
    /**
     * Checks whether there exist more inputs after user inputs command word.
     *
     * @param input Expected input from user after the command word taskType is typed.
     * @return true if input is empty, false if input is not empty.
     */
    public static boolean isEmpty(String input) {
        return input.equals("");
    }


}
