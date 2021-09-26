package duke.processes.commands;

import duke.Duke;
import duke.processes.utility.Interface;
import duke.processes.tasks.Task;

import java.util.ArrayList;

public class ListCommand extends Command {

    public static ArrayList<Task> sortedList = new ArrayList<>();
    protected String listType;

    /**
     * Constructor for ListCommand. Parses through the different commands typed by the user
     * and initializes the listType variable
     *
     * @param command an array of strings of the users response
     */
    public ListCommand(String[] command) {

        if (command.length == 1) {
            this.listType = "list";
        } else if (command[1].equalsIgnoreCase("event")) {
            this.listType = "event";
        } else if (command[1].equalsIgnoreCase("deadline")) {
            this.listType = "deadline";
        } else if (command[1].equalsIgnoreCase("todo")) {
            this.listType = "todo";
        } else {
            this.listType = "others";
        }
    }

    /**
     * lists the specified type of list. Normal list will list out the entire list of tasks
     * but the specific task lists will, make a new sorted array based on its Task type and
     * the date and time of each of the tasks.
     *
     * @return CommandResult indicating if the list has been printed successfully or if errors
     * were encountered along the way
     */
    public CommandResult execute() {

        sortedList.clear();

        switch (listType) {
        case "list":
            System.out.println("Here are the tasks in your list:");
            Interface.printList(Duke.taskList);
            break;
        case "event":
        case "deadline":
        case "todo":
            getListTypeTasks(listType);
            bubbleSortTask();
            System.out.println(listType + " list! Total number of " + listType + "s = "
                    + sortedList.size());
            Interface.printList(sortedList);
            break;
        case "others":
            return new CommandResult("please specify type for list " +
                    "[list, list deadline, list event,list todo ]");
        }

        return new CommandResult("--------END OF LIST-----------");
    }

    /**
     * places the tasks of the specified type into a sortedList that is not yet
     * sorted
     *
     * @param type String indicating the type of the task
     */
    private void getListTypeTasks(String type) {

        for (Task task : Duke.taskList) {
            if (task.getTaskType().equalsIgnoreCase(type)) {
                sortedList.add(task);
            }
        }
    }

    /**
     * sorts the unsorted list using bubble sort
     */
    private void bubbleSortTask() {

        for (int j = 0; j < sortedList.size() - 1; j++) {
            for (int i = 0; i < sortedList.size() - j - 1; i++) {
                if (sortedList.get(i + 1).getDateValue().isBefore(sortedList.get(i).getDateValue())) {
                    swap(i);
                }
            }
        }
    }

    /**
     * utility method to help to swap tasks in the sortedList
     *
     * @param i index of the task being swapped
     */
    private void swap(int i) {

        Task t;
        t = sortedList.get(i);
        sortedList.set(i, sortedList.get(i + 1));
        sortedList.set(i + 1, t);
    }
}
