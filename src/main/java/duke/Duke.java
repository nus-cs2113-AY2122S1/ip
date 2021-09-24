package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class Duke {

    private static Storage storage = new Storage("Tasks.txt");
    private static Ui ui = new Ui();
    private static TaskList tasks = new TaskList();


    private static int byeFlag = 0;
    private static int loadFlag = 0;
    private static int positionCheck = 0;

    private static ArrayList<Task> commands = tasks.commands;

    private static void sendCommand() {
        String line;
        Scanner in = new Scanner(System.in);
        while (byeFlag != 1) {
            try {
                System.out.println(Response.LINE);
                System.out.print(Response.USER_PROMPT_MESSAGE);
                line = in.nextLine();
                System.out.println(Response.LINE);
                checkCommand(line);
            } catch (NumberFormatException e) {
                System.out.println(Response.NUMBER_FORMAT_EXCEPTION);
            } catch (DukeException e) {
                //Catching DukeException errors
            } catch (IOException e) {
                System.out.print("There is an error in your input master! Please check it out!");
            } catch (DateTimeParseException e) {
                System.out.println("Correct date format please!");
            }
        }
    }

    public static void checkCommand(String line) throws DukeException, IOException {
        String[] input = line.split(" ");
        String firstWord = input[0];
        if (input.length == 1) {
            switch (line) {
            case "bye":
                byeFlag = 1;
                break;
            case "list":
                if (positionCheck == 0) {
                    throw new DukeException(Response.EMPTY);
                } else {
                    printList();
                    break;
                }
            case "done":
                throw new DukeException(Response.UNSPECIFIED_DONE);
            case "delete":
                throw new DukeException(Response.UNSPECIFIED_DELETE);
            case "find":
                throw new DukeException(Response.UNSPECIFIED_FIND);
            case "date":
                throw new DukeException(Response.UNSPECIFIED_DATE);
            default:
                throw new DukeException(Response.UNSPECIFIED_TASK);
            }
        } else {
            String secondWord = input[1];
            switch (firstWord) {
            case "done":
            case "delete":
                int taskNumber = Integer.parseInt(input[1]);
                int taskIndex = taskNumber - 1;
                if (positionCheck <= 0) {
                    throw new DukeException(Response.EMPTY);
                } else if ( (taskNumber > positionCheck ) || ( taskNumber <= 0) ) {
                    throw new DukeException(Response.INVALID);
                } else if (firstWord.equals("done")) {
                    markDone(taskIndex);
                    break;
                } else {
                    deleteTask(taskIndex);
                    break;
                }
            case "find":
                String keyword = secondWord;
                for (int i = 2 ; i < input.length ; i++ ) {
                    keyword += " " + input[i];
                }
                printListForFindingTask(keyword);
                break;
            case "date":
                printListForFindingDate(secondWord);
                break;
            default:
                checkTypeOfTask(line);
                break;
            }
        }
    }

    public static void addDeadline(String[] input, int length) throws DukeException, IOException, DateTimeParseException {
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
                commands.add(new Deadline(description,by));
                if (loadFlag == 1) {
                    System.out.println("Added to Galactic database:" );
                    System.out.println(commands.get(positionCheck));
                    storage.saveNewTask(input);
                }
                positionCheck += 1;
                return;
            }
        }
        throw new DukeException(Response.DEADLINE_ERROR);
    }

    public static void addEvent(String[] input, int length) throws DukeException, IOException, DateTimeParseException {
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
                    storage.saveNewTask(input);
                }
                positionCheck += 1;
                return;
            }
        }
        throw new DukeException(Response.EVENT_ERROR);
    }

    public static void addTodo(String[] input, int length) throws DukeException, IOException {
        if (length == 1) {
            throw new DukeException(Response.TODO_ERROR);
        } else {
            String description = input[1];
            for (int i = 2 ; i < length ; i++) {
                description += (" " + input[i]);
            }
            commands.add( new Todo(description) );
            if (loadFlag == 1) {
                System.out.println("Added to Galactic database:");
                System.out.println(commands.get(positionCheck));
                storage.saveNewTask(input);
            }
            positionCheck += 1;
        }
    }

    public static void checkTypeOfTask(String line) throws DukeException, IOException {
        String[] input = line.split(" ");
        int length = input.length;
        String firstWord = input[0];
        switch (firstWord) {
        case "deadline":
            addDeadline(input,length);
            break;
        case "event":
            addEvent(input,length);
            break;
        case "todo":
            addTodo(input,length);
            break;
        default:
            throw new DukeException(Response.UNSPECIFIED_TASK);
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

    private static void printListForFindingDate(String keyword) {

        System.out.println("Accessing archives...");
        System.out.println("Generating all the tasks that contain \"" + keyword + "\"...");
        int i = 1;
        LocalDate date = LocalDate.parse(keyword);
        for (Task num : commands) {
            if (num instanceof Event) {
                if (((Event) num).at.contains(keyword)) {
                    System.out.println(i + ". " + num);
                    i += 1;
                }
            } else if (num instanceof Deadline) {
                if (((Deadline) num).by.contains(keyword)) {
                    System.out.println(i + ". " + num);
                    i += 1;
                }
            }
        }
        if (i == 1) {
            System.out.println("There are no tasks that occur on \"" + keyword + "\" master. My apologies!");
        }
    }

    private static void printListForFindingTask(String keyword) {
        System.out.println("Accessing archives...");
        System.out.println("Generating all the tasks that contain \"" + keyword + "\"...");
        int i = 1;
        for (Task num : commands) {
            if ((num.description).contains(keyword)) {
                System.out.println(i + ". " + num);
                i += 1;
            }
        }
        if (i == 1) {
            System.out.println("Unfortunately, there are no tasks that contain \"" + keyword + "\" master. My apologies!");
        }
    }

    private static void markDone(int doneTaskNumber) throws IOException {
        (commands.get(doneTaskNumber)).markAsDone();
        if (loadFlag == 1) {
            System.out.println("The following task has been marked as done Master!");
            System.out.println((doneTaskNumber + 1) + ". " + commands.get(doneTaskNumber));
            storage.saveAllTasks(commands);
        }
    }

    private static void deleteTask(int doneTaskNumber) throws IOException {
        System.out.println("Taking one last look Master, at this Task. Removing the following from my memory");
        System.out.println((doneTaskNumber+1) + ". " + commands.get(doneTaskNumber));
        commands.remove(commands.get(doneTaskNumber));
        positionCheck -= 1;
        System.out.println("Goodbye Task, may the force be with you. You have " + positionCheck + " task(s) left Master");
        storage.saveAllTasks(commands);
    }

    /*
    public static void saveNewTask(String[] input) throws IOException {
        String filePath = new File("Tasks.txt").getAbsolutePath();
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
        String filePath = new File("Tasks.txt").getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, false);
        String taskInFile;
        String done;
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
     */

    public static void loadTasks() throws DukeException, IOException {
        String filePath = new File("Tasks.txt").getAbsolutePath();
        File f = new File(filePath);
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
        System.out.println(Response.STARTING_MESSAGE);
        loadFlag = 1;
    }

    public static void main(String[] args) throws DukeException, IOException {
        try {
            loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Automatic text file creation initiated master!");
            loadFlag = 1;
        }
        ui.greetUser();
        sendCommand();
        ui.sayBye();
    }
}
