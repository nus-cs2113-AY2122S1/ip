


import java.util.Scanner;

public class Duke {
    public Duke() {
    }

    public static void main(String[] args) {
        Logo duke = new Logo();
        System.out.println(duke);
        Scanner in = new Scanner(System.in);
        System.out.print("Type something: ");
        String line = in.nextLine();
        Manager m = new Manager();

        while(true) {
            String command = m.findCommand(line);
            String content = m.findContent(line);
            if (command.equalsIgnoreCase("bye")) {
                m.printBye();
                break;
            } else if (command.equalsIgnoreCase("list")) {
                m.printList();
            } else if (command.equalsIgnoreCase("done")) {
                m.handleDone(line);
            } else if (command.equalsIgnoreCase("todo")) {
                m.addTodo(content);
            } else if (command.equalsIgnoreCase("deadline")) {
                m.addDeadline(content);
            } else if (command.equalsIgnoreCase("event")) {
                m.addEvent(content);
            } else {
                System.out.println("Oops, please reenter your command");
            }

            line = in.nextLine();
        }
    }
}
