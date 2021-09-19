package tan;

import tan.exceptions.DukeFormatExceptions;
import tan.tasktype.Deadline;
import tan.tasktype.Event;
import tan.tasktype.Task;
import tan.tasktype.ToDo;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    protected static List<Task> listOfTasks = new LinkedList<>();
    private static int totalNumberOfTask = 0;

    /**
     * Prints a list of tasks that contains
     * a substring in its description of what the user input.
     * If no task matched, it will inform the user.
     *
     * @param userInput The whole user's input in String
     */
    public static void findTask(String userInput) {
        String searchString = Parser.getDescription(userInput);
        if (searchString == null) {
            return;
        }
        List<Task> listOfMatchedTask = getAllMatchingTask(searchString);
        if (listOfMatchedTask == null) {
            //No matching task found.
            System.out.println("No tasks matched your input! Please try again.");
        } else {
            System.out.println("The tasks that matched your inputs are:");
            Ui.printListOfTask(listOfMatchedTask);
        }
    }

    /**
     * Returns a list containing all the tasks with descriptions
     * that contain the string provided. If none matched, return null.
     *
     * @param stringToMatch String to match.
     * @return List of Tasks containing the string provided. Null if list is empty.
     */
    public static List<Task> getAllMatchingTask(String stringToMatch) {
        List<Task> matchedTasks = listOfTasks.stream()
                .filter((t) -> t.getTaskName().contains(stringToMatch))
                .collect(Collectors.toList());
        if (matchedTasks.size() == 0) {
            return null;
        }
        return matchedTasks;
    }

    /**
     * Initializes the save-file & loads the tasks stored in the file
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
     * Marks the task at the specified index as done.
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
     * Deletes the task specified at the index.
     * If the index is out of range, prompts user to key in again.
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
     * creates it. If none of the task type
     * matches, prompts the user to try again.
     *
     * @param userInput The entire input from the user as a String.
     */
    public static void addTask(String userInput) {
        String typeOfTask = Parser.getTypeOfTask(userInput);
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
        try {
            String eventDesc = Parser.getDescriptionOfEvent(userInput);
            String eventTimeDate = Parser.getDateTimeOfEvent(userInput);
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
    private static Task createDeadlineTask(String userInput) {
        try {
            String deadlineDesc = Parser.getDescriptionOfDeadline(userInput);
            String deadlineDateTime = Parser.getDateTimeOfDeadline(userInput);
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
    private static Task createTodoTask(String userInput) {
        try {
            String todoDesc = Parser.getDescriptionOfToDo(userInput);
            Task curTask = new ToDo(todoDesc);
            listOfTasks.add(curTask);
            return curTask;
        } catch (DukeFormatExceptions e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Calls the necessary functions to print the current
     * list of tasks.
     */
    public static void printCurrentList() {
        Ui.printListOfTask(listOfTasks);
    }

}
