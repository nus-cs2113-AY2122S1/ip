package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath = "duke.txt";
    private static TaskList tasks = new TaskList();
    private static Parser parser = new Parser();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);

        for (int i = 0; i < tasks.getSize(); i++){
            Task currentTask = tasks.getTask(i);
            try {
                clearFile();

                String status = "ND";
                if (currentTask.getStatusIcon().equals("X")) {
                    status = "D";
                }
                fw.write(currentTask.type + " | " + status + " | "
                        + currentTask.description + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        }

        fw.close();
    }

    public void clearFile() throws IOException {
        FileWriter f = new FileWriter(filePath);
        f.write("");
        f.close();
    }

    public TaskList loadTasksFromFile() throws IOException, DukeException {
        File dataFile = new File(filePath);
        Scanner s = new Scanner(dataFile);
        String[] taskInfo;

        while (s.hasNext()) {
            String input = s.nextLine();
            taskInfo = input.split("\\|");

            boolean status = taskInfo[1].trim().equals("D");

            Task t;
            switch (taskInfo[0].trim()) {
            case "T":
                t = new Todo(taskInfo[2].trim());
                t.isDone = status;
                tasks.addTask(t);

                break;
            case "D": {
                String fullText = taskInfo[2];
                String[] details = new String[2];
                details = parser.extractDetails(fullText,"(by:" );
//                int index = fullText.lastIndexOf("(by:");
//                String name = fullText.substring(0, index).trim();
//                String by = fullText.substring(index + 4, fullText.length() - 1).trim();

                t = new Deadline(details[0], details[1]);
                t.isDone = status;
                tasks.addTask(t);

                break;
            }
            case "E": {
                String fullText = taskInfo[2];
                String[] details = new String[2];
                details = parser.extractDetails(fullText,"(at:" );
//                int index = fullText.lastIndexOf("(at:");
//                String name = fullText.substring(0, index).trim();
//                String at = fullText.substring(index + 4, fullText.length() - 1).trim();

                t = new Event(details[0], details[1]);
                t.isDone = status;
                tasks.addTask(t);
                break;
            }
            default: {
                throw new DukeException("unrecognisedTask");

            }
            }

        }
        s.close();
        return tasks;
    }
}
