package parser.command;

import storage.Storage;
import ui.Ui;
import task.*;

import java.util.HashMap;

public class AddCommand extends Command{

    public AddCommand(HashMap<String, String> params) {
        super(params);
    }

    public void execute(Storage storage, TaskManager taskMgr, Ui ui) {
        Task newTask;

        try {
            newTask = taskMgr.createNewTask(params);
            taskMgr.addTask(newTask);
            storage.updateTaskFile(taskMgr.toFileString());

            printConfirmation(ui, taskMgr, newTask);
        } catch(Exception e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void printConfirmation(Ui ui, TaskManager taskMgr, Task task) {
        ui.printSuccessMessage("Got it. I've added this task: ");
        ui.printMessage(String.format("%s\n", task));

        int numOfTasks = taskMgr.getNumOfTasks();
        String taskPlural = (numOfTasks <= 1) ? "task" : "tasks";

        ui.printMessage(String.format("Now you have %d %s in the list.\n", numOfTasks, taskPlural));
    }
}
