package duke.commands;

import java.io.IOException;

public abstract class Command {

    public  void run() throws IOException {
    }

    public boolean exit(){
        return true;
    }
}
