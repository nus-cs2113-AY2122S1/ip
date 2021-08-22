public class Duke {

    private final TaskManager taskManager = new TaskManager();

    private void printHorizontal() {
        System.out.println("____________________________________________________________");
    }

    public void greet() {
        final String logo = " ██████████              █████\n"
                + "░░███░░░░███            ░░███\n"
                + " ░███   ░░███ █████ ████ ░███ █████  ██████\n"
                + " ░███    ░███░░███ ░███  ░███░░███  ███░░███\n"
                + " ░███    ░███ ░███ ░███  ░██████░  ░███████\n"
                + " ░███    ███  ░███ ░███  ░███░░███ ░███░░░\n"
                + " ██████████   ░░████████ ████ █████░░██████\n"
                + "░░░░░░░░░░     ░░░░░░░░ ░░░░ ░░░░░  ░░░░░░\n";
        printHorizontal();
        System.out.println("Welcome to\n" + logo);
        System.out.println("Hello there! I'm Duke, your very helpful personal assistant chat bot. \uD83D\uDE0A\n"
                + "What can I do for you today?");
        printHorizontal();
    }

    public void exit() {
        System.out.println("Bye! Have a great day ahead and see you again soon. \uD83D\uDE04");
        printHorizontal();
    }

    public void echo(String command) {
        System.out.println(command);
        printHorizontal();
    }

    public void addTask(String command) {
        taskManager.addTask(command);
        printHorizontal();
    }

    public void listTasks() {
        taskManager.listTasks();
        printHorizontal();
    }
}
