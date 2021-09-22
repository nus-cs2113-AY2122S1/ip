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
import java.util.stream.Collectors;

/**
 * Contains all Tasks given by user.
 * Tasks are stored in an ArrayList.
 * Contains methods that work on Tasks such as searching and adding.
 */
public class TaskManager {
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int BY_OR_AT_INDEX = 1;
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int numOfTasks = 0;

    public static Task getLatestTask() {
        return tasks.get(getIndexForArray(numOfTasks));
    }

    public static Task getTask(int num) {
        return tasks.get(getIndexForArray(num));
    }

    public static int getNumOfTasks() {
        return numOfTasks;
    }

    /**
     * Sets task as Done.
     * If taskIndex given is valid.
     *
     * @param taskIndex Index of task.
     * @throws DukeInvalidTaskIndexException If wrong index is passed into the function.
     */
    public static void setDone(int taskIndex) throws DukeInvalidTaskIndexException {
        if (isCorrectIndex(taskIndex)) {
            tasks.get(getIndexForArray(taskIndex)).markDone();
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

    /**
     * Prints out all Task, separated by new line into standard output.
     */
    public static void printTaskList() {
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
     * @param type        The type of Task we are adding.
     * @param description The description of the Task.
     * @throws DukeBlankDescriptionsException If the description is empty.
     */
    public static void addTask(Command type, String description) throws DukeBlankDescriptionsException {
        switch (type) {
        case ADD_TO_DO:
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
            Task deleting = tasks.get(getIndexForArray(taskIndex));
            tasks.remove(getIndexForArray(taskIndex));
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
        String[] descriptions = Parser.splitDescription(description, " /at ");
        if (hasBlankEntry(descriptions)) {
            throw new DukeBlankDescriptionsException();
        }
        tasks.add(new Event(descriptions[TASK_DESCRIPTION_INDEX], descriptions[BY_OR_AT_INDEX]));
        numOfTasks++;

    }

    private static void addDeadline(String description) throws DukeBlankDescriptionsException {
        String[] descriptions = Parser.splitDescription(description, " /by ");
        if (hasBlankEntry(descriptions)) {
            throw new DukeBlankDescriptionsException();
        }
        tasks.add(new Deadline(descriptions[TASK_DESCRIPTION_INDEX], descriptions[BY_OR_AT_INDEX]));
        numOfTasks++;
    }

    private static boolean hasBlankEntry(String[] descriptions) {
        return descriptions[TASK_DESCRIPTION_INDEX].isBlank() || descriptions[BY_OR_AT_INDEX].isBlank();
    }

    /**
     * Prints out a list of all Tasks that contains the term given by user.
     *
     * @param term The search term given by user to find related Task.
     */
    public static void printRelatedTask(String term) {
        if (term.isBlank()) {
            System.out.println("No search term given!");
            return;
        }

        if (!Parser.isOneWord(term)) {
            System.out.println("Please give only 1 keyword.");
            return;
        }

        ArrayList<Task> relatedTasks = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(term))
                .collect(Collectors.toList());

        if (relatedTasks.isEmpty()) {
            System.out.println("No related tasks.");
        } else {
            System.out.println("Here are the matching tasks in your list: ");
            for (int i = 0; i < relatedTasks.size(); i++) {
                System.out.println(i + 1 + "." + relatedTasks.get(i));
            }
        }
    }

    private static int getIndexForArray(int index) {
        return index - 1;
    }
}
