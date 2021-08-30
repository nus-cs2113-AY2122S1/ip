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
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }

    private static void showWelcomeScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }
}

