package duke.task;

import duke.exception.EmptyArgumentException;
import duke.exception.EmptyParameterException;
import duke.exception.MultipleKeywordsException;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.storage.Storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Contains the task list and methods to manage the list and display task related
 * information for the user.
 */
public class TaskList {

    /**
     * The list of all the user's tasks
     */
    private ArrayList<Task> taskList;

    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();
    private static final Storage storage = new Storage();

    /**
     * Changes the current task list to that of another given task list
     *
     * @param taskList the task list to replace the original one
     */
    public void updateTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new Event type task to the task list
     *
     * @param input the full input string given by the user
     */
    public void addEvent(String input) {
        try {
            String content = parser.extractContent(input);
            String descr = parser.extractDescriptionFromEvent(content);
            LocalDateTime at = parser.extractAtFromEvent(content);
            taskList.add(new Event(descr, at));
            storage.updateDataFile(taskList);
            ui.printTaskAddedMessage(taskList);
        } catch (EmptyArgumentException e) {
            ui.printCommandGuide("event [description] /at [date]");
        } catch (EmptyParameterException e) {
            ui.printMissingParameter("Event");
            ui.printCommandGuide("event [description] /at [date]");
        } catch (DateTimeParseException e) {
            ui.printWrongDateTimeFormat();
        }

    }

    /**
     * Adds a new Deadline type task to the task list.
     *
     * @param input the full input string given by the user
     */
    public void addDeadline(String input) {
        try {
            String content = parser.extractContent(input);
            String descr = parser.extractDescriptionFromDeadline(content);
            LocalDateTime by = parser.extractByFromDeadline(content);
            taskList.add(new Deadline(descr, by));
            storage.updateDataFile(taskList);
            ui.printTaskAddedMessage(taskList);
        } catch (EmptyArgumentException e) {
            ui.printCommandGuide("deadline [description] /by [date]");
        } catch (EmptyParameterException e) {
            ui.printMissingParameter("Deadline");
            ui.printCommandGuide("deadline [description] /by [date]");
        } catch (DateTimeParseException e) {
            ui.printWrongDateTimeFormat();
        }
    }

    /**
     * Adds a new Todo type task to the task list.
     *
     * @param input the full input string given by the user
     */
    public void addTodo(String input) {
        try {
            String content = parser.extractContent(input);
            taskList.add(new Todo(content));
            storage.updateDataFile(taskList);
            ui.printTaskAddedMessage(taskList);
        } catch (EmptyArgumentException e) {
            ui.printCommandGuide("todo [description]");
        }
    }

    /**
     * Marks the task of the given ranking as done.
     *
     * @param input the full input string given by the user
     */
    public void markTaskAsDone(String input) {
        //to store the array index of the task
        int index;
        try {
            index = parser.extractIndex(input);
            taskList.get(index).setDone(true);
            storage.updateDataFile(taskList);
            ui.printMarkTaskAsDone(taskList, index, true);
        } catch (IndexOutOfBoundsException e) {
            ui.printMarkTaskAsDone(taskList, 0, false);
        } catch (NumberFormatException e) { //not a number
            ui.printInvalidNumber();
            ui.printCommandGuide("done [task index]");
        } catch (EmptyArgumentException e) {
            ui.printCommandGuide("done [task index]");
        }
    }

    /**
     * Deletes the task of the given ranking.
     *
     * @param input the full input string given by the user
     */
    public void deleteTask(String input) {
        int index; //to store the array index of the task
        Task removedTask = null; //to temporarily store the removed task
        try {
            index = parser.extractIndex(input);
            removedTask = taskList.remove(index);
            Task.decreaseTotalTasks();
            storage.updateDataFile(taskList);
            ui.printDeleteTask(removedTask, index, true);
        } catch (IndexOutOfBoundsException e) {
            ui.printDeleteTask(removedTask, 0, false);
        } catch (NumberFormatException e) { //not a number
            ui.printInvalidNumber();
            ui.printCommandGuide("delete [task index]");
        } catch (EmptyArgumentException e) {
            ui.printCommandGuide("delete [task index]");
        }
    }

    /**
     * Displays all the tasks in the task list for the user in a list format.
     */
    public void showTaskList() {
        ui.printTaskList(taskList);
    }

    /**
     * Displays a list of all tasks that contains the keyword in their descriptions
     *
     * @param input the input string containing the command and keyword
     */
    public void showSearchList(String input) {
        try {
            String keyword = parser.extractKeyword(input);
            ui.printSearchList(taskList, keyword);
        } catch (EmptyArgumentException e) {
            ui.printCommandGuide("find [keyword]");
        } catch (MultipleKeywordsException e) {
            ui.printOnlyUseOneKeyword();
        }
    }

    /**
     * Displays a list of all upcoming events or deadlines within a specified
     * number of days
     *
     * @param input the input string containing the command and keyword
     */
    public void showUpcoming(String input) {
        try {
            int days = parser.extractNumber(input);
            ui.printUpcomingDeadlines(taskList, days);
            ui.printUpcomingEvents(taskList, days);
        } catch (NumberFormatException e) { //not a number
            ui.printInvalidNumber();
            ui.printCommandGuide("upcoming [days]");
        } catch (EmptyArgumentException e) {
            ui.printCommandGuide("upcoming [days]");
        }
    }

}
