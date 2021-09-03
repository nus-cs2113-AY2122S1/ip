public class InputHandler {
    public static final String HELP = "help";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String ADD_TODO = "todo";
    public static final String ADD_DEADLINE = "deadline";
    public static final String ADD_EVENT = "event";
    public static final String REMOVE = "remove";
    public static final String BYE = "bye";
    public static final String EXIT = "exit";
    public static final String QUIT = "quit";
    public static final String GREETING = "hello";

    public Command handleCommand(String inputCommand) {
        switch (inputCommand) {
        case LIST:
            return Command.LIST_TASKS;

        case DONE:
            return Command.MARK_DONE;

        case ADD_TODO:
            return Command.ADD_TODO;

        case ADD_DEADLINE:
            return Command.ADD_DEADLINE;

        case ADD_EVENT:
            return Command.ADD_EVENT;

        case REMOVE:
            return Command.REMOVE_TASK;

        case HELP:
            return Command.HELP;

        case GREETING:
            return Command.GREETING;

        case BYE:
            //Fallthrough
        case EXIT:
            //Fallthrough
        case QUIT:
            return Command.EXIT;

        default:
            return Command.DEFAULT;
        }
    }
}
