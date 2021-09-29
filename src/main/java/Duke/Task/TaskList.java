package Duke.Task;

import Duke.Exception.DukeException;
import Duke.Information;
import Duke.Ui;

import java.util.ArrayList;

/**
 * The main Class in which the different commands from Parser are executed from
 */


public class TaskList {
    public static Information input = new Information();
    private static Ui message = new Ui();

    /**
     * Prints the list of all current tasks
     */
    public static void printList() {
        for (int i = 0; i < input.List.size(); i++) {
            System.out.println((i + 1) + ".[" + input.List.get(i).getTaskIcon() + "]" + "[" + input.List.get(i).getStatusIcon() + "] " + input.List.get(i).getDescription());
        }
        message.printLineBreak();
    }

    /**
     * Marks when the task is done
     *
     * @param instructionTask Contains the details of the command given by the user
     * @throws NumberFormatException Throws an error if the selected task does not exist
     */
    public static void thisDone(String instructionTask) {
        try {
            int whichTask = Integer.parseInt(instructionTask.replaceAll("[\\D]", ""));
            if (whichTask <= 0) {
                message.printListError();
                message.printLineBreak();
            } else if (whichTask > input.List.size()) {
                message.printListError();
                message.printLineBreak();
            } else {
                input.List.get(whichTask - 1).markAsDone();
                System.out.println("YAY! That task has been marked as complete");
                message.printLineBreak();
            }
        } catch (NumberFormatException e) {
            message.printListError();
            message.printLineBreak();
        }
    }

    /**
     * Adds a ToDo task by the user
     *
     * @param instructionTask Contains the details of the command given by the user
     */
    public static void addToDo(String instructionTask) {
        try {
            if (instructionTask.isEmpty() || instructionTask.equals(" ")) {
                throw new DukeException();
            }
            input.List.add(new ToDos(instructionTask));
            System.out.println("Nice! The task has been added to your todo list");
            message.printLineBreak();
        } catch (DukeException e) {
            message.printEmptyDescription();
            message.printLineBreak();
        }
    }

    /**
     * Adds a task with a deadline to the list
     *
     * @param instructionTask Contains the details of the command given by the user
     * @throws  NumberFormatException Throws an error when the deadline contains alphabets
     * @throws  IndexOutOfBoundsException Throws an error when the deadline is not given in the specified format
     */
    public static void addDeadline(String instructionTask) {
        try {
            if (!instructionTask.contains("/by")) {
                throw new DukeException();
            }
            int indexOfDeadline = instructionTask.indexOf("/by");
            String theTask = instructionTask.substring(0, indexOfDeadline - 1);
            String theDeadline = instructionTask.substring(indexOfDeadline);
            input.List.add(new Deadline(theTask, theDeadline));
            System.out.println("Nice! The task has been added to your deadlines");
            message.printLineBreak();
        } catch (DukeException | NumberFormatException | IndexOutOfBoundsException e) {
            message.printMissingDeadline();
            message.printLineBreak();
        }
    }

    /**
     * Adds an event task to the list
     *
     * @param instructionTask Contains the details of the command given by the user
     */
    public static void addEvent(String instructionTask) {
        try {
            if (!instructionTask.contains("/at")) {
                throw new DukeException();
            }
            int indexOfEvent = instructionTask.indexOf("/at");
            String theTask2 = instructionTask.substring(0, indexOfEvent - 1);
            String theEvent = instructionTask.substring(indexOfEvent);
            input.List.add(new Events(theTask2, theEvent));
            System.out.println("Nice! The task has been added to your events list");
            message.printLineBreak();
        } catch (DukeException e) {
            message.printMissingEvent();
            message.printLineBreak();
        }
    }

    /**
     * Deletes a task from the list
     *
     * @param instructionTask Contains the details of the command given by the user
     * @throws  NumberFormatException Throws an error when alphabets are input instead of a number
     * @throws  IndexOutOfBoundsException Throws an error when a task that does not exist is chosen
     */
    public static void deleteTask(String instructionTask) {
        try {
            if (instructionTask.isEmpty() || instructionTask.equals(" ")) {
                throw new DukeException();
            }
            int number = Integer.parseInt(instructionTask);
            input.List.remove(number - 1);
            System.out.println("Ok! The task has been deleted from your list");
            message.printLineBreak();
        } catch (DukeException | NumberFormatException | IndexOutOfBoundsException e) {
            message.printListError();
            message.printLineBreak();
        }
    }

    /**
     * Finds any task that contains the keyword given by user
     *
     * @param word Contains the keyword
     */
    public static void findTask(String word) {
        ArrayList<Task> matchingWordList = new ArrayList<>();
        try {
            if (word.isEmpty() || word.equals(" ")) {
                throw new DukeException();
            }
            for (int i = 0; i < input.List.size(); i++) {
                if (input.List.get(i).getDescription().contains(word)) {
                    matchingWordList.add(input.List.get(i));
                }
            }
            if (!matchingWordList.isEmpty()) {
                System.out.println("These are the matching tasks in your list: ");
                for (int i = 0; i < matchingWordList.size(); i++) {
                    System.out.println((i + 1) + ".[" + matchingWordList.get(i).getTaskIcon() + "]" + "[" + matchingWordList.get(i).getStatusIcon() + "] " + matchingWordList.get(i).getDescription());
                }
                message.printLineBreak();
            } else {
                System.out.println("There does not seem to be a task like that in your list :(");
                }
            } catch (DukeException e) {
            message.printMissingKeyword();
            message.printLineBreak();
        }
    }
}
