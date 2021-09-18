public class Parser {

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
        case "find":
            TaskList.findTask(inputParts[1]);
            break;
        default:
            attemptStore(input);
            break;
        }
    }

    public static void attemptStore(String input) {
        try {
            TaskList.storeTask(input, true);
        } catch (DukeException e) {
            Ui.echo("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (IndexOutOfBoundsException e) {
            Ui.echo("OOPS!!! The description of a task cannot be empty.");
        }
    }
}

