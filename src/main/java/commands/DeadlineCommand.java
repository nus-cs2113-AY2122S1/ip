package commands;

import parser.DateParser;
import storage.Storage;
import tasks.Deadline;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;
import java.util.Date;

public class DeadlineCommand extends Command {
    private static final String byNotProvided = "I could not find '/by dd/MM/YYYY HHmm' in your command!";
    private static final String deadlineTitleNotProvided = "I could not find the title of your deadline!";
    public static final String commandSyntax = "Command Syntax for Deadline: deadline <name> "
            + "/by <date in dd/MM/YYYY HHmm>";

    public String description;
    public String date;

    public DeadlineCommand(String command, String description, String date) {
        super(command);
        this.description = description;
        this.date = date;
    }

    @Override
    public String help() {
        return commandSyntax;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        Date dateObj = DateParser.stringToDateTime(date);
        // Handle case it user did not provide date
        if (dateObj == null) {
            ui.customPrint(byNotProvided + "\n" + help());
        } else if (description.equals("")) {
            ui.customPrint(deadlineTitleNotProvided + "\n" + help());
        } else {
            Deadline deadline = new Deadline(description, dateObj);
            tasks.add(deadline);
            ui.customPrint(String.format(addTaskMessage, deadline, tasks.size()));
            storage.saveData(ui, tasks);
        }
    }
}
