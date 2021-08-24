public class TaskManager {
    private Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void addTask(String taskInput){
        Task task = new Task(taskInput);
        tasks[tasksCount++] = task;
        System.out.println("added: " + task.getTask());
    }

    public void listTasks(){
        System.out.println("List of tasks:");
        for(int i=0; i<tasksCount; i++){
            System.out.println(Integer.toString(i+1) + ": " + tasks[i].getTask());
        }
    }


}
