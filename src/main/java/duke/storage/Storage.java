package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.ToDos;
import duke.text.Text;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage extends Text {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createFile() throws IOException {
        Files.createDirectories(Path.of(DIRECTORY)); //create directory data
        Files.createFile(Path.of(filePath)); //create text file to store data
    }

    public void saveFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);

            String textToAdd = "";

            for (int i = 0; i < taskList.size(); i++) {
                textToAdd = textToAdd.concat(taskList.getTask(i).storageText() + "\n"); //combine all task in task list
            }
            fw.write(textToAdd); //write all the task to file
            fw.close();
        }
        catch (IOException | DukeException e) {
            Ui.printWithLine(SAVING_ERROR);
        }
    }

    public TaskList openFile() throws DukeException {
        TaskList taskList = new TaskList();
        LocalDate formattedDate;
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                String task = s.nextLine();
                String[] splitTaskString = task.split("\\|");
                switch (splitTaskString[0]) {
                case TODO_T:
                    taskList.addTask(new ToDos(splitTaskString[2]));
                    break;
                case DEADLINE_D:
                    formattedDate = LocalDate.parse(splitTaskString[3], DateTimeFormatter.ofPattern(DATE_FORMAT));
                    taskList.addTask(new Deadlines(splitTaskString[2], formattedDate));
                    break;
                case EVENT_E:
                    formattedDate = LocalDate.parse(splitTaskString[3], DateTimeFormatter.ofPattern(DATE_FORMAT));
                    taskList.addTask(new Events(splitTaskString[2], formattedDate));
                    break;
                }
                if (splitTaskString[1].equals("1")) {
                    taskList.getTask(taskList.size() - 1).markDone();
                }
            }
        }
        catch (FileNotFoundException e) { //if no existing file, then create new file
            try {
                createFile();
            }
            catch (IOException ioe) {
                throw new DukeException(FILE_ERROR);
            }
        }
        return taskList;
    }
}
