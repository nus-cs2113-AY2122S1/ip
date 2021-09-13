package duke;

import duke.exceptions.DukeException;
import duke.exceptions.IncompleteInformationException;
import duke.exceptions.InvalidRequestException;

import java.util.Scanner;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        TaskList list = new TaskList();
        //if file exist, load file into TaskList. else create file
        File file = loadFile();
        //read file and store data into list
        readFile(list,file);
        String request = in.nextLine();
        while (Request.isBye(request)) {
            try {
                if (Request.isList(request)) {
                    list.printTasks();
                } else if (Request.isDone(request)) {
                    list.doneTask(request);
                } else {
                    list.addTask(request);
                }
                request = in.nextLine();
            } catch (DukeException ex) {
                if (ex instanceof IncompleteInformationException) {
                    System.out.println(ex.getMessage());
                } else if (ex instanceof InvalidRequestException) {
                    System.out.println("☹ OOPS!!! I can't do that.");
                }
                request = in.nextLine();
            } catch (Exception ex) {
                if (ex instanceof IndexOutOfBoundsException) {
                    System.out.println("Sorry I can't do that! " +
                            "Try \"done <number inside the list>\" instead :)");
                } else {
                    System.out.println(ex.getClass());
                    System.out.println("Some error i don't know of");
                }
                request = in.nextLine();
            }
        }
        //write taskList into the txt file
        writeFile(list,file);
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void writeFile(TaskList list, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        for(int i = 0; i < list.size(); i++) {
            writer.write(list.saveTask(i) + "\n");
        }
        writer.close();
    }

    private static void readFile(TaskList list, File file) throws FileNotFoundException{
        Scanner s = new Scanner(file);
        while (s.hasNext()){
            String[] taskInfo = s.nextLine().split(",");
            if (taskInfo[0].equals("duke.Task")) {
                Task todo = new Task(Boolean.parseBoolean(taskInfo[1]), taskInfo[2]);
                list.loadTask(todo);
            } else if (taskInfo[0].equals("duke.Event")) {
                Task event = new Event(Boolean.parseBoolean(taskInfo[1]),taskInfo[2], taskInfo[3]);
                list.loadTask(event);
            } else {
                Task deadline = new Deadline(Boolean.parseBoolean(taskInfo[1]), taskInfo[2], taskInfo[3]);
                list.loadTask(deadline);
            }
        }
    }

    private static File loadFile() throws IOException {
        File file = new File("data/duke.txt");
        if (!file.exists()) {
            File dir = new File("data");
            dir.mkdir();
            File newFile = new File("data/duke.txt");
            newFile.createNewFile();
            return newFile;
        }
        return file;
    }
}
