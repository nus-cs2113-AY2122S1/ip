package duke.util;

import duke.exception.DukeException;
import duke.task.Task;
import static duke.Duke.*;

public class CommandAction {
    public static void add(Task t) {
        tasks[taskCount] = t;
        taskCount++;
        System.out.println("|| Got it. I've added this task");
        System.out.println("|| \t" + t.toString());
        System.out.println("|| Now you have " + taskCount + " in the list.");

    }

    public static void markDone(String id)throws DukeException {
        int i = Integer.parseInt(id) - 1;
        if (taskCount == 0) {
            System.out.println("No items in list yet.");
            return;
        } else if (i == -1 || i > taskCount) {
            throw new DukeException();
        }
        tasks[i].setDone(true);
        System.out.println("Nice!I've marked this task as done:");
        System.out.println(tasks[i].id + ". " + tasks[i].toString());
    }

    public static void printList() {
        if (taskCount == 0) {
            System.out.println("No items in list yet.");
            return;
        }

        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            Task t = tasks[i];
            System.out.println(t.id + ". " + t);
        }
    }
}
