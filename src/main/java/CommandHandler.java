
/*---------LOCAL IMPORT--------*/
import exceptions.DukeException;

public class CommandHandler {

    /*-------- Private Variables -----*/
    private String command;
    private String input;

    /*------- Public Variables ---------*/
    public String descriptorBeforeClause;
    public String descriptorAfterClause;

    public CommandHandler(String input) {
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
}
