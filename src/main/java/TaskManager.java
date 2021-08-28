public class TaskManager {

    private static final int MAX_TASKS_COUNT = 100;
    private static int currentTasksCount = 0;

    private Task[] tasks = new Task[MAX_TASKS_COUNT];

    public void addTask(String command, String taskContent) {
        /*if (currentTasksCount < MAX_TASKS_COUNT) {
            tasks[currentTasksCount] = new Task(task);
            currentTasksCount++;
            System.out.println("You have added a new task: " + task);
        } else {
            System.out.println("You are too busy! Please clear some of your tasks first...");
        }*/

        InputFilter filter = new InputFilter();
        String[] filteredTaskContent;

        switch (command) {
        case "todo":
            tasks[currentTasksCount] = new ToDo(taskContent);
            System.out.println("You have added a new task: " + taskContent);
            break;
        case "deadline":
            filteredTaskContent = filter.separateDate(taskContent);
            tasks[currentTasksCount] = new Deadline(filteredTaskContent[0], filteredTaskContent[1]);
            System.out.println("You have added a new task: " + filteredTaskContent[0]);
            break;
        case "event":
            filteredTaskContent = filter.separateDate(taskContent);
            tasks[currentTasksCount] = new Event(filteredTaskContent[0], filteredTaskContent[1]);
            System.out.println("You have added a new task: " + filteredTaskContent[0]);
            break;
        }
        currentTasksCount++;
    }

    public void listTasks() {
        System.out.println("You have the following tasks:");
        for (int i = 0; i < currentTasksCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
    }

    public void markTaskAsDone(int taskIndex) {
        tasks[taskIndex - 1].setDone();
        System.out.println("Good job! You have finished the following:");
        System.out.println(tasks[taskIndex - 1].toString());
    }
}
