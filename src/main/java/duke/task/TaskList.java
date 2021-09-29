package duke.task;

import duke.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    static final int INDEX_OF_COMMAND_WORD = 0;
    static final int INDEX_OF_DESCRIPTION = 1;
    static final int INDEX_OF_TIME = 2;
    static final String SEPARATOR = " | ";

    private ArrayList<Task> tasks;
    private Ui ui;
    private Storage storage;

    public TaskList(Storage storage) {
        tasks = new ArrayList<>();
        ui = new Ui();
        this.storage = storage;
    }

    public TaskList(ArrayList<String> data, Storage storage) {
        tasks = new ArrayList<>();
        ui = new Ui();
        this.storage = storage;
        int i = 0;
        while (i < data.size())
            switch (data.get(i)) {
            case "T":
                tasks.add(new Todo(data.get(i + 2)));
                if (data.get(i + 1).equals("true")) {
                    tasks.get(Task.getNumberOfTasks() - 1).setDone(); //else leave isDone as false
                }
                i += 3;
                break;
            case "D":
                tasks.add(new Deadline(data.get(i + 2), data.get(i + 3)));
                if (data.get(i + 1).equals("true")) {
                    tasks.get(Task.getNumberOfTasks() - 1).setDone(); //else leave isDone as false
                }
                i += 4;
                break;
            case "E":
                tasks.add(new Event(data.get(i + 2), data.get(i + 3)));
                if (data.get(i + 1).equals("true")) {
                    tasks.get(Task.getNumberOfTasks() - 1).setDone(); //else leave isDone as false
                }
                i += 4;
                break;
            //no default case here because the file follows a certain structure which will
            //go into all the 3 cases above
            }
    }

    public void addTask(String[] parsedFullCommand) {

        if (parsedFullCommand[INDEX_OF_COMMAND_WORD].equals("todo")) {
            tasks.add(new Todo(parsedFullCommand[1]));
        } else if (parsedFullCommand[INDEX_OF_COMMAND_WORD].equals("deadline")) {
            tasks.add(new Deadline(parsedFullCommand[INDEX_OF_DESCRIPTION],
                    parsedFullCommand[INDEX_OF_TIME]));
        } else if (parsedFullCommand[INDEX_OF_COMMAND_WORD].equalsIgnoreCase("event")) {
            tasks.add(new Event(parsedFullCommand[INDEX_OF_DESCRIPTION],
                    parsedFullCommand[INDEX_OF_TIME]));
        }
        ui.printAddedTasked(tasks);
        storage.save(tasks);
    }

    public void listAllTasks() {
        ui.listAllTasks(tasks);
    }

    public void markAsDone(String index) {
        try {
            int indexInteger = Integer.parseInt(index);
            tasks.get(indexInteger - 1).setDone();
            ui.printMarkAsDone(tasks, indexInteger);
        } catch (NumberFormatException e) {
            ui.showLoadingError(e);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.showLoadingError(e);
        }
        storage.save(tasks);
    }

    public void deleteTask(String index) {
        try {
            int indexInteger = Integer.parseInt(index);
            String description = tasks.get(indexInteger - 1).toString();
            tasks.remove(indexInteger - 1);
            Task.decreaseNumberOfTasks();
            ui.showDeletedTask(description);
        } catch (NumberFormatException e) {
            ui.showLoadingError(e);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.showLoadingError(e);
        }
        storage.save(tasks);
    }

    public void filterTasksByString(String filterWord) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().toLowerCase().contains(filterWord.toLowerCase()))
                .collect(Collectors.toList());
        ui.listMatchingTasks(filteredList);
    }
}
