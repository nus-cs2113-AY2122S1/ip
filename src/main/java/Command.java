public interface Command {
    void run() throws DukeException;

    CommandType getType();
}
