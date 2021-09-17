package kate.tasklist;

import kate.exception.EmptyFieldException;
import kate.exception.EmptyTaskException;
import kate.exception.InvalidFieldException;
import kate.parser.Parser;
import kate.storage.Storage;
import kate.task.Deadline;
import kate.task.Event;
import kate.task.Task;
import kate.task.ToDo;
import kate.ui.KateUI;

import java.util.ArrayList;

public class TaskList {

    private static final String TEXT_INDENTATION = "    ";

    private static final String COMMAND_TODO = "todo [description]";
    private static final String COMMAND_DEADLINE = "deadline [description] /by [deadline]";
    private static final String COMMAND_EVENT = "event [description] /at [time frame]";
    private static final String COMMAND_DONE = "done [task number shown in list]";
    private static final String COMMAND_DELETE = "delete [task number]";

    private static final String FAILURE_MESSAGE_ADD_TODO = TEXT_INDENTATION
            + "Please specify a task with:\n"
            + TEXT_INDENTATION + "\"" + COMMAND_TODO + "\"\n";
    private static final String FAILURE_MESSAGE_ADD_DEADLINE = TEXT_INDENTATION
            + "Please specify a task with:\n"
            + TEXT_INDENTATION + "\"" + COMMAND_DEADLINE + "\"\n";
    private static final String FAILURE_MESSAGE_ADD_EVENT = TEXT_INDENTATION
            + "Please specify a task with:\n"
            + TEXT_INDENTATION + "\"" + COMMAND_EVENT + "\"\n";
    private static final String FAILURE_MESSAGE_SET_DONE = TEXT_INDENTATION
            + "Please specify a task with:\n"
            + TEXT_INDENTATION + "\"" + COMMAND_DONE + "\"\n";
    private static final String FAILURE_MESSAGE_DELETE_TASK = TEXT_INDENTATION
            + "Please provide a valid task number:\n"
            + TEXT_INDENTATION + "\"" + COMMAND_DELETE + "\"\n";
    private static final String FAILURE_MESSAGE_EMPTY_TASK = TEXT_INDENTATION
            + "Task list is empty!\n"
            + TEXT_INDENTATION + "Please specify a task using the <help> page \n";

    public ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Retrieves task size of the array list
     *
     * @return The task size in integer
     */
    public int getTaskSize() {
        return tasks.size();
    }

    /**
     * Retrieves a Task given a task index
     *
     * @param taskIndex Index of task supplied by user
     * @return Task object of the current task
     */
    public Task getCurrentTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Retrieves the task added most recently
     *
     * @return Most recently added Task object
     */
    private Task getMostRecentAddedTask() {
        int lastElementIndex = tasks.size() - 1;
        return tasks.get(lastElementIndex);
    }

    /**
     * Adds a todo task to be done
     *
     * @param ui        KateUI object to interact with UI
     * @param storage   Storage object to append tasks to file
     * @param userInput A general task that user wants to add
     */
    public void addToDo(KateUI ui, Storage storage, String userInput) {
        try {
            String taskDescription = Parser.processToDoInput(userInput);
            tasks.add(new ToDo(taskDescription));
            ui.printAddedTask(getMostRecentAddedTask(), getTaskSize());
            storage.appendTaskToFile(ui, getMostRecentAddedTask());
        } catch (EmptyFieldException e) {
            ui.printMessage(FAILURE_MESSAGE_ADD_TODO);
        }
    }

    /**
     * Adds a todo task from file
     *
     * @param taskDescription Description of task
     * @param isDone          Task done status
     */
    public void addToDoFromFile(String taskDescription, boolean isDone) {
        tasks.add(new ToDo(taskDescription, isDone));
    }

    /**
     * Adds a task that has a deadline
     *
     * @param ui        KateUI object to interact with UI
     * @param storage   Storage object to append tasks to file
     * @param userInput A task a user wants to add that has a deadline
     */
    public void addDeadline(KateUI ui, Storage storage, String userInput) {
        try {
            String[] infoArr = Parser.processDeadlineInput(userInput);
            String taskDescription = infoArr[0];
            String deadline = infoArr[1];

            tasks.add(new Deadline(taskDescription, deadline));
            ui.printAddedTask(getMostRecentAddedTask(), getTaskSize());
            storage.appendTaskToFile(ui, getMostRecentAddedTask());
        } catch (EmptyFieldException e) {
            ui.printMessage(FAILURE_MESSAGE_ADD_DEADLINE);
        }
    }

    /**
     * Adds a task that has a deadline from file
     *
     * @param taskDescription Description of task
     * @param isDone          Task done status
     * @param deadline        Deadline of the task
     */
    public void addDeadlineFromFile(String taskDescription, boolean isDone, String deadline) {
        tasks.add(new Deadline(taskDescription, isDone, deadline));
    }

    /**
     * Adds a task that is an event
     *
     * @param ui        KateUI object to interact with UI
     * @param storage   Storage object to append tasks to file
     * @param userInput An event the user wants to add
     */
    public void addEvent(KateUI ui, Storage storage, String userInput) {
        try {
            String[] infoArr = Parser.processEventInput(userInput);
            String taskDescription = infoArr[0];
            String timeFrame = infoArr[1];

            tasks.add(new Event(taskDescription, timeFrame));
            ui.printAddedTask(getMostRecentAddedTask(), getTaskSize());
            storage.appendTaskToFile(ui, getMostRecentAddedTask());
        } catch (EmptyFieldException e) {
            ui.printMessage(FAILURE_MESSAGE_ADD_EVENT);
        }
    }

    /**
     * Adds a task that is an event from file
     *
     * @param taskDescription Description of task
     * @param isDone          Task done status
     * @param event           Event time frame of the task
     */
    public void addEventFromFile(String taskDescription, boolean isDone, String event) {
        tasks.add(new Event(taskDescription, isDone, event));
    }

    /**
     * Marks task as done for a particular task number
     *
     * @param ui        KateUI object to interact with UI
     * @param storage   Storage object to append tasks to file
     * @param userInput Input provided by user to indicate task completion for a task
     */
    public void indicateDone(KateUI ui, Storage storage, String userInput) {
        try {
            Task curTask = Parser.processDoneInput(tasks, userInput);
            curTask.setDone();

            String doneMessage = TEXT_INDENTATION + "Nice! I've marked this task as done:\n"
                    + TEXT_INDENTATION + "  " + curTask.getTaskInfo() + "\n";
            ui.printMessage(doneMessage);
            storage.updateTasksToFile(ui, tasks);
        } catch (EmptyFieldException | InvalidFieldException e) {
            ui.printMessage(FAILURE_MESSAGE_SET_DONE);
        } catch (EmptyTaskException e) {
            ui.printMessage(FAILURE_MESSAGE_EMPTY_TASK);
        }
    }

    /**
     * Delete a task specified by a task number
     *
     * @param ui        KateUI object to interact with UI
     * @param storage   Storage object to append tasks to file
     * @param userInput Input provided by user
     */
    public void deleteTask(KateUI ui, Storage storage, String userInput) {
        try {
            Task deletedTask = Parser.processDeleteInput(tasks, userInput);
            String deletedTaskInfo = deletedTask.getTaskInfo();
            tasks.remove(deletedTask);

            String deleteMessage = TEXT_INDENTATION + "Noted. I've removed this task:\n"
                    + TEXT_INDENTATION + "  " + deletedTaskInfo + "\n"
                    + TEXT_INDENTATION + "You currently have ("
                    + tasks.size() + ") tasks in your list :)\n";
            ui.printMessage(deleteMessage);
            storage.updateTasksToFile(ui, tasks);
        } catch (EmptyFieldException | InvalidFieldException e) {
            ui.printMessage(FAILURE_MESSAGE_DELETE_TASK);
        } catch (EmptyTaskException e) {
            ui.printMessage(FAILURE_MESSAGE_EMPTY_TASK);
        }

    }

}
