import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    // All Task items created will be saved to ip/lennox.txt local file.
    private static String fileName;
    private TaskList updatedTasks;

    /**
     * Populates list at the beginning of session with items from previous session.
     *
     * @throws FileNotFoundException If there was no file saved from previous session.
     */
    public void initializeList() throws FileNotFoundException {
        File f = new File(fileName);
        Scanner s = new Scanner(f);
        int lineNo = 0;
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] splitUp = line.strip().split(" < ");
            String cmd = Parser.createCommand(splitUp);
            Command add = new AddCommand(cmd);
            add.execute(updatedTasks);
            if (splitUp[1].equals("Done")) {
                Command complete = new DoneCommand("Done " + (lineNo + 1));
                complete.execute(updatedTasks);
            }
            lineNo++;
        }
    }

    /**
     * Structures items in list to an appropriate string to be written
     * into locally stored file.
     *
     * @param t A Task item in the current session's list.
     * @return toReturn contains a string to be written onto the local file.
     */
    public static String createLineForFile(Task t) {
        String toReturn = null;
        String doneStatus;
        if (t.getStatusIcon().equals("[X] ")) {
            doneStatus= "Done";
        } else {
            doneStatus = "Incomplete";
        }

        if (t instanceof Todo) {
            toReturn = "T < " + doneStatus + " < " + t.description;
        } else if (t instanceof Deadline) {
            String[] parts = t.description.split("[(]");
            toReturn = "D < " + doneStatus + " < " + parts[0] +
                    " < " + ((Deadline) t).due;
        } else if (t instanceof Event) {
            String[] parts = t.description.split("[(]");
            toReturn = "E < " + doneStatus + " < " + parts[0] +
                    " < " + ((Event) t).time;
        }
        return toReturn;
    }

    /**
     * Writes Tasks in list to a file as readable string.
     *
     * @throws IOException If error occurs while writing to file.
     */
    public void saveToFile(TaskList tList) throws IOException {
        FileWriter fw = new FileWriter(fileName, false);
        for (int i = 0; i < tList.getLength(); i++) {
            Task item = tList.atIndex(i);
            String textToWrite = createLineForFile(item) + System.lineSeparator();
            fw.write(textToWrite);
        }
        updatedTasks = tList;
        fw.close();
    }

    public TaskList getUpdatedTasks() {
        return updatedTasks;
    }

    public Storage(String filePath) {
        fileName = filePath;
        updatedTasks = new TaskList();
        // Read in list from previous session if it exists
        try {
            initializeList();
        } catch (FileNotFoundException e) {
            // Create new text file for saving if file not found
            File newFile = new File(filePath);
            try {
                newFile.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
