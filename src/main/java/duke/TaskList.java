package duke;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list = new ArrayList<>();

    public void addTodo (String description, int count) {
        Todo todo = new Todo(description, count);
        todo.printAdded(count);
        list.add(todo);
    }

    public void addEvent (String description, String time, int count) {
        Event event = new Event(description, count, time);
        event.printAdded(count);
        list.add(event);
    }

    public void addDeadline (String description, String time, int count) {
        Deadline deadline = new Deadline(description, count, time);
        deadline.printAdded(count);
        list.add(deadline);
    }

    public void delete (String description, int count) {
        int index = Integer.parseInt(description) - 1;
        list.get(index).printTaskDelete(count);
        list.remove(index);
        for (int i=index; i<count; i++) {
            list.get(i).index--;
        }
    }

    public void list () {
        if (list.isEmpty()) {
            throw new NullPointerException();
        }
        for(Task task: list){
            task.printTask();
        }
    }

    public void done (String description) {
        int index = Integer.parseInt(description) - 1;
        list.get(index).printMarkAsDone();
    }


}
