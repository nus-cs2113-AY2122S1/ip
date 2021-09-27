import java.io.*;

public class Storage {
    private static File file;

    public Storage(String path) {
        try {
            file = new File(path);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        readFromFile();
    }

    // Write into file
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
            e.printStackTrace();
        }
    }

    // Read from file
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
            e.printStackTrace();
        }
    }
}
