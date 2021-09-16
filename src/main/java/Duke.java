import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import src.main.java.Task;

public class Duke {

    private static String[] stringList = new String[100];
    private static Task[] taskList = new Task[100];
    private static String[] dueDate = new String[100];
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
        taskList[taskListElement].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + taskList[taskListElement].getDescription());
        System.out.println(LINE);
    }

    private static void list() {
        int taskCount = 1;
        for (int i = 0; i < listCount; i++) {
            System.out.println(taskCount + "." + taskList[i]);
            taskCount++;
        }
        System.out.println(LINE);
    }

    private static Todo todo(String line) {

        char taskType = line.toUpperCase().charAt(0);
        String taskDisplay = line.substring(5);

        stringList[listCount] = taskDisplay;
        Todo todoTask = new Todo(taskDisplay, taskType);
        taskList[listCount] = todoTask;
        return todoTask;
    }

    private static Deadline deadline(String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(9, startingIndex);
        String doBy = "(" + line.substring(startingIndex + 1) + ")";
        char taskType = line.toUpperCase().charAt(0);

        stringList[listCount] = taskDisplay;
        dueDate[listCount] = line.substring(startingIndex + 1);
        Deadline deadlineTask = new Deadline(taskDisplay, taskType, doBy);
        taskList[listCount] = deadlineTask;
        return deadlineTask;
    }

    private static Event event(String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(6, startingIndex);
        String doBy = "(" + line.substring(startingIndex + 1) + ")";
        char taskType = line.toUpperCase().charAt(0);

        stringList[listCount] = taskDisplay;
        dueDate[listCount] = line.substring(startingIndex + 1);
        Event eventTask = new Event(taskDisplay, taskType, doBy);
        taskList[listCount] = eventTask;
        return eventTask;
    }

    private static void printTask(Task task, int listCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + listCount + " tasks in the list.");
        System.out.println(LINE);
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

            } else {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(LINE);
        }
    }

    public static void createFile(){
        File f = new File(DATA_FILE_PATH);
        try {
            f.getParentFile().mkdirs();
            if (!f.exists()){
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
                stringList[listCount] = taskDisplay;
                Todo todoTask = new Todo(taskDisplay, 'T');
                taskList[listCount] = todoTask;

                if(line.charAt(4) == '1'){
                    taskList[listCount].markAsDone();
                }
            } else if (line.startsWith("D")) {
                int deadlineIndex = line.indexOf("/");
                String taskDisplay = line.substring(8, deadlineIndex);
                stringList[listCount] = taskDisplay;
                String doBy = "(" + line.substring(deadlineIndex + 1) + ")";
                Deadline deadlineTask = new Deadline(taskDisplay, 'D', doBy);
                taskList[listCount] = deadlineTask;

                if(line.charAt(4) == '1'){
                    taskList[listCount].markAsDone();
                }
            } else if (line.startsWith("E")) {
                int eventIndex = line.indexOf("/");
                String taskDisplay = line.substring(8, eventIndex);
                stringList[listCount] = taskDisplay;
                String doBy = "(" + line.substring(eventIndex + 1) + ")";
                Event eventTask = new Event(taskDisplay, 'E', doBy);
                taskList[listCount] = eventTask;

                if(line.charAt(4) == '1') {
                    taskList[listCount].markAsDone();
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

    public static void writeData(){
        try{
            String firstData = taskList[0].getTaskType() + " | " + taskList[0].getWrittenIcon() + " | " + stringList[0];
            writeToFile(DATA_FILE_PATH , firstData + System.lineSeparator());

            for (int i = 1; i < listCount; i++){
                if (taskList[i].getTaskType() == 'T') {
                    String data = "T | " + taskList[i].getWrittenIcon() + " | " + stringList[i];
                    appendToFile(DATA_FILE_PATH , data + System.lineSeparator());
                } else if (taskList[i].getTaskType() == 'D') {
                    String data = "D | " + taskList[i].getWrittenIcon() + " | " + stringList[i] + " /" + dueDate[i];
                    appendToFile(DATA_FILE_PATH,data + System.lineSeparator());
                } else if (taskList[i].getTaskType() == 'E') {
                    String data = "E | " + taskList[i].getWrittenIcon() + " | " + stringList[i] + " /" + dueDate[i];
                    appendToFile(DATA_FILE_PATH,data + System.lineSeparator());
                }
            }
        } catch(IOException e){
            System.out.println(" I/O ERROR ");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readData();
        greetUser();

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







