public class TaskManager {
    private static int noTasks = 0;
    private static int maxTasks = 100;
    private static String[] tasks = new String[maxTasks];

    static void add(String task) {
        if (noTasks < maxTasks) {
            tasks[noTasks] = task;
            noTasks++;
            System.out.printf("added: %s\n", task);
        }
    }

    static void list() {
        for (int i = 0; i < noTasks; i++) {
            System.out.printf("%d. %s\n",i+1, tasks[i]);
        }
    }
}
