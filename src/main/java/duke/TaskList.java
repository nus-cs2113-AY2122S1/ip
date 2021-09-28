package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    protected static int taskCount = 0; //can be replaced with tasks.size()
    protected static int taskCompleted = 0; //encapsulate in type Task?
    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";

    protected static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds inputs from user to list[] to keep track of user's tasks, deadlines, and events.
     *
     * @param taskName    Name of task from user.
     * @param taskType    Type of task from user.
     * @param taskDetails Time/date of event/deadline.
     */
    protected static void addTask(String taskType, String taskName, String taskDetails) {
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
            confirmTaskAdded(false);
        } catch (DukeException e) {
            Ui.showMissingTextError();
        }
    }

    public static void addTaskFromFile(String taskType, String taskIsDone, String taskName, String taskDetails) {
        Task task;
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
            updateTaskCountAndTaskCompleted(isDone);
        } catch (DukeException e) {
            Ui.showMissingTextError();
        }
    }

    public static void addTodo(boolean isDone, String taskName) throws DukeException {
        if (taskName.isBlank()) {
            throw new DukeException("todo name missing.");
        }
        tasks.add(new Todo(isDone, taskName));
    }

    public static void addDeadline(boolean isDone, String taskName, String taskDetails) throws DukeException {
        if (taskName.isBlank()) {
            throw new DukeException("deadline name missing.");
        }
        if (taskName.equals(taskDetails)) {
            throw new DukeException("deadline details missing");
        }
        tasks.add(new Deadline(isDone, taskName, taskDetails));

    }

    public static void addEvent(boolean isDone, String taskName, String taskDetails) throws DukeException {
        if (taskName.isBlank()) {
            throw new DukeException("event name missing.");
        }
        if (taskName.equals(taskDetails)) {
            throw new DukeException("event details missing");
        }
        tasks.add(new Event(isDone, taskName, taskDetails));

    }

    public static void updateTaskCountAndTaskCompleted(boolean isDone) {
        taskCount++;
        if (isDone) {
            taskCompleted++;
        }
    }

    /**
     * Prints confirmation to user of added task and updates taskCount number
     */
    public static void confirmTaskAdded(boolean isDone) {
        updateTaskCountAndTaskCompleted(isDone);
        int taskPending = taskCount - taskCompleted;
        String isPlural = (taskPending) == 1 ? "" : "s";

        Ui.printTopLine();
        Ui.printAddedTask(tasks, isPlural, taskPending);
        Ui.printBottomLine();
    }

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
            Ui.showNumberFormatError();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.showMissingTextError();
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
            } else if (tasks.get(taskNumber).getDoneStatus()){
                taskCount--;
                Ui.printTaskAlreadyDone();
                Ui.printDeleteDoneTask();
                tasks.remove(tasks.get(taskNumber));
            } else {
                taskCount--;
                Ui.printTaskDeleted(tasks, taskNumber);
                tasks.remove(tasks.get(taskNumber));
            }
            Ui.printBottomLine();
        } catch (NumberFormatException e){
            Ui.showNumberFormatError();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.showMissingTextError();
        }
    }

    /**
     * Engages user base on what the user has typed.
     * Passes execution to parse();
     */
    public static void engageUser() {
        try {
            Parser.parse();
        } catch (DukeException e) {
            Ui.showWrongTaskTypeError("Unexpected task type.");
        }
    }
}
