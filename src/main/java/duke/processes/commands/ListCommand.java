package duke.processes.commands;

import duke.Duke;
import duke.processes.utility.Interface;
import duke.processes.tasks.Task;

import java.util.ArrayList;

public class ListCommand extends Command {

    public static ArrayList<Task> sortedList = new ArrayList<>();
    protected String listType;

    public ListCommand(String[] command) {
        if (command.length == 1) {
            this.listType = "list";
        } else if (command[1].equalsIgnoreCase("event")) {
            this.listType = "event";
        } else if (command[1].equalsIgnoreCase("deadline")) {
            this.listType = "deadline";
        }
    }

    public CommandResult execute() {
        sortedList.clear();
        switch (listType) {
        case "list":
            Interface.printList();
            break;
        case "event":
        case "deadline":
            getListTypeTasks(listType);
            bubbleSortTask();
            Interface.printSortedList();
            break;
        }
        return new CommandResult("--------END OF LIST-----------");
    }

    private void getListTypeTasks(String type) {
        for (Task task : Duke.taskList) {
            if (task.getTaskType().equalsIgnoreCase(type)) {
                sortedList.add(task);
            }
        }
    }

    private void bubbleSortTask() {
        for (int j = 0; j < sortedList.size() - 1; j++) {
            for (int i = 0; i < sortedList.size() - j - 1; i++) {
                if (sortedList.get(i + 1).getDateValue().isBefore(sortedList.get(i).getDateValue())) {
                    swap(i);
                }
            }
        }
    }

    private void swap(int i) {
        Task t;
        t = sortedList.get(i);
        sortedList.set(i, sortedList.get(i + 1));
        sortedList.set(i + 1, t);
    }
}
