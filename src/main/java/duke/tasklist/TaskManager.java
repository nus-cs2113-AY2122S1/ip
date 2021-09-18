package duke.tasklist;

import duke.Time;
import duke.generalmethods.GeneralMethods;
import duke.exceptions.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskManager extends Ui {

    public static final String INCORRECT_TASK_COMMAND = "Please specify a valid task to be added!";
    public static final String INCORRECT_DEADLINE_COMMAND = "Please specify a valid task/deadline of completion!";
    public static final String INCORRECT_EVENT_COMMAND = "Please specify a valid event/time of the event!";
    public static final String INCORRECT_DONE_COMMAND = "Please specify the number of the task to be marked as done!";
    public static final String INCORRECT_DELETE_COMMAND = "Please specify a valid number of the task to be deleted!";
    public static final String INVALID_INDEX = "No number or Invalid number specified! Please specify the number on the list of the task you have completed!";

    public TaskManager() {

    }

    public static void addTaskAsEvent(ArrayList<Task> taskList, String userInput, Boolean isPrintingPreviousList) throws DukeException {
        if (userInput.contains("/at")) {
            Task taskAdded = new Event(userInput);
            if (!taskAdded.toString().equals("") && !taskAdded.getDeadline().equals("") && Time.isDateValid(taskAdded.getDeadline())) {
                taskList.add(taskAdded);
                if(!isPrintingPreviousList) {
                    printTaskAddedConfirmation(taskAdded);
                }
            } else {
                throw new DukeException(INCORRECT_EVENT_COMMAND);
            }
        } else {
            throw new DukeException(INCORRECT_EVENT_COMMAND);
        }
    }

    public static void addTaskAsDeadline(ArrayList<Task> taskList, String userInput, Boolean isPrintingPreviousList) throws DukeException {
        if (userInput.contains("/by")) {
            Task taskAdded = new Deadline(userInput);
            if (!taskAdded.toString().equals("") && !taskAdded.getDeadline().equals("") && Time.isDateValid(taskAdded.getDeadline())) {
                taskList.add(taskAdded);
                if(!isPrintingPreviousList) {
                    printTaskAddedConfirmation(taskAdded);
                }
            } else {
                throw new DukeException(INCORRECT_DEADLINE_COMMAND);
            }
        } else {
            throw new DukeException(INCORRECT_DEADLINE_COMMAND);
        }
    }

    public static void addTaskAsToDo(ArrayList<Task> taskList, String userInput, Boolean isPrintingPreviousList) throws DukeException {
        String task = userInput.replace("to do ", "").trim();
        Task taskAdded = new Todo(task);
        if (!task.equals("")) {
            taskList.add(taskAdded);
            if(!isPrintingPreviousList) {
                printTaskAddedConfirmation(taskAdded);
            }
        } else {
            throw new DukeException(INCORRECT_TASK_COMMAND);
        }
    }

    public static void deleteTaskFromToDo(ArrayList<Task> taskList, String userInput) throws DukeException {
        int wordIndex = 0;
        boolean numberExists = false;
        String[] splitTask = userInput.replaceAll("[\\p{Alpha}, [\\p{Punct}&&[^-]]+]", " ").trim().split(" ");
        for (String word : splitTask) {
            if (GeneralMethods.isValidNumber(word)) {
                numberExists = true;
                int taskNumber = (Integer.parseInt(splitTask[wordIndex])) - 1;
                try {
                    printTaskDeletedConfirmation(taskList.get(taskNumber), taskNumber);
                    taskList.remove(taskNumber);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(INCORRECT_DELETE_COMMAND);
                }
            }
            wordIndex++;
        }
        if (!numberExists) {
            throw new DukeException(INCORRECT_DELETE_COMMAND);
        }
    }

    public static void markTaskAsDone(ArrayList<Task> taskList, String userInput) throws DukeException {
        int wordIndex = 0;
        boolean numberExists = false;
        String[] splitTask = userInput.replaceAll("[\\p{Alpha}, [\\p{Punct}&&[^-]]+]", " ").trim().split(" ");
        for (String word : splitTask) {
            if (GeneralMethods.isValidNumber(word)) {
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
