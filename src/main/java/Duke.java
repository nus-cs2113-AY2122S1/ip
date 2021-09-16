import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class Duke {
    public static int inputCount = 0;
    public static Task[] tasks = new Task[100];
    private static String taskDoneChecker = "0";
    private static String filePath = "C:\\Users\\Edwar\\Documents\\ip\\data\\duke.txt";


    public static void loadFile() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner fileScan = new Scanner(f); // create a Scanner using the File as the source
        String taskType;
        int fileScanNumber = 1; //order of tasks in duke.txt

        while(fileScan.hasNext())
        {
            String data = fileScan.nextLine(); //scans first line of the file
            String[] arrayString = data.split(" \\| "); //split "todo hello" "0"
            String[] arrayString2 = arrayString[0].split(" ");//split "todo hello"  -> "todo" "hello"
            taskType = arrayString2[0]; //chooses "todo"

            switch (taskType) {
            case "todo":
                toDoMethod(arrayString[0]);
                if (arrayString[1].equals("1")) {
                    loadFileDone(fileScanNumber);
                }
                break;
            case "deadline":
                deadlineMethod(arrayString[0]);
                if (arrayString[1].equals("1")) {
                    loadFileDone(fileScanNumber);
                }
                break;
            case "event":
                eventMethod(arrayString[0]);
                if (arrayString[1].equals("1")) {
                    loadFileDone(fileScanNumber);
                }
                break;
            default:
            }
            fileScanNumber++;
        }
        fileScan.close();

    }

    public static void loadFileDone(Integer number){
        number = number - 1;
        Task task = tasks[number];
        task.markAsDone();
    }

    public static void main(String[] args) {
        try {
            loadFile();
        } catch (FileNotFoundException e22){
            System.out.println("file not found!");
        }
        String input;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        input = in.nextLine();

        while (!(input.equals("bye"))) {

            if (input.equals("list")) {
                input = printList(in);

            } else if (input.contains("done")) {
                input = testTaskDone(input, in);
                try{
                    saveAllTasks();
                }catch (IOException e33) {
                    System.out.println("check your shit");
                    input = in.nextLine();
                }

            } else if (input.contains("todo")) {
                try {
                    testInput(input); // test todo input
                    addTodo(input); // add new entry to duke.txt
                    Task whatToDo = toDoMethod(input);
                    System.out.println("    Got it. I've added this task:\n" + "    " + whatToDo + "\n"
                            + "    Now you have " + inputCount + " tasks in the list.");

                    input = in.nextLine();
                } catch (DukeExceptions e3) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    input = in.nextLine();
                }


            } else if (input.contains("deadline")) {
                try {
                    addDeadline(input); //add new entry to duke.txt
                    Task whatDeadline = deadlineMethod(input);
                    System.out.println("    Got it. I've added this task:\n" + "    " + whatDeadline + "\n"
                            + "    Now you have " + inputCount + " tasks in the list.");

                    input = in.nextLine();
                } catch (StringIndexOutOfBoundsException e1) {
                    System.out.println("input = " + input + "\n☹ OOPS!!! Please retype your input!");
                    input = in.nextLine();
                } catch (ArrayIndexOutOfBoundsException e2) {
                    System.out.println("you have typed in = " + input + "\n☹ OOPS!!! where is your /by my friend? ");
                    input = in.nextLine();
                }

            } else if (input.contains("event")) {
                try {
                    addEvent(input); //add new entry to duke.txt
                    Task whatEvent = eventMethod(input);
                    System.out.println("    Got it. I've added this task:\n" + "    " + whatEvent + "\n"
                            + "    Now you have " + inputCount + " tasks in the list.");
                    
                    input = in.nextLine();
                } catch (StringIndexOutOfBoundsException e1) {
                    System.out.println("input = " + input + "\n☹ OOPS!!! Please retype your input!");
                    input = in.nextLine();
                } catch (ArrayIndexOutOfBoundsException e2) {
                    System.out.println("you have typed in = " + input + "\nw☹ OOPS!!! where is your /at brother? ");
                    input = in.nextLine();
                }

            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");//overall error check
                input = in.nextLine();

            }
        }
        System.out.println("    Bye! Hope to see you again soon!");

    }

    private static String printList(Scanner in) {
        String input;
        System.out.println("    Here are the tasks in your list:");

        for (int outputCount = 0; outputCount < inputCount; outputCount++) {
            System.out.println("    " + (outputCount + 1) + "." + tasks[outputCount]);
        }
        input = in.nextLine();
        return input;
    }

    private static String testTaskDone(String input, Scanner in) {
        int taskNumber;
        String[] inputSplitter;
        inputSplitter = input.split(" ");
        taskNumber = Integer.parseInt(inputSplitter[1]);
        taskNumber = taskNumber - 1;

        if (((taskNumber + 1) > 0) && ((taskNumber + 1) <= inputCount)) { //check for invalid inputs

            input = taskDone(in, tasks[taskNumber]);

        } else {
            System.out.println("    Invalid input! Please check the list again!");
            input = in.nextLine();
        }
        return input;
    }

    private static String taskDone(Scanner in, Task task) {
        String input;
        System.out.println("    Nice! I've marked this task as done:" + System.lineSeparator());
        System.out.println("    " + task + " has been updated to -->");

        task.markAsDone(); //mark x in [ ]

        System.out.println("    " + task);
        input = in.nextLine();
        return input;
    }

    public static Task toDoMethod(String input) {
        String toDoDescription = input.substring(4).trim();

        Task tasksToDo = new Todo(toDoDescription);
        tasks[inputCount] = tasksToDo;
        inputCount++;

        return tasksToDo;

    }

    public static Task deadlineMethod(String input) {
        String[] deadlineSplitter = input.substring(9).split(" /by ");
        String deadlineDescription = deadlineSplitter[0]; //before /by
        String deadlineBy = deadlineSplitter[1]; // after /by

        Task description = new Deadline(deadlineDescription, deadlineBy);
        tasks[inputCount] = description;
        inputCount++;

        return description;

    }

    public static Task eventMethod(String input) {
        String[] eventSplitter = input.substring(6).split(" /at ");
        String eventDescription = eventSplitter[0]; //before /at
        String eventAt = eventSplitter[1]; // after /at

        Task description = new Event(eventDescription, eventAt);
        tasks[inputCount] = description;
        inputCount++;

        return description;
    }

    public static void testInput(String input) throws DukeExceptions {
        if (input.length() < 5) { //check for description after todo
            throw new DukeExceptions();
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void addTodo(String input) {
        String isDone = taskDoneChecker;
        String addToDoDescription = input.substring(4).trim(); // "return book"

        try {
            writeToFile(filePath,
                    System.lineSeparator() + "todo " + addToDoDescription + " | " + isDone);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    public static void addEvent(String input){
        String isDone = taskDoneChecker;
        String[] eventSplitterString = input.substring(6).split(" /at ");
        String addEventDescription = eventSplitterString[0];
        String location = eventSplitterString[1];

        try {
            writeToFile(filePath,
                    System.lineSeparator() + "event " + addEventDescription + " /at " + location + " | " + isDone);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void addDeadline(String input){
        String isDone = taskDoneChecker;
        String[] deadlineSplitterString = input.substring(9).split(" /by ");
        String addDeadlineDescription = deadlineSplitterString[0];
        String date = deadlineSplitterString[1];

        try {
            writeToFile(filePath,
                    System.lineSeparator() + "deadline " + addDeadlineDescription + " /by " + date + " | " + isDone);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void saveAllTasks() throws IOException {
        String taskInFile = "";
        for (int i = 0; i < inputCount ; i++) {
            if (tasks[i] instanceof Deadline) {
                taskInFile = tasks[i].getStoreDataString();
            } else if (tasks[i] instanceof Event) {
                taskInFile = tasks[i].getStoreDataString();
            } else {
                taskInFile = tasks[i].getStoreDataString();
            }

            String fullTaskAsString = taskInFile;
            Files.write(Paths.get(filePath), fullTaskAsString.getBytes(), StandardOpenOption.APPEND);
        }
    }

}

