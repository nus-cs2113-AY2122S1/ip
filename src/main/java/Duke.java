import tasks.Deadline;
import tasks.DukeException;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import commands.Command;
import java.util.Scanner;

public class Duke {

    private boolean exit = false;
    private TaskList taskList = new TaskList();

    public void run() {
        String userInput;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        exit = true;
        while(exit) {
            userInput = in.nextLine();
            try {
                Command c = Parser.parse(userInput, taskList);
                c.run();
                exit = c.exit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
       Duke d = new Duke();
       d.run();
    }
}
