package duke;

import duke.tasks.Task;
import duke.exceptions.DukeException;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(int size) {
        list = new ArrayList<>(100);
    }

    public TaskList(TaskList taskList) {
        list = (ArrayList<Task>) taskList.list.clone();
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void add(Task t) {
        list.add(t);
    }

    public Task remove(int index) throws DukeException {

        if (index > list.size() - 1) {
            throw new DukeException("Task number " + (list.size() + 1) + " is invalid!\nEnter a valid task number.");
        }

        return list.remove(index);
    }

    /*
    public Task remove(Task t) {
        return list.remove(list.indexOf(t));
    }*/

    public void setTaskDone(int index) throws DukeException {
        if (index < 0 || index > list.size()) {
            throw new DukeException("Task number " + (index + 1) + " is invalid!\nEnter a valid task number.");
        }

        list.get(index).setDone();
    }

    public TaskList search(String keyword) {
        TaskList resultsList = new TaskList();

        for (Task t : list) {
            if (t.contains(keyword)) {
                resultsList.add(t);
            }
        }
        return resultsList;
    }

    public String listTasks() {

        String text;

        if (list.size() == 0) {
            text = "Empty task list!";
        } else {
            text = "Your task list:\n";
            for (int i = 0; i < list.size(); i++) {
                text = text.concat((i + 1) + ". " + list.get(i).toString() + "\n");
            }

            // erase last newline character
            text = text.substring(0, text.length() - 1);
        }
        return text;
    }
}
