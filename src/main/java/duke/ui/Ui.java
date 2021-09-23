package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    public static final String LINE =
            "    ____________________________________________________________" + System.lineSeparator();

    public static final String PADDING = "     ";

    public static final String LOGO =
              PADDING + "MMMMMMMMMMMMMMMMMMMMNmd+h/....`.omohMMMMMMMMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMMMMNs.    `-`  `  `.  -sNMMMMMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMMMN-  ` ` ...`.-````   `:oMMMMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMN/   ` -+hmNNNNNNdh+.```  yMMMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMN   `/mMMMNmNNNNMNNNNy:`  .hMMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMh..-sNNMMNN+mNNNMMNNMNNd/ `NMMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMM:`.mNNMMmNs.+NMNNMNNNNNNNmmMMMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMm`hNNNMd/N-..yNmNNdNNNNNNNmMMMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMMdNNNNm-+o....N+yN+oNNNNMNNMMMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMMNNNNN:.+-....--.:yyodNNmdNMMMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMMNNMNN.+oo-.....-ydN+hNNo-NNMMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMMNMMMo+-yNN......-yo.yNN/:NNMMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMMMMMM/..-o+`.........:dm+NMmMMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMMNNMMo:.............::hMNMMmNMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMMMNNMMhy............+:NMNdMNmMMMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMMMyohMMMNNm+.........-hmMMNNh-/mdMMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMmo-.`oNMNNMMMs:--..--/NmNMNy`    hMMMMMMMMMMMM" + System.lineSeparator()
            + PADDING + "MMMMMMMMMMMMN++o`.NNydNNy::::/:...ohNMs`    .hMMMMMMMMMMMM" + System.lineSeparator();

    private final Scanner sc = new Scanner(System.in);

    /** Reads user response. */
    public String readUserResponse() {
        return sc.nextLine().strip();
    }

    /** Displays logo and greet user. */
    public void printGreeting() {
        System.out.print(LINE);
        System.out.println(LOGO);
        System.out.println(PADDING
                + "Konnichiwa! I'm your personal maid. Call me Maid-chan! "
                + Message.EXPRESSION_BLUSH);
        System.out.println(PADDING + "What can I do for you?");
        System.out.println(LINE);
    }

    /** Displays goodbye message to user. */
    public void printGoodbye() {
        System.out.print(LINE);
        System.out.println(PADDING
                + "Bye ❤ Hope to see you again soon! "
                + Message.EXPRESSION_JOY);
        System.out.print(LINE);
    }

    /**
     * Displays message to user.
     *
     * @param message message
     */
    public void printMessage(String message) {
        System.out.print(LINE);
        System.out.println(PADDING + message);
        System.out.println(LINE);
    }

    /**
     * Displays to user the task added.
     *
     * @param task the task added
     * @param size the size of the task list
     */
    public void printTaskAdded(Task task, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Noted. I've added this task:");
        System.out.println(PADDING + "  " + task);
        System.out.println(PADDING + "Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays to user the task deleted.
     *
     * @param task the task deleted
     * @param size the size of the task list
     */
    public void printTaskDeleted(Task task, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Noted. I've removed this task:");
        System.out.println(PADDING + "  " + task);
        System.out.println(PADDING + "Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays to user the task marked as done.
     *
     * @param task the task marked as done
     * @param size the size of the task list
     */
    public void printTaskMarkedAsDone(Task task, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Good job! I've marked this task as done:");
        System.out.println(PADDING + "  " + task);
        System.out.println(LINE);
    }

    /**
     * Displays to user the task list.
     *
     * @param taskList the task list
     */
    public void printTaskList(TaskList taskList) {
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the tasks in your list:");
        System.out.print(taskList);
        System.out.println(LINE);
    }
}

    /**
     * Displays to user the filtered task list that occurs
     * at the specified date.
     *
     * @param taskList the initial task list
     * @param date the specified date
     */
    public void printTaskWithDate(TaskList taskList, String date) {
        TaskList filteredTaskList = taskList.filterDate(date);

        System.out.print(LINE);
        if (filteredTaskList.isEmpty()) {
            System.out.println(PADDING + "You have no task on " + date + ".");
        } else {
            System.out.println(PADDING + "Here are the tasks on " + date + ":");
            System.out.print(filteredTaskList);
        }
        System.out.println(LINE);
    }

    /**
     * Displays to user the filtered task list based
     * on the specified keyword.
     *
     * @param taskList the initial task list
     * @param keyword the specified keyword
     */
    public void printTaskWithKeyword(TaskList taskList, String keyword) {
        TaskList filteredTaskList = taskList.filterTaskByKeyword(keyword);

        System.out.print(LINE);
        if (filteredTaskList.isEmpty()) {
            System.out.println(PADDING + "There is no matching task in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching tasks in your list:");
            System.out.println(filteredTaskList);
        }
        System.out.println(LINE);
    }
}
