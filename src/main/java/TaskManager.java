public class TaskManager {

    private Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void printTasks() {
        if (tasksCount == 0){
            System.out.println("\tYou have no tasks in your to-do list at the moment\n");
            return;
        }
        System.out.println("\tThis is your current to-do list");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks[i].getName());
        }
        System.out.println("\tThere are " + tasksCount + " task(s) in your to-do list\n");
    }

    public void addTask(Task task) {
        // add new task to tasks array
        tasks[tasksCount] = task;
        tasksCount++;

        // print name of task to system output
        System.out.println("\tYour task has been added: " + task.getName());
        System.out.println("\tThere are " + tasksCount + " task(s) in your to-do list\n");
    }
}
