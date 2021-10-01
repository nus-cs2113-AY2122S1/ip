package duke.command;
import java.util.Scanner;

public class Duke {
    private static Parser parser = new Parser();
    private static Ui ui = new Ui();
    private static TaskList taskList = new TaskList();
    private static Storage s = new Storage();

    public static void running() {
        s.loadFromFile(taskList);
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            line = in.nextLine();

            if (line.equals("list")) {
                taskList.listOut();
            } else if (line.startsWith("find")) {
                taskList.find(line);
            } else if (line.startsWith("done")) {
                taskList.markDone(line);
            } else if (line.startsWith("delete")) {
                int index = parser.getIndexForDelete(line);
                taskList.deleteTask(index);
            } else if (line.startsWith("todo")) {
                taskList.addTodo(line);
            } else if (line.startsWith("deadline")) {
                taskList.addDeadline(line);
            } else if (line.startsWith("event")) {
                taskList.addEvent(line);
            } else if (line.equals("bye")) {
                s.saveToFile("data/data.txt", taskList);
                return;
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args) {
        ui.sayHi();
        running();
        ui.sayBye();
    }
}
