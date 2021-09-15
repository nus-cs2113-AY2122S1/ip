package duke;

import duke.command.Deadline;
import duke.command.Event;
import duke.command.ToDo;
import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Duke {
    static final String line_separator = "     _______________________\n";
    static final String spacing = "     ";
    static final String CANNOT_IDENTIFY =
            "I am sorry, but I do not know what do you mean. " +
                    "Please key in a valid input.";
    static final String GREETINGS = line_separator
            + spacing + "Hello! I'm Duke\n"
            + spacing + "What can I do for you?\n"
            + line_separator;
    static final String NO_ARGUMENT_1 = "The ";
    static final String NO_ARGUMENT_2 = " command is incomplete.";
    static final int MAXIMUM = 100;
    static Task[] task_list = new Task[MAXIMUM];
    static int task_num = 0;

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
                list(task_num, task_list);
                break;
            case "done":
                try {
                    int current_task = Integer.parseInt(line.split(" ")[1]) - 1;
                    task_list[current_task].markAsDone();
                    String update = line_separator
                            + spacing + "Nice! I've marked this task as done: \n"
                            + spacing + task_list[current_task].toString() + "\n"
                            + line_separator;
                    System.out.println(update);
                    writeData();
                } catch (ArrayIndexOutOfBoundsException e) {
                    String error = line_separator
                            + spacing + NO_ARGUMENT_1 + "done" + NO_ARGUMENT_2 + "\n"
                            + line_separator;
                    System.out.println(error);
                }
                break;
            case "deadline":
                try {
                    String task_name = line.substring("deadline ".length(), line.indexOf("/by "));
                    String task_ddl = line.substring(line.indexOf("/by ") + "/by ".length());
                    task_list[task_num] = new Deadline(task_name, task_ddl);
                    task_num += 1;
                    String response = line_separator
                            + spacing + "added: " + task_list[task_num - 1] + "\n"
                            + line_separator;
                    System.out.println(response);
                    writeData();
                } catch (StringIndexOutOfBoundsException e) {
                    String error = line_separator
                            + spacing + NO_ARGUMENT_1 + "deadline" + NO_ARGUMENT_2 + "\n"
                            + line_separator;
                    System.out.println(error);
                }

                break;
            case "event":
                try {
                    String task_name = line.substring("event ".length(), line.indexOf("/at "));
                    String task_time = line.substring(line.indexOf("/at ") + "/at ".length());
                    task_list[task_num] = new Event(task_name, task_time);
                    task_num += 1;
                    String response = line_separator
                            + spacing + "added: " + task_list[task_num - 1] + "\n"
                            + line_separator;
                    System.out.println(response);
                    writeData();
                } catch (StringIndexOutOfBoundsException e) {
                    String error = line_separator
                            + spacing + NO_ARGUMENT_1 + "event" + NO_ARGUMENT_2 + "\n"
                            + line_separator;
                    System.out.println(error);
                }

                break;
            case "todo":
                try {
                    String task_name = line.substring("todo ".length());
                    task_list[task_num] = new ToDo(task_name);
                    task_num += 1;
                    String response = line_separator
                            + spacing + "added: " + task_list[task_num - 1] + "\n"
                            + line_separator;
                    System.out.println(response);
                    writeData();
                } catch (StringIndexOutOfBoundsException e) {
                    String error = line_separator
                            + spacing + NO_ARGUMENT_1 + "todo" + NO_ARGUMENT_2 + "\n"
                            + line_separator;
                    System.out.println(error);
                }

                break;
            default:
                String response = line_separator
                        + spacing + CANNOT_IDENTIFY + "\n"
                        + line_separator;
                System.out.println(response);
            }
        }

        String Bye = line_separator
                + spacing + "Bye. Hope to see you again soon!\n"
                + line_separator;
        System.out.println(Bye);
    }


    public static void list(int task_num, Task[] task_list ) {
        String list_output = line_separator;
        for (int i = 0; i < task_num; i++) {
            list_output += spacing + (i+1) + ". "
                    + task_list[i].toString() + "\n";
        }
        list_output += line_separator;
        System.out.println(list_output);

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
                    task_list[task_num] = new ToDo(currentData[2]);
                    task_num += 1;
                    if (currentData[1].equals("1")) {
                        task_list[task_num - 1].markAsDone();
                    }
                    break;
                case "D":
                    task_list[task_num] = new Deadline(currentData[2], currentData[3]);
                    task_num += 1;
                    if (currentData[1].equals("1")) {
                        task_list[task_num - 1].markAsDone();
                    }
                    break;
                case "E":
                    task_list[task_num] = new Event(currentData[2], currentData[3]);
                    task_num += 1;
                    if (currentData[1].equals("1")) {
                        task_list[task_num - 1].markAsDone();
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
        String filePath = "data/data.txt";
        String updatedData = "";
        for (int i = 0; i < task_num; i++) {
            String currentTask = task_list[i].toString();
            String type = currentTask.substring(1,2);
            String status = (currentTask.charAt(4) == 'X') ? "1" : "0";
            if (currentTask.contains("(by:")) {
                int end = currentTask.indexOf("(by: ");
                String content = currentTask.substring(7, end);
                String ddl = currentTask.substring(end + 5, currentTask.length() - 1);
                updatedData += type + " * " + status + " * " + content + " * " + ddl
                        + System.lineSeparator();
            } else if (currentTask.contains("(at:")) {
                int end = currentTask.indexOf("(at: ");
                String content = currentTask.substring(7, end);
                String time = currentTask.substring(end + 5, currentTask.length() - 1);
                updatedData += type + " * " + status + " * " + content + " * " + time
                        + System.lineSeparator();
            } else {
                String content = currentTask.substring(7);
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
//    public boolean mode(String input) {
//
//    }


}
