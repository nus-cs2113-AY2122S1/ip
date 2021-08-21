import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean isExit = false;
        TaskManager taskManager = new TaskManager();
        taskManager.greet();

        do {
            Scanner line = new Scanner(System.in);
            String input = line.nextLine();
            if (input.startsWith("bye")) {
                taskManager.exitMessage();
                isExit = true;
            } else {
                taskManager.echo(input);
            }
        } while (!isExit);

    }
}
