import todo.Task;

public class Duke {
    public static void main(String[] args) {
        Task toDoList = new Task(100);
        Boolean isBye = false;
        showWelcomeScreen();
        while (!isBye) {
            isBye = toDoList.checkWord();
        }
        showByeScreen();
    }

    private static void showByeScreen() {
        Task.printDivider();
        System.out.println("Bye. Hope to see you again soon!");
        Task.printDivider();
    }

    private static void showWelcomeScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Task.printDivider();
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?\n");
        Task.printDivider();
    }
}

