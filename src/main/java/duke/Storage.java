package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;

import java.util.Scanner;

import java.util.ArrayList;

import java.time.LocalDate;

public class Storage {

    protected TaskList taskList = new TaskList();

    protected String DIRECTORY_NAME = "data";

    protected String FILE_PATH = "data/duke.txt";

    protected int TASK_INITIALISE_PADDING = 1;

    public Storage() {
        this.folderInitialise();
    }

    public void folderInitialise() {
        File directory = new File(DIRECTORY_NAME);
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Missing directory! Creating new directory!");
        }
    }

    public void saveFileInitialise(ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(FILE_PATH);

        Scanner saveFile = new Scanner(f);

        int iterationCounter = 0;

        while (saveFile.hasNextLine()) {
            String line = saveFile.nextLine();

            String[] processWordsArray = line.split(("[|]"));

            if (line.charAt(0) == 'T') {

                taskList.addTask(new Todo(processWordsArray[2].substring(TASK_INITIALISE_PADDING)), tasks);

            } else if (line.charAt(0) == 'D') {

                taskList.addTask(new Deadline(processWordsArray[2].substring(TASK_INITIALISE_PADDING), LocalDate.parse(processWordsArray[3].substring(TASK_INITIALISE_PADDING))), tasks);

            } else if (line.charAt(0) == 'E') {

                taskList.addTask(new Event(processWordsArray[2].substring(TASK_INITIALISE_PADDING), LocalDate.parse(processWordsArray[3].substring(TASK_INITIALISE_PADDING))), tasks);

            }

            if (processWordsArray[1].charAt(1) == '1') {

                tasks.get(iterationCounter).setAsDone();

            }

            iterationCounter++;
        }
    }

    public void createSaveFile() throws IOException {
        File f = new File(FILE_PATH);
        f.createNewFile();
        System.out.println("Missing duke.txt! creating new file!");
    }

    public void writeToSave(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);

        for (int taskIndex = 0; taskIndex < tasks.size(); taskIndex++) {

            String doneNumber = "0";

            if (tasks.get(taskIndex).isDone) {

                doneNumber = "1";

            }

            String textToAddToTheFile;

            if (tasks.get(taskIndex).getType().equals("T")) {

                textToAddToTheFile = tasks.get(taskIndex).getType() + " | "

                        + doneNumber + " | "

                        + tasks.get(taskIndex).description + System.lineSeparator();

            } else {

                textToAddToTheFile = tasks.get(taskIndex).getType() + " | "

                        + doneNumber + " | "

                        + tasks.get(taskIndex).description + "| " + tasks.get(taskIndex).getWhen() + System.lineSeparator();
            }

            fileWriter.write(textToAddToTheFile);
        }

        fileWriter.close();
    }
}
