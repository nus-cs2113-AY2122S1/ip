package tan;

import java.util.LinkedList;
import java.util.List;

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
        Task curTask = null;
        switch (typeOfTask) {
        case "todo":
            String todoDesc = getDescriptionOfToDo(userInput);
            if (todoDesc == null) {
                //if user did not key in any description.
                System.out.println("Please enter a valid description!");
                return;
            }
            curTask = new ToDo(todoDesc);
            listOfTasks.add(curTask);
            break;
        case "deadline":
            String deadlineDesc = getDescriptionOfDeadline(userInput);
            String deadlineDateTime = getDateTimeOfDeadline(userInput);
            curTask = new Deadline(deadlineDesc, deadlineDateTime);
            listOfTasks.add(curTask);
            break;
        case "event":
            String eventDesc = getDescriptionOfEvent(userInput);
            String eventTimeDate = getDateTimeOfEvent(userInput);
            curTask = new Event(eventDesc, eventTimeDate);
            listOfTasks.add(curTask);
            break;
        default:
            System.out.println("Unknown command, Try again.");
            return;
        }
        totalNumberOfTask += 1;
        System.out.println("You have added: " + curTask);
        System.out.println("Your current total number of task is: " + totalNumberOfTask);
    }


    /**
     * Takes in the whole user input as a string
     * when the user is adding an event and returns
     * the "at" date/time as a string. The function
     * uses the "/at" to find the date/time. Returns NULL
     * if its unable to find "/at"
     *
     * @param x The whole user input as a String.
     * @return the date/time of the input or null if "/at" is not found.
     */
    private static String getDateTimeOfEvent(String x) {
        //Checks if user has used the /at... format.
        if (x.toLowerCase().contains("/at")) {
            int indexOfSlash = x.indexOf("/at");
            //+3 to the index as we don't want to capture "/at".
            String dateTime = x.substring(indexOfSlash + 3);
            return dateTime.trim();
        }
        return null;
    }

    /**
     * Takes in the whole user input as a string
     * when the user is adding an event and returns
     * the description as a string. The function
     * uses the "/at" to find the description. Returns NULL
     * if its unable to find "/at"
     *
     * @param x The whole user input as a String.
     * @return the date/time of the input or null if "/at" is not found.
     */
    private static String getDescriptionOfEvent(String x) {
        //Checks if user has used the /by... format.
        if (x.toLowerCase().contains("/at")) {
            int indexOfFirstSpace = x.indexOf(" ");
            int indexOfSlash = x.indexOf("/at");
            String description = x.substring(indexOfFirstSpace + 1, indexOfSlash - 1);
            return description.trim();
        }
        return null;
    }

    /**
     * Takes in the whole user input as a string
     * when the user is adding a deadline and returns
     * the deadline date/time as a string. The function
     * uses the "/by" to find the date/time. Returns NULL
     * if its unable to find "/by"
     *
     * @param x The whole user input as a String.
     * @return the date/time of the input or null if "/by" is not found.
     */
    private static String getDateTimeOfDeadline(String x) {
        //Checks if user has used the /by... format.
        if (x.toLowerCase().contains("/by")) {
            int indexOfSlash = x.indexOf("/by");
            //+3 to the index as we don't want to capture "/by".
            String dateTime = x.substring(indexOfSlash + 3);
            return dateTime.trim();
        }
        return null;
    }

    /**
     * Takes in the whole user input as a string
     * when the user is adding a deadline and returns
     * the description as a string. The function
     * uses the "/by" to find the description. Returns NULL
     * if its unable to find "/by"
     *
     * @param x The whole user input as a String.
     * @return the date/time of the input or null if "/by" is not found.
     */
    private static String getDescriptionOfDeadline(String x) {
        //Checks if user has used the /by... format.
        if (x.toLowerCase().contains("/by")) {
            int indexOfFirstSpace = x.indexOf(" ");
            int indexOfSlash = x.indexOf("/by");
            String description = x.substring(indexOfFirstSpace + 1, indexOfSlash - 1);
            return description.trim();
        }
        return null;
    }

    /**
     * Assumes the type of tasks is the
     * first word in the string and returns
     * that word.
     *
     * @param x The whole string of user input.
     * @return The first word of that String.
     */
    private static String getTypeOfTask(String x) {
        String[] inputs = x.split(" ");
        return inputs[0].toLowerCase();
    }

    /**
     * Returns the original string with the first
     * word excluded. It finds the first space char
     * and assumes anything before it is the first word.
     * This will return NULL if it is unable to find
     * a space character in the string.
     *
     * @param x The whole user input as a String.
     * @return The remaining String excluding the 1st word.
     */
    private static String getDescriptionOfToDo(String x) {
        //Gets the index of the first space.
        int indexOfFirstSpace = x.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            return null;
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
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task currentTask = listOfTasks.get(i);
            System.out.println(currentTask);
        }
    }
}
