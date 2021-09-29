package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";

    protected static int taskCount = 0;
    protected static int taskCompleted = 0;
    protected static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds inputs from user to ArrayList tasks to keep track of user's tasks, deadlines, and events.
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

    /**
     * Adds tasks from file to ArrayList tasks to keep track of user's tasks, deadlines, and events.
     *
     * @param taskType Type of task: Todo, Deadline, or Event.
     * @param taskIsDone Boolean status of task.
     * @param taskName Description of task.
     * @param taskDetails Time information pertaining to task.
     * @throws DukeException When the isDone value is not 1 or 0.
     */
    public static void addTaskFromFile(String taskType, String taskIsDone, String taskName, String taskDetails) throws DukeException {
        boolean isDone = false;

        if (taskIsDone.equals("1")) {
            isDone = true;
        } else if (taskIsDone.equals("0")) {
            isDone = false;
        } else {
            throw new DukeException("Done status of task corrupted.");
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

    /**
     * Adds a task of type Todo to ArrayList tasks.
     *
     * @param isDone Boolean status representing whether the task has been done.
     * @param taskName Description of task.
     * @throws DukeException Description of task not inputted.
     */
    public static void addTodo(boolean isDone, String taskName) throws DukeException {
        if (taskName.isBlank()) {
            throw new DukeException("todo name missing.");
        }
        tasks.add(new Todo(isDone, taskName));
    }

    /**
     * Adds a task of type Deadline to ArrayList tasks.
     *
     * @param isDone Boolean status representing whether the task has been done.
     * @param taskName Description of task.
     * @param taskDetails Time of deadline in String format.
     * @throws DukeException Description of task not inputted.
     */
    public static void addDeadline(boolean isDone, String taskName, String taskDetails) throws DukeException {
        if (taskName.isBlank()) {
            throw new DukeException("deadline name missing.");
        }
        if (taskName.equals(taskDetails)) {
            throw new DukeException("deadline details missing");
        }
        tasks.add(new Deadline(isDone, taskName, taskDetails));

    }

    /**
     * Adds a task of type Event to ArrayList tasks.
     *
     * @param isDone Boolean status representing whether the task has been done.
     * @param taskName Description of task.
     * @param taskDetails Time of event in String format.
     * @throws DukeException Description of task not inputted.
     */
    public static void addEvent(boolean isDone, String taskName, String taskDetails) throws DukeException {
        if (taskName.isBlank()) {
            throw new DukeException("event name missing.");
        }
        if (taskName.equals(taskDetails)) {
            throw new DukeException("event details missing");
        }
        tasks.add(new Event(isDone, taskName, taskDetails));

    }

    /**
     * Updates taskCount and taskCompleted after a task has been added from save file.
     *
     * @param isDone Boolean status representing whether the task has been done.
     */
    public static void updateTaskCountAndTaskCompleted(boolean isDone) {
        taskCount++;
        if (isDone) {
            taskCompleted++;
        }
    }

    /**
     * Prints confirmation to user of added task and updates taskCount number
     *
     * @param isDone Boolean status representing whether the task has been done.
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
     * Marks a task in ArrayList tasks as done, only if they exist or has not been completed.
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

    /**
     * Deletes a task from ArrayList tasks, only if they exist or has not been completed.
     *
     * @param userInput String from user to be converted into a number that is associated with a task.
     */
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
     * Searches the description of all tasks with a String query from user.
     * Prints the tasks that contains the query (as a substring).
     *
     * @param userInput Query of user.
     * @throws DukeException Query is blank.
     */
    public static void findTask(String userInput) throws DukeException {
        ArrayList<Task> tempTasks = new ArrayList<>();
        if (!userInput.isBlank()) {
            userInput = userInput.toLowerCase();
        } else {
            throw new DukeException("Query is missing.");
        }

        if (tasks.size() != 0) {
            for (Task task : tasks) {
                if (task.getTaskName().toLowerCase().contains(userInput)) {
                    tempTasks.add(task);
                }
            }
        } else {
            throw new DukeException("List is empty.");
        }

        if (tempTasks.size() != 0) {
            Ui.showQueryList(tempTasks);
        } else {
            Ui.showQueryNotFound();
        }
    }

    /**
     * Engages user based on what the user has typed.
     * Passes execution to parse();
     */
    public static void engageUser() {
            Scanner input = new Scanner(System.in);
            Parser.parse(input);
    }
}
