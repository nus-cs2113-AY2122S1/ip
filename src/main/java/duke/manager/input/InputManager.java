package duke.manager.input;

import duke.manager.command.Command;

import java.util.Scanner;

public class InputManager {
    private Scanner scanner = new Scanner(System.in);
    private Command inputCommand;
    private String commandArguments;

    public void setInputCommand(String input) {
        if (input.equals("bye")) {
            inputCommand = Command.EXIT;
        } else if (input.equals("list")) {
            inputCommand = Command.SHOW_LIST;
        } else if (input.equals("todo")) {
            inputCommand = Command.ADD_TODO;
        } else if (input.equals("event")) {
            inputCommand = Command.ADD_EVENT;
        } else if (input.equals("deadline")) {
            inputCommand = Command.ADD_DEADLINE;
        } else if (input.equals("done")) {
            inputCommand = Command.DONE_TASK;
        } else {
            inputCommand = Command.INVALID;
        }
    }

    public Command getInputCommand() {
        return inputCommand;
    }

    public String getCommandArguments() {
        return commandArguments;
    }

    public void readInput() {
        String input = scanner.nextLine();
        Parser parsedInput = new Parser(input);
        if (parsedInput.isMoreThanTwoWords()) {
            String firstWord = parsedInput.getFirstWord();
            setInputCommand(firstWord);
            commandArguments = parsedInput.getArguments()[1].trim();
        } else {
            setInputCommand(parsedInput.getInput()); //one word/empty string
            commandArguments = "none";
        }
    }
}
