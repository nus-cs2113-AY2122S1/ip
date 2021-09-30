package duke;

import java.util.Scanner;

public class Duke {

    // specific indexes for written commands
    public static final int INDEX_OF_TASK_DONE = 4;
    public static final int INDEX_OF_TASK_DELETED = 6;

    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void readCommand(String line) {
        if (line.equals("list")) {
            tasks.listTasks();
        } else if (line.contains("done")) {
            int taskDone = Integer.parseInt(line.substring(INDEX_OF_TASK_DONE).trim());
            tasks.markDone(taskDone);
        } else if (line.contains("delete")) {
            int taskDeleted = Integer.parseInt(line.substring(INDEX_OF_TASK_DELETED).trim());
            tasks.delete(taskDeleted);
        } else {
            tasks.addTask(line);
        }
    }

    public static void run() {
        ui.sayHello();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            readCommand(line);
            storage.save();
            line = in.nextLine();
        }
        ui.sayBye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
