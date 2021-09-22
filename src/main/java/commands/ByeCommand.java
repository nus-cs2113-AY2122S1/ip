package commands;

import processors.Storage;
import processors.TaskList;
import processors.Ui;

import java.io.IOException;

public class ByeCommand extends Command{
    public Ui ui = new Ui();
    public Storage storage = new Storage();

    public void execute(TaskList taskList) throws IOException {
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            throw new IOException(e);
        }
        ui.goodbyeMessage();
    }
}
