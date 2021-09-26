package duke.processes.commands;

import duke.Duke;
import duke.processes.tasks.Task;
import duke.processes.tasks.ToDo;
import duke.processes.utility.Interface;

public class UpdateCommand extends Command {
    protected String listType;
    protected String[] userCommand;

    public UpdateCommand(String[] command) {
        userCommand = command;
        if (userCommand.length <= 2) {
            listType = "invalid";
        } else if (userCommand[2].equalsIgnoreCase("description")) {
            listType = "description";
        } else if (userCommand[2].equalsIgnoreCase("date")) {
            listType = "date";
        }
    }

    public CommandResult execute() {
        try {
            if (listType.equalsIgnoreCase("invalid")) {
                return new CommandResult("please specify what to update [update taskNumber description(or)date]");
            }
            int taskNum = Integer.parseInt(userCommand[1]);
            Task task = Duke.taskList.get(taskNum - 1);

            String userUpdate;
            if (userCommand[2].equalsIgnoreCase("description")) {
                System.out.println("type new description below" + System.lineSeparator() + Interface.lineBreak);
                userUpdate = Interface.readInput();
                task.setDescription(userUpdate);
            } else if (userCommand[2].equalsIgnoreCase("date")) {
                if (task instanceof ToDo) {
                    return new CommandResult("Date cannot be updated for todo!");
                }
                System.out.println("type new date in the format: dd/MM/yyyy-HHmm" + System.lineSeparator() + Interface.lineBreak);
                userUpdate = Interface.readInput();
                task.setDescription(userUpdate);
            }
            return new CommandResult("Task updated successfully!");
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("That task does not exist!");
        }
    }

}
