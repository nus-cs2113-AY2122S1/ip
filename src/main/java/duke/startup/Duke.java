package duke.startup;

import duke.command.Command;
import duke.data.Storage;
import duke.data.TaskList;
import duke.security.AccountDetail;
import duke.ui.Ui;

public class Duke {
    private final TaskList taskList;
    private final AccountDetail accountDetail;

    public Duke(String filePath) {
        Ui ui = new Ui();
        Storage storage = new Storage(filePath);
        accountDetail = new AccountDetail();
        taskList = new TaskList(Storage.load());
    }

    public static void main(String[] args) {
        new Duke("data/list.txt").run();
    }

    public void run() {
        Ui.sayHi(accountDetail.getUsername());
        boolean isExit = false;
        do {
            String fullCommand = Ui.readLine();
            Ui.printLine();
            Command c = Parser.parse(fullCommand);
            c.execute(taskList);
            isExit = c.isExit();
            Ui.printLine();
        } while (!isExit);

    }

}
