package ip.src.main.java;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    public static void main(String[] args) throws DukeException, IOException {

        Scanner in = new Scanner(System.in);
        Parser parser = new Parser();

//       Storage storage = new Storage();
//       TaskList tasks = new TaskList();
//       Ui ui = new Ui();

        int localCount=0;
        String line;

        Ui.hello();

        try {
            Ui.printContents();
            Storage.printFileContents();
        } catch (FileNotFoundException e) {
            Ui.printFailContents();
        }

        while (!Objects.equals(parser.input(), "bye")) {
            line = in.nextLine();
            parser.feed(line);

            if (Objects.equals(parser.input(), "list")) {
                Ui.printTasks();
                TaskList.printTask();
            }
            else if (Objects.equals(parser.keyword(), "remove")) {
                Ui.removeTasks();
                TaskList.remove(Integer.parseInt(parser.listIndex()));
            }
            else if (Objects.equals(parser.keyword(), "done")) {
                Ui.markDone();
                TaskList.markDone(Integer.parseInt(parser.listIndex()));
            }
            else if (Objects.equals(parser.keyword(), "find")) {
                Ui.find();
                System.out.println(parser.listIndex());
                TaskList.find(parser.listIndex());
            }
            else if (Objects.equals(parser.keyword(), "d")) {
                Deadline t = new Deadline(parser.splitDeadlineTask(), parser.splitDeadlineBy());
                TaskList.addTask(t, 'D');
                Ui.addDeadline();
                Storage.saveFile(TaskList.getTask(++localCount));
            }
            else if (Objects.equals(parser.keyword(), "e")) {
                Event t = new Event(parser.splitEventTask(), parser.splitEventBy());
                TaskList.addTask(t, 'E');
                Ui.addEvent();
                Storage.saveFile(TaskList.getTask(++localCount));
            }
            else if (Objects.equals(parser.keyword(), "t")){
                try {
                    Task t = new Task(parser.data());
                    TaskList.addTask(t, 'T');
                    Ui.addTodo();
                    Storage.saveFile(TaskList.getTask(++localCount));
                } catch (DukeException e) {
                    Ui.emptyTodo();
                }
            }
            else if (!Objects.equals(parser.keyword(), "bye")) {
                Ui.rephrase();
            }
        }
        Ui.bye();
    }
}
