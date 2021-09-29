package duke;

import duke.task.Task;
import duke.task.ToDo;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    private static final ArrayList<Task> userLists = new ArrayList<>();
    private static int numOfTasks = 0;

    /**
     * Calls different methods to store different types of tasks depending on the user command.
     *
     * @param userCommand Command denoting the type of task to be stored.
     * @param userInput Full string of the user input.
     * @param isPrint Flag to determine whether to print the message after task has been added to the list.
     */
    public static void taskHandler(String userCommand, String userInput, boolean isPrint) {
        switch (userCommand) {
        case "event":
            addEvent(userInput, isPrint);
            break;
        case "deadline":
            addDeadline(userInput, isPrint);
            break;
        default:
            addTodo(userInput, isPrint);
            break;
        }
    }

    /**
     * Marks the chosen task as complete.
     *
     * @param chosenIndex Index of task to be marked as complete.
     * @param isPrint Flag to determine whether to print the message after task has been marked as done.
     */
    public static void completeTask(int chosenIndex, boolean isPrint) {
        try {
            int doneIndex = chosenIndex - 1;
            String userDoneEntry;
            userLists.get(doneIndex).markAsDone();
            userDoneEntry = userLists.get(doneIndex).toString();
            if (isPrint) {
                UI.printDone(userDoneEntry);
            }
            Storage.saveFile(userLists, numOfTasks);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("\tCouldn't understand your instructions. Please enter a valid index");
        }
    }

    /**
     * Deletes the chosen task from the task list.
     *
     * @param chosenIndex Index of task to be deleted.
     */
    public static void deleteTask(String chosenIndex) {
        try {
            int deleteIndex = Integer.parseInt(chosenIndex) - 1;
            String chosenDeleteEntry = userLists.get(deleteIndex).toString();
            UI.printDelete(numOfTasks, chosenDeleteEntry);
            userLists.remove(deleteIndex);
            numOfTasks--;
            Storage.saveFile(userLists, numOfTasks);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("\tCouldn't understand your instructions. Please enter a valid index to delete");
        }
    }

    /**
     * Takes the user input and stores the contents as a to-do task.
     * @param userLineInput Full string of the user input to be stored as a to-do task.
     * @param isPrint Flag to determine whether to print the message after storing the task.
     */
    public static void addTodo(String userLineInput, boolean isPrint) {
        try {
            String todoDescription = userLineInput.substring(5);
            Task newToDo = new ToDo(todoDescription);
            userLists.add(numOfTasks, newToDo);
            numOfTasks++;
            if (isPrint) {
                UI.printAdd(numOfTasks, newToDo);
            }
            Storage.saveFile(userLists, numOfTasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("\tHey, please check your inputs again.");
        }
    }

    /**
     * Takes the user input and stores the contents as an event task.
     *
     * @param userLineInput Full string of the user input to be stored as an event task.
     * @param isPrint Flag to determine whether to print the message after storing the task.
     */
    public static void addEvent(String userLineInput, boolean isPrint) {
        try {
            Task newEvent = Parser.getEventTask(userLineInput);
            userLists.add(numOfTasks, newEvent);
            numOfTasks++;
            if (isPrint) {
                UI.printAdd(numOfTasks, newEvent);
            }
            Storage.saveFile(userLists, numOfTasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("\tThere's something wrong with you input. Please try again.");
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Takes the user input and stores the contents as a deadline task.
     *
     * @param userLineInput Full string of the user input to be stored as a deadline task.
     * @param isPrint Flag to determine whether to print the message after storing the task.
     */
    public static void addDeadline(String userLineInput, boolean isPrint) {
        try {
            Task newDeadline = Parser.getDeadlineTask(userLineInput);
            userLists.add(numOfTasks, newDeadline);
            numOfTasks++;
            if (isPrint) {
                UI.printAdd(numOfTasks, newDeadline);
            }
            Storage.saveFile(userLists, numOfTasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("\tHey, please check your inputs again." +
                    System.lineSeparator() + "\tYou should add a /b before your event date");
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the current task list and prints the task list.
     */
    public static void getList() {
        UI.printLineSpacer();
        if (numOfTasks == 0) {
            System.out.println("\tList is empty!");
        } else {
            System.out.println("\tHere's your list of tasks:");
            for (int i = 0; i < numOfTasks; i++) {
                System.out.println("\t" + (i + 1) + "." + userLists.get(i).toString());
            }
        }
    }

    /**
     * Returns the done status of the chosen task.
     *
     * @param userList List of all tasks stored by the user.
     * @param numTask Index of the chosen task.
     * @return "1" if the task is marked done, if not returns "0"
     */
    public static String getDoneStatus(ArrayList<Task> userList, int numTask) {
        String currentStatusIcon = userList.get(numTask).getStatusIcon();
        if (currentStatusIcon.equals("[X] ")){
            return "1";
        }
        return "0";
    }

    /**
     * Takes the keyword input by the user and searches the task list for entries that contains the keyword
     *
     * @param userInput Full string of the search input containing the keyword to be found.
     */
    public static void findEntry(String[] userInput) {
        String searchEntry = userInput[1];
        List<Task> matches =userLists.stream()
                .filter(t -> t.getDescription().contains(searchEntry))
                .collect(Collectors.toList());
        UI.printFoundEntries(matches);
    }
}
