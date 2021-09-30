package duke;

import duke.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static String path;
    private static String content = "";

    /**
     * Loads and saves the taskList from/into a .txt file.
     *
     * @param filePath The file path (to a .txt file) from which taskList is loaded from or saved onto.
     * @return (for load function) The saved TaskList from the .txt file in the path.
     * @throws FileNotFoundException if the file path does not exist.
     */

    public Storage(String filePath){
        this.path = filePath;
    }

    public TaskList load() throws FileNotFoundException {

        TaskList taskList = new TaskList();

        File f = new File(path); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String lineType = currentLine.split("\\|", 3)[0];
            String lineIsDone = currentLine.split("\\|", 3)[1];
            String lineAction = currentLine.split("\\|", 3)[2];
            String lineDescription;
            String lineDueDate;
            switch(lineType){
                case "[T]":
                    Todo oldTodo = new Todo(lineAction);
                    taskList.add(oldTodo);
                    break;
                case "[D]":
                    lineDescription = lineAction.split("/by", 2)[0];
                    lineDueDate = lineAction.split("/by", 2)[1];
                    Deadline oldDeadline = new Deadline(lineDescription, lineDueDate);
                    taskList.add(oldDeadline);
                    break;
                case "[E]":
                    lineDescription = lineAction.split("/at", 2)[0];
                    lineDueDate = lineAction.split("/at", 2)[1];
                    Event oldEvent = new Event(lineDescription, lineDueDate);
                    taskList.add(oldEvent);
                    break;
            }
        }
        return taskList;
    }

    public void write(TaskList taskList) {
        try {
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                String currentType = currentTask.getType();
                switch (currentType) {
                    case "[T]":
                        content += currentType + '|' + String.valueOf(currentTask.getIsDone()) + '|' + currentTask.getDescription() + System.lineSeparator();
                        break;
                    case "[D]":
                        content += currentType + '|' + String.valueOf(currentTask.getIsDone()) + '|' + currentTask.getDescription() + "/by" + currentTask.getDueDate() + System.lineSeparator();
                        break;
                    case "[E]":
                        content += currentType + '|' + String.valueOf(currentTask.getIsDone()) + '|' + currentTask.getDescription() + "/at" + currentTask.getDueDate() + System.lineSeparator();
                        break;
                }
            }
            FileWriter fw = new FileWriter(path);
            fw.write(content);
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
