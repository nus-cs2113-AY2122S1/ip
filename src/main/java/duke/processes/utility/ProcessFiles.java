package duke.processes.utility;

import duke.Duke;
import duke.processes.tasks.Deadlines;
import duke.processes.tasks.Event;
import duke.processes.tasks.Task;
import duke.processes.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ProcessFiles {

    protected static DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy-HHmm");

    /**
     * Scans the text file and inputs the values into the programs ArraysList by
     * calling the loadTasks function
     */
    public static void loadFile() {

        String[] command;
        File ikarosTaskData = new File("ikarosTaskData.txt");
        Scanner scan;

        try {
            scan = new Scanner(ikarosTaskData);
            if (ikarosTaskData.exists()) {
                while (scan.hasNext()) {
                    command = scan.nextLine().split(">");
                    loadTasks(command);
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
            System.out.println("Here are the tasks in your list:");
            Interface.printList(Duke.taskList);
            System.out.println(Interface.lineBreak);
        }
    }

    /**
     * Parses through the given string array of commands and then adds the specific
     * command to the Arraylist of type Task and prints error if file is in the incorrect
     * format or corrupted.
     *
     * @param command the single line command stored in the .txt file
     */
    private static void loadTasks(String[] command) {

        try {
            switch (command[0]) {
            case "todo":
                ToDo taskTodo = new ToDo(command[2], LocalDateTime.parse(command[3], format1));
                Duke.taskList.add(taskTodo);
                break;
            case "deadline":
                Deadlines taskDeadline = new Deadlines(command[2], LocalDateTime.parse(command[3], format1));
                Duke.taskList.add(taskDeadline);
                break;
            case "event":
                Event taskEvent = new Event(command[2], LocalDateTime.parse(command[3], format1));
                Duke.taskList.add(taskEvent);
                break;
            default:
                System.out.println("file error");
            }
            if (command[1].equalsIgnoreCase("X")) {
                Duke.taskList.get(Duke.taskList.size() - 1).markAsDone();
            }
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            System.out.println("File may be corrupted please check your storage file and start the program again!");
        }
    }

    /**
     * At the end of the programs rewrites the date file and stores the new values in
     * the Arraylist to remember the list for the next time the program runs
     */
    public static void saveTasks() {

        try {
            FileWriter fw = new FileWriter("ikarosTaskData.txt");
            for (Task task : Duke.taskList) {
                fw.write(task.getTaskType() + ">"
                        + task.getStatusIcon() + ">"
                        + task.getDescription() + ">"
                        + task.getDateValue().format(format1) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }
}
