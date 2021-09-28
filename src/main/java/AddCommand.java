public class AddCommand extends Command {
    /** A Task object is stored for adding to list */
    protected Task newTask = null;

    /**
     * Creates respective Task subclass object
     * depending on the starting keyword present in user input.
     *
     * @param userInput String command input by user or parsed from local file.
     */
    public static Task createTask(String userInput) throws DukeException {
        if (userInput.startsWith("event ")) {
            if (userInput.contains("/at ")) {
                return new Event(userInput);
            } else {
                throw new DukeException("\t*** Remember to include event timing after '/at ' in description.");
            }
        } else if (userInput.startsWith("deadline ")) {
            if (userInput.contains("/by ")) {
                return new Deadline(userInput);
            } else {
                throw new DukeException("\t*** Remember to indicate deadline after '/by ' in description.");
            }
        } else if (userInput.startsWith("todo ")) {
            if (!userInput.substring("todo".length()).isBlank()){
                return new Todo(userInput);
            } else {
                throw new DukeException("\t*** OOPS!!! The description of a todo cannot be empty.");
            }
        } else {
            // If the starting substring does not conform to expected keywords, throw an error msg.
            throw new DukeException("\t☹ INVALID COMMAND\n" +
                    "\tPlease begin commands with\n" +
                    "\t'event', 'deadline', 'todo',\n" +
                    "\t'done', 'remove', or 'delete', and\n" +
                    "\tinput description after a whitespace.");
        }
    }

    /**
     * Adds task to program list by calling record() mathod
     * from TaskList class.
     *
     * @param tl TaskList object which stores all user-created tasks.
     */
    @Override
    public void execute(TaskList tl) {
        if (newTask != null) {
            tl.record(newTask);
        }
    }

    public AddCommand(String addCommandInput) {
        try {
            newTask = createTask(addCommandInput);
        } catch (DukeException e) {
            e.printErrorMessage();
        }
    }
}
