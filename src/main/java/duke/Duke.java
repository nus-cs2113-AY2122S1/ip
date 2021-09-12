package duke;

import duke.exceptions.DukeException;
import duke.exceptions.IncompleteInformationException;
import duke.exceptions.InvalidRequestException;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
        //if file exist, load file into TaskList
        File f = getFile();
        System.out.println(f.getAbsolutePath());

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
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static File getFile() throws IOException {
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
