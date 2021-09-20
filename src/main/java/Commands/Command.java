package Commands;

import Parsing.ParseInput;
import Storage.Storage;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import UI.UI;
import Main.Duke;

import java.util.List;

/**
 * Utility class indicating how Duke responds to commands made by User
 */
public class Command {

    /**
     * Utility function for executing the commands that Duke has received (after Parsing)
     *
     * @param parseInput  User input that has been parsed appropriately
     * @param tasks       User's tasks in Duke
     * @param storage     Actions involving read/write to hard drive
     */
    public static void executeCommand (ParseInput parseInput, List<Task> tasks, Storage storage) {
        int taskIndex = -1;

        if (parseInput == null) {
            Duke.isLoading = false;
            return;
        }

        switch (parseInput.parseResult) {

        //---------- Non-Insertion Commands ----------//
        case BYE :
            UI.dukeGoodbye();
            Duke.isExit = true;
            break;

        case DONE :
            taskIndex = findTaskIndex(parseInput);
            tasks.get(taskIndex - 1).markAsDone();

            if (!Duke.isLoading) {
                storage.saveCommand(parseInput.userInput);
                UI.doneTask(tasks, taskIndex);
            }
            break;

        case LIST :
            UI.listTask(tasks);
            break;

        case DELETE :
            taskIndex = findTaskIndex(parseInput);

            if (!Duke.isLoading) {
                storage.saveCommand(parseInput.userInput);
                UI.deletedTask(tasks, taskIndex);
            }

            else {
                tasks.remove(taskIndex -1);
                Task.numberOfTasks -= 1;
            }
            break;

        case FIND :
            UI.searchTask(tasks, parseInput.userInput);
            break;

        //---------- Insertion Commands ----------//
        case TODO :
            Task todoTask = new Todo(parseInput.userInput.replaceAll(parseInput.taskType, ""));
            tasks.add(todoTask);
            if (!Duke.isLoading) {
                storage.saveCommand(parseInput.userInput);
                UI.addedTask(todoTask);
            }
            break;

        case DEADLINE :
            Task deadlineTask = new Deadline(parseInput.descriptionAndTime.split("/")[0], parseInput.descriptionAndTime.split("/")[1]);
            tasks.add(deadlineTask);
            if (!Duke.isLoading) {
                storage.saveCommand(parseInput.userInput);
                UI.addedTask(deadlineTask);
            }
            break;

        case EVENT :
            Task eventTask = new Event(parseInput.descriptionAndTime.split("/")[0], parseInput.descriptionAndTime.split("/")[1]);
            tasks.add(eventTask);
            if (!Duke.isLoading) {
                storage.saveCommand(parseInput.userInput);
                UI.addedTask(eventTask);
            }
            break;
        }
    }

    /**
     * Given a ParseInput object, finds the index (in the list) of the corresponding Task
     *
     * @param parseInput    User input that has been parsed appropriately
     * @return              Index of corresponding task
     */
    private static int findTaskIndex (ParseInput parseInput){
        return (Integer.parseInt(parseInput.userInput.replaceAll("^[0-9]", "")));
    }
}
