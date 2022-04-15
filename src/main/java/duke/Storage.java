package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * A class containing methods necessary for file saving and loading every time when using Duke
 * Previous tasks will be automatically loaded into Duke when user opens Duke programme
 * Two saved files, "data.txt" saves the taskList as displayed one; "inputFile.txt" saves the taskList as the original user input string.
 */
public class Storage {
    public static final int LENGTH_MARKER = 4;
    public static String filePath = "data.txt";
    public static String originalInputPath = "inputFile.txt";
    public static TaskList taskList = new TaskList();


    /**
     * Saves the content of tasks into a txt file
     * @param dataToWrite string of all tasks to write in a file
     * @param path file path to save the txt file
     */
    public static void saveData(String dataToWrite, String path) {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(dataToWrite);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads all the tasks and their status from previously saved Duke file
     * calls loadAllTasksList() method
     * @throws IOException
     */
    public static void loadData() throws IOException {
        File f = new File(filePath);
        File inputFile = new File(originalInputPath);
        if(f.createNewFile()) {
            System.out.println("Welcome! This is the first time for you to use Duke! Enjoy!");
        } else {
            try{
                loadAllTasksList();
                System.out.println("Welcome bake to Duke, your task list saved was successfully loaded!");
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Loads all tasks saved before to the taskList
     * @throws FileNotFoundException
     */
    public static void loadAllTasksList() throws FileNotFoundException {
        File f = new File(filePath);
        File inputFile = new File(originalInputPath);
        Scanner s1 = new Scanner(f);
        Scanner s2 = new Scanner(inputFile);
        int taskCount = 0;
        while(s2.hasNext()) {
            String isDone = s1.nextLine().substring(LENGTH_MARKER, LENGTH_MARKER + 1);
            String taskMessage = s2.nextLine();   //read the string form of each task
            Command taskType = identifyTaskType(taskMessage);
            switch(taskType) {
            case EVENT:
                Task event = new Event(taskMessage);
                putToTaskList(taskCount, isDone, event);
                break;
            case DEADLINE:
                Task deadline = new DeadLine(taskMessage);
                putToTaskList(taskCount, isDone, deadline);
                break;
            case TODO:
                Task todo = new ToDo(taskMessage);
                putToTaskList(taskCount, isDone, todo);
                break;
            }
            taskCount++;
        }
    }

    private static void putToTaskList(int taskCount, String isDone, Task task) {
        taskList.addTask(task);
        if (isDone.equals("X")) {
            taskList.tasks.get(taskCount).markAsDone();
        }
    }


    public static Command identifyTaskType(String s) {
        Parser.input = s;
        if(Parser.isEvent()) {
            return Command.EVENT;
        } else if (Parser.isDeadLine()) {
            return Command.DEADLINE;
        } else {
            return Command.TODO;
        }
    }
}
