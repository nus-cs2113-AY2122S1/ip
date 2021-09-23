package duke.processes.commands;

import duke.exceptions.DoneUndoException;
import duke.Duke;

public class DoneUndoCommand extends Command {
    public static String[] userCommand;

    public DoneUndoCommand(String[] command) {
        userCommand = command;
    }

    public CommandResult execute() {
        try {
            if (userCommand[0].equalsIgnoreCase("done")) {
                done(userCommand);
            } else if (userCommand[0].equalsIgnoreCase("undo")) {
                undo(userCommand);
            }
            return new CommandResult("-------LIST UPDATED--------");
        } catch (DoneUndoException e) {
            return new CommandResult(e.getMessage());
        }
    }

    protected static void undo(String[] command) throws DoneUndoException {
        try {
            String[] tasks = command[1].split(",");
            int i;
            for (String j : tasks) {
                i = Integer.parseInt(j) - 1;
                if (!Duke.taskList.get(i).isDone) {
                    throw new DoneUndoException("Task is currently incomplete");
                }
                Duke.taskList.get(i).undo();
                System.out.println("Ok i have changed the status for the specific task:\n ["
                        + Duke.taskList.get(i).getStatusIcon() + "] "
                        + Duke.taskList.get(i).getDescription());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("That task does not exist");
        }
    }

    protected static void done(String[] command) throws DoneUndoException {
        try {
            String[] tasks = command[1].split(",");
            int j;
            for (String i : tasks) {
                j = Integer.parseInt(i) - 1;
                if (Duke.taskList.get(j).isDone) {
                    throw new DoneUndoException("Task is already completed");
                }
                Duke.taskList.get(j).markAsDone();
                System.out.println("Nice! i have marked this task as done:\n ["
                        + Duke.taskList.get(j).getStatusIcon() + "] "
                        + Duke.taskList.get(j).getDescription());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("That task does not exist");
        }
    }
}
