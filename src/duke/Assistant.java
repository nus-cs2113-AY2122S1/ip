package duke;

import duke.exception.InvalidIndexException;
import duke.exception.MissingInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Assistant {
    public static final int MAX_TASKS = 100;
    public static final String SEPARATOR_SLASH = "/";
    public static final String FILE_NAME = "duke/data/duke.txt";
    public static final String DIRECTORY_NAME = "duke/data/";

    private int taskIndex;
    private Task[] tasks;
    private File data;

    public Assistant() {
        taskIndex = 0;
        tasks = new Task[MAX_TASKS];
    }

    public void listTasks() {
        for (int i = 0; i < taskIndex; i++) {
            System.out.println((i + 1) + ". " + tasks[i].getStatus() + tasks[i].toString()
            );
        }
    }

    public void addTask(Task newTask) {
        tasks[taskIndex] = newTask;
        taskIndex++;
        System.out.println("added: " + newTask.getTaskName());
    }

    public void completeTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= taskIndex) {
            throw new InvalidIndexException();
        }
        tasks[index].finishTask();
        System.out.println("Completed task: " + tasks[index].getTaskName());
    }

    public void addDeadline(String deadline) throws MissingInputException {
        String[] inputs = deadline.split(SEPARATOR_SLASH, 2);
        if (inputs.length == 1) { //no / detected
            throw new MissingInputException();
        }
        addTask(new Deadline(inputs[0], inputs[1]));
    }

    public void addEvent(String event) throws MissingInputException {
        String[] inputs = event.split(SEPARATOR_SLASH, 2);
        if (inputs.length == 1) { //no / detected
            throw new MissingInputException();
        }
        addTask(new Event(inputs[0], inputs[1]));
    }

    public void addToDo(String todo) {
        addTask(new ToDo(todo));
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
                        tasks[taskIndex].finishTask();
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
            for (int i = 0; i < taskIndex; i++) {
                String data = tasks[i].exportTask();
                fw.write(data);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to save file");
        }
    }
}
