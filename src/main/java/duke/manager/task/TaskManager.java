package duke.manager.task;

import duke.logic.UserData;
import duke.logic.UserInterface;
import duke.manager.command.MissingCommandArgumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager extends UserInterface {

    private ArrayList<Task> tasks = new ArrayList<Task>();
    private final int TASK_INDEX = 1;
    private final int TASK_STATUS_INDEX = 4;
    private final int TASK_DESCRIPTION_INDEX = 7;

    public String saveTasksAsString() {
        if (tasks.isEmpty()) {
            return "";
        }
        String taskListAsString = "";
        String taskLabel = "";
        int isDoneAsInteger;

        for (Task t : tasks) {
            isDoneAsInteger = t.isDone() ? 1 : 0;
            taskLabel = convertTaskTypeToString(t);
            taskListAsString = taskListAsString + "[" + taskLabel + "]["
                    + isDoneAsInteger + "] " + t.getTaskDescription()
                    + convertTaskArgumentToString(t) + System.lineSeparator();
        }

        return taskListAsString;
    }

    public String convertTaskTypeToString(Task task) {
        String output = "";
        if (task instanceof ToDo) {
            output = "T";
        } else if (task instanceof Event) {
            output = "E";
        } else if (task instanceof Deadline) {
            output = "D";
        }
        return output;
    }

    public String convertTaskArgumentToString(Task task) {
        String output = "";
        if (task instanceof Event) {
            output = " : " + ((Event) task).getAt();
        } else if (task instanceof Deadline) {
            output = " : " + ((Deadline) task).getBy();
        }
        return output;
    }

    public void preloadTasks() throws FileNotFoundException {
        File dataFile = new File(UserData.getFilePath());

        // short circuits preload if file is empty
        if (dataFile.length() == 0) {
            return;
        }

        Scanner fileScanner = new Scanner(dataFile);
        String currentLine;
        String taskType;
        boolean taskStatus;
        while (fileScanner.hasNext()) {
            currentLine = fileScanner.nextLine();

            // if any empty lines, skip to next iteration of the while loop
            if (currentLine.equals("")) {
                continue;
            }
            taskType = String.valueOf(currentLine.charAt(TASK_INDEX));
            taskStatus = String.valueOf(currentLine.charAt(TASK_STATUS_INDEX)).equals("1");
            String restOfLine[] = currentLine.substring(TASK_DESCRIPTION_INDEX).split(" : ", 2);
            loadCurrentLineTask(taskType, taskStatus, restOfLine);
        }
    }

    public void loadCurrentLineTask(String taskType, boolean taskStatus,
                                    String[] restOfLine) {
        if (taskType.equals("T")) {
            ToDo newToDo = new ToDo(restOfLine[0]);
            if (taskStatus) {
                newToDo.setDone();
            }
            tasks.add(newToDo);
        } else if (taskType.equals("E")) {
            Event newEvent = new Event(restOfLine[0], restOfLine[1]);
            if (taskStatus) {
                newEvent.setDone();
            }
            tasks.add(newEvent);
        } else if (taskType.equals("D")) {
            Deadline newDeadline = new Deadline(restOfLine[0], restOfLine[1]);
            if (taskStatus) {
                newDeadline.setDone();
            }
            tasks.add(newDeadline);
        } else {
            System.out.println("Failed to a line from saved data...");
        }
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            echo("  List is empty!");
        } else {
            System.out.println(HORIZONTAL_BAR);
            // Traverse down the ArrayList and prints out each task
            for (int i = 0; i < tasks.size(); i++) {
                int currentIndexInOnesIndexing = i + 1;
                System.out.println("  " + currentIndexInOnesIndexing + ". "
                        + tasks.get(i).getTaskDescriptionWithStatus());
            }
            System.out.println(HORIZONTAL_BAR);
        }
    }

    /**
     * Marks task in array as done and prints echo message depending on validity of task no.
     */
    public void markTaskAsDone(String input) throws InvalidTaskNumberException, NumberFormatException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.trim(), 10);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException();
        }
        boolean taskNumberInRange = (taskNumber <= tasks.size()) && (taskNumber >= 1);

        if (!taskNumberInRange) {
            throw new InvalidTaskNumberException();
        }

        tasks.get(taskNumber - 1).setDone();
        echo("Task " + taskNumber + ": " + tasks.get(taskNumber - 1).getTaskDescription()
                + System.lineSeparator() + "  Marked as done!");
    }

    public void addToDo(String description) {
        tasks.add(new ToDo(description));
        echo("  You have successfully added the task:" + System.lineSeparator()
                + "    " + tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus() + System.lineSeparator()
                + "  You now have " + tasks.size() + " tasks in your list!");
    }

    public void addEvent(String description, String at) {
        tasks.add(new Event(description, at));
        echo("  You have successfully added the task:" + System.lineSeparator()
                + "    " + tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus() + System.lineSeparator()
                + "  You now have " + tasks.size() + " tasks in your list!");
    }

    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
        echo("  You have successfully added the task:" + System.lineSeparator()
                + "    " + tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus() + System.lineSeparator()
                + "  You now have " + tasks.size() + " tasks in your list!");
    }

    public void checkInputThenAddToDo(String argument) throws MissingCommandArgumentException {
        if (argument.equals("none")) {
            throw new MissingCommandArgumentException();
        }
        addToDo(argument);
    }

    public void checkInputThenAddEvent(String[] arguments) throws MissingCommandArgumentException {
        if (arguments.length > 1) {
            // arguments[0] equals description, arguments[1] equals "at"
            addEvent(arguments[0].trim(), arguments[1].trim());
        } else {
            // no arguments after event
            if (arguments[0].equals("none")) {
                throw new MissingCommandArgumentException();
            }
            // adds the description, no user input for "at"
            addEvent(arguments[0].trim(), "");
        }
    }

    public void checkInputThenAddDeadline(String[] arguments) throws MissingCommandArgumentException {

        if (arguments.length > 1) {
            // arguments[0] equals description, arguments[1] equals "by"
            addDeadline(arguments[0].trim(), arguments[1].trim());
        } else {
            // no arguments after deadline
            if (arguments[0].equals("none")) {
                throw new MissingCommandArgumentException();
            }
            // adds the description, no user input for "by"
            addDeadline(arguments[0].trim(), "");
        }
    }

    public void printMessageForTaskNumberOutOfRange() {
        echo("  Invalid Task number!" + System.lineSeparator()
                + "  Please try again with a Task number within range! :)");
    }

    public void printMessageForTaskNumberNonInteger() {
        echo("  Invalid Task number!" + System.lineSeparator()
                + "  Please enter a valid integer! :)");
    }

    public void printMessageForMissingTaskDescription(String taskType) {
        echo("  I'm sorry... For a <" + taskType + "> you must include a description!"
                + System.lineSeparator() + "  Please try again with a valid input! :)");
    }

    public void printMessageForInvalidInput() {
        echo("  Invalid Input..." + System.lineSeparator()
                + "  Please enter a valid input!");
    }
}
