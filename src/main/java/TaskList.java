import DukeUtility.OwlException;
import DukeUtility.Ui;
import TypeOfTasks.Deadline;
import TypeOfTasks.Event;
import TypeOfTasks.Task;
import TypeOfTasks.Todo;


import java.util.ArrayList;


/**
 * Represents a list of different types of task with a counter that keep tracks of how many tasks there are.
 */
public class TaskList {
    protected int taskCount;
    protected ArrayList<Task> tasks;


    /**
     * Constructs a TaskList with a given ArrayList of task and initialise counter as the size of input ArrayList.
     * 
     * @param tasks An ArrayList containing Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskCount = tasks.size();
        this.tasks = tasks;
    }
    
    /**
     * Returns the ArrayList of Task objects from the TaskList object.
     * 
     * @return An ArrayList containing Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints out the entire list of tasks containing the keyword the user is searching for.
     * 
     * @param keyword Keyword that matches any part of the task's description, user input to find task.
     * @param tasks A list of different types of task with a counter that keep tracks of how many tasks there are.
     */
    public void findTask(String keyword, TaskList tasks) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for(Task task: tasks.getTasks()) {
            if(task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }

            boolean isFoundInDeadlineOrEvent = task.getInfo() != null && task.getInfo().contains(keyword);
            if(isFoundInDeadlineOrEvent) {
                foundTasks.add(task);
            }
            
        }
        try {
            listTask(new TaskList(foundTasks));
        } catch (OwlException owlException) {
            System.out.println("No task was found that matches!!");
        }
    }

    /**
     * Set the completion of a task in the TaskList as done and prints a completion message to show that the process
     * has been achieved.
     *
     * @param tasks A list of different types of task with a counter that keep tracks of how many tasks there are.
     * @param taskNumberAsString The task number as a string given as an input by the user.
     * @throws OwlException If taskNumber input is out of range,not a number or when the task is already marked as done.
     */
    public void markCompletionOfTask(TaskList tasks, String taskNumberAsString) throws OwlException {        try {
            int taskNumber = Integer.parseInt(taskNumberAsString);
            int taskIndex = taskNumber - 1;
            if (Verifier.isInvalidTaskCount(tasks.taskCount, taskNumber)) {
                throw new OwlException("You have keyed in an invalid task number!!!");
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

    /**
     * Deletes a task from the TaskList.
     * 
     * @param tasks A list of different types of task with a counter that keep tracks of how many tasks there are.
     * @param taskNumberAsString The task number as a string given as an input by the user.
     * @throws OwlException If taskNumber input is out of range or when the taskNumber is not a number.
     */
    public void deleteTask(TaskList tasks, String taskNumberAsString) throws OwlException {
        try {
            int taskNumber = Integer.parseInt(taskNumberAsString);
            int taskIndex = taskNumber - 1;
            if(Verifier.isInvalidTaskCount(tasks.getTaskCount(), taskNumber)) {
                throw new OwlException("Invalid task number");
            }
            Task deletedTask = tasks.getTask(taskIndex);
            tasks.deleteTask(taskIndex);
            Ui.printDeletionMsg(tasks.getTaskCount(), deletedTask);
        } catch(NumberFormatException numberFormatException) {
            throw new NumberFormatException("You can only delete a task number!!!");
        }
    }

    /**
     * Adds a newly created Event object into the TaskList and prints a message of what kind of event was added.
     *
     * @param tasks A list of different types of task with a counter that keep tracks of how many tasks there are.
     * @param inputs An array representing the different parts of the user input.
     * @throws OwlException If /at is not specified in the user input.
     */
    public void addEvent(TaskList tasks, String[] inputs) throws OwlException {
        String[] inputsAt = inputs[1].split(" /at ", 2);
        if (inputs[1].contains(" /at ") && !inputsAt[1].isEmpty()) {
            Event newEvent = new Event(inputsAt[0], inputsAt[1]);
            tasks.addTask(newEvent);
            addTask(tasks.getTaskCount(), "[E] " + inputsAt[0] + "(at: " + newEvent.getInfo() + ")");
            return;
        }
        throw new OwlException("Did not specify /at");
    }

    /**
     * Adds a newly created Deadline object into the TaskList and prints a message of what kind of deadline was added.
     *
     * @param tasks A list of different types of task with a counter that keep tracks of how many tasks there are.
     * @param inputs An array representing the different parts of the user input.
     * @throws OwlException If /by is not specified in the user input.
     */
    public void addDeadline(TaskList tasks, String[] inputs) throws OwlException {
        String[] inputsBy = inputs[1].split(" /by ", 2);
        
        if (inputs[1].contains(" /by ") && !inputsBy[1].isEmpty()) {
            Deadline newDeadline = new Deadline(inputsBy[0],inputsBy[1]);
            tasks.addTask(newDeadline);
            addTask(tasks.getTaskCount(), "[D] " + inputsBy[0] + "(by: " + newDeadline.getInfo() + ")");
            return;
        }
        throw new OwlException("Did not specify /by");
    }

    /**
     * Adds a newly created Todo object into the TaskList and prints a message of what kind of Todo was added.
     * 
     * @param tasks A list of different types of task with a counter that keep tracks of how many tasks there are.
     * @param inputs An array representing the different parts of the user input.
     */
    public void addTodo(TaskList tasks, String[] inputs) {
        Todo newTodo = new Todo(inputs[1]);
        tasks.addTask(newTodo);
        addTask(tasks.getTaskCount(), ("[T] " + inputs[1]));
    }

    /**
     * List out every Task's details in the given taskList.
     * 
     * @param tasks A list of different types of task with a counter that keep tracks of how many tasks there are.
     * @throws OwlException If there is no task in the TaskList.
     */
    public void listTask(TaskList tasks) throws OwlException{
        if(tasks.getTaskCount() > 0) {
            int listIndex = 1;
            Ui.printListingMsg();
            for(Task task: tasks.getTasks()) {
                task.printTaskDetails(task,listIndex);
                listIndex++;
            }
            Ui.printLine();
            return;
        }
        throw new OwlException("There are no task in the list!!!");
    }

    private Task getTask(int i) {
        return tasks.get(i);
    }

    private void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    private void deleteTask(int i) {
        tasks.remove(i);
        taskCount--;
    }

    private int getTaskCount() {
        return taskCount;
    }

    private void addTask(int taskCount, String command) {
        Ui.printAddedMsg(taskCount, command);
    }

}
