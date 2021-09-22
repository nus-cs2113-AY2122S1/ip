package duke;

import duke.command.AddTaskCommand;
import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String patchiDataPath = "data/patchidata.txt";

    public Storage() {
    }

    public void loadData(TaskList tasks, Ui ui, Storage storage) {
        Parser parser = new Parser();

        try {
            File patchiData = new File(patchiDataPath);
            Scanner s = new Scanner(patchiData);
            while (s.hasNext()) {
                String[] taskInfo = s.nextLine().split(",", 0);
                String input = taskInfo[0];
                Boolean isDone = taskInfo[1].equals("t") ? true : false; //t or f
                try {
                    AddTaskCommand c = (AddTaskCommand) parser.parseCommand(input);
                    c.addTask(tasks);
                    if(isDone) {
                        tasks.markTaskAsDone(tasks.getTasks().size()-1);
                    }
                } catch (DukeException e) {
                }
            }
        } catch (FileNotFoundException e) {
            ui.println("<patchidata.txt file not found>");
        }
    }

    public void storeTask(String input) {
        try {
            appendToFile(patchiDataPath, input + ",f" + System.lineSeparator());
        } catch (IOException e) {
        }
    }

    public void alterTaskDone(Integer taskIndex) {
        String line;
        try {
            line = getLine(taskIndex);
            line = line.replace(",f", ",t");
            overwriteLine(taskIndex, line);
        } catch (IOException e) {
        }
    }

    public void deleteTask(Integer taskIndex) {
        deleteLine(taskIndex);
    }

    public void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public String getLine(Integer lineIndex) throws IOException {
        File patchiData = new File(patchiDataPath);
        Scanner s = new Scanner(patchiData);
        Integer i = 0;

        while (s.hasNext()) {
            if (i == lineIndex) {
                return s.nextLine();
            }
            i++;
        }

        return null;
    }

    public void deleteLine(Integer lineIndex) {
        try {
            File patchiData = new File(patchiDataPath);
            Scanner s = new Scanner(patchiData);
            String newFileData = "";
            Integer i = 0;

            while (s.hasNext()) {
                if (i == lineIndex) {
                    s.nextLine();
                } else {
                    newFileData += s.nextLine() + System.lineSeparator();
                }
                i++;
            }
            overwriteFile(patchiDataPath, newFileData);
        } catch (IOException e) {
        }
    }

    public void overwriteLine(Integer lineIndex, String newLine) throws IOException {
        File patchiData = new File(patchiDataPath);
        Scanner s = new Scanner(patchiData);
        String newFileData = "";
        Integer i = 0;

        while (s.hasNext()) {
            if (i == lineIndex) {
                newFileData += newLine + System.lineSeparator();
                s.nextLine();
            } else {
                newFileData += s.nextLine() + System.lineSeparator();
            }
            i++;
        }
        overwriteFile(patchiDataPath, newFileData);
    }

    private static void overwriteFile(String filePath, String text) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(text);
        fw.close();
    }
}
