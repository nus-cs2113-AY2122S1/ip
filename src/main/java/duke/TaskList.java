package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static Ui ui = new Ui();
    private Storage storage = new Storage("data/duke.txt");

    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /*METHODS*/
    public int getSize(){
        return tasks.size();
    }

    public Task getTask(int taskNumber){
        return tasks.get(taskNumber);
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void removeTask (Task t){
        tasks.remove(t);
    }

    public void printTaskList() {

        ui.printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String type = tasks.get(i).type;
            String icon = tasks.get(i).getStatusIcon();
            System.out.println((i + 1) + "." + "[" + type + "]" + " [" + icon + "] " + tasks.get(i).description);
        }
        ui.printHorizontalLine();
    }

    //prints specific task that is done
    public void setDoneTask(Task t, int i, TaskList tasks) throws IOException {
        tasks.getTask(i).setDone(true);
        storage.writeToFile(tasks);

        ui.printHorizontalLine();
        System.out.println("Nice, I've marked this task as done:");
        String type = t.type;
        String icon = t.getStatusIcon();
        System.out.println("[" + type + "]" + " [" + icon + "] " + t.description);
        ui.printHorizontalLine();
    }










}

