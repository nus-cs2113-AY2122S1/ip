package duke;

import duke.exception.MissingInputException;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

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

    public Storage(String saveLocation) {
        this.saveDataFile = new File(saveLocation);
        this.saveDataDirectory = saveDataFile.getParentFile();
    }

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
                System.out.println("No existing save duke.data\nNew file created");
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
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading save file");
        } catch (MissingInputException e) {
            System.out.println(e + " contains invalid duke.data");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date in save file duke.data");
//            System.out.println("Date should be in the form DDMMYYYY or DD/MM/YYYY or DD-MM-YYYY");
        }
        return taskList;
    }

    public void saveFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(saveDataFile, false);
            for (Task task : tasks) {
                String data = task.exportTask();
                fileWriter.write(data);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to save file");
        }
    }

    private LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("[ddMMyyyy][dd/MM/yyyy][dd-MM-yyyy]"));
    }
}
