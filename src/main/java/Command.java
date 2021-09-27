import java.io.IOException;

public abstract class Command {
    public void execute() throws IOException {

    }
    public boolean isExit() {
        return false;
    }
}
