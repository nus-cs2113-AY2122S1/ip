public class Storage {
    private static final Task[] inputTasks = new Task[100];
    private static int inputTasksSize = 0;

    public static void storeInput(String input) {
        Task newTask;
        if (input.startsWith("todo")) {
            newTask = new Todo(input.substring(5));
        } else if (input.startsWith("deadline")) {
            String[] inputSlices = input.substring(9).split("/");
            newTask = new Deadline(inputSlices[0].trim(), inputSlices[1].substring(2).trim());
        } else {
            String[] inputSlices = input.substring(6).split("/");
            newTask = new Event(inputSlices[0].trim(), inputSlices[1].substring(2).trim());
        }

        inputTasks[inputTasksSize] = newTask;
        inputTasksSize++;

        String acknowledgeMessage = "Got it. I've added this task: \n  " + inputTasks[inputTasksSize - 1] + "\n"
                + "Now you have: " + inputTasksSize + " tasks in the list";

        Response.echo(acknowledgeMessage);
    }

    public static void list() {
        System.out.println(Response.getLine());

        for (int i = 0; i < inputTasksSize; i++) {
            System.out.println((i + 1) + ". " + inputTasks[i]);
        }

        System.out.println(Response.getLine());
    }

    public static void markComplete(int completedTask) {
        inputTasks[completedTask - 1].markComplete();
        Response.echo("Nice! I've marked this task as done: \n" + inputTasks[completedTask - 1]);
    }
}