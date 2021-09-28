package duke.task;

import duke.Storage.Storage;

import java.util.ArrayList;
import duke.Ui.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    private Ui ui;

    public TaskList(Ui ui){
        this.ui = ui;
        storage = new Storage();
        tasks = new ArrayList<>();
    }

    public Task getIndexTask(int index){
        return tasks.get(index);
    }

    public int getSize(){
        return tasks.size();
    }

    public void openTask(Task task){
        tasks.add(task);
    }

    public void createTodo(String command){
        Todo todo = new Todo(command);
        tasks.add(todo);
        Ui.printTodoMessage(todo,tasks);
        Storage.saveFile(this);
    }

    public void createDeadline(String command,String by) {
        LocalDate byTime = LocalDate.parse(by);
        Deadline deadline = new Deadline(command,byTime);
        tasks.add(deadline);
        Ui.printAddDeadlineMessage(deadline,tasks);
        Storage.saveFile(this);
    }

    public void createEvent(String command, String at){
        Event event = new Event(command,at);
        tasks.add(event);
        Ui.printAddEventMessage(event,tasks);
    }

    public void createNormalTask(String command){

    }

    public void deleteTask(int index){
        Task deleteTask = tasks.get(index);
        tasks.remove(index);
        Storage.saveFile(this);
        Ui.printDeleteMessage(deleteTask,tasks);
    }

    public void markAsDone(int index) throws DukeException {
        if(tasks.size() == 0){
            throw new DukeException("Hmm... It seems that you have no task to mark in your empty list.");
        } else{
            Task done = tasks.get(index);
            done.taskDone();
            Ui.printMarkAsDoneMessage(done);
        }
    }
}
