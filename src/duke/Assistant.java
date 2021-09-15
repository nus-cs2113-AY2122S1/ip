package duke;

import duke.exception.InvalidIndexException;
import duke.exception.MissingInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Assistant {
    public static final String SEPARATOR_SLASH = "/";

    private ArrayList<Task> tasks;
    public static final String FILE_NAME = "duke/data/duke.txt";
    public static final String DIRECTORY_NAME = "duke/data/";

    private File data;

    public Assistant() {
        tasks = new ArrayList<>();
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i).getStatus() + tasks.get(i).toString()
            );
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

    public void addDeadline(String deadline) throws MissingInputException {
        String[] inputs = deadline.split(SEPARATOR_SLASH, 2);
        if(inputs.length == 1  || inputs[1].equals("")) { //no / detected
            throw new MissingInputException();
        }
        addTask(new Deadline(inputs[0], inputs[1]));
    }

    public void addEvent(String event) throws MissingInputException {
        String[] inputs = event.split(SEPARATOR_SLASH, 2);
        if(inputs.length == 1 || inputs[1].equals("")) { //no / detected or / without input
            throw new MissingInputException();
        }
        addTask(new Event(inputs[0], inputs[1]));
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
    
    public void loadFile() {
        data = new File(FILE_NAME);
        File directory = new File(DIRECTORY_NAME);
        //if save file does not exist, create new file
        //otherwise, read and load duke.data
        try {
            if (!directory.exists()) {
                data.getParentFile().mkdirs();
            }
            if (!data.exists()) {
                data.createNewFile();
                System.out.println("No existing save duke.data\nNew file created");
            }
            else {
                Scanner in = new Scanner(data);
                while (in.hasNext()) {
                    String nextLine = in.nextLine();
                    String[] inputs = nextLine.split("\\|");
                    switch (inputs[0]) {
                    case "T":
                        addToDo(inputs[2]);
                        break;
                    case "E":
                        String event = inputs[2] + "/" + inputs[3];
                        addEvent(event);
                        break;
                    case "D":
                        String deadline = inputs[2] + "/" + inputs[3];
                        addDeadline(deadline);
                        break;
                    default:
                        throw new MissingInputException();
                    }
                    if (inputs[1].equals("[X]")) {
                        tasks.get(tasks.size() - 1).finishTask();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading save file");
        } catch (MissingInputException e) {
            System.out.println(e + " contains invalid duke.data");
        }
    }

    public void saveFile() {
        try {
            FileWriter fw = new FileWriter(FILE_NAME, false);
            for (int i = 0; i < tasks.size(); i++) {
                String data = tasks.get(i).exportTask();
                fw.write(data);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to save file");
        }
    }
}
