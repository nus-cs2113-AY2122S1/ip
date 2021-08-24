import java.util.Objects;
import java.util.Arrays;

public class Program {
    //when true, the program exits
    private boolean terminateHal = false;
    private static Task[] taskList = new Task[999];



    private static int numOfitems;

    public Program() {
        this.numOfitems = 0;
    }

    public static int getNumOfitems() {
        return numOfitems;
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
//            System.out.println("Sorry, I am not smart enough to understand your high level commands! Try something simpler!");
            executeAddTask(string);
        }

    }

    public static void executeList() {
        System.out.println("____________________________________________________________");
        System.out.println("Displaying all items saved:");
        if (numOfitems == 0) {
            System.out.println("No items found...Add some items now!");
        }
        for (int i = 0; i < numOfitems; i++) {
            System.out.println(i + 1 + ": " + taskList[i].getStatusIcon() + " " + taskList[i].description);
        }
        System.out.println("____________________________________________________________");
    }

    public static void executeAddTask(String task) {
        Task newTask = new Task(task);
        System.out.println("____________________________________________________________");
        taskList[numOfitems] = newTask;
        System.out.println("added: " + task);
        numOfitems++;
        System.out.println("____________________________________________________________");
        System.out.print("Enter command: ");
    }

    public void executeDoneTask(String task) {
        int taskNum = Integer.parseInt(task.substring(task.indexOf(' ') + 1));
        if (taskNum > this.getNumOfitems() || taskNum <= 0) {
            System.out.println("No such task exist! Are you sure you keyed in the correct number?");
        } else {
            taskList[taskNum - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskList[taskNum-1].getStatusIcon() + " " + taskList[taskNum-1].description);
        }
    }

    public void executeBye() {
        System.out.println("bye");
        this.setTerminateHal(true);
    }

    public Boolean getTerminateHal() {
        return terminateHal;
    }

    public void setTerminateHal(boolean terminateHal) {
        this.terminateHal = terminateHal;
    }
}
