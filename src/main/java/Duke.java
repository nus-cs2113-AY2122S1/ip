import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {

    public static boolean hasSpaceError(String[] splitUserInput, String UserInput) {
        //Checks whether last character is a space
        int lastCharacterIndex = UserInput.length() - 1;
        String lastCharacter = UserInput.substring(lastCharacterIndex);
        if (lastCharacter.equals(" ")) {
            return true;
        }
        //Checks for multiple spaces between words
        int userInputLength = splitUserInput.length;
        for (int i = 0; i < userInputLength; i++) {
            if (splitUserInput[i].equals("")) {
                return true;
            }
        }
        return false;
    }

    private static String[] processFileLine (String fileLine) {
        String[] splitFileLine = fileLine.split(" ");
        String[] processedFileLine = new String[3];
        //Sets category
        switch (splitFileLine[0]) {
        case "T":
            processedFileLine[0] = "todo";
            break;
        case "D":
            processedFileLine[0] = "deadline";
            break;
        case "E":
            processedFileLine[0] = "event";
            break;
        default:
            break;
        }
        //Sets description and details
        String newFileLine = fileLine.substring(8);
        if (splitFileLine[0].equals("T")) {
            processedFileLine[1] = newFileLine;
        } else if (splitFileLine[0].equals("D")) {
            int lineIndex = newFileLine.indexOf("|");
            processedFileLine[1] = newFileLine.substring(0, lineIndex - 1);
            processedFileLine[2] = "by " + newFileLine.substring(lineIndex + 2);
        } else {
            int lineIndex = newFileLine.indexOf("|");
            processedFileLine[1] = newFileLine.substring(0, lineIndex - 1);
            processedFileLine[2] = "at " + newFileLine.substring(lineIndex + 2);
        }

        return processedFileLine;
    }

    private static ArrayList<Task> readFileContents(String filePath, ArrayList<Task> taskList) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            //Processes file line
            String fileLine = s.nextLine();
            String[] splitFileLine = fileLine.split(" ");
            String isDone = splitFileLine[2];
            String[] processedFileLine = processFileLine(fileLine);

            //Creates and adds task to task list
            Task newTask = Functions.createTask(processedFileLine);
            taskList.add(newTask);

            //Marks done if "1"
            if (isDone.equals("1")) {
                taskList.get(taskList.size() - 1).setDone();
            }
        }
        return taskList;
    }

    private static int getTaskListSize(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int taskListSize = 0;
        while (s.hasNext()) {
            String fileLine = s.nextLine();
            taskListSize++;
        }
        return taskListSize;
    }

    public static void writeTask(String[] processedUserInput, String filePath) {
        String finalMessage = "";

        String category = processedUserInput[0];
        switch (category) {
        case "todo":
            finalMessage = "T | 0";
            break;
        case "deadline":
            finalMessage = "D | 0";
            break;
        case "event":
            finalMessage = "E | 0";
            break;
        default:
            break;
        }

        String description = processedUserInput[1];
        finalMessage = finalMessage + " | " + description;

        if (!category.equals("todo")) {
            String details = processedUserInput[2].substring(3);
            finalMessage = finalMessage + " | " + details;
        }
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(finalMessage);
            fw.write("\r\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateTask(String[] processedUserInput, int taskNumber, String filePath) throws FileNotFoundException, IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        String finalText = "";
        int lineNumber = 0;
        while (s.hasNext()) {
            if (lineNumber == taskNumber) {
                String oldLine = s.nextLine();
                String newLine = oldLine.substring(0, 4) + "1" + oldLine.substring(5);
                finalText = finalText + newLine + "\n";
            } else {
                finalText = finalText + s.nextLine() + "\n";
            }
            lineNumber++;
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(finalText);
        fw.close();
    }

    public static void removeTask(String[] processedUserInput, int taskNumber, String filePath) throws FileNotFoundException, IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        String finalText = "";
        int lineNumber = 0;
        while (s.hasNext()) {
            if (lineNumber == taskNumber) {
                //Do nothing
                s.nextLine();
            } else {
                finalText = finalText + s.nextLine() + "\n";
            }
            lineNumber++;
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(finalText);
        fw.close();
    }

    public static void main(String[] args) {

        //Greeting
        Functions.printGreeting();

        //Setting up task list
        ArrayList<Task> taskList = new ArrayList<>();
        int taskListSize = taskList.size();
        String workingDir = System.getProperty("user.dir");
        Path filePath2 = Paths.get(workingDir+File.separator+"duke.txt");
        String filePath = String.valueOf(filePath2);
        try {
            taskList = readFileContents(filePath, taskList);
            taskListSize = getTaskListSize(filePath);
        } catch (FileNotFoundException e) {
            //Creates a new duke.txt document
            File f = new File(filePath);
            try {
                f.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        //Initialization
        String userInput = "start";
        Scanner in = new Scanner(System.in);

        //Main Loop
        while (!userInput.equals("bye")) {

            //Scans for user input
            userInput = in.nextLine();

            //Splits user input into an array of words
            String[] splitUserInput = userInput.split(" ");

            //Checks for space error
            if (hasSpaceError(splitUserInput, userInput)) {
                DukeException.printSpaceError();
                continue;
            }

            //Processes user input
            String firstWord = splitUserInput[0];
            int userInputLength = splitUserInput.length;
            String[] processedUserInput = Functions.processUserInput(userInput, splitUserInput, firstWord, userInputLength);

            //Duke's actions based on command given
            String command = processedUserInput[0];
            switch (command) {
            case "list":
                //Prints all tasks in task list
                Functions.printTaskList(taskList);
                break;
            case "done":
                //Marks task as "done"
                int doneTaskNumber = Integer.parseInt(processedUserInput[1]) - 1;
                Functions.markAsDone(taskList, doneTaskNumber);
                //Updates duke.txt
                try {
                    updateTask(processedUserInput, doneTaskNumber, filePath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                //Deletes task from task list
                int deleteTaskNumber = Integer.parseInt(processedUserInput[1]) - 1;
                Functions.deleteTask(taskList, deleteTaskNumber);
                //Deletes from duke.txt
                try {
                    removeTask(processedUserInput, deleteTaskNumber, filePath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                //Creates and adds task to task list
                Task newTask = Functions.createTask(processedUserInput);
                Functions.addTask(taskList, newTask);
                taskListSize++;
                //Writes on duke.txt
                writeTask(processedUserInput, filePath);
                break;
            default:
                //Either "bye" or "error"
                break;
            }
        }

        //Farewell
        Functions.printFarewell();
    }

}
