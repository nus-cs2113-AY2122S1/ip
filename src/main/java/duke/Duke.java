package duke;
import duke.command.DukeException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private static TaskList tasks;

    /**
     * at the beginning of Duke class, create a file using Storage class
     * then load the TaskList
     *
     * @param filePath by default is "data/duke.txt"
     * @throws IOException ensure it reads the file
     */
    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFileContents());
    }


    /**
     * Execute the user command.
     *
     * @param c new Parser object to understand user input
     * @param tasks stores all the tasks
     * @param storage deal with file storing and loading
     */
    public static void executeCommand(Parser c, TaskList tasks, Storage storage) throws DukeException, IOException {
        switch (c.getCommand()) {
        case "bye":
            Ui.showBye();
            break;
        case "list":
            tasks.printTaskList();
            break;
        case "done":
            tasks.markTaskAsDone(c.getDescription());
            break;
        case "todo":
            tasks.addToDoTaskToList(c.getDescription());
            break;
        case "deadline":
            tasks.addDeadlineTaskToList(c.getDescription());
            break;
        case "event":
            tasks.addEventTaskToList(c.getDescription());
            break;
        case "delete":
            tasks.deleteTask(c.getDescription());
            break;
        case "find":
            tasks.findTaskList(c.getDescription());
            break;
        default:
            throw new DukeException("Oops, command not recognised!");
        }
        storage.writeToFile(tasks);
    }

    /**
     * Main program runs here, terminate when isExit == true
     */
    public void run() {

        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readCommand();
                Ui.showLine(); // show the divider line ("_______")
                Parser c = new Parser(fullCommand);
                c.parse();
                executeCommand(c,tasks,storage);
                isExit = c.getIsExit();
            } catch (StringIndexOutOfBoundsException e) {
                Ui.showError("OOPS!!! The description after this command word cannot be empty.");
            } catch (IndexOutOfBoundsException e) {
                Ui.showError("OOPS!!! It's out of range.");
            } catch (NumberFormatException e) {
                Ui.showError("OOPS!!! Input after done/delete must be a number.");
            }catch (DukeException e) {
                Ui.showError(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Ui.showLine();
            }
        }
    }

    /**
     * Create a new Duke class and begin the 'run' method
     *
     * @param args don't need to input
     * @throws IOException ensure it reads a file
     */
    public static void main(String[] args) throws IOException {

        new Duke("data/duke.txt").run();
    }
}



