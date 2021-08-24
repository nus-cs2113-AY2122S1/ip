public class TaskManager {

    private Task[] tasks = new Task[100];
    private int completedTasksCount = 0;
    private int tasksCount = 0;

    public void printTasks() {
        if (tasksCount == 0){
            System.out.println("\tYou have no tasks in your to-do list at the moment\n");
            return;
        }
        System.out.println("\tThis is your current to-do list");
        for (int i = 0; i < tasksCount; i++) {
            System.out.print("\t" + (i + 1) + ".[");
            if (tasks[i].isDone()){
                System.out.print("X] ");
            } else {
                System.out.print(" ] ");
            }
            System.out.println(tasks[i].getName());
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

    public void markAsDone(int inputTaskIndex){
        int taskIndex = inputTaskIndex - 1;
        tasks[taskIndex].setDone();
        completedTasksCount++;

        // Print name of task to system output
        System.out.println("\tGood job! I have marked your task as done.");
        System.out.println("\t\t[X] " + tasks[taskIndex].getName());
        System.out.println("\tYou have " + (tasksCount - completedTasksCount) + " uncompleted task(s) left in your to-do list");
    }
}
