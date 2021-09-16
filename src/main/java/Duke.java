import tasks.Deadline;
import tasks.DukeException;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import commands.Command;
import java.util.Scanner;

public class Duke {

    private boolean exit = false;
    private TaskList taskList = new TaskList();

    public void run() throws IOException {
        String userInput;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        exit = true;
        while (exit) {
            SaveFile s = new SaveFile("data.txt");
            taskList = s.read();
            while (true) {
                userInput = in.nextLine();
                try {
                    Command c = Parser.parse(userInput, taskList);
                    c.run();
                    exit = c.exit();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                s.save(taskList);
            }

        }
    }

        public static void main (String[]args) throws IOException {
            Duke d = new Duke();
            d.run();
        }
}
