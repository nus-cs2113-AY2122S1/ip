public class Duke {

    private final String LOGO = " ██████████              █████\n"
            + "░░███░░░░███            ░░███\n"
            + " ░███   ░░███ █████ ████ ░███ █████  ██████\n"
            + " ░███    ░███░░███ ░███  ░███░░███  ███░░███\n"
            + " ░███    ░███ ░███ ░███  ░██████░  ░███████\n"
            + " ░███    ███  ░███ ░███  ░███░░███ ░███░░░\n"
            + " ██████████   ░░████████ ████ █████░░██████\n"
            + "░░░░░░░░░░     ░░░░░░░░ ░░░░ ░░░░░  ░░░░░░\n";

    private final String greetMessage = "Welcome to\n" + LOGO
            + "Hello there! I'm Duke, your very helpful personal assistant chat bot. \uD83D\uDE0A\n"
            + "Enter a task to add it to your list!";

    private final String exitMessage = "Bye! Have a great day ahead and see you again soon. \uD83D\uDE04";

    private final String invalidCommandMessage = "Sorry, I do not understand the command you just entered.";

    private TaskManager taskManager = new TaskManager();

    private void printHorizontal() {
        System.out.println("____________________________________________________________");
    }

    public void greet() {
        System.out.println(greetMessage);
        printHorizontal();
    }

    public void exit() {
        System.out.println(exitMessage);
        printHorizontal();
    }

    public void echo(String command) {
        System.out.println(command);
        printHorizontal();
    }

    public void showInvalidCommandMessage() {
        System.out.println(invalidCommandMessage);
        printHorizontal();
    }

    public void addTask(String command, String taskContent) {
        taskManager.addTask(command, taskContent);
        printHorizontal();
    }

    public void listTasks() {
        taskManager.listTasks();
        printHorizontal();
    }

    public void markTaskAsDone(int taskIndex) {
        taskManager.markTaskAsDone(taskIndex);
        printHorizontal();
    }
}
