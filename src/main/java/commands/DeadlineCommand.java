package commands;

import parser.DateParser;
import storage.Storage;
import tasks.Deadline;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents the Deadline command. Helps to do all operations of the deadline command such as
 * creating the Deadline object and does all the error handling related to that command.
 */

public class DeadlineCommand extends Command {
    private static final String BY_NOT_PROVIDED = "I could not find '/by dd/MM/YYYY HHmm' in your command!";
    private static final String DEADLINE_TITLE_NOT_PROVIDED = "I could not find the title of your deadline!";
    public static final String COMMAND_SYNTAX = "Command Syntax: deadline <name> /by <date in dd/MM/yyyy HHmm>";

    public String description;
    public String date;

    public DeadlineCommand(String command, String description, String date) {
        super(command);
        this.description = description;
        this.date = date;
    }

    @Override
    public String help() {
        return COMMAND_SYNTAX;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        Date dateObj = DateParser.stringToDateTime(date);
        // Handle case it user did not provide date
        if (dateObj == null) {
            ui.customPrint(BY_NOT_PROVIDED + "\n" + help());
        } else if (description.equals("")) {
            ui.customPrint(DEADLINE_TITLE_NOT_PROVIDED + "\n" + help());
        } else {
            Deadline deadline = new Deadline(description, dateObj);
            tasks.add(deadline);
            ui.customPrint(String.format(addTaskMessage, deadline, tasks.size()));
            storage.saveData(ui, tasks);
        }
    }
}
