package duke;

import java.util.Scanner;

public class Duke {

    // Specific indexes for user's command inputs after "done", "deleted" and "find"
    public static final int INDEX_OF_TASK_DONE = 4;
    public static final int INDEX_OF_TASK_DELETED = 6;
    public static final int INDEX_OF_KEYWORD = 4;

    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;

    /**
     * Duke is a command line interface meant to store tasks, and has features
     * to add different types of tasks, list, delete and find tasks.
     *
     * @param filePath File path of the file used to store data of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Makes sense of commands typed by users and calls functions to make changes
     * or show an intended output.
     *
     * @param line Line of command input by the user.
     */
    public static void readCommand(String line) {
        if (line.equals("list")) {
            tasks.listTasks();
        } else if (line.contains("done")) {
            int taskDone = Integer.parseInt(line.substring(INDEX_OF_TASK_DONE).trim());
            tasks.markDone(taskDone);
        } else if (line.contains("delete")) {
            int taskDeleted = Integer.parseInt(line.substring(INDEX_OF_TASK_DELETED).trim());
            tasks.delete(taskDeleted);
        } else if (line.contains("find")) {
            String keyword = line.substring(INDEX_OF_KEYWORD).trim();
            tasks.findTasks(keyword);
        } else {
            tasks.addTask(line);
        }
    }

    /**
     * Runs the main program of greeting, reading commands, storing data
     * and exiting the program.
     */
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
        new Duke("data/duke.txt");
        Duke.run();
    }
}
