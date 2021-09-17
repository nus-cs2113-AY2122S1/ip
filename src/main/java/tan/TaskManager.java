package tan;

import tan.exceptions.DukeFormatExceptions;
import tan.tasktype.Deadline;
import tan.tasktype.Event;
import tan.tasktype.Task;
import tan.tasktype.ToDo;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class TaskManager {

    protected static List<Task> listOfTasks = new LinkedList<>();
    private static int totalNumberOfTask = 0;

    public static void loadDataIntoList(List<Task> listOfDatas) {
        listOfTasks.addAll(listOfDatas);
        totalNumberOfTask = listOfTasks.size();
    }

    /**
     * Returns 0 if the file successfully saved, -1 otherwise.
     * Saves the current list into the data file taskData.csv.
     * It calls the function saveCurrentList and passes it
     * the current list of task to be saved. It will also
     * inform the user if any error occurs.
     *
     * @return Returns 0 if the file was successfully saved, else -1.
     */
    protected static int saveCurrentList() {
        try {
            DataManager.saveCurrentList(listOfTasks);
        } catch (IOException e) {
            System.out.println("Error in writing/opening file! Please try again.");
            return -1;
        } catch (Exception e) {
            System.out.println("Generic Error in saving file. Error is:" + e);
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    /**
     * Returns the Task which is at
     * the index specified by indexTask, null otherwise.
     *
     * @param indexTask Index of the task to obtain.
     * @return Task at specified index.
     */
    private static Task getTask(int indexTask) {
        if (isOutOfRange(indexTask)) {
            System.out.println("Task is out of range!");
            return null;
        }
        return listOfTasks.get(indexTask);
    }

    /**
     * Marks a certain task as done.
     * Takes in the index of the task
     * and checks if it is within range.
     * If it is, mark it as done. If not,
     * prompt user and return from function.
     *
     * @param indexTask The index of the task to be marked as done.
     */
    public static void markTaskAsDone(int indexTask) {
        if (isOutOfRange(indexTask)) {
            System.out.println("No such task!");
            return;
        }
        Task currentTask = getTask(indexTask - 1);
        if (currentTask != null) {
            currentTask.markAsDone();
        }
    }

    /**
     * Deletes a task by checking if the index is
     * within range if it is, delete. If not,
     * prompts user to key in again.
     *
     * @param indexTask The index of the task to be marked as done.
     */
    public static void deleteTask(int indexTask) {
        if (isOutOfRange(indexTask)) {
            System.out.println("No such task!");
            return;
        }
        Task toDelete = getTask(indexTask - 1);
        System.out.println("OooOOHHhh Weeee. I have removed this:");
        System.out.println(toDelete);
        listOfTasks.remove(indexTask - 1);
        totalNumberOfTask -= 1;
        System.out.println("Number of tasks left: " + totalNumberOfTask);
    }

    /**
     * Returns true if index provided is out of the range
     * for the current number of stored task. Else false.
     *
     * @param index The index of the task.
     * @return True if it is out of range, False otherwise.
     */
    public static boolean isOutOfRange(int index) {
        return (index > listOfTasks.size() || index < 0);
    }

    /**
     * Adds a new task by taking the user input as a string
     * and decides what type of task it is and
     * executes its respective jobs. If none of the task type
     * matches, prompts the user to try again.
     *
     * @param userInput The entire input from the user as a String.
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
            if (curTask == null) {
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


    /**
     * Adds an Event Task to the list and returns the same task, else
     * returns null.
     * This function takes in the users input and tries to get the
     * description and the event date, then creates a new
     * event task adds it to the current list and returns the task.
     *
     * @param userInput - The user input in String.
     * @return The Event task created, else null.
     */
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

    /**
     * Adds a Deadline Task to the list and returns the same task, else
     * returns null.
     * This function takes in the users input and tries to get the
     * description and the deadline date, then creates a new
     * deadline task adds it to the current list and returns the task.
     *
     * @param userInput - The user input in String.
     * @return The Task created, else null.
     */
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
     * Adds a Todo Task to the list and returns the same task, else
     * returns null.
     * This function takes in the users input and tries to get the
     * description, then creates a new Todo task and
     * adds it to the current list and returns a copy of the task.
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
     * Returns the Date/Time specified when creating an Event task,
     * else throws a DukeFormatExceptions. The function
     * uses the "/at" specified in the user's input to find the date/time.
     * Throws a DukeFormatExceptions error if its unable to find "/at".
     *
     * @param x The whole user input as a String.
     * @return The date/time of the input in String.
     * @throws DukeFormatExceptions If "/at" does not exists in the Input.
     * @throws IndexOutOfBoundsException If index of (/at + 3) is out of the index range of the input.
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
     * Returns the description of an event task from the user's input.
     * Else throws a DukeFormatExceptions error
     * The function Takes in the whole user input as a string
     * when the user is adding an event. The function assumes the
     * description is between the first " " and the "/at" in the input.
     *
     * @param x The whole user input as a String.
     * @return The date/time of the input in String.
     * @throws DukeFormatExceptions If "/at" does not exists in the Input.
     * @throws IndexOutOfBoundsException If index of /at is out of the index range of the input
     * or there is no " " in the input.
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
     * Returns the Date/Time specified when creating a deadline task,
     * else throws a DukeFormatExceptions. The function
     * uses the "/by" specified in the user's input to find the date/time.
     * Throws a DukeFormatExceptions error if its unable to find "/by".
     *
     * @param x The whole user input as a String.
     * @return The date/time of the input in String.
     * @throws DukeFormatExceptions If "/by" does not exists in the Input.
     * @throws IndexOutOfBoundsException If index of (/by + 3) is out of the index range of the input.
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
     * Returns the description of a deadline task from the user's input.
     * Else throws a DukeFormatExceptions error
     * The function takes in the whole user input as a string
     * when the user is adding a deadline. The function assumes the
     * description is between the first " " and the "/by" in the input.
     *
     * @param x The whole user input as a String.
     * @return The date/time of the input in String.
     * @throws DukeFormatExceptions If "/by" does not exists in the Input.
     * @throws IndexOutOfBoundsException If index of /by is out of the index range of the input
     * or there is no " " in the input.
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
     * Returns the type of task in String, else null.
     * Assumes the type of tasks is the
     * first word in the string and returns
     * that word. If unable to split, returns null.
     *
     * @param x The whole string of user input.
     * @return The type of task in String, else null.
     */
    private static String getTypeOfTask(String x) {
        try {
            String[] inputs = x.split(" ");
            return inputs[0].toLowerCase();
        } catch (PatternSyntaxException p) {
            System.out.println("Unable to read the input properly. Please try again.");
        }
        return null;
    }

    /**
     * Returns the description of a Todo task from the user's input.
     * Else throws a DukeFormatExceptions error
     * The function takes in the whole user input as a string
     * when the user is adding a todo task. The function assumes the
     * description is after the first " " in the input.
     *
     * @param x The whole user input as a String.
     * @return The remaining String excluding the 1st word.
     * @throws DukeFormatExceptions If there is no " "(Space) in the string.
     * @throws IndexOutOfBoundsException If the index of the space + 1 is out of
     * range of the current input.
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
     * in the list else, informs the user if the list
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
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Error in printing task! Contact Admin =(");
        }
    }
}
