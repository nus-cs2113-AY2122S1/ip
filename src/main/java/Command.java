import java.io.IOException;

/**
 * Represents a command made by the user.
 */
public abstract class Command {
    public void execute() throws IOException {

    }
    public boolean isExit() {
        return false;
    }
}
