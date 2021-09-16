import exceptions.DukeException;
import java.io.IOException;
import exceptions.DeadlineException;
import exceptions.EventException;
import exceptions.TodoException;
import exceptions.DoneException;
import exceptions.DeleteException;

import java.util.Scanner;

public class Duke {
    private static final String BYE = "bye";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String LIST = "list";
    private static final String DELETE = "delete";
    private static final String DONE = "done";
    private static final String TODO = "todo";

    public static ProcessManager processManager = new ProcessManager();
    public static void main(String[] args) {
        processManager.welcomeMessage();
        processManager.loadTasks();
        String line;
        Scanner in = new Scanner(System.in);

        boolean isProgress = true;

        while (isProgress) {
            line = in.nextLine();
            line = line.trim();

            if (line.equals(BYE)) {
                isProgress = false;
                try {
                    processManager.saveTasks();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                processManager.goodbyeMessage();
            } else if (line.equals(LIST)) {
                processManager.handleListRequest();
            } else if (line.startsWith(DELETE)) {
                try {
                    processManager.handleDeleteRequest(line);
                } catch (DeleteException e) {
                    processManager.printDeleteException(e);
                }
            } else if (line.startsWith(DONE)) {
                try {
                    processManager.handleDoneRequest(line);
                } catch (DoneException e) {
                    processManager.printDoneException(e);
                }
            } else if (line.startsWith(TODO)) {
                try {
                    processManager.handleToDoRequest(line);
                } catch (TodoException e) {
                    processManager.printTodoException(e);
                }
            } else if (line.startsWith(DEADLINE)) {
                try {
                    processManager.handleDeadlineRequest(line);
                } catch (DeadlineException e) {
                    processManager.printDeadlineException(e);
                }
            } else if (line.startsWith(EVENT)){
                try {
                    processManager.handleEventRequest(line);
                } catch (EventException e) {
                    processManager.printEventException(e);
                }
            } else {
                processManager.help();
            }
        }
    }
}