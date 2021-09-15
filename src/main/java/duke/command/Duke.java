package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.Task;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    static final String LINE_SEPARATOR = "     _______________________\n";
    static final String SPACING = "     ";
    static final String CANNOT_IDENTIFY =
            "I am sorry, but I do not know what do you mean. " +
                    "Please key in a valid input.";
    static final String GREETINGS = LINE_SEPARATOR
            + SPACING + "Hello! I'm Duke\n"
            + SPACING + "What can I do for you?\n"
            + LINE_SEPARATOR;
    static final String NO_ARGUMENT_1 = "The ";
    static final String NO_ARGUMENT_2 = " command is incomplete.";
    static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        readData();
        System.out.println(GREETINGS);
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            switch (line.split(" ")[0]) {
            case "list":
                list();
                break;
            case "done":
                try {
                    int currentTask = Integer.parseInt(line.split(" ")[1]) - 1;
                    taskList.get(currentTask).markAsDone();
                    String update = LINE_SEPARATOR
                            + SPACING + "Nice! I've marked this task as done: \n"
                            + SPACING + taskList.get(currentTask).toString() + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(update);
                    writeData();
                } catch (IndexOutOfBoundsException e) {
                    String error = LINE_SEPARATOR
                            + SPACING + NO_ARGUMENT_1 + "done" + NO_ARGUMENT_2 + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(error);
                }
                break;
            case "deadline":
                try {
                    String taskName = line.substring("deadline ".length(), line.indexOf("/by "));
                    String taskDdl = line.substring(line.indexOf("/by ") + "/by ".length());
                    taskList.add(new Deadline(taskName, taskDdl));
                    String response = LINE_SEPARATOR
                            + SPACING + "added: " + taskList.get(taskList.size() - 1) + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(response);
                    writeData();
                } catch (StringIndexOutOfBoundsException e) {
                    String error = LINE_SEPARATOR
                            + SPACING + NO_ARGUMENT_1 + "deadline" + NO_ARGUMENT_2 + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(error);
                }

                break;
            case "event":
                try {
                    String taskName = line.substring("event ".length(), line.indexOf("/at "));
                    String taskTime = line.substring(line.indexOf("/at ") + "/at ".length());
                    taskList.add(new Event(taskName, taskTime));
                    String response = LINE_SEPARATOR
                            + SPACING + "added: " + taskList.get(taskList.size() - 1) + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(response);
                    writeData();
                } catch (StringIndexOutOfBoundsException e) {
                    String error = LINE_SEPARATOR
                            + SPACING + NO_ARGUMENT_1 + "event" + NO_ARGUMENT_2 + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(error);
                }

                break;
            case "todo":
                try {
                    String taskName = line.substring("todo ".length());
                    taskList.add(new ToDo(taskName));
                    String response = LINE_SEPARATOR
                            + SPACING + "added: " + taskList.get(taskList.size() - 1) + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(response);
                    writeData();
                } catch (StringIndexOutOfBoundsException e) {
                    String error = LINE_SEPARATOR
                            + SPACING + NO_ARGUMENT_1 + "todo" + NO_ARGUMENT_2 + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(error);
                }
                break;
            case "delete":
                try {
                    int currentTask = Integer.parseInt(line.split(" ")[1]) - 1;
                    String update = LINE_SEPARATOR
                            + SPACING + "Noted! I've removed this task: \n"
                            + SPACING + taskList.get(currentTask) + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(update);
                    taskList.remove(currentTask);
                    writeData();
                } catch (IndexOutOfBoundsException e) {
                    String error = LINE_SEPARATOR
                            + SPACING + NO_ARGUMENT_1 + "delete" + NO_ARGUMENT_2 + "\n"
                            + LINE_SEPARATOR;
                    System.out.println(error);
                }
                break;
            default:
                String response = LINE_SEPARATOR
                        + SPACING + CANNOT_IDENTIFY + "\n"
                        + LINE_SEPARATOR;
                System.out.println(response);
            }
        }

        String Bye = LINE_SEPARATOR
                + SPACING + "Bye. Hope to see you again soon!\n"
                + LINE_SEPARATOR;
        System.out.println(Bye);
    }


    public static void list() {
        String listOutput = LINE_SEPARATOR;
        for (int i = 0; i < taskList.size(); i++) {
            listOutput += SPACING + (i + 1) + ". "
                    + taskList.get(i).toString() + "\n";
        }
        listOutput += LINE_SEPARATOR;
        System.out.println(listOutput);

    }
    public static void readData() {
        String filePath = "data/data.txt";
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                String[] currentData = currentLine.split(" \\* ");
                switch (currentData[0]) {
                case "T":
                    taskList.add(new ToDo(currentData[2]));
                    if (currentData[1].equals("1")) {
                        taskList.get(taskList.size() - 1).markAsDone();
                    }
                    break;
                case "D":
                    taskList.add(new Deadline(currentData[2], currentData[3]));
                    if (currentData[1].equals("1")) {
                        taskList.get(taskList.size() - 1).markAsDone();
                    }
                    break;
                case "E":
                    taskList.add(new Event(currentData[2], currentData[3]));
                    if (currentData[1].equals("1")) {
                        taskList.get(taskList.size() - 1).markAsDone();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Something is wrong...");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("File corrupted!");
        }
    }

    public static void writeData () {
        String ddlIndicator = "(by:";
        int statusIdx = 4;
        int contentStartIdx = 7;
        String filePath = "data/data.txt";
        String updatedData = "";
        int indicatorLength = 5;
        for (int i = 0; i < taskList.size(); i++) {
            String currentTask = taskList.get(i).toString();
            String type = currentTask.substring(1,2);
            String status = (currentTask.charAt(statusIdx) == 'X') ? "1" : "0";
            if (currentTask.contains(ddlIndicator)) {
                int end = currentTask.indexOf(ddlIndicator);
                String content = currentTask.substring(contentStartIdx, end);
                String ddl = currentTask.substring(end + indicatorLength, currentTask.length() - 1);
                updatedData += type + " * " + status + " * " + content + " * " + ddl
                        + System.lineSeparator();
            } else if (currentTask.contains("(at:")) {
                int end = currentTask.indexOf("(at: ");
                String content = currentTask.substring(contentStartIdx, end);
                String time = currentTask.substring(end + indicatorLength, currentTask.length() - 1);
                updatedData += type + " * " + status + " * " + content + " * " + time
                        + System.lineSeparator();
            } else {
                String content = currentTask.substring(contentStartIdx);
                updatedData += type + " * " + status + " * " + content
                        + System.lineSeparator();
            }
        }

        try {
            writeToFile(filePath, updatedData);
        } catch (IOException e) {
            System.out.println("Error!");
        }
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
