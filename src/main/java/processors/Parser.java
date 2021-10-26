package processors;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.SavedCommand;
import commands.TodoCommand;
import commands.UncheckCommand;

public class Parser {
    private static final String BYE = "bye";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String LIST = "list";
    private static final String DELETE = "delete";
    private static final String DONE = "done";
    private static final String TODO = "todo";
    private static final String FIND = "find";
    private static final String SAVE = "save";
    private static final String HELP = "help";
    private static final String UNCHECK = "uncheck";

    public DeadlineCommand deadlineCommand = new DeadlineCommand();
    public EventCommand eventCommand = new EventCommand();
    public TodoCommand todoCommand = new TodoCommand();
    public DeleteCommand deleteCommand = new DeleteCommand();
    public DoneCommand doneCommand = new DoneCommand();
    public ListCommand listCommand = new ListCommand();
    public InvalidCommand invalidCommand = new InvalidCommand();
    public SavedCommand savedCommand = new SavedCommand();
    public FindCommand findCommand = new FindCommand();
    public ByeCommand byeCommand = new ByeCommand();
    public HelpCommand helpCommand = new HelpCommand();
    public UncheckCommand uncheckCommand = new UncheckCommand();

    /**
     * Function takes the input line and identify the type of Command it is
     * before returning the specific Command class
     * @param line the input line from the user
     * @return the Command class that the user wants
     */
    public Command decipher(String line) {
        if (line.equals(BYE)) {
            return byeCommand;
        } else if (line.equals(LIST)) {
            return listCommand;
        } else if (line.equals(HELP)) {
            return helpCommand;
        } else if (line.startsWith(DELETE)) {
            return deleteCommand;
        } else if (line.startsWith(DONE)) {
            return doneCommand;
        } else if (line.startsWith(TODO)) {
            return todoCommand;
        } else if (line.startsWith(DEADLINE)) {
            return deadlineCommand;
        } else if (line.startsWith(EVENT)){
            return eventCommand;
        } else if (line.startsWith(FIND)) {
            return findCommand;
        } else if (line.startsWith(SAVE)) {
            return savedCommand;
        } else if (line.startsWith(UNCHECK)) {
            return uncheckCommand;
        } else {
            return invalidCommand;
        }
    }
}
