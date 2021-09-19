package processing;
/*---------LOCAL IMPORT--------*/
import Duke.Duke;
import exceptions.DukeException;
import tasks.TaskType;

public class CommandHandler {

    /*-------- Private Variables -----*/
    private final String command;
    private final String input;

    /*------- Public Variables ---------*/
    public String descriptorBeforeClause;
    public String descriptorAfterClause;

    public CommandHandler(String input) throws DukeException{
        if (input.isBlank()) {
            throw new DukeException("Invalid Task Input");
        }
        // padding extra space to circumvent indexOutOfBounds
        this.input = input + " ";
        this.command = input.split(" ")[0];
        descriptorBeforeClause = command;
        descriptorAfterClause = "";
    }

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
        } else if (!isBeforeClauseEmpty && descriptorBeforeClause.equals("")){
            throw new DukeException("Description cannot be empty before " + clause);
        }
        this.descriptorBeforeClause = descriptorBeforeClause;
        this.descriptorAfterClause = descriptorAfterClause;
    }

    public String getCommand() {
        return command;
    }

    public void execute(TaskManager taskManager) throws DukeException{
        switch (command) {
        case "bye":
            Duke.exit();
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
