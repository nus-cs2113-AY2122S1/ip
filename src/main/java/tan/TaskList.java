package tan;

import tan.exceptions.DukeFormatExceptions;
import tan.tasktype.Deadline;
import tan.tasktype.Event;
import tan.tasktype.Task;
import tan.tasktype.ToDo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class TaskList {

    protected static List<Task> listOfTasks = new LinkedList<>();
    private static int totalNumberOfTask = 0;

    /**
     * Initializes the file & loads the tasks stored in the file
     * into the list. Then updates the total number of tasks.
     */
    public static void initializeFileAndLoadDataIntoList() {
        List<Task> listOfStoredTasks = Storage.initializeFileAndGetTasks();
        listOfTasks.addAll(listOfStoredTasks);
        totalNumberOfTask = listOfTasks.size();
        System.out.println("Successfully loaded " + totalNumberOfTask + " tasks.");
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
    protected static void saveCurrentList() {
        try {
            Storage.saveCurrentList(listOfTasks);
            System.out.println("File successfully updated.");
        } catch (IOException e) {
            System.out.println("Error in writing/opening file! Please try again.");
        } catch (Exception e) {
            System.out.println("Generic Error in saving file. Error is:" + e);
            e.printStackTrace();
        }
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
        Task currentTask = getTask(indexTask - 1); //Minus 1 as array's index starts at 0.
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
        Task toDelete = getTask(indexTask - 1); //Minus 1 as array's index starts at 0.
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
            curTask = createTodoTask(userInput);
            if (curTask == null) {
                return;
            }
            break;
        case "deadline":
            curTask = createDeadlineTask(userInput);
            if (curTask == null) {
                return;
            }
            break;
        case "event":
            curTask = createEventTask(userInput);
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
    private static Task createEventTask(String userInput) {
        Task curTask = null;
        try {
            String eventDesc = getDescriptionOfEvent(userInput);
            String dateOfEventInString = getDateTimeOfEventInString(userInput);
            LocalDate taskDate = Parser.getInDateFormat(dateOfEventInString);
            if (taskDate == null) {
                System.out.println("Unable to parse date");
                return null;
            }
            curTask = new Event(eventDesc, taskDate);
        } catch (DukeFormatExceptions d) {
            System.out.println(d);
            return null;
        } catch (IndexOutOfBoundsException I) {
            System.out.println("Please check your formatting & input!");
            return null;
        }
        listOfTasks.add(curTask);
        return curTask;
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
    private static Task createDeadlineTask(String userInput) {
        Task curTask = null;
        try {
            String deadlineDesc = getDescriptionOfDeadline(userInput);
            String dateInString = getDateTimeOfDeadline(userInput);
            LocalDate taskDate = Parser.getInDateFormat(dateInString);
            if (taskDate == null) {
                System.out.println("Unable to parse Date.");
                return null;
            }
            curTask = new Deadline(deadlineDesc, taskDate);
        } catch (IndexOutOfBoundsException x) {
            System.out.println("Please check your formatting & input!");
            return null;
        } catch (DukeFormatExceptions e) {
            System.out.println(e);
            return null;
        }
        listOfTasks.add(curTask);
        return curTask;
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
    private static Task createTodoTask(String userInput) {
        Task curTask = null;
        try {
            String todoDesc = getDescriptionOfToDo(userInput);
            curTask = new ToDo(todoDesc);

        } catch (DukeFormatExceptions e) {
            System.out.println(e);
            return null;
        } catch (IndexOutOfBoundsException x) {
            System.out.println("Please check your formatting & input!");
            return null;
        }
        listOfTasks.add(curTask);
        return curTask;
    }

    /**
     * Returns the Date/Time specified when creating an Event task,
     * else throws a DukeFormatExceptions. The function
     * uses the "/at" specified in the user's input to find the date/time.
     * Throws a DukeFormatExceptions error if its unable to find "/at".
     *
     * @param x The whole user input as a String.
     * @return The date/time of the input in String.
     * @throws DukeFormatExceptions      If "/at" does not exists in the Input.
     * @throws IndexOutOfBoundsException If index of (/at + 3) is out of the index range of the input.
     */
    private static String getDateTimeOfEventInString(String x) throws DukeFormatExceptions, IndexOutOfBoundsException {
        //Checks if user has used the /at... format.
        if (x.toLowerCase().contains("/at")) {
            int indexOfSlash = x.indexOf("/at");
            //+3 to the index as we don't want to capture "/at" itself.
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
     * @throws DukeFormatExceptions      If "/at" does not exists in the Input.
     * @throws IndexOutOfBoundsException If index of /at is out of the index range of the input
     *                                   or there is no " " in the input.
     */
    private static String getDescriptionOfEvent(String x) throws DukeFormatExceptions, IndexOutOfBoundsException {
        //Checks if user has used the /by... format.
        if (x.toLowerCase().contains("/at")) {
            int indexOfFirstSpace = x.indexOf(" ");
            int indexOfSlash = x.indexOf("/at");
            //Minus 1 and plus 1 to index to avoid capturing the " " & "/" itself.
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
     * @throws DukeFormatExceptions      If "/by" does not exists in the Input.
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
     * @throws DukeFormatExceptions      If "/by" does not exists in the Input.
     * @throws IndexOutOfBoundsException If index of /by is out of the index range of the input
     *                                   or there is no " " in the input.
     */
    private static String getDescriptionOfDeadline(String x) throws DukeFormatExceptions, IndexOutOfBoundsException {
        //Checks if user has used the /by... format.
        if (x.toLowerCase().contains("/by")) {
            int indexOfFirstSpace = x.indexOf(" ");
            int indexOfSlash = x.indexOf("/by");
            //Minus 1 and plus 1 to index to avoid capturing the " " & "/" itself.
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
     * @throws DukeFormatExceptions      If there is no " "(Space) in the string.
     * @throws IndexOutOfBoundsException If the index of the space + 1 is out of
     *                                   range of the current input.
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
