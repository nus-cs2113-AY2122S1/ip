/**
 * Parser class interprets the user's command.
 */
public class Parser {
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";

    /**
     * Parses the input command given by the user to determine which functionality to execute.
     *
     * @param input String entered by the user, denoting instructions for the program.
     */
    public static void parseInput(String input) {
        String[] inputParts = input.split(" ");
        String instruction = inputParts[0];

        switch (instruction) {
        case COMMAND_DONE:
            TaskList.markComplete(Integer.parseInt(inputParts[1]), true);
            break;
        case COMMAND_BYE:
            Ui.bye();
            break;
        case COMMAND_LIST:
            TaskList.list();
            break;
        case COMMAND_DELETE:
            TaskList.deleteTask(Integer.parseInt(inputParts[1]));
            break;
        case COMMAND_FIND:
            TaskList.findTask(inputParts[1]);
            break;
        default:
            store(input);
            break;
        }
    }

    /**
     * Attempts to store the given user input as a task. If the input does not correspond to todo, deadline or event,
     * a DukeException is caught, indicating failed storing. This signifies a wrong input command or an incomplete
     * description of the given task.
     *
     * @param input Input command given by the user
     */
    private static void store(String input) {
        try {
            Task newTask = TaskList.createTask(input);
            TaskList.storeTask(newTask, true);
        } catch (DukeException e) {
            Ui.echo("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (IndexOutOfBoundsException e) {
            Ui.echo("OOPS!!! The description of a task cannot be empty.");
        }
    }
}
