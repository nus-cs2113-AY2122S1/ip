package tan;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class TaskManager {
    private static List<Task> listOfTasks = new LinkedList<Task>();
    private static int totalNumberOfTask = 0;

    public TaskManager() {

    }

    /**
     * Returns the Task which is at
     * the index, null otherwise.
     *
     * @param indexTask index of the task to obtain.
     * @return Task at specified index.
     */
    private static Task getTask(int indexTask) {
        if (indexTask > listOfTasks.size() || indexTask < 0) {
            System.out.println("Task is out of range!");
            return null;
        }
        return listOfTasks.get(indexTask);
    }

    /**
     * Marks a certain task as done.
     *
     * @param indexTask The index of the task to be marked as done.
     */
    public static void markTaskAsDone(int indexTask) {
        if (indexTask > listOfTasks.size() || indexTask < 0) {
            System.out.println("No such task!");
            return;
        }
        Task currentTask = getTask(indexTask - 1);
        if (currentTask != null) {
            currentTask.markAsDone();
        }
    }

    /**
     * Takes the user input as a string
     * and decides what type of command it is and
     * executes its respective jobs.
     * If none of the inputs matches any of the command,
     * It tells the user to try again.
     *
     * @param userInput The entire input from the user.
     */
    public static void addTask(String userInput) {
        String typeOfTask = getTypeOfTask(userInput);
        Task curTask;
        switch (typeOfTask) {
        case "todo":
            curTask = getToDoTask(userInput);
            if (curTask == null) {
                return;
            }
            break;
        case "deadline":
            curTask = getDeadlineTask(userInput);
            if (curTask == null) {
                return;
            }
            break;
        case "event":
            curTask = getEventTask(userInput);
            if (curTask == null){
                return;
            }
            break;
        default:
            System.out.println("Unknown command, Try again.");
            return;
        }
        totalNumberOfTask += 1;
        System.out.println("You have added: " + curTask);
        System.out.println("Your current total number of task is: " + totalNumberOfTask);
    }

    private static Task getEventTask(String userInput) {
        try {
            String eventDesc = getDescriptionOfEvent(userInput);
            String eventTimeDate = getDateTimeOfEvent(userInput);
            Task curTask = new Event(eventDesc, eventTimeDate);
            listOfTasks.add(curTask);
            return curTask;
        } catch (DukeFormatExceptions d) {
            System.out.println(d);
        } catch (IndexOutOfBoundsException I) {
            System.out.println("Please check your formatting & input!");
        }
        return null;
    }

    private static Task getDeadlineTask(String userInput) {
        try {
            String deadlineDesc = getDescriptionOfDeadline(userInput);
            String deadlineDateTime = getDateTimeOfDeadline(userInput);
            Task curTask = new Deadline(deadlineDesc, deadlineDateTime);
            listOfTasks.add(curTask);
            return curTask;
        } catch (DukeFormatExceptions e) {
            System.out.println(e);
        } catch (IndexOutOfBoundsException x) {
            System.out.println("Please check your formatting & input!");
        }
        return null;
    }

    /**
     * This function takes in the users input
     * as a String and tries to create the ToDo
     * Task with it. If there is an issue
     * with the formatting, it will return NULL.
     *
     * @param userInput The user's input in String.
     * @return Returns the task else NULL.
     */
    private static Task getToDoTask(String userInput) {

        try {
            String todoDesc = getDescriptionOfToDo(userInput);
            Task curTask = new ToDo(todoDesc);
            listOfTasks.add(curTask);
            return curTask;
        } catch (DukeFormatExceptions e) {
            System.out.println(e);
        }
        return null;
    }


    /**
     * Takes in the whole user input as a string
     * when the user is adding an event and returns
     * the "at" date/time as a string. The function
     * uses the "/at" to find the date/time. Throws a
     * DukeFormatExceptions error if its unable to find "/at".
     *
     * @param x The whole user input as a String.
     * @return the date/time of the input.
     */
    private static String getDateTimeOfEvent(String x) throws DukeFormatExceptions, IndexOutOfBoundsException {
        //Checks if user has used the /at... format.
        if (x.toLowerCase().contains("/at")) {
            int indexOfSlash = x.indexOf("/at");
            //+3 to the index as we don't want to capture "/at".
            String dateTime = x.substring(indexOfSlash + 3);
            return dateTime.trim();
        }
        throw new DukeFormatExceptions("Code could not find '/at'");
    }

    /**
     * Takes in the whole user input as a string
     * when the user is adding an event and returns
     * the description as a string. The function
     * uses the "/at" to find the description. Throws a
     * DukeFormatExceptions error if its unable to find "/at".
     *
     * @param x The whole user input as a String.
     * @return the date/time of the input.
     */
    private static String getDescriptionOfEvent(String x) throws DukeFormatExceptions, IndexOutOfBoundsException {
        //Checks if user has used the /by... format.
        if (x.toLowerCase().contains("/at")) {
            int indexOfFirstSpace = x.indexOf(" ");
            int indexOfSlash = x.indexOf("/at");
            String description = x.substring(indexOfFirstSpace + 1, indexOfSlash - 1);
            return description.trim();
        }
        throw new DukeFormatExceptions("Code could not find '/at'");
    }

    /**
     * Takes in the whole user input as a string
     * when the user is adding a deadline and returns
     * the deadline date/time as a string. The function
     * uses the "/by" to find the date/time. Throws a
     * DukeFormatExceptions error if its unable to find "/by".
     *
     * @param x The whole user input as a String.
     * @return the date/time of the input
     */
    private static String getDateTimeOfDeadline(String x) throws DukeFormatExceptions, IndexOutOfBoundsException {
        //Checks if user has used the /by... format.
        if (x.toLowerCase().contains("/by")) {
            int indexOfSlash = x.indexOf("/by");
            //+3 to the index as we don't want to capture "/by".
            String dateTime = x.substring(indexOfSlash + 3);
            return dateTime.trim();
        }
        throw new DukeFormatExceptions("Code could not find '/by'");
    }

    /**
     * Takes in the whole user input as a string
     * when the user is adding a deadline and returns
     * the description as a string. The function
     * uses the "/by" to find the description. Throws a
     * * DukeFormatExceptions error if its unable to find "/by".
     *
     * @param x The whole user input as a String.
     * @return the date/time of the input or null if "/by" is not found.
     */
    private static String getDescriptionOfDeadline(String x) throws DukeFormatExceptions, IndexOutOfBoundsException {
        //Checks if user has used the /by... format.
        if (x.toLowerCase().contains("/by")) {
            int indexOfFirstSpace = x.indexOf(" ");
            int indexOfSlash = x.indexOf("/by");
            String description = x.substring(indexOfFirstSpace + 1, indexOfSlash - 1);
            return description.trim();
        }
        throw new DukeFormatExceptions("Code could not find '/by'");
    }

    /**
     * Assumes the type of tasks is the
     * first word in the string and returns
     * that word. If unable to split, returns null.
     *
     * @param x The whole string of user input.
     * @return The first word of that String, else null.
     */
    private static String getTypeOfTask(String x) {
        try {
            String[] inputs = x.split(" ");
            return inputs[0].toLowerCase();
        } catch (PatternSyntaxException p) {
            System.out.println("Unable to the input properly. Please try again.");
        }
        return null;
    }

    /**
     * Returns the original string with the first
     * word excluded. It finds the first space char
     * and assumes anything before it is the first word.
     * This will throw a DukeFormatExceptions error
     * if it is unable to find a space character in the string.
     *
     * @param x The whole user input as a String.
     * @return The remaining String excluding the 1st word.
     */
    private static String getDescriptionOfToDo(String x) throws DukeFormatExceptions, IndexOutOfBoundsException {
        //Gets the index of the first space.
        int indexOfFirstSpace = x.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            throw new DukeFormatExceptions("Parameters are empty! Please try again.");
        }
        String description = x.substring(indexOfFirstSpace + 1);
        return description;
    }

    /**
     * Prints all the task & their current status
     * in the list. Or informs the user if the list
     * is empty.
     */
    public static void printList() {
        if (listOfTasks.size() == 0) {
            System.out.println("List is empty!");
            return;
        }
        System.out.println("Your list of tasks contains:");
        try {
            for (int i = 0; i < listOfTasks.size(); i++) {
                Task currentTask = listOfTasks.get(i);
                System.out.println(currentTask);
            }
        } catch (IndexOutOfBoundsException i){
            System.out.println("Error in printing task! Contact Admin =(");
        }

    }
}
