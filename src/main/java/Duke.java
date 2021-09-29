/**
 * Duke Main Class
 * @author husysg
 * @version 1.1
 */
public class Duke {

    /**
     * @param args default parameter
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Ui.greeting();
        Storage.loadTasks();
        Ui.getCommand();
    }
}
