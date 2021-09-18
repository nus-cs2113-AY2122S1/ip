package duke;

import duke.command.Command;
import duke.exception.DukeBlankDescriptionsException;
import duke.exception.DukeInvalidTaskIndexException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskManager {
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int BY_OR_AT_INDEX = 1;
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int numOfTasks = 0;


    public static Task getLatestTask() {
        return tasks.get(numOfTasks - 1);
    }

    public static Task getTask(int num) {
        return tasks.get(num - 1);
    }

    public static int getNumOfTasks() {
        return numOfTasks;
    }

    /**
     * Sets task as Done.
     * If taskIndex given is valid.
     *
     * @param taskIndex Index of task.
     * @throws DukeInvalidTaskIndexException  If wrong index is passed into the function.
     */
    public static void setDone(int taskIndex) throws DukeInvalidTaskIndexException {
        if (isCorrectIndex(taskIndex)) {
            tasks.get(taskIndex - 1).markDone();
        } else {
            throw new DukeInvalidTaskIndexException();
        }
    }

    /**
     * Returns an iterator that iterates all available Task.
     */
    public static Iterator<Task> createIterator() {
        return tasks.iterator();
    }

    private static String[] splitDescription(String description, String splitBy) {
        return description.split(splitBy, 2);
    }

    /**
     * Prints out all Task, separated by new line into standard output.
     */
    public static void printList() {
        if (numOfTasks == 0) {
            System.out.println("List is empty!");
            return;
        }
        System.out.println("Here are your items: ");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
    }

    private static boolean isCorrectIndex(int index) {
        return index > 0 && index <= numOfTasks;
    }

    /**
     * Adds a Task into the ArrayList.
     * 
     * @param type The type of Task we are adding.
     * @param description The description of the Task.
     * @throws DukeBlankDescriptionsException If the description is empty.
     */
    public static void addTask(Command type, String description) throws DukeBlankDescriptionsException {
        switch (type) {
        case ADD_TODO:
            addToDo(description);
            break;
        case ADD_DEADLINE:
            addDeadline(description);
            break;
        case ADD_EVENT:
            addEvent(description);
            break;
        }
    }

    /**
     * Returns the Task that got deleted.
     * If the Task index given is valid.
     * 
     * @param taskIndex The index of Task we are deleting.
     * @return The deleted Task.
     * @throws DukeInvalidTaskIndexException If the index given exceeds the range of current number of Task.
     */
    public static Task delete(int taskIndex) throws DukeInvalidTaskIndexException {
        if (isCorrectIndex(taskIndex)) {
            Task deleting = tasks.get(taskIndex - 1);
            tasks.remove(taskIndex - 1);
            numOfTasks--;
            return deleting;
        } else {
            throw new DukeInvalidTaskIndexException();
        }
    }

    private static void addToDo(String description) throws DukeBlankDescriptionsException {
        if (!description.isBlank()) {
            tasks.add(new ToDo(description));
            numOfTasks++;
        } else {
            throw new DukeBlankDescriptionsException();
        }
    }

    private static void addEvent(String description) throws DukeBlankDescriptionsException {
        String[] descriptions = splitDescription(description, " /at ");
        if (hasBlankEntry(descriptions)) {
            throw new DukeBlankDescriptionsException();
        }
        tasks.add(new Event(descriptions[TASK_DESCRIPTION_INDEX], descriptions[BY_OR_AT_INDEX]));
        numOfTasks++;

    }

    private static void addDeadline(String description) throws DukeBlankDescriptionsException {
        String[] descriptions = splitDescription(description, " /by ");
        if (hasBlankEntry(descriptions)) {
            throw new DukeBlankDescriptionsException();
        }
        tasks.add(new Deadline(descriptions[TASK_DESCRIPTION_INDEX], descriptions[BY_OR_AT_INDEX]));
        numOfTasks++;
    }

    private static boolean hasBlankEntry(String[] descriptions) {
        return descriptions[TASK_DESCRIPTION_INDEX].isBlank() || descriptions[BY_OR_AT_INDEX].isBlank();
    }
}
