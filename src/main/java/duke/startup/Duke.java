package duke.startup;


import Type.Task;
import duke.data.Storage;
import duke.data.TaskList;
import duke.exception.DukeException;
import duke.security.AccountDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private AccountDetail accountDetail;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        accountDetail = new AccountDetail();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() throws IOException {
        Scanner in = new Scanner(System.in);
        ui.sayHi(AccountDetail.getUsername());
        String command;
        ArrayList<Task> taskList = Storage.load();
        //parse command, execute
        Storage.saveList(taskList);
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/list.txt").run();
    }

}
