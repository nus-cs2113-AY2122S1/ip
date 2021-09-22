package duke.startup;


import Type.Task;
import duke.command.Command;
import duke.data.Storage;
import duke.data.TaskList;
import duke.security.AccountDetail;

import java.io.IOException;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private AccountDetail accountDetail;

    //setup of Duke
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        accountDetail = new AccountDetail();
        taskList = new TaskList(storage.load());
    }

    //functional bit of Duke
    public void run(){
        ui.sayHi(accountDetail.getUsername());
        boolean isExit = false;
        do {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
                ui.printLine();
        } while (!isExit);

    }

    public static void main(String[] args) throws IOException {
        new Duke("data/list.txt").run();
    }

}
