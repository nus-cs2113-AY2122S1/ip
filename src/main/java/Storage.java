import java.io.*;

public class Storage {
    private static File file;

    /**
     * Constructor for class Storage.
     * @param path File path for the save file.
     */
    public Storage(String path) {
        try {
            file = new File(path);
            if(file.createNewFile()) {
                System.out.println("No save file found, creating a new file");
            }
        } catch (IOException e) {
            new DukeException(2);
        }
        readFromFile();
    }

    /**
     * Writes into the save file.
     */
    public static void writeIntoFile() {
        try {
            FileWriter myWriter = new FileWriter(file);

            for (Task t : TaskList.getTasksInstance()) {
                myWriter.write(t.getTask()
                        + " / " + t.getTrail()
                        + " / " + t.getType()
                        + " / " + (t.isDone() ? 1 : 0)
                        + "\n"
                );
            }

            myWriter.close();
        } catch (IOException e) {
            new DukeException(4);
        }
    }

    /**
     * Reads from the save file.
     */
    public static void readFromFile() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String strLine;

            while ((strLine = bufferedReader.readLine()) != null) {
                String[] saved = strLine.split(" / ", 4);
                TaskList.getTasksInstance().add(new Task(saved[0], saved[1], saved[2].charAt(0), saved[3].equals("1")));
            }
        } catch (IOException e) {
            new DukeException(3);
        }
    }
}
