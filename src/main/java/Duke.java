package ip.src.main.java;
import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    //private Ui ui;
    //private Parser parser;

    public static void main(String[] args) throws DukeException, IOException {

        Storage storage = new Storage();
        TaskList tasks = new TaskList();
        int localCount=0;

        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        String line = null;
        String formattedLine;
        String[] newline;

        try {
            System.out.println("Here's your content from last time!");
            storage.printFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while (!Objects.equals(line, "bye")) {
            line = in.nextLine();
            formattedLine = line.substring(1); //chops off first letter

            String[] arr = line.split(" ", 2);
            if (Objects.equals(line, "list")) {
                System.out.println("Here are the tasks in your list:");
                tasks.printTask();
            }
            else if (Objects.equals(arr[0], "remove")) {
                System.out.println("Removing to do list item boss!");
                tasks.remove(Integer.parseInt(arr[1]));
            }
            else if (Objects.equals(arr[0], "done")) {
                System.out.println("Nice! I've marked Task " + arr[1] + " as done!");
                TaskList.markDone(Integer.parseInt(arr[1]));
            }
            else if (Objects.equals(arr[0], "d")) {
                newline = formattedLine.split("/by");
                Deadline t = new Deadline(newline[0], newline[1]);
                TaskList.addTask(t, 1);
                System.out.println("Gotcha! I've added this deadline");
                storage.saveFile(tasks.getTask(++localCount));
            }
            else if (Objects.equals(arr[0], "e")) {
                newline = formattedLine.split("/at");
                Event t = new Event(newline[0], newline[1]);
                tasks.addTask(t, 2);
                System.out.println("Gotcha! I've added this event");
                storage.saveFile(tasks.getTask(++localCount));
            }
            else if (Objects.equals(arr[0], "t")){
                try {
                    Task t = new Task(formattedLine);
                    tasks.addTask(t, 3);
                    System.out.println("Gotcha! I've added this todo");
                    //System.out.println("i=" + localCount);
                    storage.saveFile(tasks.getTask(++localCount));
                    if (!Objects.equals(line, "bye")) System.out.println("added: " + formattedLine);
                } catch (DukeException e) {
                    System.out.println("OOPS! The description of a todo cannot be empty. Please input again!");
                }
            }
            else {
                if (!Objects.equals(line, "bye")) System.out.println("Sorry please rephrase");
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
