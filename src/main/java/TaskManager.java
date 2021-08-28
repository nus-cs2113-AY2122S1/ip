public class TaskManager {
    private Task[] tasks = new Task[100];
    private int numTask = 0;

    private static final String GREETING = "****************************\n"
            + "*  ____             ____   *\n"
            + "* |  _ \\    ____   |  _ \\  *\n"
            + "* | |_| |  / __ \\  | |_| | *\n"
            + "* |  _  | | |  | | |  _  | *\n"
            + "* | |_| | | |__| | | |_| | *\n"
            + "* |____/   \\____/  |____/  *\n"
            + "****************************\n"
            + "_________________________________________\n"
            + "Have no fear, Bob is here!\n"
            + "What is it that you require?\n"
            + "_________________________________________\n";

    private static final String EXIT_MESSAGE = "_________________________________________\n"
            + "Bye. Have a nice day!\n"
            + "_________________________________________\n";

    public TaskManager() {
    }

    public void greet() {
        System.out.println(GREETING);
    }

    public void exitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    public void addTodoTask(String description) {
        tasks[numTask] = new Todo(description);
        System.out.println("_________________________________________\n" +
                "The task has been added: ");
        System.out.println(tasks[numTask]);
        System.out.println("_________________________________________\n");
        numTask++;
    }

    public void addDeadlineTask(String task) {
        String[] separator = task.split("/by");
        String description = separator[0].trim();
        String deadline = separator[1].trim();
        tasks[numTask] = new Deadline(description, deadline);
        System.out.println("_________________________________________\n" +
                "The task has been added: ");
        System.out.println(tasks[numTask]);
        System.out.println("_________________________________________\n");
        numTask++;
    }
    public void addEventTask(String task) {
        String[] separator = task.split("/at");
        String description = separator[0].trim();
        String timing = separator[1].trim();
        tasks[numTask] = new Event(description, timing);
        System.out.println("_________________________________________\n" +
                "The task has been added: ");
        System.out.println(tasks[numTask]);
        System.out.println("_________________________________________\n");
        numTask++;
    }
    public void listTasks() {
        System.out.println("_________________________________________");
        if (numTask == 0) {
            System.out.println("No Tasks");
        } else {
            System.out.println("_________________________________________");
            System.out.println("Task List:\n");
            for (int i = 0; i < numTask; i++) {
                System.out.print((i+1) + ". ");
                System.out.println(tasks[i]);
            }
        }
        System.out.println("_________________________________________");
    }

    public void markAsDone(int number) {
        System.out.println("_________________________________________");
        if (number > numTask) {
            System.out.println("Invalid task number");
        } else {
            tasks[number - 1].markAsDone();
            System.out.println("Congrats on finishing a task! Have a cookie!");
            System.out.println(tasks[number-1]);
        }
        System.out.println("_________________________________________");
    }
}
