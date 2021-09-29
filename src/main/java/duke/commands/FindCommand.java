package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.MissingSearchTermException;
import duke.tasks.Task;

public class FindCommand extends Command {
    public static final int END_OF_FIND_INDEX = 4;

    public FindCommand(String fullCommand) {
        this.isExit = false;
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            find(tasks, ui, storage);
        } catch(DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public void find(TaskList tasks, Ui ui, Storage storage) throws MissingSearchTermException {
        String searchKey = fullCommand.substring(END_OF_FIND_INDEX).trim();
        if (searchKey.equals("")) {
            throw new MissingSearchTermException();
        }
        int taskNumber = 1;
        System.out.println("Here are the matching tasks in your list:");
        for(Task task : tasks.getTasks()) {
            if(task.getDescription().contains(searchKey)) {
                System.out.println((taskNumber) + "." + task);
            }
            taskNumber++;
        }
    }
}
