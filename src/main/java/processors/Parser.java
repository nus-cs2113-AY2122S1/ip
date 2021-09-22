package processors;

import commands.*;

public class Parser {
    private static final String BYE = "bye";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String LIST = "list";
    private static final String DELETE = "delete";
    private static final String DONE = "done";
    private static final String TODO = "todo";

    public static Ui ui = new Ui();

    public DeadlineCommand deadlineCommand = new DeadlineCommand();
    public EventCommand eventCommand = new EventCommand();
    public TodoCommand todoCommand = new TodoCommand();
    public DeleteCommand deleteCommand = new DeleteCommand();
    public DoneCommand doneCommand = new DoneCommand();
    public ListCommand listCommand = new ListCommand();
    public InvalidCommand invalidCommand = new InvalidCommand();
    public SavedCommand savedCommand = new SavedCommand();

    public Command decipher(String line) {
        if (line.equals(BYE)) {
            return savedCommand;
        } else if (line.equals(LIST)) {
            return listCommand;
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
        } else {
            return invalidCommand;
        }
    }
}
