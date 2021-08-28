/**
 * A generic Command class to handle commands sent by the user.
 */
public abstract class Command {

    private final String name;

    public static final String INVALID_FORMAT_MESSAGE =
            "Sorry, Unker need you to type this way for me to understand arh (no need brackets):";
    public static final String ADDED_TASK_MESSAGE = "Okay Unker help you add this to your to-do list:";

    protected Command(String name) {
        this.name = name;
    }

    /**
     * Get the name of the command.
     *
     * @return The name of the command.
     */
    public String getName() {
        return name;
    }

    /**
     * Executes the command provided by the user.
     *
     * @param ui The UI instance that is executing the command.
     * @param unker The task manager Unker that will be read from and updated to.
     * @param data The command line data (excluding the command name).
     */
    public abstract void execute(UI ui, Unker unker, String data);

}
