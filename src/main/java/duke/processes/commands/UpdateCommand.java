package duke.processes.commands;

import duke.Duke;
import duke.exceptions.DateTimeException;
import duke.processes.tasks.Task;
import duke.processes.tasks.ToDo;
import duke.processes.utility.Interface;
import duke.processes.utility.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateCommand extends Command {

    protected String listType;
    protected String[] userCommand;
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HHmm");

    /**
     * Constructor for update command that initializes the type of date to be updated
     * (listType) and the array string of the users commands (userCommand). Flags the listType
     * as invalid if the user did not type the command in the correct format.
     *
     * @param command an array of strings of the users response
     */
    public UpdateCommand(String[] command) {

        userCommand = command;

        if (userCommand.length <= 2) {
            listType = "invalid";
        } else if (userCommand[2].equalsIgnoreCase("description")) {
            listType = "description";
        } else if (userCommand[2].equalsIgnoreCase("date")) {
            listType = "date";
        } else {
            listType = "invalid";
        }
    }

    /**
     * executes the specific update depending on what the user wanted to update. Prompts the
     * user a second time to get the new update that the user wants and replaces, the old
     * data with the new userInputted data. Prompts user to type command in the correct format
     * if the listType is invalid.
     *
     * @return CommandResult that indicates whether the update was executed successfully
     */
    public CommandResult execute() {

        try {
            if (listType.equalsIgnoreCase("invalid")) {
                return new CommandResult("please specify what to update " +
                        "[update taskNumber description(or)date]");
            }

            int taskNum = Integer.parseInt(userCommand[1]);
            Task task = Duke.taskList.get(taskNum - 1);

            if (userCommand[2].equalsIgnoreCase("description")) {
                System.out.println("type new description below" + System.lineSeparator() +
                        Interface.lineBreak);
                String updateDescription = Interface.readInput();
                task.setDescription(updateDescription);
            } else if (userCommand[2].equalsIgnoreCase("date")) {
                if (task instanceof ToDo) {
                    return new CommandResult("Date cannot be updated for todo!");
                }

                System.out.println("type new date in the format: dd/MM/yyyy-HHmm" + System.lineSeparator() +
                        Interface.lineBreak);
                LocalDateTime updateDateTime = LocalDateTime.parse(Interface.readInput(), formatter);
                if (updateDateTime.isBefore(LocalDateTime.now())) {
                    throw new DateTimeException("Unfortunately we cannot travel back in Time. " +
                            "Please key in a valid date");
                }
                task.setDate(updateDateTime);
            }
            return new CommandResult("Task updated successfully!");
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("That task does not exist!");
        } catch (NumberFormatException e) {
            Command runCommand = Parser.parseCommand("find " + userCommand[1]);
            CommandResult feedback = runCommand.execute();
            System.out.println(System.lineSeparator() + "perhaps you were looking for these tasks?" +
                    feedback.feedbackToUser);
            return new CommandResult("Please use the correct format [update task_number description(or)date eg, " +
                    "update 1 date]");
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
            return new CommandResult("type new date in the format: dd/MM/yyyy-HHmm");
        }
    }

}
