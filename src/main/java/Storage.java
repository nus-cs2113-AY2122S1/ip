public class Storage {
    private static final Task[] inputTasks = new Task[100];
    private static int inputTasksSize = 0;

    public static void storeInput(String input) {
        Task newTask;
        switch (input)
        inputTasks[inputTasksSize] = "[ ] " + input;
        inputTasksSize++;
        Response.echo("added: " + input);
    }

    public static void list() {
        System.out.println(Response.getLine());

        for (int i = 0; i < inputTasksSize; i++) {
            System.out.println((i + 1) + ". " + inputTasks[i]);
        }

        System.out.println(Response.getLine());
    }

    public static void markComplete(int completedTask) {
        inputTasks[completedTask - 1] = "[X" + inputTasks[completedTask - 1].substring(2);
        Response.echo("Nice! I've marked this task as done: \n" + inputTasks[completedTask - 1]);
    }
}