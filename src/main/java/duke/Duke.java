package duke;

import duke.commands.Command;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception ex) {
                ui.showExceptionMessage(ex);
            }
        }
    }

//    public void run(){
//        ui.showWelcome();
//        String request = ui.getCommand();
//        while (CommandType.isNotBye(request)) {
//            try {
//                if (CommandType.isList(request)) {
//                    tasks.printTasks();
//                } else if (CommandType.isDone(request)) {
//                    tasks.doneTask(request);
//                } else if (CommandType.isDelete(request)) {
//                    tasks.deleteTask(request);
//                } else {
//                    tasks.addTask(request);
//                }
//                storage.store(tasks);
//            } catch (Exception ex) {
//                ui.showExceptionMessage(ex);
//            } finally {
//                request = ui.getCommand();
//            }
//        }
//        ui.showGoodByeMessage();
//    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
