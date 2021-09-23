import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;


public class Storage {

    /**
     *This method is used when delete and done commands are called as it is not possible
     * to navigate to specific tasks descriptions in Duke.txt without specialised libraries
     * hence it is easier to overwrite the whole text file with the updated task descriptions
     * if delete or done command is called, the for-each uses the getStoredDataString method
     * in the child classes to format the output. the output is then stored as a String, which
     * is then used to overwrite the file with other untouched task descriptions
     *
     * @return nil, this is a void method
     */
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

    /**
     *This method is called by storageWelcomeMessage() in StorageUI class when Duke starts
     * up. It will read the text in Duke.txt and scan it with fileScan. fileScan saves the
     * text as a String "data". "data" is then split into arraySplitter and arraySplitter2
     * arraySplitter splits "data" into one part containing the command eg. "todo hello"
     * and the other part containing he boolean value of either 1 or 0 , which denotes
     * whether the task has been marked as done or not done respectively.
     * arraySplitter2 splits "arraySplitter" into two parts. One containing the input
     * command word eg. "todo"
     * and the other part contains the description of the task eg. "hello"
     *
     * Switch statement identifies the input command by checking whether arraySplitter[0]
     * has the keywords "todo, event, dealine" and calls the respective method in taskList
     * class. This in turn calls the respective toString override in the respective classes
     * and prints out the list in the required manner
     *
     * @return nil, this is a void method
     */
    public static void loadFile() throws FileNotFoundException {
        String filePath = new File("duke.txt").getAbsolutePath(); //added
        File f = new File(filePath);
        Scanner fileScan = new Scanner(f);
        String taskType;
        int fileScanNumber = 1; //order of tasks in duke text file

        while (fileScan.hasNext()) {
            String data = fileScan.nextLine(); //scans first line of the file
            String[] arraySplitter = data.split(" \\| ");
            String[] arraySplitter2 = arraySplitter[0].split(" ");
            taskType = arraySplitter2[0];

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

    /**
     * This method is called by todoInputCommand when a todo command is typed in by the user
     * It adds the new todo task to the list and checks whether the user has marked the task
     * as done. if isDone is false, a 0 is stored after the "|". if isDone is true, a 1 is
     * stored instead. The method also adds the description of the task by splitting the
     * String input after the todo command keyword and storing it in addToDoDescription.
     * The combined string is then added to Duke.txt by the writeToFile method
     *
     * @param input which stores the user command
     * @return nil, this is a void method
     * @throws IOException if input is wrong
     */
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

    /**
     * This method is called by eventInputCommand when an event command is typed in by the user
     * It adds the new event task to the list and checks whether the user has marked the task
     * as done. if isDone is false, a 0 is stored after the "|". if isDone is true, a 1 is
     * stored instead. The method also adds the description of the task by splitting the
     * String input after the event command keyword and storing it in addEventDescription.
     * The location of the event is isolated by taking the first string in the string array
     * eventSplitterString.
     * The combined string is then added to Duke.txt by the writeToFile method
     *
     * @param input which stores the user command
     * @return nil, this is a void method
     * @throws IOException if input is wrong
     */
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

    /**
     * This method is called by deadlineInputCommand when an deadline command is typed in by the user
     * It adds the new deadline task to the list and checks whether the user has marked the task
     * as done. if isDone is false, a 0 is stored after the "|". if isDone is true, a 1 is
     * stored instead. The method also adds the description of the task by splitting the
     * String input after the deadline command keyword and storing it in addDeadlineDescription.
     * The date of the deadline is isolated by taking the first string in the string array
     * dateTime. The time is the second string in the string array dateTime
     * The combined string is then added to Duke.txt by the writeToFile method
     *
     * @param input which stores the user command
     * @return nil, this is a void method
     * @throws IOException if input is wrong
     */
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

    /**
     * This method is called to write the final string of the appendDeadline, appendEvent and appendTodo
     * methods to Duke.txt
     *
     * @param filePath Relative path of Duke.txt
     * @param textToAdd String to be written to Duke.txt
     * @return nil, this is a void method
     * @throws IOException if input is wrong
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * This method is called to get the string of the stored tasks in the list so that loadFile() can
     * print the list and include the isDone field at the same time
     *
     * @param number the index number of the task in the list
     * @return nil, this is a void method
     */
    public static void loadFileDone(Integer number) {
        number = number - 1;
        Task task = Duke.tasks.get(number);
        task.markAsDone();
    }

}
