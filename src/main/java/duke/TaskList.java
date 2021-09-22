package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Ui ui = new Ui();

    public TaskList() {
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void listTasks() {
        if (tasks.size() > 0) {
            ui.println("Patchi: Here is the list of tasks you currently have! Work hard~ Œ(˙O˙)B");
            for (int i = 0; i < tasks.size(); i++) {
                ui.println((i + 1) + ". " + tasks.get(i).toString());
            }
        } else {
            ui.println("Patchi: You have no tasks for now! Go and relax~ Œ(ˊuˋ)B");
        }
    }

    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    public void addDeadline(String description, LocalDate by){
        tasks.add(new Deadline(description, by));
    }

    public void addEvent(String description, LocalDate at) {
        tasks.add(new Event(description, at));
    }

    public void markTaskAsDone(int taskIndex) throws DukeException {
        if (tasks.size() > taskIndex) {
            tasks.get(taskIndex).setDone(true);
        }  else {
            throw new DukeException("task doesn't exist");
        }
    }

    public void deleteTask(int taskIndex) throws DukeException {
        if (tasks.size() > taskIndex) {
            tasks.remove(taskIndex);
        } else {
            throw new DukeException("task doesn't exist");
        }
    }
}
