import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;


/**
 * This class handles the saving and loading of the task list to dukedata.txt
 */
public class Storage {

    public static final String FILE_PATH = "data/dukedata.txt";
    public static final String FILE_DIREC = "data";
    public static final String GAP = " / ";
    public static final int COMMAND_INDEX = 0;
    public static final int DONE_INDEX = 1;
    public static final int DESCRIPTION_INDEX = 2;
    public static final int DUE_INDEX = 3;




    public Storage() {}

    /**
     * Checks whether there is any error in loading dukedata.txt
     * Returns the new, updated schedule if there is no error.
     *
     * @param schedule the empty schedule given in the TaskList class
     * @return the updated schedule from dukedata.txt
     */
    public ArrayList<Task> checkFile(ArrayList<Task> schedule) {

        ArrayList<Task> newSchedule = null;
        try {
            newSchedule = loadFile(schedule);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return newSchedule;
    }

    /**
     * Loads the file dukedata.txt that contains the previously saved task list
     *
     * @param schedule the empty schedule given in the TaskList class
     * @return the updated schedule from dukedata.txt
     */
    public ArrayList<Task> loadFile(ArrayList<Task> schedule) throws IOException {
        File tempFile = new File(FILE_PATH);
        if (!tempFile.exists()) {
            File dir = new File(FILE_DIREC);
            dir.mkdir();
            File newFile = new File("data/dukedata.txt");
            newFile.createNewFile();
        }
        return loadContent(tempFile, schedule);
    }

    /**
     * Loads content from dukedata.txt
     *
     * @param datafile dukedata.txt
     * @param schedule the empty schedule given in the TaskList class
     * @return the updated schedule from dukedata.txt
     */

    public ArrayList<Task> loadContent(File datafile, ArrayList<Task> schedule) throws FileNotFoundException {
        Scanner s = new Scanner(datafile);

        int taskNumber = 0;
        if (!s.hasNext()) {
            return schedule;
        }

        while(s.hasNext()) {
            String line = s.nextLine();
            String [] lineContent = line.split(GAP);
            String commandType = lineContent[COMMAND_INDEX];
            if (commandType.equalsIgnoreCase("T")) {
                Todo t = new Todo(lineContent[DESCRIPTION_INDEX]);
                schedule.add(taskNumber, t);
                if (lineContent[DONE_INDEX].equalsIgnoreCase("X")) {
                    schedule.get(taskNumber).markAsDone();
                }
                taskNumber++;

            } else if (commandType.equalsIgnoreCase("D")) {
                Deadline d = new Deadline(lineContent[DESCRIPTION_INDEX], lineContent[DUE_INDEX]);
                schedule.add(taskNumber, d);
                if (lineContent[DONE_INDEX].equalsIgnoreCase("X")) {
                    schedule.get(taskNumber).markAsDone();
                }
                taskNumber++;
            } else if (commandType.equalsIgnoreCase("E")) {
                Event e = new Event(lineContent[DESCRIPTION_INDEX], lineContent[DUE_INDEX]);
                schedule.add(taskNumber, e);
                if (lineContent[DONE_INDEX].equalsIgnoreCase("X")) {
                    schedule.get(taskNumber).markAsDone();
                }
                taskNumber++;
            }

        }
        return schedule;
    }

    /**
     * Updates dukedata.txt with the current task list.
     *
     * @param schedule the current schedule in the TaskList class
     */

    public void saveFile(ArrayList<Task> schedule, int totalTasks) {
        try {
            writeFile(schedule, totalTasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeFile(ArrayList<Task> schedule, int totalTasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);

        for (int i = 0; i < totalTasks; i++) {
            fw.write(schedule.get(i).fileForm());
        }
        fw.close();
    }

}
