import java.io.IOException;
import java.util.Scanner;

public class Parser {
    public static void parse() {
        String toBeWritten = null;
        String dukeDataPath = "C:\\Users\\edly1\\Documents\\ip\\data\\dukeData.txt";
        Scanner in = new Scanner(System.in);
        boolean isContinueLoop = true;

        while(isContinueLoop){
            String userIn = in.nextLine();
            String userCommand = userIn.contains(" ") ? userIn.substring(0, userIn.indexOf(" ")): userIn;
            String userCommandDetails = userIn.contains(" ") ? userIn.substring(userIn.indexOf(" ") + 1): "invalid";

            switch (userCommand) {
                case "todo":
                    toBeWritten = TaskList.addToDo(userCommandDetails, toBeWritten);
                    try {
                        if (toBeWritten != null) {
                            Storage.writeToFile(dukeDataPath, toBeWritten + System.lineSeparator());
                        }
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    break;
                case "deadline":
                    toBeWritten = TaskList.addDeadline(userCommandDetails, toBeWritten);
                    try {
                        if (toBeWritten != null) {
                            Storage.writeToFile(dukeDataPath, toBeWritten + System.lineSeparator());
                        }
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    break;
                case "event":
                    toBeWritten = TaskList.addEvent(userCommandDetails, toBeWritten);
                    try {
                        if (toBeWritten != null) {
                            Storage.writeToFile(dukeDataPath, toBeWritten + System.lineSeparator());
                        }
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    break;

                case "list":
                    TaskList.list();
                    break;

                case "done":
                    TaskList.done(userIn);
                    break;

                case "find":
                    TaskList.find(userCommandDetails);
                    break;

                case "delete":
                    TaskList.delete(userIn);
                    break;

                case "bye":
                    Ui.farewell();
                    isContinueLoop = false;
                    break;
                default:
                    Ui.invalidCommand(userIn);
                    break;
            }
        }
    }
}
