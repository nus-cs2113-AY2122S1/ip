import java.awt.desktop.QuitEvent;
import java.util.Scanner;

public class User {
    private TaskList userTasks = new TaskList();
    private Scanner sc = new Scanner(System.in);

    private static final String DIVISIONLINE = "    ____________________________________________________________\n";
    private static final String GREETINGS = "     Hello! I'm Duke\n" + "     What can I do for you?\n";

    User() {
        System.out.print(DIVISIONLINE + GREETINGS + DIVISIONLINE);
    }

    public void serviceBegin(){
        String userInput;
        UserCommand input;

        do {
            userInput = readInput();
            try {
                input = handleCommand(userInput);
            } catch (InvalidCommandException e) {
                System.out.print(DIVISIONLINE);
                System.out.println(e);
                System.out.print(DIVISIONLINE);
                input = null;
            }

            if (input != null) {
                commandExecute(input);
            }

        } while (! (input instanceof QuitCommand));
        sc.close();
    }


    private void commandExecute(UserCommand input) {
        System.out.print(DIVISIONLINE);
        try {
            input.execute();
        } catch (TaskEmptyException | TimeMissingException e) {
            System.out.println(e);
        }
        System.out.print(DIVISIONLINE);
    }

    private String readInput() {
        return sc.nextLine();
    }


    private UserCommand handleCommand(String userInput) throws InvalidCommandException{
        String[] inputSplits = userInput.split(" ");
        UserCommand input;

        switch (inputSplits[0]) {
        case "bye":
            input = new QuitCommand();
            break;
        case "list":
            input = new ListCommand(userTasks);
            break;
        case "done":
            input = new DoneCommand(Integer.parseInt(inputSplits[1]), userTasks);
            break;
        case "todo" : case "deadline" : case "event":
            input = new AddTaskCommand(userInput, userTasks);
            break;
        default:
            throw new InvalidCommandException();
        }
        return input;
    }
}
