import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    protected Ui ui = new Ui();
    protected int DONE_OFFSET = 1;
    protected int TODO_LEN_OFFSET = 4;
    protected int TODO_SUBSTRING_OFFSET = 5;
    protected int DEADLINE_BY_OFFSET = 4;
    protected int DEADLINE_LEN_OFFSET = 8;
    protected int DEADLINE_SUBSTRING_OFFSET = 9;
    protected int DELETE_OFFSET = 1;
    protected int DEADLINE_DESC_OFFSET = 9;
    protected int EVENT_DESC_OFFSET = 6;
    protected int EVENT_BY_OFFSET = 4;
    protected int EVENT_LEN_OFFSET = 5;
    protected int EVENT_SUBSTRING_OFFSET = 6;

    public void markTaskDone(int numToMark, ArrayList<Task> tasks) throws IndexOutOfBoundsException {
        if ((numToMark - DONE_OFFSET >= 0) && (tasks.get(numToMark - DONE_OFFSET) != null)) {
            tasks.get(numToMark - DONE_OFFSET).markAsDone();
            ui.printTaskMarked(numToMark, tasks, DONE_OFFSET);
        }
        else {
            ui.printDoneInvalidNumError();
        }
    }

    public void addTask(Task t, ArrayList<Task> tasks) {
        tasks.add(t);
    }

    public void addTodo(String line, ArrayList<Task> tasks) throws DukeMissingDescException {
        if (line.length() == TODO_LEN_OFFSET || line.substring(TODO_SUBSTRING_OFFSET).isBlank()) {
            throw new DukeMissingDescException();
        }
        String todoDescription = line.substring(TODO_SUBSTRING_OFFSET);
        this.addTask(new Todo(todoDescription), tasks);
        ui.printAddedTodo(todoDescription, tasks);
    }

    public void addDeadline(String line, ArrayList<Task> tasks) throws DukeMissingDescException, DukeMissingParamException, DateTimeParseException {
        if (line.length() == DEADLINE_LEN_OFFSET || line.substring(DEADLINE_SUBSTRING_OFFSET).isBlank()) {
            throw new DukeMissingDescException();
        }

        int posOfBy = line.indexOf("/by");
        // throw error if missing /by parameter
        if (posOfBy == -1) {
            throw new DukeMissingParamException();
        }

        int posOfLastChar = line.length();

        // get description from input
        String deadlineDescription = line.substring(DEADLINE_DESC_OFFSET, posOfBy);

        // get deadline when from input
        LocalDate deadlineBy = LocalDate.parse(line.substring(posOfBy + DEADLINE_BY_OFFSET, posOfLastChar));

        this.addTask(new Deadline(deadlineDescription, deadlineBy), tasks);
        ui.printAddedDeadline(deadlineDescription, deadlineBy, tasks);
    }

    public void addEvent(String line, ArrayList<Task> tasks) throws DukeMissingDescException, DukeMissingParamException, DateTimeParseException {
        if (line.length() == EVENT_LEN_OFFSET || line.substring(EVENT_SUBSTRING_OFFSET).isBlank()) {
            throw new DukeMissingDescException();
        }

        int posOfAt = line.indexOf("/at");

        // throw error if missing /at parameter
        if (posOfAt == -1) {
            throw new DukeMissingParamException();
        }

        int posOfLastChar = line.length();

        // get description from input
        String eventDescription = line.substring(EVENT_DESC_OFFSET, posOfAt);

        // get event when from input
        LocalDate eventAt = LocalDate.parse(line.substring(posOfAt + EVENT_BY_OFFSET, posOfLastChar));

        addTask(new Event(eventDescription, eventAt), tasks);
        ui.printAddedEvent(eventDescription, eventAt, tasks);
    }


    public void deleteTask(int numToRemove, ArrayList<Task> tasks) throws DukeMissingParamException, NumberFormatException, IndexOutOfBoundsException {
        if ((numToRemove - DELETE_OFFSET >= 0) && (tasks.get(numToRemove - DELETE_OFFSET) != null)) {
            String byString = "by: ";
            String atString = "at: ";
            String taskWordString = "";

            // change word string to print depending on if a todo, deadline or event
            if (tasks.get(numToRemove - DELETE_OFFSET).getType().equals("D")) {
                taskWordString = byString;
            } else if (tasks.get(numToRemove - DELETE_OFFSET).getType().equals("E")) {
                taskWordString = atString;
            } else {
                taskWordString = "";
            }

            if (taskWordString.equals("")) {
                ui.printWithoutTaskWordString(numToRemove, DELETE_OFFSET, tasks);

            } else {
                ui.printWithTaskWordString(numToRemove, DELETE_OFFSET, taskWordString, tasks);
            }
        }   tasks.remove(tasks.get(numToRemove - DELETE_OFFSET));
    }

}
