import DukeUtility.OwlException;
import DukeUtility.Ui;
import TypeOfTasks.Deadline;
import TypeOfTasks.Event;
import TypeOfTasks.Task;
import TypeOfTasks.Todo;

import java.util.ArrayList;

public class TaskList {
    protected int taskCount;
    protected ArrayList<Task> tasks;
    
    public TaskList(ArrayList<Task> tasks) {
        this.taskCount = tasks.size();
        this.tasks = tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }
    
    public void deleteTask(int i) {
        tasks.remove(i);
        taskCount--;
    }
    
    public int getTaskCount() {
        return taskCount;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markCompletionOfTask(TaskList tasks, String s) throws OwlException {
        try {
            int taskNumber = Integer.parseInt(s);
            int taskIndex = taskNumber - 1;
            if (Verifier.isInvalidTaskCount(tasks.taskCount, taskNumber)) {
                throw new OwlException("invalid task number");
            }
            if (tasks.getTask(taskIndex).isDone()) {
                throw new OwlException("Task already done!!");
            }
            tasks.getTask(taskIndex).markDone();
            Ui.printTaskCompletionMsg(taskNumber);
        } catch(NumberFormatException numberFormatException) {
            throw new NumberFormatException("You can only done a task number");
        }
    }

    public void deleteTask(TaskList tasks, String input) throws OwlException {
        try {
            int taskNumber = Integer.parseInt(input);
            int taskIndex = taskNumber - 1;
            if(Verifier.isInvalidTaskCount(tasks.getTaskCount(), taskNumber)) {
                throw new OwlException("invalid task number");
            }
            Task deletedTask = tasks.getTask(taskIndex);
            tasks.deleteTask(taskIndex);
            Ui.printDeletionMsg(tasks.getTaskCount(), deletedTask);
        } catch(NumberFormatException numberFormatException) {
            throw new NumberFormatException("You can only delete a task number!!!");
        }
    }

    public void addTask(int taskCount, String command) {
        Ui.printTaskCount(taskCount, command);
    }

    public void addEvent(TaskList tasks, String[] inputs) throws OwlException {
        String[] inputsAt = inputs[1].split(" /at ", 2);
        if (inputs[1].contains(" /at ") && !inputsAt[1].isEmpty()) {
            Event newEvent = new Event(inputsAt[0], inputsAt[1]);
            tasks.addTask(newEvent);
            addTask(tasks.getTaskCount(), ("[E] " + inputsAt[0] + "(at: " + inputsAt[1]) + ")");
        }
        throw new OwlException("Did not specify /at");
    }

    public void addDeadline(TaskList tasks, String[] inputs) throws OwlException {
        String[] inputsBy = inputs[1].split(" /by ", 2);
        if (inputs[1].contains(" /by ") && !inputsBy[1].isEmpty()) {
            Deadline newDeadline = new Deadline(inputsBy[0], inputsBy[1]);
            tasks.addTask(newDeadline);
            addTask(tasks.getTaskCount(), ("[D] " + inputsBy[0] + "(by: " + inputsBy[1]) + ")");
        }
        throw new OwlException("Did not specify /by");
    }

    public void addTodo(TaskList tasks, String[] inputs) {
        Todo newTodo = new Todo(inputs[1]);
        tasks.addTask(newTodo);
        addTask(tasks.getTaskCount(), ("[T] " + inputs[1]));
    }

    public void listTask(TaskList tasks) {
        if(tasks.getTaskCount() > 0) {
            int listIndex = 1;
            Ui.printListingMsg();
            for(Task task: tasks.getTasks()) {
                task.printList(task,listIndex);
                listIndex++;
            }
            Ui.printLine();
            return;
        }
        System.out.println("There are no task in the list!!!");
    }

}
