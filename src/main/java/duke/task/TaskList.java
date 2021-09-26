package duke.task;

import duke.ui.Ui;
import java.util.ArrayList;

/**
 * A class represents all operations to the task list
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    /**
     * Constructor of a new TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor of loading a existing TaskList
     *
     * @param tasks ArrayList of tasks in the data file
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns all the tasks in ArrayList
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Prints all tasks in ArrayList as a list
     */
    public void printList() {
        ui.printList(tasks);
    }

    /**
     * Adds a Todo Task to ArrayList tasks
     *
     * @param userCommand command user key in
     */
    public void addTodo(String userCommand) {
        //add exception
        try {
            int contentStart = 5;
            String description = userCommand.substring(contentStart);
            tasks.add(new Todo(description));
            ui.printTotalNumOfTasks(tasks);
        }
        catch (StringIndexOutOfBoundsException e) {
            ui.printSign();
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            ui.printSign();
        }
    }

    /**
     * Adds an Event Task to ArrayList tasks
     *
     * @param userCommand command user key in
     */
    public void addEvent(String userCommand) {
        //add exception
        try {
            int contentStart = 6;
            int contentEnd = userCommand.indexOf("/at") - 1;
            int atStart = userCommand.indexOf("/at") + 4;
            String description = userCommand.substring(contentStart, contentEnd);
            String at = userCommand.substring(atStart);
            tasks.add(new Event(description, at));
            ui.printTotalNumOfTasks(tasks);
        }
        catch (StringIndexOutOfBoundsException e) {
            ui.printSign();
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
            ui.printSign();
        }
    }

    /**
     * Adds a Deadline Task to ArrayList tasks
     *
     * @param userCommand command user key in
     */
    public void addDeadline(String userCommand) {
        //add exception
        try {
            int contentStart = 9;
            int contentEnd = userCommand.indexOf("/by") - 1;
            int byStart = userCommand.indexOf("/by") + 4;
            String description = userCommand.substring(contentStart, contentEnd);
            String by = userCommand.substring(byStart);
            tasks.add(new Deadline(description, by));
            ui.printTotalNumOfTasks(tasks);
        }
        catch (StringIndexOutOfBoundsException e) {
            ui.printSign();
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            ui.printSign();
        }
    }

    /**
     * Deletes a task from ArrayList tasks
     *
     * @param userCommand command user key in
     */
    public void deleteTask(String userCommand) {
        try {
            int contentStart = 7;
            int id = Integer.parseInt(userCommand.substring(contentStart));
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.get(id - 1).toString());
            tasks.remove(id - 1);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            ui.printSign();
        }
        catch (StringIndexOutOfBoundsException e) {
            ui.printSign();
            System.out.println("☹ OOPS!!! The index of the task to be deleted cannot be empty.");
            ui.printSign();
        }
    }

    /**
     * Marks a task as done in ArrayList tasks
     *
     * @param userCommand command user key in
     */
    public void taskDone(String userCommand) {
        // Mark the relevant task as "done", and print out a line indicates that the task is marked as done
        try {
            int id = Integer.parseInt(userCommand.substring(5));
            tasks.get(id - 1).markAsDone();
            ui.printSign();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(id - 1).toString());
            ui.printSign();
        }
        catch (StringIndexOutOfBoundsException e) {
            ui.printSign();
            System.out.println("☹ OOPS!!! The index of the task to be marked as done cannot be empty.");
            ui.printSign();
        }
    }

    /**
     * Finds a task in ArrayList tasks
     *
     * @param userCommand command user key in
     */
    public void findTask(String userCommand) {
        // Give users a way to find a task by searching for a keyword.
        try {
            String keyword = userCommand.substring(5); // "find " got 5 spaces
            int taskNum = 1;
            ui.printSign();
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : tasks) {
                if (task.getDescription().contains(keyword)) {
                    System.out.println(taskNum + "." + task);
                    taskNum++;
                }
            }
            if (taskNum == 1) {
                System.out.println("None.");
            }
            ui.printSign();
        }
        catch (StringIndexOutOfBoundsException e) {
            ui.printSign();
            System.out.println("☹ OOPS!!! The index of the task to be found as done cannot be empty.");
            ui.printSign();
        }
    }
}
