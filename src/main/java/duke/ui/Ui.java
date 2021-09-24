package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    public static final String LINE =
            "    ____________________________________________________________" + System.lineSeparator();

    public static final String PADDING = "     ";

    public static final String LOGO =
              PADDING + "                    -/ /mmddddmNmh` .:`                   " + System.lineSeparator()
            + PADDING + "              `/sdydNNmhmNMMMMMMMMhymMm+`                 " + System.lineSeparator()
            + PADDING + "            -sNMMMMMMNNddNMMMNMMMMmmMMMMms.               " + System.lineSeparator()
            + PADDING + "           /NMMMNMMNMMmmdNMNMmMMNMmNMMMMMNyy-             " + System.lineSeparator()
            + PADDING + "         .:oNMMMMNNMmhos+so+/::ohdmMMMMMmmMMm.            " + System.lineSeparator()
            + PADDING + "        /mNMMMNNNdy+.````````````../ydNNNMMMMy            " + System.lineSeparator()
            + PADDING + "        /MMMMNmy/.`  `````````  `````.sdNMMMMm+           " + System.lineSeparator()
            + PADDING + "        sNNNNNy.``  ```.:`` ``` ```````./dmNMMh`          " + System.lineSeparator()
            + PADDING + "       `ommdy:```  ` ``sy`` ```  `````````:mmN:           " + System.lineSeparator()
            + PADDING + "       +dNNh.```` ````:dm+` ````  `````````--:            " + System.lineSeparator()
            + PADDING + "       -dMm.````  `s``hdmm-`````````````````-:            " + System.lineSeparator()
            + PADDING + "        -N+````` `sm`/mmmmh`.-```-.``````````-            " + System.lineSeparator()
            + PADDING + "        .:```````+mh.dmmmmm/`y:-`-d/`````` ```            " + System.lineSeparator()
            + PADDING + "        ````````:md/oymmmmmh/ymh+/mmo-````  ```           " + System.lineSeparator()
            + PADDING + "         `` ````dmmymmmmmmmmmmmmd+`.++-````o. .           " + System.lineSeparator()
            + PADDING + "        ``` `` `mmsssdmmmmmmmmmh-s `.m.```-dy .           " + System.lineSeparator()
            + PADDING + "         .`  ` +h:sy.`/dmmmmmmmdy`./-N-` `+dm`.           " + System.lineSeparator()
            + PADDING + "         .`   `mdsN-```dmmmmmmmmNy+smm-- `hds ``          " + System.lineSeparator()
            + PADDING + "          `   -dmmNd/:+mmmmmmmmmmmmmmmy/ .d+  ``          " + System.lineSeparator()
            + PADDING + "         ``   .hdmmNmddmmmmmmmmmmmmmmyd+ ...  .``         " + System.lineSeparator()
            + PADDING + "         `.`   yddmmmdmmmmmmmmmmmmmmm+mo`  `` `..         " + System.lineSeparator()
            + PADDING + "          ```  `/+hmmmmmmmmmmmmmmmmmm/m:` ``- . .`        " + System.lineSeparator()
            + PADDING + "        `` ` `  ```ymmmmmmmmmmmmmmmmm/: ` `.``-//`        " + System.lineSeparator()
            + PADDING + "       :hho- `  ``  :sdmmmmmmmmmmmmd+`.    .`:dMm-:/.     " + System.lineSeparator()
            + PADDING + "     `/hmmdN:`` ` `   `:shmmmmmmmdds..``   `/NMMMNMMs     " + System.lineSeparator()
            + PADDING + "     +ddddmMy```. ``   odyhhhhhhhdmdy..`   .mNMMMMMMd     " + System.lineSeparator()
            + PADDING + "      os//mMm```+o-``-odddhyhhhdmmdMN/:`` oMMMMMMMNm+     " + System.lineSeparator();

    private final Scanner sc = new Scanner(System.in);

    public String readUserResponse() {
        return sc.nextLine().strip();
    }

    /** Displays logo and greet user. */
    public void printGreeting() {
        System.out.print(LINE);
        System.out.println(LOGO);
        System.out.println(PADDING + "Konnichiwa! I'm your personal maid. Call me Maid-chan!");
        System.out.println(PADDING + "What can I do for you? " + Message.EXPRESSION_BLUSH);
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
     */
    public void printTaskMarkedAsDone(Task task) {
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

    /**
     * Displays to user the filtered task list that occurs
     * at the specified date.
     *
     * @param taskList the initial task list
     * @param date the specified date
     */
    public void printTaskWithDate(TaskList taskList, String date) {
        TaskList filteredTaskList = taskList.filterTaskByDate(date);

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
            System.out.print(filteredTaskList);
        }
        System.out.println(LINE);
    }
}
