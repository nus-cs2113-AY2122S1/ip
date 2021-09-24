package duke;

import duke.exception.MissingInputException;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import ui.ErrorUI;
import ui.UI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File saveDataFile;
    private final File saveDataDirectory;

    /**
     * Creates an instance of the Storage class from a given file path of the save file.
     *
     * @param saveLocation A string containing the relative path of the save file to be loaded
     */
    public Storage(String saveLocation) {
        this.saveDataFile = new File(saveLocation);
        this.saveDataDirectory = saveDataFile.getParentFile();
    }

    /**
     * Loads the list of tasks from the save file specified in saveDataFile by parsing the input
     * into a TaskList and returning it.
     *
     * @return Returns a TaskList object containing the list of tasks loaded from the save file.
     */
    public TaskList loadData() {
        TaskList taskList = new TaskList();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            //if save file does not exist, create new file
            //otherwise, read and load duke.data
            if (!saveDataDirectory.exists()) {
                saveDataFile.getParentFile().mkdirs();
            }
            if (!saveDataFile.exists()) {
                saveDataFile.createNewFile();
                UI.printNewSave();
                return new TaskList();
            }
            else {
                Scanner in = new Scanner(saveDataFile);
                while (in.hasNext()) {
                    String nextLine = in.nextLine();
                    String[] inputs = nextLine.split("\\|");
                    switch (inputs[0]) {
                    case "T":
                        tasks.add(new ToDo(inputs[2]));
                        break;
                    case "E":
                        tasks.add(new Event(inputs[2], parseDate(inputs[1])));
                        break;
                    case "D":
                        tasks.add(new Deadline(inputs[2], parseDate(inputs[1])));
                        break;
                    default:
                        throw new MissingInputException();
                    }
                    if (inputs[1].equals("[X]")) {
                        tasks.get(tasks.size() - 1).finishTask();
                    }
                }
            }
            taskList.setTasks(tasks);
        } catch (IOException | MissingInputException e) {
            ErrorUI.printInvalidSaveFile();
        } catch (DateTimeParseException e) {
            ErrorUI.printInvalidDateLoaded();
        }
        return taskList;
    }

    /**
     * Saves the TaskList containing all tasks by parsing it into a string and writing it
     * into a text file in the path specified by saveDataFile.
     *
     * @param tasks The TaskList to be converted into a save file.
     */
    public void saveFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(saveDataFile, false);
            for (Task task : tasks) {
                String data = task.exportTask();
                fileWriter.write(data);
            }
            fileWriter.close();
        } catch (IOException e) {
            ErrorUI.printInvalidWrite();
        }
    }

    /**
     * Parses the given String containing the date into a LocalDate object.
     *
     * @param date String containing the date in the format ddMMyyyy or dd/MM/yyyy or dd-MM-yyyy
     * @return Returns a LocalDate object containing the date.
     * @throws DateTimeParseException when the given date cannot be parsed into a LocalDate object properly.
     */
    private LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("[ddMMyyyy][dd/MM/yyyy][dd-MM-yyyy]"));
    }
}
