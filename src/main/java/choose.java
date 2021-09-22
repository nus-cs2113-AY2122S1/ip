import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class choose {
    protected static Tasks[] list = new Tasks[100];
    private static final int tasksAdded = 0;
    private static final int LINE_WIDTH = 60;
    private static final Tasks[] List = new Tasks[100];
    private static int listSize = 0;
    private static final String FILE_DIR = "./data";
    private static final String FILE_NAME = "duke.txt";

    public static void printHorizontalLine() {
        System.out.println("_".repeat(LINE_WIDTH));
    }

    public static void initDataStore() throws IOException {
        File data_dir = new File(FILE_DIR);
        if (data_dir.mkdir()) {
            System.out.println("Directory " + FILE_DIR + " created");
        }
        File f = new File(FILE_DIR + "/" + FILE_NAME);
        if (f.createNewFile()) {
            System.out.println("File " + FILE_NAME + " created");
        }
    }

    // Loads tasks that are stored in ./data/duke.txt
    public static void loadTasks() throws FileNotFoundException, duke.DukeException {
        File file = new File(FILE_DIR + "/" + FILE_NAME);
        Scanner inputFile = new Scanner(file);
        int pendingTasksCounter=0;
        while (inputFile.hasNext()) {
            Tasks pendingTasks;
            String tasksFromFile = inputFile.nextLine();
            String[] taskTypeSplit = tasksFromFile.split("_");
            switch (taskTypeSplit[0]) {
                case "T":
                    pendingTasks = new Todo(taskTypeSplit[2]);
                    List[pendingTasksCounter]=pendingTasks;
                    if (taskTypeSplit[1].equals("X")) {
                        List[pendingTasksCounter].setDone("X");
                    }
                        pendingTasksCounter++;
                    break;
                case "D":
                    pendingTasks = new Deadline(taskTypeSplit[2], taskTypeSplit[3]);
                    List[pendingTasksCounter]=pendingTasks;
                    if (taskTypeSplit[1].equals("X")) {
                        List[pendingTasksCounter].setDone("X");
                    }
                    pendingTasksCounter++;
                    break;
                case "E":
                    pendingTasks = new Event(taskTypeSplit[2], taskTypeSplit[3]);
                    List[pendingTasksCounter]=pendingTasks;
                    if (taskTypeSplit[1].equals("X")) {
                        List[pendingTasksCounter].setDone("X");
                    }
                        pendingTasksCounter++;
                    break;
                default:
                    throw new duke.DukeException("not valid Task");
            }
        }
        listSize=pendingTasksCounter;
    }

    // Saves all tasks to ./data/duke.txt
    public static void saveTasks() throws IOException{
        FileWriter file_writer = new FileWriter(FILE_DIR + "/" + FILE_NAME);
        StringBuilder outputString = new StringBuilder();
        for (int j = 0; j <listSize; j++) {
            outputString.append(List[j].toOutput());
            outputString.append(System.lineSeparator());
        }
        file_writer.write(outputString.toString());
        file_writer.close();
    }

    public static void addTask(String line) {
        List[listSize] = new Tasks(line);
        listSize++;
    }

    public static void list() {
        for (int i = 0; i < listSize; i++) {
            System.out.println(i + 1 + "." + List[i]);
        }
    }

    public static void listLast() {
        System.out.println(List[listSize]);
    }

    public static void setDone(int doneNumber) {
        List[doneNumber - 1].setDone("X");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(List[doneNumber - 1]);
    }

    public static void setDeadline(String item, String timing) {
        List[listSize] = new Deadline(item, timing);
        System.out.println("Got it. I've added this task:");
        listLast();
        listSize++;
        System.out.println(" Now you have " + listSize + " tasks in the list.");
    }

    public static void setEvent(String item, String timing) {
        List[listSize] = new Event(item, timing);
        System.out.println("Got it. I've added this task:");
        listLast();
        listSize++;
        System.out.println(" Now you have " + listSize + " tasks in the list.");
    }

    public static void setTodo(String item) {
        List[listSize] = new Todo(item);
        System.out.println("Got it. I've added this task:");
        listLast();
        listSize++;
        System.out.println(" Now you have " + listSize + " tasks in the list.");
    }
}




