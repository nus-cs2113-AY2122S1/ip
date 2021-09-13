package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.*;

public class Duke {

    private static int byeFlag = 0;
    private static int loadFlag = 0;
    private static int positionCheck = 0;
    private static String EMPTY = "There is no data in your list master!";
    //private static String EXCEEDED = "Oh dear me! We have exceeded my system's maximum capacity!";
    private static String UNSPECIFIED_DONE = "Oh no master, I am not quite sure which task you would like me to mark as done!";
    private static String UNSPECIFIED_DELETE = "Oh no master, I am not quite sure which task you would like me to delete!";
    private static String INVALID = "Please type in a valid number master! Type \"list\" to check the index number of your list data";
    public static String DEADLINE_ERROR = ("Sorry Master! I don't think you have properly keyed in the parameters.\n" +
            "Please enter \"deadline\", followed by the task, followed by \"/by\", \n" +
            "and lastly followed by the due date to specify the deadline Master!");
    public static String EVENT_ERROR = ("Sorry Master! I don't think you have properly keyed in the parameters. \n" +
            "Please enter \"event\", followed by the event, followed by \"/at\", and \n" +
            "lastly followed by the event duration to specify the timing of the event Master!");
    public static String TODO_ERROR = ("Sorry Master! I don't think you have properly keyed in the parameters.\n" +
            " Please enter \"todo\", followed by the task you wish to add to your \n" +
            "duke.Todo list Master!");
    public static String UNSPECIFIED_TASK = ("Sorry Master! Despite the fact that I am fluent in over six million forms\n" +
            " of communication, I am unable to comprehend your request. Please specify\n" +
            " the type of task that you wish to add Master!");

    private static ArrayList<Task> commands = new ArrayList<>();

    private static String filePath = "C:\\Users\\visha\\Desktop\\Year 2 Sem 1 Modules\\CS2113T\\IndivFinalProject\\Tasks.txt";

    // sendCommand() is a method used to allow the user to send his/her commands to C3PO
    private static void sendCommand() {
        String line;
        Scanner in = new Scanner(System.in);
        while (byeFlag != 1) {
            try {
                System.out.println("____________________________________________________________\n");
                System.out.print("Type something: ");
                line = in.nextLine();
                System.out.println("____________________________________________________________\n");
                checkCommand(line);
            } catch (NumberFormatException e) {
                System.out.println("Master, please type in a number to indicate the task you want me to perform the necessary actions for!");
            } catch (DukeException e) {

            } catch (IOException e) {

            }
        }
    }

    // checkCommand() is a method that allows us to determine when the user says bye.
    private static void checkCommand(String line) throws DukeException, IOException {
        String[] input = line.split(" ");
        if (line.equals("bye")) {
            byeFlag = 1;
        } else if (line.equals("list")) {
            if (positionCheck == 0) {
                throw new DukeException(EMPTY);
            } else {
                printList();
            }
        } else if (line.equals("done")) {
            throw new DukeException(UNSPECIFIED_DONE);
        } else if (line.equals("delete")) {
            throw new DukeException(UNSPECIFIED_DELETE);
        } else if ((input[0].equals("done")) || (input[0].equals("delete"))) {
            if (positionCheck<=0) {
                throw new DukeException(EMPTY);
            } else if ((Integer.parseInt(input[1]) > positionCheck ) || (Integer.parseInt(input[1]) <= 0)) {
                throw new DukeException(INVALID);
            } else {
                if (input[0].equals("done")) {
                    markDone(Integer.parseInt(input[1])-1);
                } else {
                    deleteTask(Integer.parseInt(input[1])-1);
                }
            }
        } else {
            checkTypeOfTask(line);
        }
    }

    public static void sayBye() {
        System.out.println("Goodbye master! May the force be with you!\n");
        System.out.println("____________________________________________________________\n");
    }

    public static void addDeadline(String[] input, int length) throws DukeException, IOException {
        String description;
        String by;
        for (int i = 1 ; i < length ; i++) {
            if ((input[i].equals("/by")) && (i != 1) && (i != (length-1))) {
                description = input[1];
                by = input[i+1];
                for (int j = 2 ; j < i ; j++) {
                    description += (" " + input[j]);
                }
                for (int k = i+2 ; k < length ; k++) {
                    by += (" " + input[k]);
                }
                commands.add( new Deadline(description,by) );
                if (loadFlag == 1) {
                    System.out.println("Added to Galactic database:" );
                    System.out.println(commands.get(positionCheck));
                    saveNewTask(input);
                }
                positionCheck += 1;
                return;
            }
        }
        throw new DukeException(DEADLINE_ERROR);
    }

    public static void addEvent(String[] input, int length) throws DukeException, IOException{
        String description;
        String at;
        for (int i = 1 ; i < length ; i++) {
            if ((input[i].equals("/at")) && (i != 1) && (i != (length-1))) {
                description = input[1];
                at = input[i+1];
                for (int j = 2 ; j < i ; j++) {
                    description += (" " + input[j]);
                }
                for (int k = i+2 ; k < length ; k++) {
                    at += (" " + input[k]);
                }
                commands.add( new Event(description,at) );
                if (loadFlag == 1) {
                    System.out.println("Added to Galactic database:");
                    System.out.println(commands.get(positionCheck));
                    saveNewTask(input);
                }
                positionCheck += 1;
                return;
            }
        }
        throw new DukeException(EVENT_ERROR);
    }

    public static void addTodo (String[] input, int length) throws DukeException, IOException{
        if (length == 1) {
            throw new DukeException(TODO_ERROR);
        } else {
            String description = input[1];
            for (int i = 2 ; i < length ; i++) {
                description += (" " + input[i]);
            }
            commands.add( new Todo(description) );
            if (loadFlag == 1) {
                System.out.println("Added to Galactic database:");
                System.out.println(commands.get(positionCheck));
                saveNewTask(input);
            }
            positionCheck += 1;
        }
    }

    public static void checkTypeOfTask(String line) throws DukeException, IOException {
        String[] input = line.split(" ");
        int length = input.length;
        if (input[0].toLowerCase().equals("deadline")) {
            addDeadline(input,length);
        } else if (input[0].toLowerCase().equals("event")) {
            addEvent(input,length);
        } else if (input[0].toLowerCase().equals("todo")) {
            addTodo(input,length);
        } else {
            throw new DukeException(UNSPECIFIED_TASK);
        }
    }

    private static void printList() {
        System.out.println("Accessing archives...");
        int i = 1;
        for (Task num : commands) {
            System.out.println(i + ". " + num);
            i += 1;
        }
    }

    private static void markDone(int doneTaskNumber) throws IOException{
        (commands.get(doneTaskNumber)).markAsDone();
        if (loadFlag == 1) {
            System.out.println("The following task has been marked as done Master!");
            System.out.println((doneTaskNumber + 1) + ". " + commands.get(doneTaskNumber));
        }
        saveAllTasks();
    }

    private static void deleteTask(int doneTaskNumber) throws IOException {
        System.out.println("Taking one last look Master, at this Task. Removing the following from my memory");
        System.out.println((doneTaskNumber+1) + ". " + commands.get(doneTaskNumber));
        commands.remove(commands.get(doneTaskNumber));
        positionCheck -= 1;
        System.out.println("Goodbye Task, may the force be with you. You have " + positionCheck + " task(s) left Master");
        saveAllTasks();
    }

    public static void greetUser() {
        String logo = "       /~\\\n"
                + "      |oo )\n"
                + "      _\\=/_\n"
                + "     /     \\\n"
                + "    //|/.\\|\\\\\n"
                + "   ||  \\_/  ||\n"
                + "   || |\\ /| ||\n"
                + "    # \\_ _/  #\n"
                + "      | | |\n"
                + "      | | |\n"
                + "      []|[]\n"
                + "      | | |\n"
                + "     /_]_[_\\\n";
        System.out.println("____________________________________________________________\n");
        System.out.println("Hello! I am C3P0! Human-cyborg relations! \n" + " \n" + logo);
        System.out.println("What can I do for you my master?\n");
    }

    public static void saveNewTask(String[] input) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String fullTaskAsString = "";
        for (String individualString : input) {
            fullTaskAsString += individualString + " ";
        }
        fullTaskAsString += "| 0";
        fw.write(fullTaskAsString + "\n");
        fw.close();
    }

    public static void saveAllTasks() throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        String taskInFile = "";
        String done = "";
        for (Task individualTask : commands) {
            if (individualTask instanceof Deadline) {
                taskInFile = "deadline " + individualTask.description + " /by " + ((Deadline) individualTask).by;
            } else if (individualTask instanceof Event) {
                taskInFile = "event " + individualTask.description + " /at " + ((Event) individualTask).at;
            } else {
                taskInFile = "todo " + individualTask.description;
            }
            if (individualTask.isDone) {
                done = "1";
            } else {
                done = "0";
            }
            String fullTaskAsString = taskInFile + " | " + done + "\n";
            Files.write(Paths.get(filePath), fullTaskAsString.getBytes(), StandardOpenOption.APPEND);
        }
    }

    public static void loadTasks() throws FileNotFoundException, DukeException, IOException {
        File f = new File("C:\\Users\\visha\\Desktop\\Year 2 Sem 1 Modules\\CS2113T\\IndivFinalProject\\Tasks.txt");
        Scanner s = new Scanner(f);
        int taskNumber = 0;
        String textFromFile;
        String[] taskFromFile;
        String[] taskInput;
        while (s.hasNext()) {
            textFromFile = s.nextLine();
            taskFromFile = textFromFile.split(" \\| ");
            taskInput = (taskFromFile[0]).split(" ");
            switch(taskInput[0]) {
            case "deadline":
                addDeadline(taskInput, taskInput.length);
                break;
            case "event":
                addEvent(taskInput, taskInput.length);
                break;
            case "todo":
                addTodo(taskInput, taskInput.length);
            default:
            }
            if ( taskFromFile[1].equals("1")) {
                markDone(taskNumber);
            }
            taskNumber += 1;
        }
        loadFlag = 1;
    }

    public static void main (String[] args) throws DukeException, IOException {
        try {
            loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        greetUser();
        sendCommand();
        saveAllTasks();
        sayBye();
    }
}
