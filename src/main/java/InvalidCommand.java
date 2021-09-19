public class InvalidCommand implements Command {
    public static final CommandType type = CommandType.INVALID;

    @Override
    public void run() throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    @Override
    public CommandType getType() {
        return type;
    }
}
