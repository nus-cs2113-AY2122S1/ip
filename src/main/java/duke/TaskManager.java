package duke;

import duke.command.Command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


public class TaskManager implements Serializable {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int numOfTask = 0;
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int BY_OR_AT_INDEX = 1;

    public static Task getLatestTask() {
        return tasks.get(numOfTask - 1);
    }

    public static Task getTask(int num) {
        return tasks.get(num - 1);
    }

    public static int getNumOfTask() {
        return numOfTask;
    }

    public static void setDone(int taskIndex) throws DukeInvalidTaskIndexException {
        if (checkCorrectIndex(taskIndex)) {
            tasks.get(taskIndex - 1).markDone();
        } else {
            throw new DukeInvalidTaskIndexException();
        }
    }
    
    public static Iterator<Task> createIterator() {
        return tasks.iterator();
    }

    private static String[] splitDescription(String description, String splitBy) {
        return description.split(splitBy, 2);
    }

    public static void printList() {
        if (numOfTask == 0) {
            System.out.println("List is empty!");
            return;
        }
        System.out.println("Here are your items: ");
        for (int i = 0; i < numOfTask; i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
    }

    private static boolean checkCorrectIndex(int index) {
        return index > 0 && index <= numOfTask;
    }

    public static void addTask(Command type, String description) throws DukeBlankDescriptionsException{
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

    private static void addToDo(String description) throws DukeBlankDescriptionsException {
        if (!description.isBlank()) {
            tasks.add(new ToDo(description));
            numOfTask++;
        } else {
            throw new DukeBlankDescriptionsException();
        }
    }

    private static void addEvent(String description) throws DukeBlankDescriptionsException {
        String[] descriptions = splitDescription(description, " /at ");
        if (checkBlankEntry(descriptions)) {
            throw new DukeBlankDescriptionsException();
        }
        tasks.add(new Event(descriptions[TASK_DESCRIPTION_INDEX], descriptions[BY_OR_AT_INDEX]));
        numOfTask++;
    }

    private static void addDeadline(String description) throws DukeBlankDescriptionsException {
        String[] descriptions = splitDescription(description, " /by ");
        if (checkBlankEntry(descriptions)) {
            throw new DukeBlankDescriptionsException();
        }
        tasks.add(new Deadline(descriptions[TASK_DESCRIPTION_INDEX], descriptions[BY_OR_AT_INDEX]));
        numOfTask++;
    }

    private static boolean checkBlankEntry(String[] descriptions) {
        return descriptions[TASK_DESCRIPTION_INDEX].isBlank() || descriptions[BY_OR_AT_INDEX].isBlank();
    }
}
