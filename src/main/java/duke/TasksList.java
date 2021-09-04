package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TasksList {
    private static final int LENGTH_OF_AT = 4;
    private static final int LENGTH_OF_BY = 4;
    private static final int LENGTH_OF_TODO = 5;
    private static final int LENGTH_OF_EVENT = 6;
    private static final int LENGTH_OF_DEADLINE = 9;
    private static final String THUMBS_UP_EMOJI = "\uD83D\uDC4D";

    protected ArrayList<Task> tasks = new ArrayList<>();

    public int getSize() {
        return tasks.size();
    }

    public String getTaskString(int taskIndex) {
        Task task = tasks.get(taskIndex);
        return task.toString();
    }

    public void addToDo(String rawInput) {
        try {
            String description = rawInput.substring(LENGTH_OF_TODO);
            tasks.add(new ToDo(description));
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t\t" + this.getTaskString(this.getSize() - 1));
            System.out.println("\tNow you have " + this.getSize() + " tasks in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void addEvent(String rawInput) {
        try{
            int idx = rawInput.indexOf("/at");
            String description = rawInput.substring(LENGTH_OF_EVENT, idx-1);
            String at = rawInput.substring(idx+LENGTH_OF_AT);
            tasks.add(new Event(description, at));
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t\t" + this.getTaskString(this.getSize() - 1));
            System.out.println("\tNow you have " + this.getSize() + " tasks in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("\tWrong deadline format! Please follow `deadline <description> /at <at>`.");
        }
    }

    public void addDeadline(String rawInput) {
        try {
            int idx = rawInput.indexOf("/by");
            String description = rawInput.substring(LENGTH_OF_DEADLINE, idx-1);
            String by = rawInput.substring(idx+LENGTH_OF_BY);
            tasks.add(new Deadline(description, by));
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t\t" + this.getTaskString(this.getSize() - 1));
            System.out.println("\tNow you have " + this.getSize() + " tasks in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("\tWrong deadline format! Please follow `deadline <description> /by <by>`.");
        }
    }

    public void markTaskAsDone(String rawInput) {
        try {
            int taskIndex = Integer.parseInt(rawInput.substring(5)) - 1;
            Task task = tasks.get(taskIndex);
            System.out.println("\tNice! " + THUMBS_UP_EMOJI + " I've marked this task as done:");
            task.markAsDone();
            System.out.println("\t\t[" + task.getStatusIcon() + "] " + task.getDescription());
        } catch (NumberFormatException e) {
            System.out.println("\tWrong format, please retype with format `done <taskIndex>`.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t Task index is out of range.");
        }
    }

    public void printTasks() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i=0; i<this.getSize(); i++) {
            System.out.println("\t" + (i+1) + "." + getTaskString(i));
        }
    }
}