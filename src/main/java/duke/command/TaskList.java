package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a list of all tasks added.
 */
public class TaskList {
    protected Task[] tasks;
    protected int taskNumber;

    public TaskList() {
        tasks = new Task[100];
        taskNumber = 0;
    }


    /**
     * Prints out all the tasks in a <code>TaskList</code> object.
     */

    public void listOut() {
        for (int i = 0; i < taskNumber; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }


    /**
     * Marks a particular task done, from index of the task
     * inputted from the command lind.
     *
     * @param line The whole line of input from the command line.
     * @throws StringIndexOutOfBoundsException If there is no index contained in the line.
     */

    public void find(String line) {
        Ui ui = new Ui();
        try {
            int j = 1;
            String keyword = line.substring(5);
            ui.find();
            for (int i = 0; i < taskNumber; i++) {
                if (tasks[i].getDescription().contains(keyword)) {
                    System.out.println(j + "." + tasks[i]);
                    j++;
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            ui.findFailed();
        }
    }


    public void markDone(String line) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        try {
            int index = parser.getIndexForDone(line);
            tasks[index].setDone();
            ui.markDone(index, tasks);
        } catch (StringIndexOutOfBoundsException e) {
            ui.markDoneFailed();
        }
    }

    /**
     * Delete a particular task from a <code>TaskList</code> object.
     * @param index Index of the task to be deleted
     * @throws StringIndexOutOfBoundsException If there is no index contained in the line.
     */
    public void deleteTask(int index) {
        Ui ui = new Ui();
        try {
            ui.deleteTask(index, tasks, taskNumber);
            for (int i = index; i < taskNumber - 1; i++) {
                tasks[i] = tasks[i + 1];
            }
            taskNumber--;
        } catch (StringIndexOutOfBoundsException e) {
            ui.deleteTaskFailed();
        }
    }

    /**
     * Add a "todo" type of task into a <code>TaskList</code> object.
     * @param line The whole line of input from the command line.
     * @throws StringIndexOutOfBoundsException If there is no argument after the "todo" command
     */
    public void addTodo(String line) {
        Ui ui = new Ui();
        try {
            tasks[taskNumber] = new ToDo(line.substring(5));
            taskNumber++;
            ui.addTask(tasks, taskNumber);
        } catch (StringIndexOutOfBoundsException e) {
            ui.addTodoFailed();
        }
    }

    /**
     * Add a "deadline" type of task into a <code>TaskList</code> object.
     * @param line The whole line of input from the command line.
     * @throws StringIndexOutOfBoundsException If there is no argument after the "deadline" command
     */
    public void addDeadline(String line) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        String[] words = line.split(" ");
        int index = parser.getIndexForDeadline(line);
        if (index == 0) {
            ui.addDeadlineFailed();
        }
        String deadlineDescription = "";
        String by = "";
        for (int i = 1; i < index; i++) {
            deadlineDescription = deadlineDescription + words[i] + " ";
        }
        deadlineDescription = deadlineDescription.substring(0, deadlineDescription.length() - 1);
        for (int i = index + 1; i < words.length; i++) {
            by = by + words[i] + " ";
        }
        by = by.substring(0, by.length() - 1);
        tasks[taskNumber] = new Deadline(deadlineDescription, by);
        taskNumber++;
        ui.addTask(tasks, taskNumber);
    }

    /**
     * Add a "event" type of task into a <code>TaskList</code> object.
     * @param line The whole line of input from the command line.
     * @throws StringIndexOutOfBoundsException If there is no argument after the "event" command
     */

    public void addEvent(String line) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        String[] words = line.split(" ");
        int index = parser.getIndexForEvent(line);
        if (index == 0) {
            ui.addEventFailed();
        }
        String eventDescription = "";
        String at = "";
        for (int i = 1; i < index; i++) {
            eventDescription = eventDescription + words[i] + " ";
        }
        eventDescription = eventDescription.substring(0, eventDescription.length() - 1);
        for (int i = index + 1; i < words.length; i++) {
            at = at + words[i] + " ";
        }
        at = at.substring(0, at.length() - 1);
        tasks[taskNumber] = new Event(eventDescription, at);
        taskNumber++;
        ui.addTask(tasks, taskNumber);
    }
}
