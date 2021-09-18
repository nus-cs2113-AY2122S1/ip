package duke.tasks;

import duke.Duke;
import static duke.Ui.NL;
import duke.exceptions.DukeEmptyDescriptionException;
import duke.exceptions.DukeEmptyTimeException;
import duke.exceptions.DukeInvalidTaskIndex;
import duke.exceptions.DukeTaskAlreadyCompletedException;
import duke.exceptions.DukeMissingKeywordException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    private int numberOfTasks;
    private ArrayList<Task> tasks;

    public TaskList() {
        numberOfTasks = 0;
        tasks = new ArrayList<>();
    }
    
    public void addTask(Task task) throws IOException {
        tasks.add(task);
        numberOfTasks++;
        writeToData(task);
    }
    
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    //TODO Storage
    public void addLoadedTask(String[] taskDetails) {
        switch (taskDetails[0].trim()) {
        case "T":
            boolean isDone = taskDetails[1].trim().equalsIgnoreCase("1");
            Task todo = new Todo(taskDetails[2].trim(), isDone);
            tasks.add(todo);
            numberOfTasks++;
            break;
        case "D":
            isDone = taskDetails[1].trim().equalsIgnoreCase("1");
            Task deadline = new Deadline(taskDetails[2].trim(), taskDetails[3].trim(), isDone);
            tasks.add(deadline);
            numberOfTasks++;
            break;
        case "E":
            isDone = taskDetails[1].trim().equalsIgnoreCase("1");
            Task event = new Event(taskDetails[2].trim(), taskDetails[3].trim(), isDone);
            tasks.add(event);
            numberOfTasks++;
            break;
        default:
            //TODO change this to ui.printError 
            System.out.println("SOME ERROR HAS OCCURRED!");
        }
    }
    //TODO Storage
    private void writeToData(Task task) throws IOException {
        FileWriter fw = new FileWriter(Duke.DATA_FILE, true);
        fw.write( ((numberOfTasks > 1) ? NL : "") + task.toData());
        fw.close();
    }
    //TODO Storage 
    public void setTaskAsDone(int taskNumber) throws DukeInvalidTaskIndex,
            DukeTaskAlreadyCompletedException,
            IOException {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            throw new DukeInvalidTaskIndex();
        }
        if (tasks.get(taskNumber - 1).isDone()) {
            throw new DukeTaskAlreadyCompletedException();
        }

        tasks.get(taskNumber - 1).setDone();
        refreshData();
    }

    //TODO storage
    public Task removeTask(int taskNumber) throws DukeInvalidTaskIndex,
            IOException {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            throw new DukeInvalidTaskIndex();
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        numberOfTasks--;
        refreshData();
        return removedTask;
    }
    //TODO storage
    private void refreshData() throws IOException {
        FileWriter fw = new FileWriter(Duke.DATA_FILE, false);
        for (int i = 0; i < numberOfTasks; i++) {
            fw.write(tasks.get(i).toData() + ((i >= numberOfTasks - 1) ? "" : NL));
        }
        fw.close();
    }

}
