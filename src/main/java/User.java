import java.util.Scanner;

public class User {
    private TaskList userTasks = new TaskList();
    private Scanner sc = new Scanner(System.in);

    private static final String DIVISIONLINE = "    ____________________________________________________________\n";
    private static final String GREETINGS = "     Hello! I'm Duke\n" + "     What can I do for you?\n";
    private static final String BYE = "    Bye. Hope to see you again soon!\n";



    User() {
        System.out.print(DIVISIONLINE + GREETINGS + DIVISIONLINE);
    }

    public void serviceBegin(){
        String userInput = readInput();
        UserCommand input = handleCommand(userInput);

        while (input != null) {
            commandExecute(input);
            userInput = readInput();
            input = handleCommand(userInput);
        }
        sc.close();
    }
    public void serviceEnd(){
        System.out.println(DIVISIONLINE + BYE + DIVISIONLINE);
    }

    private void commandExecute(UserCommand input) {
        System.out.print(DIVISIONLINE);
        input.execute();
        System.out.print(DIVISIONLINE);
    }

    private String readInput() {

        String input = sc.nextLine();
        return input;
    }


    private UserCommand handleCommand(String userInput) {
        String[] inputSplits = userInput.split(" ");
        UserCommand input;

        switch (inputSplits[0]) {
        case "bye":
            input = null;
            break;
        case "list":
            input = new ListCommand(userTasks);
            break;
        case "done":
            input = new DoneCommand(Integer.parseInt(inputSplits[1]), userTasks);
            break;
        case "todo" : case "deadline" : case "event":
            input = new AddListCommand(userInput, userTasks);
            break;
        default:
            input = new otherCommand(userInput);
            break;
        }
        return input;
    }
}
