import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner in = new Scanner(System.in);
        InputFilter filter = new InputFilter();

        String input;
        String[] filteredInput;

        duke.greet();
        while (true) {
            input = in.nextLine();
            filteredInput = filter.separateCommand(input);

            if (input.equalsIgnoreCase("bye")) {
                duke.exit();
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                duke.listTasks();
                continue;
            }

            switch (filteredInput[0].toLowerCase()) {
            case "done":
                duke.markTaskAsDone(Integer.parseInt(filteredInput[1]));
                break;
            case "todo":
                // Fallthrough
            case "deadline":
                // Fallthrough
            case "event":
                duke.addTask(filteredInput[0].toLowerCase(), filteredInput[1]);
                break;
            default:
                duke.showInvalidCommandMessage();
                break;
            }
        }
    }
}
