package duke.processes;

import duke.Duke;
import duke.processes.tasks.Deadlines;
import duke.processes.tasks.Event;
import duke.processes.tasks.Task;
import duke.processes.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProcessFiles {
    public static void LoadTasks() {
        String[] command;
        File ikarosTaskData = new File("ikarosTaskData.txt");
        Scanner scan;
        try {
            scan = new Scanner(ikarosTaskData);
            if (ikarosTaskData.exists()) {
                while (scan.hasNext()) {
                    command = scan.nextLine().split(">");
                    loadingManager(command);
                }
            } else {
                FileWriter f = new FileWriter(ikarosTaskData.getAbsoluteFile());
                f.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. pls try reload again, or start a new");
        } catch (IOException e) {
            System.out.println("File corrupted. pls try reload again, or start a new");
        } finally {
            Interface.printList();
            System.out.println(Interface.lineBreak);
        }
    }

    private static void loadingManager(String[] command) {
        switch (command[0]) {
        case "todo":
            ToDo taskTodo = new ToDo(command[2]);
            Duke.taskList.add(taskTodo);
            break;
        case "deadline":
            Deadlines taskDeadline = new Deadlines(command[2], command[3]);
            Duke.taskList.add(taskDeadline);
            break;
        case "event":
            Event taskEvent = new Event(command[2], command[3]);
            Duke.taskList.add(taskEvent);
            break;
        default:
            System.out.println("file error");
        }
        if (command[1].equalsIgnoreCase("X")) {
            Duke.taskList.get(Duke.taskList.size() - 1).markAsDone();
        }
    }

    public static void SaveTasks() {
        try {
            FileWriter fw = new FileWriter("ikarosTaskData.txt");
            for (Task task : Duke.taskList) {
                fw.write(task.getTaskType() + ">"
                        + task.getStatusIcon() + ">"
                        + task.getDescription() + ">"
                        + task.getDate() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }
}
