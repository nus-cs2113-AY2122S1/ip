import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;


public class Storage {

    public static void replaceAllTasks() throws IOException {
        String filePath = new File("duke.txt").getAbsolutePath(); //added
        FileWriter fw = new FileWriter(filePath, false);
        String taskToSave;

        for (Task individualTask : Duke.tasks) {
            if (individualTask instanceof Deadline) {
                taskToSave = individualTask.getStoredDataString();

            } else if (individualTask instanceof Event) {
                taskToSave = individualTask.getStoredDataString();

            } else {
                taskToSave = individualTask.getStoredDataString();
            }

            Files.write(Paths.get(filePath), taskToSave.getBytes(), StandardOpenOption.APPEND);
        }
    }

    public static void loadFile() throws FileNotFoundException {
        String filePath = new File("duke.txt").getAbsolutePath(); //added
        File f = new File(filePath);
        Scanner fileScan = new Scanner(f);
        String taskType;
        int fileScanNumber = 1; //order of tasks in duke text file

        while (fileScan.hasNext()) {
            String data = fileScan.nextLine(); //scans first line of the file
            String[] arraySplitter = data.split(" \\| "); //split "todo hello" "0"
            String[] arraySplitter2 = arraySplitter[0].split(" ");//split "todo hello"  -> "todo" "hello"
            taskType = arraySplitter2[0]; //chooses "todo"

            switch (taskType) {
            case "todo":
                TaskList.getToDoMethod(arraySplitter[0]);
                if (arraySplitter[1].equals("1")) {
                    loadFileDone(fileScanNumber);
                }
                break;
            case "deadline":
                TaskList.getDeadlineMethod(arraySplitter[0]);
                if (arraySplitter[1].equals("1")) {
                    loadFileDone(fileScanNumber);
                }
                break;
            case "event":
                TaskList.getEventMethod(arraySplitter[0]);
                if (arraySplitter[1].equals("1")) {
                    loadFileDone(fileScanNumber);
                }
                break;
            default:
            }

            fileScanNumber++;
        }

        fileScan.close();
    }


    public static void appendTodo(String input) {
        String filePath = new File("duke.txt").getAbsolutePath(); //added
        String isDone = Duke.taskDoneChecker;
        String addToDoDescription = input.substring(4).trim(); // "return book"

        try {
            writeToFile(filePath,
                    "todo " + addToDoDescription + " | " + isDone + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("IOException error, theres an input/output error");
        }

    }

    public static void appendEvent(String input) {
        String filePath = new File("duke.txt").getAbsolutePath(); //added
        String isDone = Duke.taskDoneChecker;
        String[] eventSplitterString = input.substring(6).split(" /at ");
        String addEventDescription = eventSplitterString[0];
        String location = eventSplitterString[1];

        try {
            writeToFile(filePath, "event " + addEventDescription + " /at "
                    + location + " | " + isDone + System.lineSeparator());

        } catch (IOException e) {
            System.out.println("IOException error, theres an input/output error");
        }
    }

    public static void appendDeadline(String input) {
        String filePath = new File("duke.txt").getAbsolutePath(); //added
        String isDone = Duke.taskDoneChecker;
        String[] deadlineSplitterString = input.substring(9).split(" /by ");
        String addDeadlineDescription = deadlineSplitterString[0];
        String[] dateTime = deadlineSplitterString[1].split(" ");
        String date = dateTime[0];
        String time = dateTime[1];

        try {
            writeToFile(filePath, "deadline " + addDeadlineDescription + " /by "
                    + date + " " + time + " | " + isDone + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("IOException error, theres an input/output error");
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void loadFileDone(Integer number) {
        number = number - 1;
        Task task = Duke.tasks.get(number);
        task.markAsDone();
    }

}
