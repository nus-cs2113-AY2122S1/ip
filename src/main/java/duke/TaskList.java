package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;


/**
 * This class stores all the tasks into 'todo'.
 */
public class TaskList {

    private ArrayList<Task> todo;
    private int todo_index;

    /**
     * Create an empty TaskList
     */
    public TaskList() {
        this.todo = new ArrayList<>(100);
        this.todo_index = 0;
    }

    /**
     * Copy a TaskList from stored file
     *
     * @param stored previously saved TaskList
     */
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

    /**
     * Print 0 task message when there is no task.
     */
    public void printTaskList(){
        if(todo_index==0){
            Ui.printTaskNumberMessage(todo_index);
        }
        for (int i = 0; i < todo_index; i++) {
            System.out.println(i + 1 + ". " + todo.get(i).toString());
        }
    }

    /**
     * Find all tasks (including time) contain the key word
     * @param item
     */
    public void findTaskList(String item){
        Ui.printFindTaskMessage();
        for (int i = 0; i < todo_index; i++) {
            if(todo.get(i).toString().contains(item)){
                System.out.println(i + 1 + ". " + todo.get(i).toString());
            }
        }
    }

    /**
     * Mark task done + number
     * @param description user input an integer, but is in String format
     */
    public void markTaskAsDone(String description){
        int i = Integer.parseInt(description);
        todo.get(i - 1).markAsDone();
        Ui.showDone();
        System.out.println(todo.get(i - 1).toString());
    }

    /**
     * Loading the tasks, shows no output message
     * @param task from stored file
     */
    public void addTask(Task task) {
        this.todo.add(task);
        todo_index+=1;
    }

    /**
     * Add todo
     * @param input task information
     */
    public void addToDoTaskToList(String input){
        todo.add(new Task(input,false));
        todo_index+=1;
        Ui.printAddedTaskMessage();
        System.out.println(todo.get(todo_index-1).toString());
        Ui.printTaskNumberMessage(todo_index);
    }

    /**
     * Add deadline
     * @param input task information (must contain /by)
     */
    public void addDeadlineTaskToList(String input) {
        String description = input.substring(0, input.indexOf("/by") - 1);
        String by = input.substring(input.indexOf("/by") + 4);
        todo.add(new Deadline(description, by, false));
        todo_index+=1;
        Ui.printAddedTaskMessage();
        System.out.println(todo.get(todo_index-1).toString());
        Ui.printTaskNumberMessage(todo_index);
    }

    /**
     * Add event
     * @param input task information (must contain /at)
     */
    public void addEventTaskToList(String input) {
        String description = input.substring(0, input.indexOf("/at") - 1);
        String at = input.substring(input.indexOf("/at") + 4);
        todo.add(new Event(description, at, false));
        todo_index+=1;
        Ui.printAddedTaskMessage();
        System.out.println(todo.get(todo_index-1).toString());
        Ui.printTaskNumberMessage(todo_index);
    }

    /**
     * Delete task + number
     * @param description user input an integer, but is in String format
     */
    public void deleteTask(String description){
        int i = Integer.parseInt(description);
        Task deleted = todo.get(i - 1);
        todo.remove(i-1);
        todo_index -= 1;
        Ui.printDeletedTaskMessage();
        System.out.println(deleted.toString());
        Ui.printTaskNumberMessage(todo_index);
    }

    /**
     * Save all tasks in to local file
     * @return Everything into a single String
     */
    public String save(){
        String saved = "";
        for (int i = 0; i < todo_index; i++) {
            saved += todo.get(i).toString()+"\n";
        }
        return saved;
    }
}
