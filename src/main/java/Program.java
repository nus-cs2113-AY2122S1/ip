import java.util.Objects;
import java.util.Arrays;

public class Program {
    //when true, the program exits
    private boolean terminateHal = false;
    private static String[] taskList = new String[999];
    private static int numOfitems;

    public Program() {
        this.numOfitems = 0;
    }

    public void executeTask(String string) {
        if (Objects.equals(string, "list")) {
            executeList();
        } else if (Objects.equals(string, "bye")) {
            this.executeBye();
        } else {
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
            System.out.println(i + 1 + ": " + taskList[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public static void executeAddTask(String task) {
        System.out.println("____________________________________________________________");
        taskList[numOfitems] = task;
        System.out.println("added: " + taskList[numOfitems]);
        numOfitems++;
        System.out.println("____________________________________________________________");
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
