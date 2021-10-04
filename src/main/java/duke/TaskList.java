package duke;

import java.time.LocalDate;

import java.util.ArrayList;

public class TaskList {

    protected Ui userInterface = new Ui();

    protected int DONE_PADDING = 1;

    protected int TODO_LENGTH_PADDING = 4;

    protected int TODO_SUBSTRING_PADDING = 5;

    protected int DEADLINE_BY_PADDING = 4;

    protected int DEADLINE_LENGTH_PADDING = 8;

    protected int DEADLINE_SUBSTRING_PADDING = 9;

    protected int DEADLINE_DESCRIPTION_PADDING = 9;

    protected int EVENT_DESCRIPTION_PADDING = 6;

    protected int EVENT_AT_PADDING = 4;

    protected int EVENT_LENGTH_PADDING = 5;

    protected int EVENT_SUBSTRING_PADDING = 6;

    protected int DELETE_PADDING = 1;


    public void markTaskAsDone(int taskNumberToMark, ArrayList<Task> tasks) throws IndexOutOfBoundsException {
        if ((taskNumberToMark - DONE_PADDING >= 0) && (tasks.get(taskNumberToMark - DONE_PADDING) != null)) {
            tasks.get(taskNumberToMark - DONE_PADDING).setAsDone();
            userInterface.printTaskAsMarked(taskNumberToMark, tasks, DONE_PADDING);
        } else {
            userInterface.printDoneInvalidNumberError();
        }
    }

    public void addTask(Task t, ArrayList<Task> tasks) {
        tasks.add(t);
    }

    public void addTodo(String line, ArrayList<Task> tasks) throws DukeMissingDescException {
        if (line.length() == TODO_LENGTH_PADDING || line.substring(TODO_SUBSTRING_PADDING).isBlank()) { //check that there is text after todo
            throw new DukeMissingDescException();
        }
        String todoDescription = line.substring(TODO_SUBSTRING_PADDING);

        this.addTask(new Todo(todoDescription), tasks);

        userInterface.printAddTodoMessage(todoDescription, tasks);
    }


    public void addDeadline(String line, ArrayList<Task> tasks) throws DukeMissingDescException, DukeMissingParamException {
        if (line.length() == DEADLINE_LENGTH_PADDING || line.substring(DEADLINE_SUBSTRING_PADDING).isBlank()) {
            throw new DukeMissingDescException();
        }

        int positionOfBy = line.indexOf("/by");

        if (positionOfBy == -1) { //missing by, throw exception
            throw new DukeMissingParamException();
        }

        int positionOfLastCharacter = line.length();

        String deadlineDescription = line.substring(DEADLINE_DESCRIPTION_PADDING, positionOfBy);

        LocalDate deadlineBy = LocalDate.parse(line.substring(positionOfBy + DEADLINE_BY_PADDING, positionOfLastCharacter)); //date of deadline
        this.addTask(new Deadline(deadlineDescription, deadlineBy), tasks);
        userInterface.printAddDeadlineMessage(deadlineDescription, deadlineBy, tasks);
    }

    public void addEvent(String line, ArrayList<Task> tasks) throws DukeMissingDescException, DukeMissingParamException {
        if (line.length() == EVENT_LENGTH_PADDING || line.substring(EVENT_SUBSTRING_PADDING).isBlank()) {
            throw new DukeMissingDescException();
        }

        int positionOfAt = line.indexOf("/at");

        if (positionOfAt == -1) { //missing at, throw exception
            throw new DukeMissingParamException();
        }

        int positionOfLastCharacter = line.length();

        String eventDescription = line.substring(EVENT_DESCRIPTION_PADDING, positionOfAt); //description of event

        LocalDate eventAt = LocalDate.parse(line.substring(positionOfAt + EVENT_AT_PADDING, positionOfLastCharacter)); //date of event

        this.addTask(new Event(eventDescription, eventAt), tasks);

        userInterface.printAddEventMessage(eventDescription, eventAt, tasks);
    }


    public void deleteTask(int taskNumberToRemove, ArrayList<Task> tasks) throws DukeMissingParamException, NumberFormatException, IndexOutOfBoundsException {
        if ((taskNumberToRemove - DELETE_PADDING >= 0) && (tasks.get(taskNumberToRemove - DELETE_PADDING) != null)) {
            String byWithColon = "by: ";
            String atWithColon = "at: ";
            String taskWordString = "";

            if (tasks.get(taskNumberToRemove - DELETE_PADDING).getType().equals("D")) {
                taskWordString = byWithColon;
            } else if (tasks.get(taskNumberToRemove - DELETE_PADDING).getType().equals("E")) {
                taskWordString = atWithColon;
            } else {
                taskWordString = "";
            }

            if (taskWordString.equals("")) {
                userInterface.printWithoutTaskWordString(taskNumberToRemove, DELETE_PADDING, tasks);

            } else {
                userInterface.printWithTaskWordString(taskNumberToRemove, DELETE_PADDING, taskWordString, tasks);
            }
        }
        tasks.remove(tasks.get(taskNumberToRemove - DELETE_PADDING));
    }

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
        userInterface.printTasksThatMatch(matchedKeys, tasks);

    }
}
