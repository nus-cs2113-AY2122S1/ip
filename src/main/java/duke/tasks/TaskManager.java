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

public class TaskManager {
    private int numberOfTasks;
    private ArrayList<Task> tasks;

    public TaskManager() {
        numberOfTasks = 0;
        tasks = new ArrayList<>();
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

    public void addTodo(String input) throws DukeEmptyDescriptionException,
            IOException {
        if (input.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        Task todo = new Todo(input);
        tasks.add(todo);
        numberOfTasks++;
        Duke.UI.acknowledgeCommand(todo,numberOfTasks);
        writeToData(todo);
    }
    //TODO Storage
    private void writeToData(Task task) throws IOException {
        FileWriter fw = new FileWriter(Duke.DATA_FILE, true);
        fw.write( ((numberOfTasks > 1) ? NL : "") + task.toData());
        fw.close();
    }


    public void addDeadline(String input) throws DukeEmptyDescriptionException,
            DukeEmptyTimeException,
            DukeMissingKeywordException,
            IOException {
        final int indexOfByPrefix = getIndexOfByPrefix(input);
        String deadlineDescription = getDescription(input, indexOfByPrefix);
        String deadlineBy = getTime(input, indexOfByPrefix);
        Task deadline = new Deadline(deadlineDescription, deadlineBy);
        tasks.add(deadline);
        numberOfTasks++;
        Duke.UI.acknowledgeCommand(deadline,numberOfTasks);
        writeToData(deadline);
    }

    private String getTime(String input, int indexOfPrefix) throws DukeEmptyTimeException {
        String time = input.substring(indexOfPrefix + 3).trim();
        if (time.isEmpty()) {
            throw new DukeEmptyTimeException();
        }
        return time;
    }

    private String getDescription(String input, int indexOfPrefix) throws
            DukeEmptyDescriptionException {
        String description = input.substring(0, indexOfPrefix).trim();
        if (description.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        return description;
    }

    private int getIndexOfByPrefix(String input) throws DukeMissingKeywordException {
        int indexOfByPrefix = input.indexOf(Duke.DEADLINE_BY_PREFIX);
        if (indexOfByPrefix == -1) {
            throw new DukeMissingKeywordException("/by");
        }
        return indexOfByPrefix;
    }

    public void addEvent(String input) throws DukeEmptyDescriptionException,
            DukeEmptyTimeException,
            DukeMissingKeywordException,
            IOException {
        final int indexOfAtPrefix = getIndexOfAtPrefix(input);
        String eventDescription = getDescription(input, indexOfAtPrefix);
        String eventAt = getTime(input, indexOfAtPrefix);
        Task event = new Event(eventDescription, eventAt);
        tasks.add(event);
        numberOfTasks++;
        Duke.UI.acknowledgeCommand(event,numberOfTasks);
        writeToData(event);
    }
    //TODO delete
    private String getEventAt(String input, int indexOfAtPrefix) throws DukeEmptyTimeException {
        String eventAt = input.substring(indexOfAtPrefix + 3).trim();
        if (eventAt.isEmpty()) {
            throw new DukeEmptyTimeException();
        }
        return eventAt;
    }
    //TODO delete
    private String getEventDescription(String input, int indexOfAtPrefix) throws
            DukeEmptyDescriptionException {
        String eventDescription = input.substring(0, indexOfAtPrefix).trim();
        if (eventDescription.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        return eventDescription;
    }

    private int getIndexOfAtPrefix(String input) throws DukeMissingKeywordException {
        final int indexOfAtPrefix = input.indexOf(Duke.EVENT_AT_PREFIX);
        if (indexOfAtPrefix == -1) {
            throw new DukeMissingKeywordException("/at");
        }
        return indexOfAtPrefix;
    }
    
    public void printTasks() {
        Duke.UI.showTasks(tasks,numberOfTasks);
    }

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
        Duke.UI.showMessage("Good Job!! I've marked this task as done:" + NL
                + tasks.get(taskNumber - 1).toString());
        refreshData();
    }

    public void removeTask(int taskNumber) throws DukeInvalidTaskIndex,
            IOException {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            throw new DukeInvalidTaskIndex();
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        Duke.UI.showMessage("I have removed the task: " + NL + removedTask.toString()
                + NL + "You now have " + (numberOfTasks - 1) + " tasks remaining");
        numberOfTasks--;
        refreshData();
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
