package duke.utility;

import duke.exceptions.EmptyArgException;
import duke.exceptions.WrongFormatException;

public class Parser {
    private String userInput;

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getCommand() {
        if (userInput != null) {
            return userInput.split(" ")[0];
        }
        System.out.println("userInput is null");
        return null;
    }

    public int getTaskNum() throws EmptyArgException {
        String[] inputArray = userInput.split(" ");
        if (inputArray.length < 2) {
            throw new EmptyArgException();
        }
        return Integer.parseInt(inputArray[1]);
    }

    public String getTaskDescription() throws EmptyArgException {
        String[] inputArray = userInput.split(" ", 2);
        if (inputArray.length < 2) {
            throw new EmptyArgException();
        }
        return inputArray[1];
    }

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
