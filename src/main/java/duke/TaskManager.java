package duke;

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

    public void tasksDone(String taskDone){
        String[] words = taskDone.split(" ");
        int indexOfTask = Integer.parseInt(words[1]);
        if(indexOfTask < 1 || indexOfTask > tasksCount){
            System.out.println("Oops! You input an invalid task index (out of range)! Please try again");
            return;
        }
        tasks[indexOfTask-1].markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks[indexOfTask-1].getTask());
    }


    public void listTasks(){
        System.out.println("List of tasks:");
        for(int i=0; i<tasksCount; i++){
            int id = i + 1;
            System.out.println(id + "." + tasks[i].getTask());
        }
    }


}
