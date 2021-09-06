package duke;

import duke.actions.Deadline;
import duke.actions.Event;
import duke.actions.Task;
import duke.actions.Todo;
import duke.exceptions.DukeException;

import java.util.ArrayList;

public class TaskManager extends GeneralMethods {

    public static final String INCORRECT_TASK_COMMAND = "Please specify a task to be added!";
    public static final String INCORRECT_DEADLINE_COMMAND = "Please specify a task/deadline of completion!";
    public static final String INCORRECT_EVENT_COMMAND = "Please specify an event/time of the event!";
    public static final String INCORRECT_DONE_COMMAND = "Please specify the number of the task to be marked as done!";
    public static final String INVALID_INDEX = "No number or Invalid number specified! Please specify the number on the list of the task you have completed!";

    public TaskManager() {

    }

    public static void addTaskAsEvent(ArrayList<Task> taskList, String userInput) throws DukeException {
        if (userInput.contains("/at")) {
            Task taskAdded = new Event(userInput);
            if (!taskAdded.toString().equals("") && !taskAdded.getDeadline().equals("")) {
                taskList.add(taskAdded);
                printTaskAddedConfirmation(taskAdded);
            } else {
                throw new DukeException(INCORRECT_EVENT_COMMAND);
            }
        } else {
            throw new DukeException(INCORRECT_EVENT_COMMAND);
        }
    }

    public static void addTaskAsDeadline(ArrayList<Task> taskList, String userInput) throws DukeException {
        if (userInput.contains("/by")) {
            Task taskAdded = new Deadline(userInput);
            if (!taskAdded.toString().equals("") && !taskAdded.getDeadline().equals("")) {
                taskList.add(taskAdded);
                printTaskAddedConfirmation(taskAdded);
            } else {
                throw new DukeException(INCORRECT_DEADLINE_COMMAND);
            }
        } else {
            throw new DukeException(INCORRECT_DEADLINE_COMMAND);
        }
    }

    public static void addTaskAsToDo(ArrayList<Task> taskList, String userInput) throws DukeException {
        String task = userInput.replace("to do ", "").trim();
        Task taskAdded = new Todo(task);
        if (!task.equals("")) {
            taskList.add(taskAdded);
            printTaskAddedConfirmation(taskAdded);
        } else {
            throw new DukeException(INCORRECT_TASK_COMMAND);
        }
    }

    public static void markTaskAsDone(ArrayList<Task> taskList, String userInput) throws DukeException {
        int wordIndex = 0;
        boolean numberExists = false;
        String[] splitTask = userInput.replaceAll("[\\p{Alpha}, [\\p{Punct}&&[^-]]+]", " ").trim().split(" ");
        for (String word : splitTask) {
            if (isValidNumber(word)) {
                numberExists = true;
                int taskNumber = (Integer.parseInt(splitTask[wordIndex])) - 1;
                try {
                    printTaskMarkAsDone(taskList, taskNumber);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(INVALID_INDEX);
                }
            }
            wordIndex++;
        }
        if (!numberExists) {
            throw new DukeException(INCORRECT_DONE_COMMAND);
        }
    }
}
