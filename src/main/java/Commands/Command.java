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

public class Command {

    public static void executeCommand (ParseInput parseInput, List<Task> tasks, Storage storage) {
        int taskIndex = -1;

        if (parseInput == null) {
            Duke.isLoading = false;
            return;
        }

        switch (parseInput.parseResult) {

        //---------- Query Commands ----------//
        case BYE :
            UI.dukeGoodbye();
            Duke.isExit = true;
            break;

        case DONE :
            taskIndex = Integer.parseInt(parseInput.userInput.replaceAll("[^0-9]", ""));
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
            taskIndex = Integer.parseInt(parseInput.userInput.replaceAll("[^0-9]", ""));

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

        //---------- addTask Commands ----------//
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
}
