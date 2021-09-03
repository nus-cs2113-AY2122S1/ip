import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final int COMMAND_INDEX = 0;

    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();

        outputHandler.printWelcomeMessage();

        //initialize the command to default
        Command command = Command.DEFAULT;

        while (!command.equals(Command.EXIT)) {
            String input = in.nextLine();
            String[] inputTokens = input.split(" ");
            command = inputHandler.handleCommand(inputTokens[COMMAND_INDEX]);
            outputHandler.outputMessage(command, input, tasks);
        }
    }
}
