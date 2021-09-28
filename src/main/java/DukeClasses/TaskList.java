package DukeClasses;

import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;
import Tasks.*;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> list;

    public TaskList(ArrayList<Task> taskList) {
        list = taskList;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void addNewEvent(String line) throws EmptyTaskException, InvalidCommandException {
        int indexOfSlash = line.indexOf("/");
        String actualTask = extractEventTask(line, indexOfSlash);
        String eventAt = extractTiming(line, indexOfSlash);
        if (actualTask.isBlank()) {
            throw new EmptyTaskException();
        }
        if (!line.contains("/at")) {
            throw new InvalidCommandException();
        }
        list.add(new Event(actualTask, eventAt));
    }

    public void addNewDeadline(String line) throws EmptyTaskException, InvalidCommandException {
        int indexOfSlash = line.indexOf("/");
        String actualTask = extractDeadlineTask(line, indexOfSlash);
        String deadlineBy = extractTiming(line, indexOfSlash);
        if (actualTask.isBlank()) {
            throw new EmptyTaskException();
        }
        if (!line.contains("/by")) {
            throw new InvalidCommandException();
        }
        list.add(new Deadline(actualTask, deadlineBy));
    }

    public void addNewTodo(String line) throws EmptyTaskException {
        String actualTask = extractTodoTask(line);
        if (actualTask.isBlank()) {
            throw new EmptyTaskException();
        }
        list.add(new Todo(actualTask));
    }

    public String extractTiming(String line, int indexOfSlash) {
        return line.substring(indexOfSlash + 4);
    }

    public String extractEventTask(String line, int indexOfSlash) {
        return line.substring(5, indexOfSlash - 1).trim();
    }

    public String extractDeadlineTask(String line, int indexOfSlash) {
        return line.substring(8, indexOfSlash - 1).trim();
    }

    public String extractTodoTask(String line) {
        return line.substring(4).trim();
    }

}
