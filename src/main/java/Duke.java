import exceptions.DeadlineException;
import exceptions.EventException;
import exceptions.TodoException;
import exceptions.DoneException;
import exceptions.DukeException;

import java.util.Scanner;

public class Duke {
    public static ProcessManager processManager = new ProcessManager();

    public static void main(String[] args) {
        processManager.welcomeMessage();
        String line;
        Scanner in = new Scanner(System.in);

        Integer trackIndex = 0;
        boolean isProgress = true;

        while (isProgress) {
            line = in.nextLine();
            line = line.trim();

            if (line.equals("bye")) {
                isProgress = false;
                processManager.goodbyeMessage();
            } else if (line.equals("list")) {
                processManager.handleListRequest(trackIndex);
            } else if (line.startsWith("done")) {
                try {
                    processManager.handleDoneRequest(line, trackIndex);
                } catch (DoneException e) {
                    e.printStatement();
                    System.out.println("Invalid Done Request. Format: done (number)");
                }
            } else if (line.startsWith("todo")) {
                try {
                    processManager.handleToDoRequest(line, trackIndex);
                } catch (TodoException e) {
                    e.printStatement();
                    System.out.println("Invalid Todo Request. Format: todo (description)");
                }
                trackIndex++;
            } else if (line.startsWith("deadline")) {
                try {
                    processManager.handleDeadlineRequest(line, trackIndex);
                } catch (DeadlineException e) {
                    e.printStatement();
                    System.out.println("Invalid Deadline Request. Format: deadline (description) /by (Date)");
                }
                trackIndex++;
            } else if (line.startsWith("event")) {
                try {
                    processManager.handleEventRequest(line, trackIndex);
                } catch (EventException e) {
                    e.printStatement();
                    System.out.println("Invalid Event Request. Format: event (description) /at (Date)");
                    System.out.println("    ____________________________________________________________\n");
                }
                trackIndex++;
            } else {
                DukeException e = new DukeException(processManager.help());
                e.printStatement();
            }
        }
    }
}