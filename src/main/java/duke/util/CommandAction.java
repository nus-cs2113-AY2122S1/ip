package duke.util;

import com.sun.source.tree.EmptyStatementTree;
import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;
import duke.task.Task;

import java.util.ArrayList;

public class CommandAction {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void add(Task t) {
        tasks.add(t);
        System.out.println("|| Got it. I've added this task");
        System.out.println("|| \t" + t.toString());
        System.out.println("|| Now you have " + tasks.size() + " in the list.");

    }

    public static int getIndex(String id) {
        return Integer.parseInt(id) -1;
    }
    public static Task getTask(int i) throws InvalidIndexException,EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }
        if (i == -1 || i >= tasks.size()) {
            throw new InvalidIndexException();
        }

        return tasks.get(i);
    }

    public static void delete(String id) throws InvalidIndexException, EmptyListException {
        int i = getIndex(id);
        Task t = getTask(i);

        System.out.println("Noted. I've removed this task: ");
        System.out.println("|| \t" + t.toString());
        tasks.remove(i);
        System.out.println("|| Now you have " + tasks.size() + " in the list.");
    }

    public static void markDone(String id) throws InvalidIndexException, EmptyListException{
        int i = getIndex(id);
        Task t = getTask(i);

        t.setDone(true);
        System.out.println("Nice!I've marked this task as done:");
        System.out.println(id + ". " + t.toString());
    }

    public static void printList() throws EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }

        System.out.println("Here are the tasks in your list:\n");
        int id = 1;
        for (Task t : tasks) {
            System.out.println(id + ". " + t);
            id++;
        }
    }
}
