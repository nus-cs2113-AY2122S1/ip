package DukeClasses;

import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;
import Tasks.*;
import java.util.ArrayList;


/**
 * Contains the ArrayList of Tasks, and has related operations
 */
public class TaskList {

    protected ArrayList<Task> list;

    public TaskList(ArrayList<Task> taskList) {
        list = taskList;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Adds a new Event object into the ArrayList list
     * @param line the line that the user inputs
     * @throws EmptyTaskException If task is left blank
     * @throws InvalidCommandException If task is not formatted correctly; i.e. no /at
     */
    public void addNewEvent(String line) throws EmptyTaskException, InvalidCommandException {
        int indexOfSlash = line.indexOf("/");
        String actualTask = Parser.extractEventTask(line, indexOfSlash);
        String eventAt = Parser.extractTiming(line, indexOfSlash);
        if (actualTask.isBlank()) {
            throw new EmptyTaskException();
        }
        if (!line.contains("/at")) {
            throw new InvalidCommandException();
        }
        list.add(new Event(actualTask, eventAt));
    }

    /**
     * Adds a new Deadline object into the ArrayList list
     * @param line the line that the user inputs
     * @throws EmptyTaskException If task is left blank
     * @throws InvalidCommandException If task is not formatted correctly; i.e. no /by
     */
    public void addNewDeadline(String line) throws EmptyTaskException, InvalidCommandException {
        int indexOfSlash = line.indexOf("/");
        String actualTask = Parser.extractDeadlineTask(line, indexOfSlash);
        String deadlineBy = Parser.extractTiming(line, indexOfSlash);
        if (actualTask.isBlank()) {
            throw new EmptyTaskException();
        }
        if (!line.contains("/by")) {
            throw new InvalidCommandException();
        }
        list.add(new Deadline(actualTask, deadlineBy));
    }

    /**
     * Adds a new Todo object into the ArrayList list
     * @param line the line that the user inputs
     * @throws EmptyTaskException If task is left blank
     */
    public void addNewTodo(String line) throws EmptyTaskException {
        String actualTask = Parser.extractTodoTask(line);
        if (actualTask.isBlank()) {
            throw new EmptyTaskException();
        }
        list.add(new Todo(actualTask));
    }

}
