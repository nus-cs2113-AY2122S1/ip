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

    /**
     * Constructor with Ui.
     * @param ui Ui to set Tasklist.
     */
    public TaskList(Ui ui){
        this.ui = ui;
        storage = new Storage();
        tasks = new ArrayList<>();
    }

    /**
     * Return the task in the task list according to the given index.
     * @param index Index of task that want to get.
     * @return The task with given index.
     */
    public Task getIndexTask(int index){
        return tasks.get(index);
    }

    /**
     * Returns the size of task list.
     * @return Size of task list.
     */
    public int getSize(){
        return tasks.size();
    }

    /**
     * Open the task from saved file.
     * @param task The task stored in file.
     */
    public void openTask(Task task){
        tasks.add(task);
    }

    /**
     * Create a new todo task in task list.
     * @param command Command of todo task.
     */
    public void createTodo(String command){
        Todo todo = new Todo(command);
        tasks.add(todo);
        Ui.printTodoMessage(todo,tasks);
        Storage.saveFile(this);
    }

    /**
     * Create a new deadline task in task list.
     * @param command Command of deadline task.
     * @param by Deadline time.
     */
    public void createDeadline(String command,String by) {
        LocalDate byTime = LocalDate.parse(by);
        Deadline deadline = new Deadline(command,byTime);
        tasks.add(deadline);
        Ui.printAddDeadlineMessage(deadline,tasks);
        Storage.saveFile(this);
    }

    /**
     * Create a new event task in task list.
     * @param command Command of event task.
     * @param at Event Time.
     */
    public void createEvent(String command, String at){
        Event event = new Event(command,at);
        tasks.add(event);
        Ui.printAddEventMessage(event,tasks);
    }

    public void createNormalTask(String command){

    }

    /**
     * Delete the task with given index in task list.
     * @param index The index of task that need to be deleted.
     */
    public void deleteTask(int index){
        Task deleteTask = tasks.get(index);
        tasks.remove(index);
        Storage.saveFile(this);
        Ui.printDeleteMessage(deleteTask,tasks);
    }

    /**
     * Mark the task with given index as done.
     * @param index The index of task that need to mark as done.
     * @throws DukeException If the size of task list is zero.
     */
    public void markAsDone(int index) throws DukeException {
        if(tasks.size() == 0){
            throw new DukeException("Hmm... It seems that you have no task to mark in your empty list.");
        } else{
            Task done = tasks.get(index);
            done.taskDone();
            Ui.printMarkAsDoneMessage(done);
        }
    }

    /**
     * Return a list of task with the tasks containing given keyword.
     * @param keyword Keyword typed in by user.
     * @param tasks The list of task.
     * @return A list of task with the tasks containing given keyword.
     */
    public static ArrayList<Task> searchTask(String keyword,TaskList tasks){
        ArrayList<Task> result = new ArrayList<>();
        for(int i = 0;i< tasks.getSize();i++){
            if(tasks.getIndexTask(i).containKeyword(keyword)){
                result.add(tasks.getIndexTask(i));
            }
        }
        return result;
    }





}
