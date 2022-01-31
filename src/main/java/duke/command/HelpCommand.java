package duke.command;

import duke.DukeException;

/**
 * Represents a help command
 * This class implements the <code>Command</code> interface
 *
 */
public class HelpCommand implements Command {
    private String query;

    private static final String HELP_TODO = "todo";
    private static final String HELP_EVENT = "event";
    private static final String HELP_DEADLINE = "deadline";
    private static final String HELP_FIND = "find";
    private static final String HELP_DONE = "done";
    private static final String HELP_DELETE = "delete";
    private static final String HELP_LIST = "list";
    private static final String HELP_BYE = "bye";

    /**
     * Constructor method for <code>HelpCommand</code>
     *
     * @param query the command to display the usage
     */
    public HelpCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the command
     *
     * @return the list of available commands to use
     * @throws DukeException if the command query is not recognisable
     */
    public String run() throws DukeException {
        String resultMsg;

        if (query.length() <= 0) {
            resultMsg = "List of available commands:\n"
                    + "\t\ttodo     - a todo task without deadline\n"
                    + "\t\tevent    - a task with a date to attend\n"
                    + "\t\tdeadline - a task with a hard due date\n"
                    + "\t\tfind     - find tasks in the list with a keyword\n"
                    + "\t\tdone     - mark a task as done in the list\n"
                    + "\t\tdelete   - delete a task from the list\n"
                    + "\t\tlist     - list all tasks currently in the list\n"
                    + "\t\tbye      - exits the program\n"
                    + "\tTo find out more about the usage of each command,\n"
                    + "\ttype \"help [command]\"";

            return resultMsg;
        }

        switch (query) {
        case HELP_TODO:
            resultMsg = "Adds a todo task in the list\n"
                    + "\tSyntax: todo [task description]\n"
                    + "\tExample: \"todo buy groceries\"";
            return resultMsg;

        case HELP_EVENT:
            resultMsg = "Adds a task with a date to attend\n"
                    + "\tSyntax: event [event description] /at DD MM yyyy HH:mm\n"
                    + "\tExample: \"event Join CCA practice /at 25 Sep 2021 14:00\"";
            return resultMsg;

        case HELP_DEADLINE:
            resultMsg = "Adds a task with a hard due date\n"
                    + "\tSyntax: deadline [deadline description] /by DD MM yyyy HH:mm\n"
                    + "\tExample: \"deadline CS2113T homework /by 01 Oct 2021 23:59\"";
            return resultMsg;

        case HELP_FIND:
            resultMsg = "Finds tasks that match a given keyword\n"
                    + "\tSyntax: find [keyword]\n"
                    + "\tExample: \"find homework\"";
            return resultMsg;

        case HELP_DONE:
            resultMsg = "Marks a task as done in the list\n"
                    + "\tSyntax: done [task number]\n"
                    + "\tExample: done 9";
            return resultMsg;

        case HELP_DELETE:
            resultMsg = "Removes a task from the list\n"
                    + "\tSyntax: delete [task number]\n"
                    + "\tExample: \"delete 9\"";
            return resultMsg;

        case HELP_LIST:
            resultMsg = "List all tasks currently in the list\n"
                    + "\tExample: \"list\"";
            return resultMsg;

        case HELP_BYE:
            resultMsg = "Exits the current program\n"
                    + "\tExample: \"bye\"";
            return resultMsg;

        default:
            throw new DukeException("Not a recognisable command by me. Try \"help\" instead!");
        }
    }
}
