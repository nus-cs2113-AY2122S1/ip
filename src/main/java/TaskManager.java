public class TaskManager {
    private String[] tasks = new String[100];
    private int numTask = 0;

    public TaskManager() {
    }

    public void greet() {
        String greeting = "****************************\n"
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
        System.out.println(greeting);
    }

    public void addTask(String task) {
        tasks[numTask] = task;
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
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println("_________________________________________");
    }

    public void exitMessage() {
        String message = "_________________________________________\n"
                + "Bye. Have a nice day!\n"
                + "_________________________________________\n";
        System.out.println(message);
    }
}
