


import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public Duke() {
    }

    /**
     * Parses the input given by user into command and content, then handles them using a task manager.
     * This process continues until user inputs "bye" to exit Duke.
     */

    public static void main(String[] args) throws IOException {
        Ui Duke = new Ui();
        TaskList manager = new TaskList();
        Parser parser = new Parser();

        System.out.println(Duke);

        manager.initSchedule();
        Scanner in = new Scanner(System.in);
        System.out.print("Type something: ");
        String line = in.nextLine();


        while(true) {
            String command = parser.findCommand(line);
            String content = parser.findContent(line);
            if (command.equalsIgnoreCase("bye")) {
                manager.printBye();
                break;
            } else if (command.equalsIgnoreCase("list")) {
                manager.printList();
            } else if (command.equalsIgnoreCase("done")) {
                manager.handleDone(line);
            } else if (command.equalsIgnoreCase("todo")) {
                manager.addTodo(content);
            } else if (command.equalsIgnoreCase("deadline")) {
                manager.addDeadline(content);
            } else if (command.equalsIgnoreCase("event")) {
                manager.addEvent(content);
            } else if (command.equalsIgnoreCase("delete")) {
                manager.delete(line);
            } else if (command.equalsIgnoreCase("find")) {
                manager.finding(content);
            }
            else {
                System.out.println("Oops, please reenter your command");
            }

            line = in.nextLine();
        }
    }
}
