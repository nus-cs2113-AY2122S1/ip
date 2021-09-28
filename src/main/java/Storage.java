import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Loads and writes all data into a text file.
 */

public class Storage {
    private static File save;
    private static Tasks loadFile;
    private static String path;

    public Storage(String filePath){
        path = filePath;
    }
    /**
     * Returns a list of saved tasks from "data/duke.txt"
     * Creates a file according to the file path if file is not found.
     * Throws IOException if an error occurs.
     */
    public static Tasks load() {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            save = new File (path);
            if (!save.exists()) {
                save.createNewFile();
            }
            loadFile = new Tasks();
            Scanner s = new Scanner(save);
            int i = 1;
            while (s.hasNext()) {
                String[] parts = s.nextLine().split("\\|");
                String type = parts[0].trim();
                String completion = parts[1].trim();
                String description = parts[2].trim();
                switch (type) {
                case "T":
                    loadFile.add(new Task(description));
                    break;
                case "D":
                    String by = parts[3].trim();
                    loadFile.add(new Deadline(description, by));
                    break;
                case "E":
                    String at = parts[3].trim();
                    loadFile.add(new Event(description, at));
                    break;
                }
                if (completion.equals("[X]")) {
                    loadFile.get(i - 1).markComplete();
                }
                i++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return loadFile;
    }
    /**
     * Writes the contents of the list of Tasks in Duke to a .txt file "duke.txt"
     * The data is stored as a formatted text file.
     *
     * @param tasks list of existing tasks in Duke to be stored.
     */
    public void writeToFile(Tasks tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(save);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).getType() + " | " + tasks.get(i).getStatus() + " | " + tasks.get(i).getDescription() + " | " + tasks.get(i).getTime() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
