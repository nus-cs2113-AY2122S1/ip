import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Task;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        while(exit) {
        SaveFile s = new SaveFile("data.txt");
        t = s.read();
        listIndex = t.size();
        while(true){
            userInput = in.nextLine();
            try {
                Command c = Parser.parse(userInput, taskList);
                c.run();
                exit = c.exit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            s.save(t);
        }

    }

    public static void main(String[] args) {
       Duke d = new Duke();
       d.run();
    }
}
