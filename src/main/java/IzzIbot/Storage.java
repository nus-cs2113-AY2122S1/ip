package IzzIbot;

import IzzIbot.tasks.Task;
import IzzIbot.tasks.ToDo;
import IzzIbot.tasks.Deadline;
import IzzIbot.tasks.Event;
import IzzIbot.exceptions.IzzIbotException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class Storage {

    protected Ui ui;
    private final Path FILE_PATH;

    /**
     * Storage constructor with its associated UI and file path.
     * @param filePath file path to be used by Storage
     * @param ui UI to be used by Storage
     */
    public Storage(String filePath, Ui ui) {
        this.ui = ui;
        FILE_PATH = Paths.get("IzzIbot_data/IzzIbot.txt");
    }

    /**
     * Reads storage file, decodes encoded TaskList written in that file.
     * @return a TaskList object
     * @throws IzzIbotException
     */
    public TaskList readFile() throws IzzIbotException {
        TaskList readTasks = new TaskList(100);

        try {
            File f = new File(FILE_PATH.toString());
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                Task t;
                String line = s.nextLine();
                String[] inputArray = line.split(" [ | ] ");

                switch (inputArray[0]) {
                case "T":
                    t = new ToDo(inputArray[2]);
                    break;
                case "D":
                    t = new Deadline(inputArray[2], inputArray[3]);
                    break;
                case "E":
                    t = new Event(inputArray[2], inputArray[3]);
                    break;
                default:
                    throw new IzzIbotException("Error while parsing");
                }

                if (inputArray[1].equals("1")) {
                    t.setDone();
                }

                readTasks.add(t);
            }

            return readTasks;

        } catch (FileNotFoundException e) {
            try {
                createFile();
            } catch (IOException o) {
                throw new IzzIbotException("Error while reading file.");
            }
        }
        return null;
    }

    /**
     * Encodes and saves a TaskList. Overwrites previously saved file
     * @param tasks a list of tasks to be encoded and saved
     * @throws IzzIbotException
     */
    public void saveFile(TaskList tasks) throws IzzIbotException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH.toString());
            String newText = "";

            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                newText = newText.concat(t.encode());
                newText = newText.concat("\n");
            }

            fw.write(newText);
            fw.close();

        } catch (IOException e) {
            throw new IzzIbotException("IO exception");
        }
    }

    /**
     * Creates a storage file if there is none existing. Also creates a directory if path is non-existing.
     * @throws IOException
     */
    public void createFile() throws IOException {
        // from https://stackoverflow.com/questions/2833853/create-whole-path-automatically-when-writing-to-a-new-file
        Files.createDirectories(FILE_PATH.getParent());
        Files.createFile(FILE_PATH);
        ui.printWithLines("New storage file created.\nInput [java -jar iP.jar] command again to launch IzzIbot.");
    }
}
