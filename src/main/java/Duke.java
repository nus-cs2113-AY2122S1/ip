import exceptions.DukeException;
import exceptions.EventException;
import exceptions.TodoException;
import exceptions.DoneException;
import exceptions.DeadlineException;
import exceptions.DeleteException;

import java.util.Scanner;

public class Duke {
    public static ProcessManager processManager = new ProcessManager();
    public static void main(String[] args) {
        processManager.welcomeMessage();
        String line;
        Scanner in = new Scanner(System.in);

        boolean isProgress = true;

        while (isProgress) {
            line = in.nextLine();
            line = line.trim();

            if (line.equals("bye")) {
                isProgress = false;
                processManager.goodbyeMessage();
            } else if (line.equals("list")) {
                processManager.handleListRequest();
            } else if (line.startsWith("delete")) {
                try {
                    processManager.handleDeleteRequest(line);
                } catch (DeleteException e) {
                    e.printStatement();
                    System.out.println("Invalid Delete Request. Format: delete (number)");
                }
            } else if (line.startsWith("done")) {
                try {
                    processManager.handleDoneRequest(line);
                } catch (DoneException e) {
                    e.printStatement();
                    System.out.println("Invalid Done Request. Format: done (number)");
                }
            } else if (line.startsWith("todo")) {
                try {
                    processManager.handleToDoRequest(line);
                } catch (TodoException e) {
                    e.printStatement();
                    System.out.println("Invalid Todo Request. Format: todo (description)");
                }
            } else if (line.startsWith("deadline")) {
                try {
                    processManager.handleDeadlineRequest(line);
                } catch (DeadlineException e) {
                    e.printStatement();
                    System.out.println("Invalid Deadline Request. Format: deadline (description) /by (Date)");
                }
            } else if (line.startsWith("event")) {
                try {
                    processManager.handleEventRequest(line);
                } catch (EventException e) {
                    e.printStatement();
                    System.out.println("Invalid Event Request. Format: event (description) /at (Date)");
                    System.out.println("    ____________________________________________________________\n");
                }
            } else {
                DukeException e = new DukeException(processManager.help());
                e.printStatement();
            }
        }
    }
}