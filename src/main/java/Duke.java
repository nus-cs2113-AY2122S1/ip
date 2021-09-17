
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import src.main.java.Task;

public class Duke {


    private static ArrayList<String> stringList = new ArrayList<>();
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static ArrayList<String> dueDate = new ArrayList<>();

    private static int listCount = 0;

    private static final String DATA_FILE_PATH = "./data/data.txt";

    private static final String LINE = "-------------------------------------------------------------------\n";
    private static final String GREET_USER = "Hello! I'm Duke \nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private static void greetUser() {
        System.out.println(GREET_USER + "\n \n" + LINE);
    }

    private static void markAsDone(String line) {
        String taskNumber = line.substring(5);
        int taskListElement = Integer.parseInt(taskNumber) - 1;
        taskList.get(taskListElement).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + taskList.get(taskListElement).getDescription());
        System.out.println(LINE);
    }

    private static void list() {
        int taskCount = 1;
        for (int i = 0; i < listCount; i++) {
            System.out.println(taskCount + "." + taskList.get(i));
            taskCount++;
        }
        System.out.println(LINE);
    }

    private static Todo todo(String line) {
        char taskType = line.toUpperCase().charAt(0);
        String taskDisplay = line.substring(5);

        stringList.add(listCount, taskDisplay);
        dueDate.add(listCount, null);
        Todo todoTask = new Todo(taskDisplay, taskType);
        taskList.add(listCount, todoTask);
        return todoTask;
    }

    private static Deadline deadline(String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(9, startingIndex);
        String doBy = "(" + line.substring(startingIndex + 1) + ")";
        char taskType = line.toUpperCase().charAt(0);

        stringList.add(listCount, taskDisplay);
        dueDate.add(listCount, line.substring(startingIndex + 1));
        Deadline deadlineTask = new Deadline(taskDisplay, taskType, doBy);
        taskList.add(listCount, deadlineTask);
        return deadlineTask;
    }

    private static Event event(String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(6, startingIndex);
        String doBy = "(" + line.substring(startingIndex + 1) + ")";
        char taskType = line.toUpperCase().charAt(0);

        stringList.add(listCount, taskDisplay);
        dueDate.add(listCount, line.substring(startingIndex + 1));
        Event eventTask = new Event(taskDisplay, taskType, doBy);
        taskList.add(listCount, eventTask);
        return eventTask;
    }

    private static void printTask(Task task, int listCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + listCount + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void delete(String line) {
        int taskIndex = Integer.parseInt(line.substring(7)) - 1;
        Task task = taskList.get(taskIndex);
        listCount--;
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + listCount + " tasks in the list.");
        System.out.println(LINE);
        taskList.remove(taskIndex);
        dueDate.remove(taskIndex);
        stringList.remove(taskIndex);
    }

    private static void mainProgram(String line, Scanner in) {

        try {
            if (line.startsWith("done")) {
                markAsDone(line);
                writeData();

            } else if (line.equals("list")) {
                list();

            } else if (line.startsWith("todo")) {
                try {
                    Todo todoTask = todo(line);
                    listCount++;
                    printTask(todoTask, listCount);
                    writeData();
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("Please format your input as 'todo <task>'");
                    System.out.println(LINE);
                }

            } else if (line.startsWith("deadline")) {
                try {
                    Deadline deadlineTask = deadline(line);
                    listCount++;
                    printTask(deadlineTask, listCount);
                    writeData();
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The format of deadline is wrong");
                    System.out.println("Please format your input as 'deadline <task>/<due date>'");
                    System.out.println(LINE);
                }

            } else if (line.startsWith("event")) {
                try {
                    Event eventTask = event(line);
                    listCount++;
                    printTask(eventTask, listCount);
                    writeData();
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The format of event is wrong");
                    System.out.println("Please format your input as 'event <task>/<event date and time>'");
                    System.out.println(LINE);
                }

            } else if (line.startsWith("delete")) {
                try {
                    delete(line);
                    writeData();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The task number is not valid");
                    System.out.println("Please check again and format your input as 'delete <task number>'");
                    System.out.println(LINE);
                }

            } else {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(LINE);
        }
    }

    public static void createFile() {
        File f = new File(DATA_FILE_PATH);
        try {
            f.getParentFile().mkdirs();
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(" I/O ERROR ");
        }
    }

    public static void readData() throws FileNotFoundException {
        File f = new File(DATA_FILE_PATH);
        createFile();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();

            if (line.startsWith("T")) {
                String taskDisplay = line.substring(8);
                stringList.add(listCount, taskDisplay);
                Todo todoTask = new Todo(taskDisplay, 'T');
                taskList.add(listCount, todoTask);
                dueDate.add(listCount, null);

                if (line.charAt(4) == '1') {
                    taskList.get(listCount).markAsDone();
                }
            } else if (line.startsWith("D")) {
                int deadlineIndex = line.indexOf("/");
                String taskDisplay = line.substring(8, deadlineIndex);
                stringList.add(listCount, taskDisplay);
                String doBy = "(" + line.substring(deadlineIndex + 1) + ")";
                Deadline deadlineTask = new Deadline(taskDisplay, 'D', doBy);
                taskList.add(listCount, deadlineTask);
                dueDate.add(listCount, line.substring(deadlineIndex + 1));

                if (line.charAt(4) == '1') {
                    taskList.get(listCount).markAsDone();
                }
            } else if (line.startsWith("E")) {
                int eventIndex = line.indexOf("/");
                String taskDisplay = line.substring(8, eventIndex);
                stringList.add(listCount, taskDisplay);
                String doBy = "(" + line.substring(eventIndex + 1) + ")";
                Event eventTask = new Event(taskDisplay, 'E', doBy);
                taskList.add(listCount, eventTask);
                dueDate.add(listCount, line.substring(eventIndex + 1));

                if (line.charAt(4) == '1') {
                    taskList.get(listCount).markAsDone();
                }
            }
            listCount++;
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void writeData() {
        try {
            if (taskList.get(0).getTaskType() == 'T') {
                String firstData = taskList.get(0).getTaskType() + " | " + taskList.get(0).getWrittenIcon() + " | " + stringList.get(0);
                writeToFile(DATA_FILE_PATH, firstData + System.lineSeparator());
            } else {
                String firstData = taskList.get(0).getTaskType() + " | " + taskList.get(0).getWrittenIcon() + " | " + stringList.get(0) + " /" + dueDate.get(0);
                writeToFile(DATA_FILE_PATH, firstData + System.lineSeparator());
            }

            for (int i = 1; i < listCount; i++) {
                if (taskList.get(i).getTaskType() == 'T') {
                    String data = "T | " + taskList.get(i).getWrittenIcon() + " | " + stringList.get(i);
                    appendToFile(DATA_FILE_PATH, data + System.lineSeparator());
                } else if (taskList.get(i).getTaskType() == 'D') {
                    String data = "D | " + taskList.get(i).getWrittenIcon() + " | " + stringList.get(i) + " /" + dueDate.get(i);
                    appendToFile(DATA_FILE_PATH, data + System.lineSeparator());
                } else if (taskList.get(i).getTaskType() == 'E') {
                    String data = "E | " + taskList.get(i).getWrittenIcon() + " | " + stringList.get(i) + " /" + dueDate.get(i);
                    appendToFile(DATA_FILE_PATH, data + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println(" I/O ERROR ");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readData();
        greetUser();
        System.out.println(listCount);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            mainProgram(line, in);
            line = in.nextLine();
        }

        System.out.println(EXIT_MESSAGE);
        System.out.println(LINE);
    }
}







