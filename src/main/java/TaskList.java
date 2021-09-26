import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Class handling the task list and its operations.
 */
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

    /**
     * Marks the selected task as done if the task number is valid.
     * Throws an error if the selected task number is not valid.
     *
     * @param numToMark the displayed number of the task to be marked as done.
     * @param tasks the ArrayList of Tasks where the task to be marked as done is stored.
     * @throws IndexOutOfBoundsException when the task number selected is not valid.
     */
    public void markTaskDone(int numToMark, ArrayList<Task> tasks) throws IndexOutOfBoundsException {
        if ((numToMark - DONE_OFFSET >= 0) && (tasks.get(numToMark - DONE_OFFSET) != null)) {
            tasks.get(numToMark - DONE_OFFSET).markAsDone();
            ui.printTaskMarked(numToMark, tasks, DONE_OFFSET);
        }
        else {
            ui.printDoneInvalidNumError();
        }
    }

    /**
     * Adds a task to the ArrayList of Tasks.
     *
     * @param t the task to be added to the ArrayList of Tasks.
     * @param tasks the ArrayList of Tasks where the task is to be added to.
     */
    public void addTask(Task t, ArrayList<Task> tasks) {
        tasks.add(t);
    }

    /**
     * Adds a TODO type task to the ArrayList of Tasks, and checks if any required parameters are missing (description).
     * Throws an error if required parameters are missing.
     *
     * @param line the input of the user.
     * @param tasks the he ArrayList of Tasks where the TODO task is to be added to.
     * @throws DukeMissingDescException when the description of the task is not input by the user.
     */
    public void addTodo(String line, ArrayList<Task> tasks) throws DukeMissingDescException {
        if (line.length() == TODO_LEN_OFFSET || line.substring(TODO_SUBSTRING_OFFSET).isBlank()) {
            throw new DukeMissingDescException();
        }
        String todoDescription = line.substring(TODO_SUBSTRING_OFFSET);
        this.addTask(new Todo(todoDescription), tasks);
        ui.printAddedTodo(todoDescription, tasks);
    }


    /**
     * Adds a DEADLINE type task to the ArrayList of Tasks, and checks if any required parameters are missing (description, due date).
     * Throws an error if required parameters are missing.
     *
     * @param line the input of the user.
     * @param tasks the he ArrayList of Tasks where the DEDADLINE task is to be added to.
     * @throws DukeMissingDescException when the description of the deadline task is not input by the user.
     * @throws DukeMissingParamException when the /by flag is not input.
     */
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

    /**
     * Adds a EVENT type task to the ArrayList of Tasks, and checks if any required parameters are missing (description, event date).
     * Throws an error if required parameters are missing.
     *
     * @param line the input of the user.
     * @param tasks the ArrayList of Tasks where the EVENT task is to be added to.
     * @throws DukeMissingDescException when the description of the event task is not input by the user.
     * @throws DukeMissingParamException when the /at flag is not input.
     * @throws DateTimeParseException when the date input is not in the correct format.
     */
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
  
    /**
     * Deletes a task from the ArrayList of Tasks. Throws an error if the task number to be deleted is missing, incorrectly
     * input or is not a valid task number.
     *
     * @param numToRemove the task number to be removed.
     * @param tasks the ArrayList of Tasks where the task is to removed.
     * @throws DukeMissingParamException when the task number to be deleted is not input.
     * @throws NumberFormatException when a string is input where the task number to be deleted is expected.
     * @throws IndexOutOfBoundsException when the task number is invalid.
     */
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

    /**
     * Prints out all tasks that contain the matching keyword. The method will look through the entire ArrayList of Tasks,
     * and checks if any of the task's description contains the keyword input by the user. It will store the tasks in another
     * ArrayList if there is a match, and print that ArrayList which contains all the matched tasks.
     *
     * @param line the input of the user.
     * @param tasks the ArrayList of Tasks to check if the keyword matches any of it's task's description.
     * @throws DukeMissingParamException when the keyword is not input by the user.
     * @throws ArrayIndexOutOfBoundsException when the keyword is not input by the user.
     * @throws DukeMultipleParamException when there are more than one keywords input by the user.
     */
    public void findTasks(String line, ArrayList<Task> tasks) throws DukeMissingParamException, ArrayIndexOutOfBoundsException, DukeMultipleParamException {
        String input[] = line.split(" ");
        if (input.length == 1) {
            throw new DukeMissingParamException();
        } else if (input.length > 2) {
            throw new DukeMultipleParamException();
        }

        ArrayList<Task> matchedKeys = new ArrayList<>();
        for (Task matchingTasks : tasks) {
            if (matchingTasks.getDescription().contains(input[1])) {
                matchedKeys.add(matchingTasks);
            }
        }
        ui.printMatchedTasks(matchedKeys, tasks);
    }

}
