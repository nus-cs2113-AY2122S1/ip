package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks;
    private static int taskCount = 0;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(String filePath) throws IOException, DukeException {
        tasks = new ArrayList<>();
        setUpDuke(filePath);
    }

    public Task get(int indexNumber) {
        return tasks.get(indexNumber);
    }

    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Sets up the array at the start of runtime of the programme, using the database.
     * Scans through the entire document to get data line by line.
     * Adds the task and its descriptive fields into the list.
     * Depending on the task type indicator, adds the specific task and its description into the list.
     *
     * @param filePath File path of database.
     * @throws IOException If file to scan does not exist.
     */
    private static void setUpDuke(String filePath) throws IOException, DukeException {
        Parser.setScanner(filePath);

        while (Parser.hasNext()) {
            String[] lineData = Parser.getLineData();
            final int TASK_TYPE_INDEX = 0;
            final int DONE_INDEX = 1;
            final int DESCRIPTION_INDEX = 2;
            String taskTypeString = lineData[TASK_TYPE_INDEX];
            String isDoneString = lineData[DONE_INDEX];
            String description = lineData[DESCRIPTION_INDEX];

            Task newTask;
            String userInput;

            switch (taskTypeString) {
            case ("T"):
                userInput = String.format("todo %s", description.trim());
                newTask = new Todo(userInput);
                tasks.add(taskCount, newTask);
                break;
            case ("E"):
                userInput = String.format("event %s", description.trim());
                newTask = new Event(userInput);
                tasks.add(taskCount, newTask);
                break;
            case ("D"):
                userInput = String.format("deadline %s", description.trim());
                newTask = new Deadline(userInput);
                tasks.add(taskCount, newTask);
                break;
            }

            if (Parser.isDone(isDoneString)) {
                tasks.get(taskCount).markAsDone();
            }
            taskCount++;
        }
    }

    public void deleteTask(int taskIndex, Ui ui) {
        int zeroIndex = taskIndex - 1;
        Task specifiedTask = tasks.get(zeroIndex);
        String taskDetails = specifiedTask.toString();
        tasks.remove(specifiedTask);
        taskCount--;
        ui.handleDelete(taskDetails, taskCount);
    }

    public void listTask(Ui ui) {
        ui.handleListComment();
        int taskIndex = 1;
        for (Task task : tasks) {
            ui.handleListFormat(taskIndex, task);
            taskIndex++;
        }
    }

    /**
     * Returns a boolean value of whether the task in the task list (esp. the description) contains the search key.
     *
     * @param userSearchKey   User specified key to search within the tasks.
     * @param taskDescription Description of task, a variable within the Task object.
     * @return Boolean of whether task description contains String of user's search key.
     */
    private static boolean hasSearchKey(String userSearchKey, String taskDescription) {
        boolean hasSearchKey = taskDescription.contains(userSearchKey);
        return hasSearchKey;
    }

    /**
     * Iterates through the list of tasks to check if tasks contains the search key.
     * If so, the ui handles the tasks that contains description that matches the search key and lists them out.
     *
     * @param searchKey User's search key
     */
    public void findTask(String searchKey, Ui ui) {
        ui.handleListComment();
        int tasksFoundIndex = 1;
        for (Task task : tasks) {
            boolean hasSearchKey = hasSearchKey(searchKey, task.description);
            if (hasSearchKey) {
                ui.handleFind(tasksFoundIndex, task);
                tasksFoundIndex++;
            }
        }
    }

    /**
     * Iterates through the list of tasks, and only checks the tasks in the list labelled as deadlines.
     * Tasks that are upcoming (due within less than three days) are listed by the Ui.
     */
    public void listUpcoming(Ui ui) {
        for (Task t : tasks) {
            if (t instanceof Deadline) {
                ui.handleUpcoming(t, Parser.isThreeDaysAway(t.deadline), t.isDone);
            }
        }
    }

    public void addTask(String userInput, TaskType specificTask, Ui ui) throws DateTimeParseException {
        Task newTask;
        switch (specificTask) {
        case EVENT:
            newTask = new Event(userInput);
            break;
        case TODO:
            newTask = new Todo(userInput);
            break;
        case DEADLINE:
            newTask = new Deadline(userInput);
            break;
        default:
            newTask = new Task(userInput);
            break;
        }
        tasks.add(newTask);
        taskCount++;

        ui.handleAdd(newTask, taskCount);
    }
}
