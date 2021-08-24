public class TaskManager {
    private Task[] tasks = new Task[100];
    private int tasksCount = 0;


    public void addTask(String taskInput){
        Task task = new Task(taskInput);
        tasks[tasksCount++] = task;
        System.out.println("added: " + task.getTask());
    }

    public boolean searchTask(String taskInput){
        boolean taskFound = false;
        for(int i=0; i<tasksCount; i++){
            if(tasks[i].getTask().equals(taskInput) && !tasks[i].isDone()){   //be aware that maybe it is added but it is done, so your can add another one
                taskFound = true;
            }
        }
        return taskFound;
    }

    public void tasksDone(String taskDone){
        String[] words = taskDone.split(" ");
        int indexTask = Integer.parseInt(words[1]);
        if(indexTask < 1 || indexTask > tasksCount){
            System.out.println("Oops! You input an invalid task index (out of range)! Please try again");
            return;
        }
        tasks[indexTask-1].markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  [X] " + tasks[indexTask-1].getTask());
    }


    public void listTasks(){
        System.out.println("List of tasks:");
        for(int i=0; i<tasksCount; i++){
            if(tasks[i].isDone()) {
                System.out.println("[X] " + Integer.toString(i+1) + ": " + tasks[i].getTask());
            }
            else{
                System.out.println("[ ] " + Integer.toString(i+1) + ": " + tasks[i].getTask());
            }
        }
    }


}
