import java.util.Objects;

public class Program {
    private boolean canTerminateHal = false;    //when true, the program exits
    private static Task[] listTasks = new Task[999];
    private static int numItems;

    public Program() {
        this.numItems = 0;
    }

    public static int getNumItems() {
        return numItems;
    }

    public void executeTask(String string) {
        if (Objects.equals(string, "list")) {
            executeList();
        } else if (Objects.equals(string, "bye")) {
            this.executeBye();
        } else if (string.contains("done")) {
            this.executeDoneTask(string);
        }
        else {
            executeAddTask(string);
        }

    }

    public static void executeList() {
        System.out.println("____________________________________________________________");
        System.out.println("Displaying all items saved:");
        if (numItems == 0) {
            System.out.println("No items found...Add some items now!");
        }
        for (int i = 0; i < numItems; i++) {
            System.out.println(i + 1 + ": " + listTasks[i].getStatusIcon() + " " + listTasks[i].description);
        }
        System.out.println("____________________________________________________________");
    }

    public static void executeAddTask(String task) {
        Task newTask = new Task(task);
        System.out.println("____________________________________________________________");
        listTasks[numItems] = newTask;
        System.out.println("added: " + task);
        numItems++;
        System.out.println("____________________________________________________________");
        System.out.print("Enter command: ");
    }

    public void executeDoneTask(String task) {
        int taskNum = Integer.parseInt(task.substring(task.indexOf(' ') + 1));
        if (taskNum > this.getNumItems() || taskNum <= 0) {
            System.out.println("No such task exist! Are you sure you keyed in the correct number?");
        } else {
            listTasks[taskNum - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(listTasks[taskNum-1].getStatusIcon() + " " + listTasks[taskNum-1].description);
        }
    }

    public void executeBye() {
        System.out.println("bye");
        this.setCanTerminateHal(true);
    }

    public Boolean getCanTerminateHal() {
        return canTerminateHal;
    }

    public void setCanTerminateHal(boolean canTerminateHal) {
        this.canTerminateHal = canTerminateHal;
    }
}
