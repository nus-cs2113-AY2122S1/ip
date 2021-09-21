package ip.src.main.java;
import java.util.Objects;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    public static void main(String[] args) throws DukeException, IOException {

        Scanner in = new Scanner(System.in);

//        Storage storage = new Storage();
//        TaskList tasks = new TaskList();
//        Ui ui = new Ui();
        Parser parser = new Parser();

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
            else if (Objects.equals(parser.keyword(), "d")) {
                Deadline t = new Deadline(parser.splitDeadlineTask(), parser.splitDeadlineBy());
                TaskList.addTask(t, 1);
                Ui.addDeadline();
                Storage.saveFile(TaskList.getTask(++localCount));
            }
            else if (Objects.equals(parser.keyword(), "e")) {
                Event t = new Event(parser.splitEventTask(), parser.splitEventBy());
                TaskList.addTask(t, 2);
                Ui.addEvent();
                Storage.saveFile(TaskList.getTask(++localCount));
            }
            else if (Objects.equals(parser.keyword(), "t")){
                try {
                    Task t = new Task(parser.data());
                    TaskList.addTask(t, 3);
                    Ui.addTodo();
                    Storage.saveFile(TaskList.getTask(++localCount));
//                    if (!Objects.equals(line, "bye")) System.out.println("added: " + formattedLine);
                } catch (DukeException e) {
                    Ui.emptyTodo();
                }
            }
            else {
                if (!Objects.equals(parser.data(), "bye")) Ui.rephrase();
            }
        }
        Ui.bye();
    }
}
