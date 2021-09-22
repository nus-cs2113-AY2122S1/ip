package duke.startup;


import Type.Task;
import duke.command.Command;
import duke.data.Storage;
import duke.data.TaskList;
import duke.security.AccountDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private AccountDetail accountDetail;
    private in = new Scanner(System.in);

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        accountDetail = new AccountDetail();
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run(){
        ui.sayHi(accountDetail.getUsername());
        boolean isExit = false;
        do {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                ArrayList<Task> taskList = Storage.load();
                //parse command, execute
                Storage.saveList(taskList);
            } catch (IOException e) {
                System.out.println("IOException, stopping Duke");
            } finally {
                ui.printLine();
            }
        } while (!isExit);

    }

    public static void main(String[] args) throws IOException {
        new Duke("data/list.txt").run();
    }

}
