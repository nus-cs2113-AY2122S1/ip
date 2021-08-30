import java.util.Scanner;

public class DukeUser {
    private TaskList userTasks = new TaskList();
    private static final String DIVISIONLINE = "    ____________________________________________________________\n";
    private static final String GREETINGS = "     Hello! I'm Duke\n" + "     What can I do for you?\n";
    private static final String BYE = "    Bye. Hope to see you again soon!\n";

    DukeUser() {
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
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(input);
        sc.close();
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
        default:
            input = new AddListCommand(userInput, userTasks);
            break;
        }
        return input;


    }
}
