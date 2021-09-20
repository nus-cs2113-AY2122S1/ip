package processing;
/*---------LOCAL IMPORT--------*/

import Duke.Duke;
import exceptions.DukeException;
import tasks.TaskType;

/**
 * <h1>Command Handler!</h1>
 * Takes in a string and parses it by finding clauses
 * Once parsed, the command can be executed on a TaskManager
 */
public class CommandHandler {

    /*-------- Private Variables -----*/
    private final String command;
    private final String input;

    /*------- Public Variables ---------*/
    public String descriptorBeforeClause;
    public String descriptorAfterClause;

    /*-------- COMMANDS ----------*/
    public static final String[] commands = {
            "bye",
            "help",
            "dates",
            "list",
            "find",
            "todo",
            "deadline",
            "event",
            "delete",
            "done"
    };

    /**
     * Creates a parser for a string input
     *
     * @param input User input that is to be parsed into a command
     * @throws DukeException throws an exception when the input is empty
     */
    public CommandHandler(String input) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException("Invalid Task Input");
        }
        // padding extra space to circumvent indexOutOfBounds
        this.input = input + " ";
        this.command = input.split(" ")[0];
        descriptorBeforeClause = command;
        descriptorAfterClause = "";
    }

    /**
     * Splits the command up by a clause into before and after the clause excluding the clause
     * Eg. "ABC {clause} XYZ" splits into
     * Before -> ABC
     * After -> XYZ
     *
     * @param clause              Phrase and String to split up the command in two. Typically used for flag
     *                            identification
     * @param startIndex          The index at which the 'Before Clause' should begin
     * @param isBeforeClauseEmpty If false (default), will throw DukeException if 'Before Clause'
     *                            is empty after splitting. Otherwise, empty before clause is allowed
     * @throws DukeException Throws Exception when After Clause is empty, or when Before Clause
     *                       is empty if <b>isBeforeClauseEmpty</b> is set to false
     * @see DukeException
     */
    public void splitByClause(String clause, int startIndex, boolean isBeforeClauseEmpty) throws DukeException {
        int idxOfClause = input.indexOf(clause);
        // Throw exception if ID string missing (e.g. "/by" missing from deadline command)
        if (idxOfClause == -1) {
            throw new DukeException("Command is missing required clause : " + clause);
        }
        String descriptorAfterClause = input.substring(idxOfClause + clause.length() + 1).trim();
        String descriptorBeforeClause = input.substring(startIndex, idxOfClause).trim();
        if (descriptorAfterClause.equals("")) {
            throw new DukeException("Description cannot be empty after " + clause);
        } else if (!isBeforeClauseEmpty && descriptorBeforeClause.equals("")) {
            throw new DukeException("Description cannot be empty before " + clause);
        }
        this.descriptorBeforeClause = descriptorBeforeClause;
        this.descriptorAfterClause = descriptorAfterClause;
    }

    /**
     * Executes the command after parsing
     *
     * @param taskManager the TaskManager that the command will be executing on
     * @throws DukeException on invalid command syntax
     */
    public void execute(TaskManager taskManager) throws DukeException {
        switch (command) {
        case "bye":
            Duke.exit();
            break;
        case "help":
            UI.listCommands();
            break;
        case "dates":
            UI.listDTFormats();
            break;
        case "list":
            taskManager.listTasks();
            break;
        case "find":
            taskManager.queryTasks(this);
            break;
        case "done":
            taskManager.markTaskAsDone(this);
            break;
        case "delete":
            taskManager.deleteTask(this);
            break;
        case "deadline":
            taskManager.addTask(this, TaskType.DEADLINE);
            break;
        case "event":
            taskManager.addTask(this, TaskType.EVENT);
            break;
        case "todo":
            taskManager.addTask(this, TaskType.TODO);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
