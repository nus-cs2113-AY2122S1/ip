package duke;

import duke.exception.TaskIndexOutOfBound;
import duke.exception.EmptyDoneIndexException;

import java.util.ArrayList;

public class TaskList {
    public static final int MAX_TASKS = 100;
    public  ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task){
        tasks.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task.getTask());
        System.out.println("Now you have " + tasks.size() + " tasks on the list.");
    }

    public boolean searchTask(Task task){
        boolean taskFound = false;
        for(int i=0; i<tasks.size(); i++){
            if(tasks.get(i).getTask().equals(task.getTask()) && !tasks.get(i).isDone()){   
                taskFound = true;
            }
        }
        return taskFound;
    }

    public void deleteTask(String taskDelete) throws TaskIndexOutOfBound,EmptyDoneIndexException {
        int indexOfTask = getIndexOfTask(taskDelete);
        Task taskToDelete = tasks.get(indexOfTask - 1);
        tasks.remove(indexOfTask - 1);
        System.out.println("Noted! I've removed this task: ");
        System.out.println(taskToDelete.getTask());
        System.out.println("Now you have" +  tasks.size()  + "tasks in the list.");
    }

    public void tasksDone(String taskDone) throws TaskIndexOutOfBound,EmptyDoneIndexException {
        int indexOfTask = getIndexOfTask(taskDone);
        tasks.get(indexOfTask-1).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(indexOfTask-1).getTask());
    }

    // Since I find that codes for tasksDone and removeTasks can be duplicated, so I refactor the common part to this function
    // The exception can also be treated together since they are the same kind of exception
    private int getIndexOfTask(String taskDone) throws TaskIndexOutOfBound, EmptyDoneIndexException {
        String[] words = taskDone.split(" ");
        if (words.length == 0 || words.length == 1) {
            throw new EmptyDoneIndexException();
        }
        int indexOfTask = Integer.parseInt(words[1]);
        if(indexOfTask < 1 || indexOfTask > tasks.size()){
            throw new TaskIndexOutOfBound("Ops! your task index is out of bound!");
        }
        return indexOfTask;
    }


    public void listTasks(){
        System.out.println("List of tasks:");
        for(int i=0; i<tasks.size(); i++){
            int id = i + 1;
            System.out.println(id + "." + tasks.get(i).getTask());
        }
    }

    /**
     * get all tasks as string list, in order to write in file to store the data.
     * @return
     */
    public String getAllTasksListFormatted() {
        String data = "";
        for(int i = 0; i < tasks.size(); i++) {
            data = data + tasks.get(i).getTask() + "\n";
        }
        return data;
    }

    /**
     * get all the tasklist in the original input form
     * @return
     */
    public String getAllTasksListOriginal() {
        String data = "";
        for(int i = 0; i < tasks.size(); i++) {
            data = data + tasks.get(i) + "\n";
        }
        return data;
    }

}
