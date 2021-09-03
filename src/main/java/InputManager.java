import java.util.Scanner;

public class InputManager {
    protected Scanner scanner = new Scanner(System.in);
    protected Command inputCommand;
    protected String commandArguments;

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

    public void readInput() {
        String input = scanner.nextLine();
        Parser parsedInput = new Parser(input);
        if (parsedInput.isMoreThanTwoWords) {
            String firstWord = parsedInput.getFirstWord();
            setInputCommand(firstWord);
            commandArguments = parsedInput.arguments[1].trim();
        } else {
            setInputCommand(parsedInput.input); //one word/empty string
            commandArguments = "";
        }
    }
}
