package storage;

import duke.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import tasklist.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public Storage() {}

    public void loadTextFile(TaskList tasks) throws IOException, DukeException {
        File file = new File("./duke.txt");
        if(file.createNewFile()) {
            throw new DukeException();
        }
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            addTaskFromFile(s,tasks);
            Task.setTotalTasks(Task.getTotalTasks() + 1);
        }
    }

    public void saveTasksToFile(TaskList tasks) throws IOException{
        FileWriter fw = new FileWriter ("./duke.txt");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            fw.write(tasks.getTask(i).getStatusIconAndDescriptionForFile() + System.lineSeparator());
        }
        fw.close();
    }

    public void addTaskFromFile(Scanner s, TaskList tasks) throws IndexOutOfBoundsException {
        String[] parsedOutput = s.nextLine().split(" / ");
        switch(parsedOutput[0]){
        case "T":
            tasks.addTask(new ToDo(parsedOutput[2]));
            updateTaskStatus(parsedOutput[1],tasks);
            break;
        case "E":
            tasks.addTask(new Event(parsedOutput[2], parsedOutput[3]));
            updateTaskStatus(parsedOutput[1],tasks);
            break;
        case "D":
            tasks.addTask(new Deadline(parsedOutput[2], parsedOutput[3]));
            updateTaskStatus(parsedOutput[1],tasks);
            break;
        }
    }

    private void updateTaskStatus(String done, TaskList tasks) {
        if (done.equals("1")) {
            tasks.getTask(Task.getTotalTasks()).markAsDone();
        }
    }
}
