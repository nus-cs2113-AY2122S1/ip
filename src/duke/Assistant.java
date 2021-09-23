package duke;

import duke.exception.InvalidIndexException;
import duke.exception.MissingInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Assistant {
    public static final String SEPARATOR_SLASH = "/";

    private Storage storage;
    private ArrayList<Task> tasks;
    public static final String FILE_NAME = "duke/data/duke.txt";
    public static final String DIRECTORY_NAME = "duke/data/";


    public Assistant() {
        tasks = new ArrayList<>();
        storage = new Storage(FILE_NAME);
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getStatus() + tasks.get(i).toString());
        }
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        System.out.println("added: " + newTask.getTaskName());
    }

    public void completeTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        tasks.get(index).finishTask();
        System.out.println("Completed task: " + tasks.get(index).getTaskName());
    }

    public void addDeadline(String deadline) throws MissingInputException, DateTimeParseException {
        String[] inputs = deadline.split(SEPARATOR_SLASH, 2);
        if (inputs.length == 1 || inputs[1].equals("")) { //no / detected
            throw new MissingInputException();
        }
        addTask(new Deadline(inputs[0], parseDate(inputs[1])));
    }

    public void addEvent(String event) throws MissingInputException, DateTimeParseException {
        String[] inputs = event.split(SEPARATOR_SLASH, 2);
        if (inputs.length == 1 || inputs[1].equals("")) { //no / detected or / without input
            throw new MissingInputException();
        }
        addTask(new Event(inputs[0], parseDate(inputs[1])));
    }

    public void addToDo(String todo) {
        //no MissingInputException required since ArrayOutOfBounds will be thrown in main
        addTask(new ToDo(todo));
    }

    public void deleteTask(String input) {
        int index = Integer.parseInt(input) - 1;
        System.out.println("Removed task: " + tasks.get(index).getTaskName());
        tasks.remove(index);
    }

    public void findTask(String input) {
        if (input.trim().isEmpty()) {
            System.out.println("Please enter a valid input");
            return;
        }
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getTaskName().contains(input.trim())) {
                searchResults.add(t);
            }
        }
        if (searchResults.isEmpty()) {
            System.out.println("No results found");
        }
        else {
            System.out.println("Tasks containing " + input.trim() + ":");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println((i + 1) + ". " + searchResults.get(i).toString());
            }
        }
    }

    public void loadFile() {
        storage.loadData();
    }

    public void saveFile() {
        storage.saveFile(tasks);
    }

    private LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("[ddMMyyyy][dd/MM/yyyy][dd-MM-yyyy]"));
    }
}
