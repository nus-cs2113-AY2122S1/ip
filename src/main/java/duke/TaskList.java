package duke;

import duke.Ui;
import duke.command.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;


public class TaskList {

    private ArrayList<Task> todo;
    private int todo_index;

    public TaskList() {
        this.todo = new ArrayList<>(100);
        this.todo_index = 0;
    }

    public TaskList(TaskList stored) {
        this.todo = stored.getTodo();
        this.todo_index = stored.getTodoIndex();

    }

    public ArrayList<Task> getTodo(){
        return todo;
    }

    public int getTodoIndex(){
        return todo_index;
    }

    public void printTaskList(){
        for (int i = 0; i < todo_index; i++) {
            System.out.println(i + 1 + ". " + todo.get(i).toString());
        }
    }

    public int getTodo_index(){
        return todo_index;
    }

    public void markTaskAsDone(String description){
        int i = Integer.parseInt(description);
        todo.get(i - 1).markAsDone();
        Ui.showDone();
        System.out.println(todo.get(i - 1).toString());
    }

    public void addTask(Task task) {
        this.todo.add(task);
        todo_index+=1;
    }

    public void addToDoTaskToList(String input){
        todo.add(new Task(input,false));
        todo_index+=1;
        Ui.printAddedTaskMessage();
        System.out.println(todo.get(todo_index-1).toString());
        Ui.printTaskNumberMessage(todo_index);
    }

    public void addDeadlineTaskToList(String input) {
        String description = input.substring(0, input.indexOf("/by") - 1);
        String by = input.substring(input.indexOf("/by") + 4);
        todo.add(new Deadline(description, by, false));
        todo_index+=1;
        Ui.printAddedTaskMessage();
        System.out.println(todo.get(todo_index-1).toString());
        Ui.printTaskNumberMessage(todo_index);
    }

    public void addEventTaskToList(String input) {
        String description = input.substring(0, input.indexOf("/at") - 1);
        String at = input.substring(input.indexOf("/at") + 4);
        todo.add(new Event(description, at, false));
        todo_index+=1;
        Ui.printAddedTaskMessage();
        System.out.println(todo.get(todo_index-1).toString());
        Ui.printTaskNumberMessage(todo_index);
    }

    public void deleteTask(String description){
        int i = Integer.parseInt(description);
        Task deleted = todo.get(i - 1);
        todo.remove(i-1);
        todo_index -= 1;
        Ui.printDeletedTaskMessage();
        System.out.println(deleted.toString());
        Ui.printTaskNumberMessage(todo_index);
    }

    public String save(){
        String saved = "";
        for (int i = 0; i < todo_index; i++) {
            saved += todo.get(i).toString()+"\n";
        }
        return saved;
    }
}
