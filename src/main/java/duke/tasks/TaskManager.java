package duke.tasks;

import duke.Duke;
import duke.exceptions.DukeEmptyDescriptionException;
import duke.exceptions.DukeEmptyTimeException;
import duke.exceptions.DukeExceedMaxTaskException;
import duke.exceptions.DukeInvalidTaskIndex;
import duke.exceptions.DukeTaskAlreadyCompletedException;
import duke.exceptions.DukeMissingKeywordException;
import static duke.Ui.NL;

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
        acknowledgeCommand(todo);
        writeToData(todo);
    }

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
        String deadlineDescription = getDeadlineDescription(input, indexOfByPrefix);
        String deadlineBy = getDeadlineBy(input, indexOfByPrefix);
        Task deadline = new Deadline(deadlineDescription, deadlineBy);
        tasks.add(deadline);
        numberOfTasks++;
        acknowledgeCommand(deadline);
        writeToData(deadline);
    }

    private String getDeadlineBy(String input, int indexOfByPrefix) throws DukeEmptyTimeException {
        String deadlineBy = input.substring(indexOfByPrefix + 3).trim();
        if (deadlineBy.isEmpty()) {
            throw new DukeEmptyTimeException();
        }
        return deadlineBy;
    }

    private String getDeadlineDescription(String input, int indexOfByPrefix) throws
            DukeEmptyDescriptionException {
        String deadlineDescription = input.substring(0, indexOfByPrefix).trim();
        if (deadlineDescription.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        return deadlineDescription;
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
        String eventDescription = getEventDescription(input, indexOfAtPrefix);
        String eventAt = getEventAt(input, indexOfAtPrefix);
        Task event = new Event(eventDescription, eventAt);
        tasks.add(event);
        numberOfTasks++;
        acknowledgeCommand(event);
        writeToData(event);
    }

    private String getEventAt(String input, int indexOfAtPrefix) throws DukeEmptyTimeException {
        String eventAt = input.substring(indexOfAtPrefix + 3).trim();
        if (eventAt.isEmpty()) {
            throw new DukeEmptyTimeException();
        }
        return eventAt;
    }

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

    private void acknowledgeCommand(Task task) {
        String acknowledgementMessage = "Understood, "
                + NL + task.toString()
                + NL + "has been added. You now have "
                + numberOfTasks + " " + "task(s) in the list";
        Duke.UI.printMessage(acknowledgementMessage);
    }

    public void printTasks() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < numberOfTasks; i++) {
            list.append(i + 1).append(".");
            list.append(tasks.get(i).toString());
            if (i < numberOfTasks - 1) {
                list.append(NL);
            }
        }
        Duke.UI.printMessage(list.toString());
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
        Duke.UI.printMessage("Good Job!! I've marked this task as done:" + NL
                + tasks.get(taskNumber - 1).toString());
        refreshData();
    }

    public void removeTask(int taskNumber) throws DukeInvalidTaskIndex,
            IOException {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            throw new DukeInvalidTaskIndex();
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        Duke.UI.printMessage("I have removed the task: " + NL + removedTask.toString()
                + NL + "You now have " + (numberOfTasks - 1) + " tasks remaining");
        numberOfTasks--;
        refreshData();
    }

    private void refreshData() throws IOException {
        FileWriter fw = new FileWriter(Duke.DATA_FILE, false);
        for (int i = 0; i < numberOfTasks; i++) {
            fw.write(tasks.get(i).toData() + ((i >= numberOfTasks - 1) ? "" : NL));
        }
        fw.close();
    }

}
