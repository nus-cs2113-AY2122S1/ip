package duke;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeFieldException;
import duke.parser.ParseFromFileFormat;
import duke.parser.ParseToFileFormat;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private Ui ui;
    private Task tasks;
    private final String filePath;
    private final String fileDirectory;

    public Storage(String filePath, String fileDirectory, Ui ui) {
        this.filePath = filePath;
        this.fileDirectory = fileDirectory;
        this.ui = ui;
    }

    public void saveData(ArrayList<Task> tasks) {
        try {
            writeToFile(tasks);
        } catch (IOException exception) {
            ui.printIoExceptionErrorMessage(exception);
        }
    }

    private void writeToFile(ArrayList<Task> tasks) throws  IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task t : tasks) {
//            Parser fileFormatter = new Parser(t);
            Parser fileFormatter = new ParseToFileFormat(t);
            String taskData = fileFormatter.getFileFormat();
            fileWriter.write(taskData);
        }
        fileWriter.close();
    }

    public void loadData(TaskList tasks) {
        try {
            File file = loadFile();
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
//                String fileFormat = scanner.nextLine();
//                Parser fileFormatHandler = new Parser(fileFormat, 0);
                Parser fileFormatHandler = new ParseFromFileFormat(scanner.nextLine());

                switch (fileFormatHandler.getCommand()) {
                case "todo":
                    Task todoTask = tasks.addTodo(fileFormatHandler.getDescription());
                    if (fileFormatHandler.isDone()) {
                        todoTask.setDone();
                    }
                    break;
                case "deadline":
                    Task deadlineTask = tasks.addDeadline(fileFormatHandler.getDescription(), fileFormatHandler.getFormattedTimeField());
                    if (fileFormatHandler.isDone()) {
                        deadlineTask.setDone();
                    }
                    break;
                case "event":
                    Task eventTask = tasks.addEvent(fileFormatHandler.getDescription(), fileFormatHandler.getFormattedTimeField());
                    if (fileFormatHandler.isDone()) {
                        eventTask.setDone();
                    }
                    break;
                }
            }
        } catch (IOException exception) {
            System.out.println("oops something went wrong..." + exception.getMessage());
        }
    }

    private File loadFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            File dir = new File(fileDirectory);
            dir.mkdir();
            File newFile = new File(filePath);
            newFile.createNewFile();
            return newFile;
        } else {
            return file;
        }
    }
}
