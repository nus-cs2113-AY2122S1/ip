import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {

    /**
     * This method sets up the duke text file.It first tries to find if the exists.
     * If the text file exists, it will call the method loadDukeTextFile. If not it will create a new duke text file.
     *
     * @param list the list of tasks in TaskList
     */
    public static void SetupFile(TaskList list) {
        File dukeTextFile = new File("Duke.txt");
        if (!dukeTextFile.exists()) {
            try {
                dukeTextFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            loadDukeTextFile(dukeTextFile, list);
        }
    }

    /**
     * This method loads the existing duke text file and adds all the contents into the current list in taskList.
     *
     * @param dukeTextFile the text file containing the list of tasks
     * @param list         the list of tasks in TaskList
     */
    public static void loadDukeTextFile(File dukeTextFile, TaskList list) {
        try {
            Scanner dukeTextScanner = new Scanner(dukeTextFile);
            while (dukeTextScanner.hasNextLine()) {
                String dukeTextLine = dukeTextScanner.nextLine();
                String taskLetter = dukeTextLine.substring(0, 1);
                if (taskLetter.equals("T")) {
                    String[] dukeLineSplit = dukeTextLine.split("\\|", 3);
                    String todoDescription = dukeLineSplit[2];
                    String todoIsDone = dukeLineSplit[1];
                    Task task = new ToDo(todoDescription, todoIsDone);
                    list.addTaskList(task);
                } else if (taskLetter.equals("D")) {//Deadline
                    String[] dukeLineSplit = dukeTextLine.split("\\|", 4);
                    String deadlineDescription = dukeLineSplit[2].trim();
                    String deadlineIsDone = dukeLineSplit[1];
                    String deadlineBy = dukeLineSplit[3].trim();
                    Task task = new Deadline(deadlineDescription, deadlineBy, deadlineIsDone);
                    list.addTaskList(task);
                } else {
                    String[] dukeLineSplit = dukeTextLine.split("\\|", 4);
                    String eventDescription = dukeLineSplit[2].trim();
                    String eventAt = dukeLineSplit[3].trim();
                    String eventIsDone = dukeLineSplit[1];
                    Task task = new Event(eventDescription, eventAt, eventIsDone);
                    list.addTaskList(task);
                }
            }
            writeDukeTextFile(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method rewrites to the duke text file the entire list in taskList.
     *
     * @param list the list of tasks in TaskList
     */
    public static void writeDukeTextFile(TaskList list) {
        File dukeTextFile = new File("Duke.txt");
        try {
            FileWriter dukeTextWriter = new FileWriter(dukeTextFile);
            int lineNumber = list.getTaskListCount();
            for (int i = 0; i < lineNumber; i++) {
                dukeTextWriter.write(list.textString(i));
            }
            dukeTextWriter.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}