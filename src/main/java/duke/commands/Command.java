package duke.commands;

import java.io.IOException;
/**
 * Represents a command made by the user.
 */
public abstract class Command {

    public  void run() throws IOException {
    }

    public boolean exit(){
        return true;
    }
}
