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
            System.out.print("\t" + (i + 1) + ".");
            tasks[i].printTask();
        }
        System.out.println("\tThere are " + tasksCount + " task(s) in your to-do list\n");
    }

    public void addTask(Task task) {
        // add new task to tasks array
        tasks[tasksCount] = task;

        // print name of task to system output
        System.out.print("\tGot it! I've added this task for you:\n\t\t");
        tasks[tasksCount].printTask();

        // increment total tasksCount
        tasksCount++;
        System.out.println("\tThere are " + tasksCount + " task(s) in your to-do list\n");
    }

    public void markAsDone(int inputTaskIndex){
        int taskIndex = inputTaskIndex - 1;
        tasks[taskIndex].setDone();
        completedTasksCount++;

        // Print name of task to system output
        System.out.print("\tGood job! I have marked your task as done.\n\t\t");
        tasks[taskIndex].printTask();
        System.out.println("\tYou have " + (tasksCount - completedTasksCount) + " uncompleted task(s) left in your to-do list");
    }
}
