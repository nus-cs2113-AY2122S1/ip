package DukeUtility;

import TypeOfTasks.Deadline;
import TypeOfTasks.Event;
import TypeOfTasks.Task;
import TypeOfTasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The System that writes and read from a storage file in the user's pc everytime changes has been made and booted.
 */
public class FileWriter {

    public static final String FULL_RELATIVE_MEMORY_PATH = "data/owlmemory";
    public static final String DATA_DIRECTORY_PATH = "data";
    public static final String NO_DATA = "";
    public static final String DATA_SEPARATOR = " ~ ";
    public static final String APPEND_DONE = "1";
    public static final String APPEND_NOT_DONE = "0";

    protected String filePath;

    /**
     * Initialises the FileWriter with a given filepath.
     * 
     * @param filePath The storage file's pathing in the user's pc.
     */
    public FileWriter(String filePath) {
        this.filePath = filePath;
    }
    
    private static void writeToFile(String filePath) throws IOException {
        java.io.FileWriter fw = new java.io.FileWriter(filePath);
        fw.write(FileWriter.NO_DATA);
        fw.close();
    }
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        java.io.FileWriter fw = new java.io.FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Update the storage file according to the changes made to the TaskList by going through the entire ArrayList.
     * 
     * @param tasks A list of different types of task with a counter that keep tracks of how many tasks there are.
     */
    public void updateFile(ArrayList<Task> tasks) {
        String dataPath = FULL_RELATIVE_MEMORY_PATH;
        try {
            wipeData(dataPath);
            for(Task task:tasks) {
                if(task.getTag().equals("T")) {
                    appendTodoData(dataPath, task);
                }
                if(task.getTag().equals("D")) {
                    appendEventDeadlineData(dataPath, task);
                }
                if(task.getTag().equals("E")) {
                    appendEventDeadlineData(dataPath, task);
                }
            }
        } catch (IOException ioexception) {
            System.out.println("Something went wrong with writing into the file: " + ioexception.getMessage());
        }
    }

    private static File getFile() throws IOException {
        File file = new File(FULL_RELATIVE_MEMORY_PATH);
        if(!file.exists()) {
            File makeNewDir = new File(DATA_DIRECTORY_PATH);
            if(makeNewDir.mkdir()) {
                System.out.println("Directory not found so I made a new directory!");
            }
            File NewFile = new File(FULL_RELATIVE_MEMORY_PATH);
            if(NewFile.createNewFile()) {
                System.out.println("File not found so I made a new file!");
            }
            return NewFile;
        }
        return file;
    }
    
    private static void wipeData(String dataPath) throws IOException {
        FileWriter.writeToFile(dataPath);
    }

    private static void appendEventDeadlineData(String dataPath, Task task) throws IOException {
        FileWriter.appendToFile(dataPath, task.getTag());
        FileWriter.appendToFile(dataPath, DATA_SEPARATOR);
        FileWriter.appendToFile(dataPath, task.getDescription());
        FileWriter.appendToFile(dataPath, DATA_SEPARATOR);
        FileWriter.appendToFile(dataPath, task.getInfo());
        FileWriter.appendToFile(dataPath, DATA_SEPARATOR);
        if (task.getStatus().equals("X")) {
            FileWriter.appendToFile(dataPath, APPEND_DONE);
        } else {
            FileWriter.appendToFile(dataPath, APPEND_NOT_DONE);
        }
        FileWriter.appendToFile(dataPath, System.lineSeparator());
    }

    private static void appendTodoData(String dataPath, Task task) throws IOException {
        FileWriter.appendToFile(dataPath, task.getTag());
        FileWriter.appendToFile(dataPath, DATA_SEPARATOR);
        FileWriter.appendToFile(dataPath, task.getDescription());
        FileWriter.appendToFile(dataPath, DATA_SEPARATOR);
        if (task.getStatus().equals("X")) {
            FileWriter.appendToFile(dataPath, APPEND_DONE);
        } else {
            FileWriter.appendToFile(dataPath, APPEND_NOT_DONE);
        }
        FileWriter.appendToFile(dataPath, System.lineSeparator());
    }

    /**
     * Returns an ArrayList consisting of all the different Tasks saved in the storage file.
     * 
     * @return An ArrayList consisting of Task objects read from the file.
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            int taskCount = 0;
            File file = getFile();
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while(s.hasNext()) {
                String textLine = s.nextLine();
                String[] inputs = textLine.split(DATA_SEPARATOR);
                if(inputs[0].equals("T")) {
                    taskCount = loadTodo(tasks, taskCount, inputs);
                }
                if(inputs[0].equals("D")) {
                    taskCount = loadDeadline(tasks, taskCount, inputs);
                }
                if(inputs[0].equals("E")) {
                    taskCount = loadEvent(tasks, taskCount, inputs);
                }
            }
        } catch(FileNotFoundException fileNotFoundException) {
            System.out.println("File not found!");
        } catch(IOException ioexception) {
            System.out.println("Theres a problem with making a new file!");
        }
        return tasks;
    }


    private static int loadEvent(ArrayList<Task> tasks, int taskCount, String[] inputs) {
        tasks.add(new Event(inputs[1], inputs[2]));
        taskCount++;
        if(inputs[3].equals(APPEND_DONE)) {
            tasks.get(taskCount - 1).markDone();
        }
        return taskCount;
    }

    private static int loadDeadline(ArrayList<Task> tasks, int taskCount, String[] inputs) {
        tasks.add(new Deadline(inputs[1], inputs[2]));
        taskCount++;
        if(inputs[3].equals(APPEND_DONE)) {
            tasks.get(taskCount - 1).markDone();
        }
        return taskCount;
    }

    private static int loadTodo(ArrayList<Task> tasks, int taskCount, String[] inputs) {
        tasks.add(new Todo(inputs[1]));
        taskCount++;
        if(inputs[2].equals(APPEND_DONE)) {
            tasks.get(taskCount - 1).markDone();
        }
        return taskCount;
    }

}
