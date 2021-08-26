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

    public void addTask(String task) {
        Task t = new Task(task);
        tasks[numTask] = t;
        System.out.println("_________________________________________\n" +
                "added: " + task +
                "\n_________________________________________\n");
        numTask++;
    }

    public void listTasks() {
        System.out.println("_________________________________________");
        if (numTask == 0) {
            System.out.println("No Tasks");
        } else {
            for (int i = 0; i < numTask; i++) {
                System.out.println((i + 1) + ". " + tasks[i].displayTask());
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
            System.out.println(tasks[number - 1].displayTask());
        }
        System.out.println("_________________________________________");
    }
}
