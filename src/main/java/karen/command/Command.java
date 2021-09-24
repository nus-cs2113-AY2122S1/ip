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

    /**
     * This method marks the Task object with the specified doneIndex in the taskList as done.
     *
     * @param doneIndex index of the Task object to be marked as done
     * @throws NumberFormatException if anything other than numbers are used as the index for the Task object
     * @throws IndexOutOfBoundsException if the doneIndex is > size of taskList and < 1, ie. there is no Task
     * object with doneIndex as their index
     * @throws IOException if there are errors when writing to storage file
     */
    public void executeDoneCommand (int doneIndex) throws NumberFormatException, IndexOutOfBoundsException, IOException {
        if (!tasks.get(doneIndex).getIsDone()) {
            tasks.get(doneIndex).markAsDone();
            Ui.printTaskDoneMessage(tasks.get(doneIndex));
        } else {
            Ui.printTaskAlreadyDoneMessage();
        }

        Storage.writeToFile(taskList);
    }

    /**
     * This method removes the Task object with the specified deleteIndex from the taskList.
     * Any changes to taskList is then saved in the storage file.
     *
     * @param deleteIndex index of the Task object to be removed from taskList
     * @throws NumberFormatException if anything other than numbers are used as the index for the Task object
     * @throws IndexOutOfBoundsException if the doneIndex is > size of taskList and < 1, ie. there is no Task
     * object with doneIndex as their index
     * @throws IOException if there are errors when writing to storage file
     */
    public void executeDeleteCommand(int deleteIndex) throws NumberFormatException, IndexOutOfBoundsException, IOException {
        Task taskToDelete = tasks.get(deleteIndex);
        taskList.removeTask(deleteIndex);

        //number of tasks in taskList left after deleting
        int totalTasks = tasks.size();
        Ui.printTaskDeletedMessage(taskToDelete, totalTasks);

        Storage.writeToFile(taskList);
    }

    /**
     * This method prints all Task objects in the taskList according to their formatted description.
     */
    public void executeListCommand() {
        Ui.printTaskList(tasks);
    }

    /**
     * This method adds a ToDo task object into the taskList. Any changes to taskList is then
     * saved in the storage file.
     *
     * @param todo ToDo task object to be added into taskList
     * @throws IOException if there are errors when writing to storage file
     */
    public void executeToDoCommand(ToDo todo) throws IOException {
        taskList.addTask(todo);

        int totalTasks = taskList.getSize();
        Ui.printTaskAddedMessage(todo, totalTasks);

        Storage.writeToFile(taskList);
    }

    /**
     * This method adds a Deadline task object into the taskList. Any changes to taskList is then
     * saved in the storage file.
     *
     * @param deadline Deadline task object to be added into taskList
     * @throws IOException if there are errors when writing to storage file
     */
    public void executeDeadlineCommand(Deadline deadline) throws IOException {
        taskList.addTask(deadline);

        int totalTasks = taskList.getSize();
        Ui.printTaskAddedMessage(deadline, totalTasks);

        Storage.writeToFile(taskList);
    }

    /**
     * This method adds a Event task object into the taskList. Any changes to taskList is then
     * saved in the storage file.
     *
     * @param event Event task object to be added into taskList
     * @throws IOException if there are errors when writing to storage file
     */
    public void executeEventCommand(Event event) throws IOException {
        taskList.addTask(event);

        int totalTasks = taskList.getSize();
        Ui.printTaskAddedMessage(event, totalTasks);

        Storage.writeToFile(taskList);
    }

    /**
     * This method ends the program.
     */
    public void executeByeCommand(){
        Ui.printGoodByeMessage();
        programManager.setIsRunningOff();
    }

}
