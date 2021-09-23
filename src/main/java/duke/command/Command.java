package duke.command;

import duke.Duke;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

public class Command {

    public static void addTodo(String description) throws IOException {
        Duke.tasks.add(Task.getTaskCount(), new Todo(description));
        Ui.printAddMessage();
        Storage.saveData();
    }

    public static void addDeadline(String description, LocalDateTime by) throws IOException {
        Duke.tasks.add(Task.getTaskCount(), new Deadline(description, by));
        Ui.printAddMessage();
        Storage.saveData();
    }

    public static void addEvent(String description, LocalDateTime at) throws IOException {
        Duke.tasks.add(Task.getTaskCount(), new Event(description, at));
        Ui.printAddMessage();
        Storage.saveData();
    }

    public static void doneTask(int taskIndex) throws IOException {
        try {
            Duke.tasks.get(taskIndex - 1).setDone(true);
            Ui.printDoneMessage();
            Storage.saveData();
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundsMessage();
        }
    }

    public static void deleteTask(int taskIndex) throws IOException {
        try {
            Ui.printDeleteMessage(taskIndex);
            Duke.tasks.remove(taskIndex - 1);
            Storage.saveData();
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundsMessage();
        }
    }

}
