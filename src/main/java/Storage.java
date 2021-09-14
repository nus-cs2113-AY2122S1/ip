import java.util.ArrayList;

public class Storage {
    private static final ArrayList<Task> inputTasks = new ArrayList<>();
    private static String acknowledgeMessage;

    public static void storeTask(String input) throws DukeException {
        Task newTask;
        if (input.startsWith("todo")) {
            newTask = new Todo(input.substring(5));
        } else if (input.startsWith("deadline")) {
            String[] inputSlices = input.substring(9).split("/");
            newTask = new Deadline(inputSlices[0].trim(), inputSlices[1].substring(2).trim());
        } else if (input.startsWith("event")) {
            String[] inputSlices = input.substring(6).split("/");
            newTask = new Event(inputSlices[0].trim(), inputSlices[1].substring(2).trim());
        } else {
            throw new DukeException();
        }

        inputTasks.add(newTask);

        acknowledgeMessage = "Got it. I've added this task: \n  " + inputTasks.get(inputTasks.size() - 1) + "\n"
                + "Now you have: " + inputTasks.size() + " tasks in the list";

        Response.echo(acknowledgeMessage);
    }

    public static void deleteTask(int taskNumber) {
        int taskIndex = taskNumber - 1;

        acknowledgeMessage = "Noted. I've removed this task: \n" + inputTasks.get(taskIndex) + "\n"
                + "Now you have: " + (inputTasks.size() - 1) + " tasks in the list";

        inputTasks.remove(taskIndex);

        Response.echo(acknowledgeMessage);
    }

    public static void list() {
        System.out.println(Response.getLine());

        for (int i = 0; i < inputTasks.size(); i++) {
            System.out.println((i + 1) + ". " + inputTasks.get(i));
        }

        System.out.println(Response.getLine());
    }

    public static void markComplete(int completedTask) {
        inputTasks.get(completedTask - 1).markComplete();
        Response.echo("Nice! I've marked this task as done: \n" + inputTasks.get(completedTask - 1));
    }
}