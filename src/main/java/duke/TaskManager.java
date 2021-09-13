package duke;

import duke.exception.TaskIndexOutOfBound;

public class TaskManager {
    public static final int MAX_TASKS = 100;
    private Task[] tasks = new Task[MAX_TASKS];
    private int tasksCount = 0;


    public void addTask(Task task){
        tasks[tasksCount++] = task;
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task.getTask());
        System.out.println("Now you have " + tasksCount + " tasks on the list.");
    }

    public boolean searchTask(Task task){
        boolean taskFound = false;
        for(int i=0; i<tasksCount; i++){
            if(tasks[i].getTask().equals(task.getTask()) && !tasks[i].isDone()){   //be aware that maybe it is added but it is done, so your can add another one
                taskFound = true;
            }
        }
        return taskFound;
    }

    public void deleteTask(String taskDelete) throws TaskIndexOutOfBound {
        int indexOfTask = getIndexOfTask(taskDelete);
        Task taskToDelete = tasks[indexOfTask - 1];
        for(int i = indexOfTask; i < tasksCount; i++) {
            tasks[i - 1] = tasks[i];
        }
        tasksCount--;
        System.out.println("Noted! I've removed this task: ");
        System.out.println(taskToDelete.getTask());
        System.out.println("Now you have" +  tasksCount  + "tasks in the list.");
    }

    public void tasksDone(String taskDone) throws TaskIndexOutOfBound {
        int indexOfTask = getIndexOfTask(taskDone);
        tasks[indexOfTask-1].markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks[indexOfTask-1].getTask());
    }

    // Since I find that codes for tasksDone and removeTasks can be duplicated, so I refactor the common part to this function
    // The exception can also be treated together since they are the same kind of exception
    private int getIndexOfTask(String taskDone) throws TaskIndexOutOfBound {
        String[] words = taskDone.split(" ");
        int indexOfTask = Integer.parseInt(words[1]);
        if(indexOfTask < 1 || indexOfTask > tasksCount){
            throw new TaskIndexOutOfBound("Ops! your task index is out of bound!");
        }
        return indexOfTask;
    }


    public void listTasks(){
        System.out.println("List of tasks:");
        for(int i=0; i<tasksCount; i++){
            int id = i + 1;
            System.out.println(id + "." + tasks[i].getTask());
        }
    }


}
