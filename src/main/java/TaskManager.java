public class TaskManager {
    private Task[] tasks = new Task[100];
    private int numTask = 0;

    public static final String BORDER = "_________________________________________\n";
    public static final String TASK_ADDED_MESSAGE = BORDER + "The task has been added: ";
    public static final String INVALID_TASK_NUMBER = "Invalid task number";
    public static final String TASK_COMPLETE_MESSAGE = "Congrats on finishing a task! Have a cookie!";
    public static final String UNKNOWN_COMMAND_MESSAGE = "I'm sorry, I didn't understand.";
    public static final String NO_TASKS_MESSAGE = "No Tasks";

    private static final String GREETING = "****************************\n"
            + "*  ____             ____   *\n"
            + "* |  _ \\    ____   |  _ \\  *\n"
            + "* | |_| |  / __ \\  | |_| | *\n"
            + "* |  _  | | |  | | |  _  | *\n"
            + "* | |_| | | |__| | | |_| | *\n"
            + "* |____/   \\____/  |____/  *\n"
            + "****************************\n"
            + BORDER
            + "Have no fear, Bob is here!\n"
            + "What is it that you require?\n"
            + BORDER;

    public static final String EXIT_MESSAGE = "Bye. Have a nice day!\n";

    public TaskManager() {
    }

    public void greet() {
        System.out.println(GREETING);
    }

    public void exitMessage() {
        System.out.println(BORDER + EXIT_MESSAGE + BORDER);
    }

    public void addTodoTask(String description) {
        tasks[numTask] = new Todo(description);
        System.out.println(TASK_ADDED_MESSAGE);
        System.out.println(tasks[numTask]);
        System.out.println(BORDER);
        numTask++;
    }

    public void addDeadlineTask(String task) {
        String[] separator = task.split("/by");
        String description = separator[0].trim();
        String deadline = separator[1].trim();
        tasks[numTask] = new Deadline(description, deadline);
        System.out.println(TASK_ADDED_MESSAGE);
        System.out.println(tasks[numTask]);
        System.out.println(BORDER);
        numTask++;
    }

    public void addEventTask(String task) {
        String[] separator = task.split("/at");
        String description = separator[0].trim();
        String timing = separator[1].trim();
        tasks[numTask] = new Event(description, timing);
        System.out.println(TASK_ADDED_MESSAGE);
        System.out.println(tasks[numTask]);
        System.out.println(BORDER);
        numTask++;
    }

    public void listTasks() {
        System.out.print(BORDER);
        if (numTask == 0) {
            System.out.println(NO_TASKS_MESSAGE);
        } else {
            System.out.println("Task List:\n");
            for (int i = 0; i < numTask; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(tasks[i]);
            }
        }
        System.out.println(BORDER);
    }

    public void markAsDone(int number) {
        System.out.print(BORDER);
        if (number > numTask) {
            System.out.println(INVALID_TASK_NUMBER);
        } else {
            tasks[number - 1].markAsDone();
            System.out.println(TASK_COMPLETE_MESSAGE);
            System.out.println(tasks[number - 1]);
        }
        System.out.println(BORDER);
    }

    public void handleUnknownCommand() {
        System.out.print(BORDER);
        System.out.println(UNKNOWN_COMMAND_MESSAGE);
        System.out.println(BORDER);
    }
}
