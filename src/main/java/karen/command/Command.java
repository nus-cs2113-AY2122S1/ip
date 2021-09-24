package karen.command;

import karen.exception.ErrorCheck;
import karen.program.ProgramManager;
import karen.storage.Storage;
import karen.exception.IncorrectDescriptionFormatException;
import karen.exception.NoDescriptionException;
import karen.tasklist.TaskList;
import karen.ui.Ui;
import karen.tasklist.task.Deadline;
import karen.tasklist.task.Event;
import karen.tasklist.task.Task;
import karen.tasklist.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Command {
    private String command;
    private TaskList taskList;
    private ArrayList<Task> tasks;
    private ProgramManager programManager;

    public Command(String command, ProgramManager programManager, TaskList taskList) {
        this.command = command;
        this.taskList = taskList;
        this.tasks = taskList.getTaskList();
        this.programManager = programManager;
    }

    public String getCommand() {
        return command;
    }

    public void executeFindCommand(String keyword, ArrayList<Task> tasks) {
        List<Task> filteredTasks = tasks.stream()
                .filter((t) -> t.getTask().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        Ui.printFoundTasks(filteredTasks, keyword);
    }

    public void executeDoneCommand(int doneIndex) throws NumberFormatException, IndexOutOfBoundsException, IOException {
        if (!tasks.get(doneIndex).getIsDone()) {
            tasks.get(doneIndex).markAsDone();
            Ui.printTaskDoneMessage(tasks.get(doneIndex));
        } else {
            Ui.printTaskAlreadyDoneMessage();
        }

        Storage.writeToFile(taskList);
    }

    public void executeDeleteCommand(int deleteIndex) throws NumberFormatException, IndexOutOfBoundsException, IOException {
        Task taskToDelete = tasks.get(deleteIndex);
        taskList.removeTask(deleteIndex);

        //number of tasks after deleting
        int totalTasks = tasks.size();
        Ui.printTaskDeletedMessage(taskToDelete, totalTasks);

        Storage.writeToFile(taskList);
    }

    public void executeListCommand() {
        Ui.printTaskList(tasks);
    }

    public void executeToDoCommand(ToDo todo) throws IOException {
        taskList.addTask(todo);

        int totalTasks = taskList.getSize();
        Ui.printTaskAddedMessage(todo, totalTasks);

        Storage.writeToFile(taskList);
    }

    public void executeDeadlineCommand(Deadline deadline) throws IOException {
        taskList.addTask(deadline);

        int totalTasks = taskList.getSize();
        Ui.printTaskAddedMessage(deadline, totalTasks);

        Storage.writeToFile(taskList);
    }

    public void executeEventCommand(Event event) throws IOException {
        taskList.addTask(event);

        int totalTasks = taskList.getSize();
        Ui.printTaskAddedMessage(event, totalTasks);

        Storage.writeToFile(taskList);
    }

    public void executeByeCommand(String rawUserInput) throws IncorrectDescriptionFormatException, NoDescriptionException {
        String[] inputWords = rawUserInput.split(" ");
        ErrorCheck.checkCommandDescriptionExceptions("bye", rawUserInput);
        Ui.printGoodByeMessage();
        programManager.setIsRunningOff();
    }

}
