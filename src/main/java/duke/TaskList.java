package duke;

import duke.exception.TaskIndexOutOfBound;
import duke.exception.EmptyDoneIndexException;

import java.util.ArrayList;


/**
 * A class containing the methods necessary for performing operations on task objects
 * Contains ArrayList for storing all the tasks and their status.
 */
public class TaskList {
    public static final int MAX_TASKS = 100;
    public static final int KEY_WORD_BEGIN_INDEX = 5;
    public  ArrayList<Task> tasks = new ArrayList<>();


    /**
     * Adds a new task (including deadline, event, todo) to the taskList
     * @param task a specific task object, can be deadline, event, todo
     */
    public void addTask(Task task){
        tasks.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task.getTask());
        System.out.println("Now you have " + tasks.size() + " tasks on the list.");
    }

    /**
     * Searches for a specific task by its content to see if the task has already been added to the list
     * @param task the task user wants to add
     * @return Nothing
     */
    public boolean searchTask(Task task){
        boolean taskFound = false;
        for (int i=0; i<tasks.size(); i++) {
            if (tasks.get(i).getTask().equals(task.getTask()) && !tasks.get(i).isDone()) {
                taskFound = true;
            }
        }
        return taskFound;
    }

    public void findByKeyWord(String taskToFind) {
        String keyword = taskToFind.substring(KEY_WORD_BEGIN_INDEX);
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTask().contains(keyword)) {
                System.out.println(tasks.get(i).getTask());
            }
        }
    }

   /**
    * Deletes a task from the taskList
    * @param taskDelete the task user wants to delete
    * @throws TaskIndexOutOfBound
    * @throws EmptyDoneIndexException
    */
    public void deleteTask(String taskDelete) throws TaskIndexOutOfBound,EmptyDoneIndexException {
        int indexOfTask = getIndexOfTask(taskDelete);
        Task taskToDelete = tasks.get(indexOfTask - 1);
        tasks.remove(indexOfTask - 1);
        System.out.println("Noted! I've removed this task: ");
        System.out.println(taskToDelete.getTask());
        System.out.println("Now you have" +  tasks.size()  + "tasks in the list.");
    }

    /**
     * Marks the task that the user completes as done
     * @param taskDone  the task that the user completes
     * @throws TaskIndexOutOfBound
     * @throws EmptyDoneIndexException
     */
    public void tasksDone(String taskDone) throws TaskIndexOutOfBound,EmptyDoneIndexException {
        int indexOfTask = getIndexOfTask(taskDone);
        tasks.get(indexOfTask-1).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(indexOfTask-1).getTask());
    }

    /**
     * Gets the index of the task that the user wants to do something with
     * @param task the task that the user wants to do something with
     * @return the index of the task
     * @throws TaskIndexOutOfBound
     * @throws EmptyDoneIndexException
     */
    private int getIndexOfTask(String task) throws TaskIndexOutOfBound, EmptyDoneIndexException {
        String[] words = task.split(" ");
        if (words.length == 0 || words.length == 1) {
            throw new EmptyDoneIndexException();
        }
        int indexOfTask = Integer.parseInt(words[1]);
        if(indexOfTask < 1 || indexOfTask > tasks.size()){
            throw new TaskIndexOutOfBound("Ops! your task index is out of bound!");
        }
        return indexOfTask;
    }

    /**
     * Prints the list of all the tasks and their status
     */
    public void listTasks(){
        System.out.println("List of tasks:");
        for(int i=0; i<tasks.size(); i++){
            int id = i + 1;
            System.out.println(id + "." + tasks.get(i).getTask());
        }
    }

    /**
     * get all tasks as string list, in order to write in file to store the data.
     * @return string of all tasks as displayed
     */
    public String getAllTasksListFormatted() {
        String data = "";
        for(int i = 0; i < tasks.size(); i++) {
            data = data + tasks.get(i).getTask() + "\n";
        }
        return data;
    }

    /**
     * get all the taskList in the original input form
     * @return string of all tasks as the original strings that user inputs
     */
    public String getAllTasksListOriginal() {
        String data = "";
        for(int i = 0; i < tasks.size(); i++) {
            data = data + tasks.get(i) + "\n";
        }
        return data;
    }

}
