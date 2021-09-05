package command;

import manager.MessageManager;
import manager.TaskManager;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class Command {
    private String command;
    private TaskManager taskManager;
    private Task[] taskList;

    public Command(String command, TaskManager taskManager) {
        this.command = command;
        this.taskManager = taskManager;
        this.taskList = taskManager.getTaskList();
    }

    public String getCommand() {
        return command;
    }

//    // Check if the Done Command is valid and returns true/false accordingly
//    public boolean checkValidDoneCommand(String input, int taskListIndex) {
//        String[] inputSplit = input.split(" ");
//        try {
//            int doneIndex = Integer.parseInt(inputSplit[1]);
//            // if number in the input is not in the range of list, throw error and ask for input again
//            if (doneIndex < 1 || doneIndex > taskListIndex) {
//                return false;
//            }
//        } catch (NumberFormatException e) {
//            return false;
//        }
//        return true;
//    }
//
//    // reads Done Command then prints out corresponding statements
//    public void executeDoneCommand(int taskListIndex, String rawUserInput) {
//        // split the input string into array
//        String[] inputWords = rawUserInput.split(" ");
//        // check if the done command is valid
//        boolean isValid = checkValidDoneCommand(rawUserInput, taskListIndex);
//
//        //valid done command
//        if (isValid) {
//            int doneIndex = Integer.parseInt(inputWords[1]) - 1;
//
//            // task has not been marked as done and needs to be marked as done
//            if (!taskManager.getTaskList()[doneIndex].getIsDone()) {
//                taskManager.getTaskList()[doneIndex].markAsDone();
//                System.out.println("    Alright! I've marked this task as done! :)");
//                System.out.printf("    [%s] %s\n", taskManager.getTaskList()[doneIndex].getStatusIcon(), taskManager.getTaskList()[doneIndex].getFormattedDescription());
//            }else{
//                System.out.println("    task.Task is already marked as done!");
//            }
//        }
//
//        // not a valid done command
//        else{
//            System.out.println("    Command is not valid :( Please specify a valid task number to be marked as done.");
//        }
//    }

    public void executeDoneCommand(int taskListIndex, String rawUserInput) {
        // split the input string into array
        String[] inputWords = rawUserInput.split(" ");
        int doneIndex = Integer.parseInt(inputWords[1]) - 1;

        // task has not been marked as done and needs to be marked as done
        if (!taskList[doneIndex].getIsDone()) {
            taskList[doneIndex].markAsDone();
            MessageManager.printTaskDoneMessage(taskList[doneIndex]);
        } else{
            MessageManager.printTaskAlreadyDoneMessage();
        }
    }

    // reads List Command then prints out all the tasks
    public void executeListCommand(int taskListIndex) {
        MessageManager.printTaskList(taskListIndex, taskList);
    }

    public void executeToDoCommand(int taskListIndex, String fullTaskDescription) {
        ToDo task = new ToDo(fullTaskDescription);
        taskManager.addTask(task, taskListIndex);
        MessageManager.printTaskAddedMessage(task, Task.getTotalTasks());
    }

    public void executeDeadlineCommand(int taskListIndex, String fullTaskDescription) {
        Deadline task = new Deadline(fullTaskDescription);
        taskManager.addTask(task, taskListIndex);
        MessageManager.printTaskAddedMessage(task, Task.getTotalTasks());
    }

    public void executeEventCommand(int taskListIndex, String fullTaskDescription) {
        Event task = new Event(fullTaskDescription);
        taskManager.addTask(task, taskListIndex);
        MessageManager.printTaskAddedMessage(task, Task.getTotalTasks());
    }
}
