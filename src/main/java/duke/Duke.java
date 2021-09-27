package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.greetUser();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        ArrayList<String> stringList = TaskList.getStringList();
        ArrayList<String> dueDateList = TaskList.getDueDateList();

        while (!line.equals("bye")) {
            Parser.parse(line, tasks, stringList, dueDateList);
            line = in.nextLine();
        }
        Ui.byeUser();
    }

    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }
}







