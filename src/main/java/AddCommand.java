public class AddCommand extends Command {
    protected Task newTask = null;

    /**
     * Depending on the starting keyword present in user input, creates respective
     * Task subclass object and stores it inside the storedTasks array.
     *
     * @param userInput String command input by user.
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
            if (!userInput.substring(4).isBlank()){
                return new Todo(userInput);
            } else {
                throw new DukeException("\t*** OOPS!!! The description of a todo cannot be empty.");
            }
        } else {
            throw new DukeException("\t☹ INVALID COMMAND\n" +
                    "\tPlease begin commands with\n" +
                    "\t'event', 'deadline', 'todo',\n" +
                    "\t'done', 'remove', or 'delete', and\n" +
                    "\tinput description after a whitespace.");
        }
    }

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
