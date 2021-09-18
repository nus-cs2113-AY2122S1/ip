/**
 * Parser class interprets the user's command.
 */
public class Parser {

    /**
     * Parses the input command given by the user to determine which functionality to execute.
     *
     * @param input String entered by the user, denoting instructions for the program.
     */
    public static void parseInput(String input) {
        String[] inputParts = input.split(" ");
        String instruction = inputParts[0];

        switch (instruction) {
        case "done":
            TaskList.markComplete(Integer.parseInt(inputParts[1]), true);
            break;
        case "bye":
            Ui.bye();
            break;
        case "list":
            TaskList.list();
            break;
        case "delete":
            TaskList.deleteTask(Integer.parseInt(inputParts[1]));
            break;
        default:
            attemptStore(input);
            break;
        }
    }

    /**
     * Attempts the store feature on the given user input. If the input does not correspond to todo, deadline or event,
     * a DukeException is caught. This signifies a wrong input command or an incomplete description of the given task.
     *
     * @param input Input command given by the user
     */
    private static void attemptStore(String input) {
        try {
            TaskList.storeTask(input, true);
        } catch (DukeException e) {
            Ui.echo("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (IndexOutOfBoundsException e) {
            Ui.echo("OOPS!!! The description of a task cannot be empty.");
        }
    }
}
