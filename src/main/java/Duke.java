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

    private static String file = "ip/src/main/java/duke.txt";

    //private static Task[] tasks = new Task[100];
    private static ArrayList<String> letter = new ArrayList<>();
    private static ArrayList<String> done = new ArrayList<>();
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static int i=0;

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }
    public static void saveFile(int i) {
        try {
            writeToFile(file, i + " | ");
            writeToFile(file, letter.get(i - 1) + " | ");
            writeToFile(file, done.get(i - 1) + " | ");
            writeToFile(file, tasks.get(i - 1).description());
            writeToFile(file, System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void addTask(Task t, int type) {
        tasks.add(t);
        if (type == 1) letter.add("D");
        else if (type == 2) letter.add("E");
        else if (type == 3) letter.add("T");
        done.add(" ");
        i++;
    }

    public static void markDone(int i) {
        done.set(i, "X");
    }

    public static void remove(int i) {
        done.remove(i-1);
        letter.remove(i-1);
        tasks.remove(i-1);
    }

    public static void printTask() {
        for (int j=1; j<=tasks.size(); j++) System.out.println(j + ". "
                + "[" + letter.get(j-1) + "] "
                + "[" + done.get(j-1) + "] " +
                tasks.get(j-1).description());
    }

    public static void main(String[] args) throws DukeException, IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        String line = null;
        String formattedLine;
        String[] newline;

        try {
            System.out.println("Here's your content from last time!");
            printFileContents(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while (!Objects.equals(line, "bye")) {
            line = in.nextLine();
            formattedLine = line.substring(1); //chops off first letter

            String[] arr = line.split(" ", 2);
            if (Objects.equals(line, "list")) {
                System.out.println("Here are the tasks in your list:");
                printTask();
            }
            else if (Objects.equals(arr[0], "remove")) {
                System.out.println("Removing to do list item boss!");
                remove(Integer.parseInt(arr[1]));
            }
            else if (Objects.equals(arr[0], "done")) {
                System.out.println("Nice! I've marked Task " + arr[1] + " as done!");
                markDone(Integer.parseInt(arr[1]));
            }
            else if (Objects.equals(arr[0], "d")) {
//                try {
//                    if (formattedLine.contains("/by")) {
                        newline = formattedLine.split("/by");
                        Deadline t = new Deadline(newline[0], newline[1]);
                        addTask(t, 1);
                        System.out.println("Gotcha! I've added this deadline");
                        saveFile(i);
//                    }
//                } catch (DukeException e) {
//                    System.out.println("OOPS! The description cannot be empty. Please input again!");
//                }
            }
            else if (Objects.equals(arr[0], "e")) {
//                try {
//                    if (formattedLine.contains("/at")) {
                        newline = formattedLine.split("/at");
                        Event t = new Event(newline[0], newline[1]);
                        addTask(t, 2);
                        System.out.println("Gotcha! I've added this event");
                        saveFile(i);
//                    }
//                } catch (DukeException e) {
//                    System.out.println("OOPS! The description cannot be empty. Please input again!");
//                }
            }
            else if (Objects.equals(arr[0], "t")){
                try {
                    Task t = new Task(formattedLine);
                    addTask(t, 3);
                    System.out.println("Gotcha! I've added this todo");
                    saveFile(i);
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
