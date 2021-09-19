package tan;

import tan.exceptions.DukeFormatExceptions;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

public class Parser {

    /**
     * Returns the command of the user's input.
     * Takes in the whole user input
     * splits it along the spaces & returns
     * the first "word" of the string.
     *
     * @param x The whole user input as a String.
     * @return The Command in String.
     */
    public static String getCommand(String x) {
        return x.split(" ")[0];
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
    static String getTypeOfTask(String x) {
        try {
            String[] inputs = x.split(" ");
            return inputs[0].toLowerCase();
        } catch (PatternSyntaxException p) {
            System.out.println("Unable to read the input properly. Please try again.");
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
     * @throws DukeFormatExceptions      If "/at" does not exists in the Input.
     * @throws IndexOutOfBoundsException If index of (/at + 3) is out of the index range of the input.
     */
    static String getDateTimeOfEvent(String x) throws DukeFormatExceptions, IndexOutOfBoundsException {
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
    static String getDateTimeOfDeadline(String x) throws DukeFormatExceptions, IndexOutOfBoundsException {
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
    static String getDescriptionOfDeadline(String x) throws DukeFormatExceptions, IndexOutOfBoundsException {
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
    static String getDescriptionOfEvent(String x) throws DukeFormatExceptions, IndexOutOfBoundsException {
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
    static String getDescriptionOfToDo(String x) throws DukeFormatExceptions, IndexOutOfBoundsException {
        //Gets the index of the first space.
        int indexOfFirstSpace = x.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            throw new DukeFormatExceptions("Parameters are empty! Please try again.");
        }
        String description = x.substring(indexOfFirstSpace + 1);
        return description;
    }

    /**
     * Returns the string to search for from the user's input.
     * else, returns null.
     *
     * @param rawInput The whole user's input in String.
     * @return The String to search for.
     */
    public static String getSearchString(String rawInput) {
        String searchString;
        String input = rawInput.strip();
        int indexOfSpace = input.indexOf(" ");
        try {
            if (indexOfSpace == -1) {
                throw new DukeFormatExceptions();
            }
            searchString = input.substring(indexOfSpace + 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please check your input and try again!");
            return null;
        } catch (DukeFormatExceptions e) {
            System.out.println("Search field can't be blank! Please try again.");
            return null;
        }
        return searchString;
    }

    /**
     * Returns the string passed in formatted as a LocalDate type. Null otherwise.
     *
     * @param dateString The date to be formatted in String.
     * @return The LocalDate.
     */
    public static LocalDate getInDateFormat(String dateString) {
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            return null;
        }
        return date;
    }

    /**
     * Returns the index of the desired task in string.
     * Else, -1. It takes in the user input as a string
     * and assumes the index is after the first space.
     * Note that this function does not verify if the
     * index is within rage of the list.
     *
     * @param input The whole user's input as a String.
     * @return The Index as an integer, else -1.
     */
    public static int getIndexFromInput(String input) {
        int index = -1;
        try {
            String[] listOfInputs = input.split(" ");
            index = Integer.parseInt(listOfInputs[1]);
        } catch (NumberFormatException e) {
            System.out.println("Index not recognized. Try again!");
            index = -1;
        } catch (ArrayIndexOutOfBoundsException exp) {
            System.out.println("Please check your input.");
            return -1;
        } catch (PatternSyntaxException exp) {
            System.out.println("Please stop using the app and contact an Admin!");
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Error in converting Index." + System.lineSeparator() + e);
            e.printStackTrace();
            return -1;
        }

        return index;
    }

    /**
     * Returns the index in integer.
     * Else, Returns -1 if the input can't be converted into an int.
     * Takes in the whole user input as a String
     * splits it into an array along spaces
     * and assumes the user keyed in the corresponding
     * index after the first space.
     *
     * @param x The whole user input as a string.
     * @return The index of the task in integer, else -1.
     */
    public static int getIndexOfTask(String x) {
        int taskIndex = -1;
        try {
            taskIndex = getIndexFromInput(x);
            if (taskIndex < 0) {
                //If user keyed in -ve number.
                throw new NumberFormatException();
            }
        } catch (NumberFormatException exp) {
            System.out.println("Please input a proper index!");
            return -1;
        } catch (Exception exp) {
            System.out.println("Error: " + exp);
            System.out.println("Please try again.");
            return -1;
        }
        return taskIndex;
    }
}
