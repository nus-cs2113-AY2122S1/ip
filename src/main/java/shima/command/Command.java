package shima.command;

import shima.storage.Storage;
import shima.task.TaskList;

import java.io.IOException;

public abstract class Command {
    public abstract void runCommand() throws IOException;
}
