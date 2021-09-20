package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    protected static final String FILE_PATH = "data/duke.txt";
    protected static final String GAP = " / ";

    public void loadTasks(TaskList tasks) {
        try {
            addTasksIntoList(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public File loadFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            File dir = new File("data");
            dir.mkdir();
            File newFile = new File("data/duke.txt");
            newFile.createNewFile();
            return newFile;
        }
        return file;
    }

    public void addTasksIntoList(TaskList tasks) throws IOException {
        File file = loadFile();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String entry = s.nextLine();
            String[] entryComponents = entry.split(GAP);
            String description = entryComponents[0] + " " + entryComponents[2];
            switch (entryComponents[0]) {
            case "T":
                tasks.addTodo(description);
                break;
            case "D":
                String by = "by " + entryComponents[3];
                tasks.addDeadline(description, by);
                break;
            case "E":
                String at = "at " + entryComponents[3];
                tasks.addEvent(description, at);
                break;
            default:
                break;
            }
            if (entryComponents[1].equals("X")) {
                tasks.completeTask(tasks.getSize() - 1);
            }
        }
    }

    public void saveTasks(TaskList tasks) {
        try {
            writeTasksToFile(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void writeTasksToFile(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            String details = task.getDescription().substring(task.getDescription().indexOf(" ") + 1);
            String date = "";
            if (task instanceof Deadline) {
                date = ((Deadline) task).getBy().split(" ", 2)[1].trim();
            } else if (task instanceof Event) {
                date = ((Event) task).getAt().split(" ", 2)[1].trim();
            }
            String taskLabel = task.getType() + GAP + task.getIsDone() + GAP;
            String taskBody = details.trim() + GAP + date + System.lineSeparator();
            fileWriter.write(taskLabel + taskBody);
        }
        fileWriter.close();
    }


}
