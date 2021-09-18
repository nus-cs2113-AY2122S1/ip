package duke.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import duke.exception.MissingArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Todo addTodo(String task) throws MissingArgumentException {
        if (task == "") {
            throw new MissingArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(task);
        tasks.add(todo);
        return todo;
    }

    public Deadline addDeadline(String task, String by) throws MissingArgumentException {
        if (task == "") {
            throw new MissingArgumentException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (by == null || by.isEmpty()) {
            throw new MissingArgumentException("☹ OOPS!!! The date of a deadline cannot be empty.");
        }
        Deadline deadline = new Deadline(task, by);
        tasks.add(deadline);
        return deadline;
    }

    public Event addEvent(String task, String at) throws MissingArgumentException {
        if (task == "") {
            throw new MissingArgumentException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (at == null || at.isEmpty()) {
            throw new MissingArgumentException("☹ OOPS!!! The date of an event cannot be empty.");
        }
        Event event = new Event(task, at);
        tasks.add(event);
        return event;
    }

    public Task setTaskDone(int index) {
        Task task = getTask(index);
        task.markAsDone();
        return task;
    }

    public Task deleteTask(int index) {
        Task task = getTask(index);
        tasks.remove(index - 1);
        return task;
    }

    public List<Task> findTasks(String search) {
        return this.tasks.stream().filter(item -> item.getDescription().contains(search))
                .collect(Collectors.toList());
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

}
