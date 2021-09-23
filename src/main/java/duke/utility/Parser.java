package duke.utility;

import duke.exceptions.EmptyArgException;
import duke.exceptions.WrongFormatException;

/**
 * Contains methods for parsing user input
 */
public class Parser {
    private String userInput;

    /**
     * Stores the raw user input. This must be done before using the other methods in this class
     *
     * @param userInput The raw user input
     */
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    /**
     * get the first word of the user input
     *
     * @return command word of the user input
     */
    public String getCommand() {
        if (userInput != null) {
            return userInput.split(" ")[0];
        }
        System.out.println("userInput is null");
        return null;
    }

    /**
     * Gets the task number for [done] and [delete] commands
     *
     * @return task number (starts from 1)
     * @throws EmptyArgException when no task number is provided
     */
    public int getTaskNum() throws EmptyArgException {
        String[] inputArray = userInput.split(" ");
        if (inputArray.length < 2) {
            throw new EmptyArgException();
        }
        return Integer.parseInt(inputArray[1]);
    }

    /**
     * Gets the task description for [todo] command
     *
     * @return todo description
     * @throws EmptyArgException when no description is found
     */
    public String getTaskDescription() throws EmptyArgException {
        String[] inputArray = userInput.split(" ", 2);
        if (inputArray.length < 2) {
            throw new EmptyArgException();
        }
        return inputArray[1];
    }

    /**
     * Gets the required arguments for creating a new deadline or event
     *
     * @return A string array containing a description and date
     * @throws EmptyArgException when no arg is provided
     * @throws WrongFormatException when no "/by" or "/at" is found
     */
    public String[] getDeadlineOrEventArgs() throws EmptyArgException, WrongFormatException {
        String[] inputArray = userInput.split(" ", 2);
        if (inputArray.length < 2) {
            throw new EmptyArgException();
        }
        String[] args = inputArray[1].split("/", 2);
        if (args.length < 2 || args[1].equals("")) {
            throw new WrongFormatException();
        }
        String description = args[0].trim();
        String byOrAt = args[1].substring(3);
        return new String[]{description, byOrAt};
    }

}
