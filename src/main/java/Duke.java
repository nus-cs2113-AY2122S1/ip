import exceptions.EventException;
import exceptions.TodoException;
import exceptions.DoneException;
import exceptions.DeadlineException;
import exceptions.DeleteException;
import exceptions.DukeException;
import java.io.IOException;
import java.util.Scanner;
import processors.Storage;
import processors.ProcessManager;
import processors.Ui;

public class Duke {
    private static final String BYE = "bye";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String LIST = "list";
    private static final String DELETE = "delete";
    private static final String DONE = "done";
    private static final String TODO = "todo";

    public static Ui ui = new Ui();
    public static ProcessManager processManager = new ProcessManager();
    public static Storage storage = new Storage();

    public static void main(String[] args) {
        ui.welcomeMessage();

        try {
            storage.loadTasks(processManager);
        } catch (IOException e) {
            ui.printIOException(e);
        } catch (SecurityException e) {
            ui.printSecurityException(e);
        }

        String line;
        Scanner in = new Scanner(System.in);
        boolean isProgress = true;

        while (isProgress) {
            line = in.nextLine();
            line = line.trim();

            if (line.equals(BYE)) {
                isProgress = false;
                try {
                    storage.saveTasks(processManager);
                } catch (IOException e) {
                    ui.printIOException(e);
                }
                ui.goodbyeMessage();
            } else if (line.equals(LIST)) {
                processManager.handleListRequest();
            } else if (line.startsWith(DELETE)) {
                try {
                    processManager.handleDeleteRequest(line);
                } catch (DeleteException e) {
                    ui.printDeleteException(e);
                }
            } else if (line.startsWith(DONE)) {
                try {
                    processManager.handleDoneRequest(line);
                } catch (DoneException e) {
                    ui.printDoneException(e);
                }
            } else if (line.startsWith(TODO)) {
                try {
                    processManager.handleToDoRequest(line);
                } catch (TodoException e) {
                    ui.printTodoException(e);
                }
            } else if (line.startsWith(DEADLINE)) {
                try {
                    processManager.handleDeadlineRequest(line);
                } catch (DeadlineException e) {
                    ui.printDeadlineException(e);
                }
            } else if (line.startsWith(EVENT)){
                try {
                    processManager.handleEventRequest(line);
                } catch (EventException e) {
                    ui.printEventException(e);
                }
            } else {
                DukeException e = new DukeException("Unknown Command");
                ui.printDukeException(e);
            }
        }
    }
}