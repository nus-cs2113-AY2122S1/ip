import java.util.Scanner;
import java.util.ArrayList;

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

    public static void main(String[] args) {

        //Greeting
        Functions.printGreeting();

        //Initialization
        ArrayList<Task> taskList = new ArrayList<>();
        int taskListSize = taskList.size();
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
            };

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
                int taskNumber = Integer.parseInt(processedUserInput[1]) - 1;
                Functions.markAsDone(taskList, taskNumber);
                break;
            case "todo":
            case "deadline":
            case "event":
                //Creates and adds task to task list
                Task newTask = Functions.createTask(processedUserInput);
                Functions.addTask(taskList, newTask);
                taskListSize++;
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
