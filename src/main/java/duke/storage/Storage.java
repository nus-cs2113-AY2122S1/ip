package duke.storage;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;


import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {

    private static final String FILEPATH = "data/duke.txt";
    private static final String FOLDERPATH = "data";

    private TaskList taskList;
    private static Ui ui;

    public Storage(TaskList taskList) {
        ui = new Ui();
        this.taskList = taskList;
    }

    public void initList() {
        try {
            File file = new File(FILEPATH);
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String input = s.nextLine();
                String[] inputWords = input.split(" / ");
                switch(inputWords[0]) {
                case "T":
                    taskList.addTodo(inputWords[2]);
                    if (inputWords[1].equals("1")) {
                        int taskIndex = taskList.size()-1;
                        taskList.markTaskAsDone(taskIndex);
                    }
                    break;
                case "D":
                    taskList.addDeadline(inputWords[2], inputWords[3]);
                    if (inputWords[1].equals("1")) {
                        int taskIndex = taskList.size()-1;
                        taskList.markTaskAsDone(taskIndex);
                    }
                    break;
                case "E":
                    taskList.addEvent(inputWords[2], inputWords[3]);
                    if (inputWords[1].equals("1")) {
                        int taskIndex = taskList.size()-1;
                        taskList.markTaskAsDone(taskIndex);
                    }
                    break;
                default:
                    throw new DukeException();
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            createFolder();
            createFile();
        } catch (DukeException | ArrayIndexOutOfBoundsException e) {
            ui.printInvalidFileInputMessage();
            System.exit(0);
        }
    }

    public void createFolder() {
        File folder = new File(FOLDERPATH);
        boolean folderIsCreated = folder.mkdir();
        if (folderIsCreated) {
            ui.printNewFolderMessage();
        }
    }

    public void createFile() {
        File file = new File(FILEPATH);
        try {
            boolean fileIsCreated = file.createNewFile();
            if (fileIsCreated) {
                ui.printNewTextFileMessage();
            }
        } catch (IOException e) {
            ui.printSomethingWentWrongMessage(e);
        }
    }

    public void appendToFile(String textToAppend) throws IOException {
        File file = new File(FILEPATH);
        if (file.length() != 0) {
            textToAppend = System.lineSeparator() + textToAppend;
        }
        FileWriter fw = new FileWriter(FILEPATH, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void updateFile() {
        try {
            ArrayList<Task> list = taskList.getList();
            clearFile();
            for (Task task : list) {
                String textToAppend = "";
                if (task instanceof Todo) {
                    String isDone = task.isDone() ? "1" : "0";
                    textToAppend = "T / " + isDone + " / " + task.getName();
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    String isDone = deadline.isDone() ? "1" : "0";
                    textToAppend = "D / " + isDone + " / " + deadline.getName() + " / " + deadline.getDeadline();
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    String isDone = event.isDone() ? "1" : "0";
                    textToAppend = "E / " + isDone + " / " + event.getName() + " / " + event.getAt();
                }
                appendToFile(textToAppend);
            }
        } catch(IOException e) {
            ui.printSomethingWentWrongMessage(e);
        }
    }

    private void clearFile() throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        fw.write("");
        fw.close();
    }
}
