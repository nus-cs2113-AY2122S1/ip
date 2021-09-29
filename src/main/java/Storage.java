import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage read in the file and send it to the database
 */
public class Storage {
    private static String path;

    /**
     * initialise storage
     * @param filePath the storage path
     */
    public Storage (String filePath) {
        this.path = filePath;
    }

    /**
     * get the file
     * @return the task list in the file
     */
    public static TaskList getFile(){
        final TaskList taskLists = new TaskList();
        //ArrayList<Task> Tasks = new ArrayList<>(MAX_TASK);
        try {
            File dukeFile = new File(path);
            File directory = dukeFile.getParentFile();
            if(!directory.exists()){
                directory.mkdir();
            }
            if(!dukeFile.exists()){
                dukeFile.createNewFile();
            }
            readFile(taskLists,path);
            printFileContents(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskLists;
    }

    /**
     * read and convert the task list in the file
     * @param Tasks the task list to read
     * @param path the file path
     * @return the task list
     * @throws FileNotFoundException
     */
    public static TaskList readFile(TaskList Tasks, String path) throws FileNotFoundException{
        File f = new File(path);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String str = s.nextLine();
            switch (str.substring(0,1)) {
            case "T":
                String[] strT = str.split(" \\| ");
                //for(String st: strT) System.out.println(st);
                Todo t = new Todo(strT[2]);
                if(Integer.parseInt(strT[1]) == 1) t.setDone(true);
                addToList(t, Tasks);
                break;
            case "D":
                String[] strD = str.split(" \\| ");
                //for(String st: strD) System.out.println(st);
                Deadline d = new Deadline(strD[2], strD[3]);
                if(Integer.parseInt(strD[1]) == 1) d.setDone(true);
                addToList(d, Tasks);
                break;
            case "E":
                String[] strE = str.split(" \\| ");
                //for(String st: strE) System.out.println(st);
                Event e = new Event(strE[2], strE[3]);
                if(Integer.parseInt(strE[1]) == 1) e.setDone(true);
                addToList(e, Tasks);
                break;
            }
        }
        return Tasks;
    }

    /**
     * write the task list to the file
     * @param Tasks the task list
     */
    public static void writeFile(TaskList Tasks){
        try {
            FileWriter fileWriter =new FileWriter(path);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String textToAdd = "";
        for(int i = 0; i < Tasks.size(); i++){
            switch (Tasks.get(i).getType()){
            case TODO:
                textToAdd += "T | " + (Tasks.get(i).isDone() ? "1" : "0") + " | " + Tasks.get(i).getDescription() + System.lineSeparator();
                break;
            case DEADLINE:
                textToAdd += "D | " + (Tasks.get(i).isDone() ? "1" : "0") + " | " + Tasks.get(i).getDescription() + " | " + Tasks.get(i).getBy() + System.lineSeparator();
                break;
            case EVENT:
                textToAdd += "E | " + (Tasks.get(i).isDone() ? "1" : "0") + " | " + Tasks.get(i).getDescription() + " | " + Tasks.get(i).getBy() + System.lineSeparator();
                break;
            }
        }
        try {
            writeToFile(path, textToAdd);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * print out the file contents
     * @param path the file path
     * @throws FileNotFoundException
     */
    private static void printFileContents(String path) throws FileNotFoundException {
        File f = new File(path); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        System.out.println("Tasks recorded: ");
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * write the task to the file
     * @param path the file path
     * @param textToAdd the text to add in the file
     * @throws IOException
     */
    private static void writeToFile(String path, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * add task to the list
     * @param t the task
     * @param list the task list
     */
    public static void addToList(Task t, TaskList list){
        list.add(t);
    }
}
