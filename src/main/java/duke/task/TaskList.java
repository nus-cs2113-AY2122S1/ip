package duke.task;

import java.util.ArrayList;
import duke.ui.Ui;
import java.io.IOException;

public class TaskList {
    
    private ArrayList<Task> list;
    private Ui ui;

    public TaskList(Ui ui) {
        this.ui = ui;
        list = new ArrayList<Task>();
    }

    public int size() {
        return list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void printList() {
        if (list.size() == 0) {
            ui.printEmptyListMessage();
        } else {
            ui.printTasks(list);
        }
    }

    public void markTaskAsDone(int taskIndex) {
        list.get(taskIndex).markAsDone();
    }


    public void deleteTask(int taskIndex) {
        list.remove(taskIndex);
    }

    public void printAddedTask() {
        ui.printTaskAddedMessage(list);
    }

    public void addTodo(String taskName) {
        list.add(new Todo(taskName));
    }

    public void addDeadline(String taskName, String deadline) {
        list.add(new Deadline(taskName, deadline));
    }

    public void addEvent(String taskName, String at) {
        list.add(new Event(taskName, at));
    }
}
