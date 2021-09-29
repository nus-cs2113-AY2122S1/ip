import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    UI ui = new UI();

    public void checkTodo(String userCommand) throws DukeException {
        if (userCommand.length() <= 5) {     //generate error when receiving invalid Todo input
            DukeException e = new DukeException();
            throw e;
        }
    }

    public void checkDeadline(String userCommand) throws DukeException {
        if (userCommand.length() <= 9) {     //generate error when receiving invalid Deadline input
            DukeException e = new DukeException();
            throw e;
        }
    }

    public void checkEvent(String userCommand) throws DukeException {
        if (userCommand.length() <= 6) {     //generate error when receiving invalid Event input
            DukeException e = new DukeException();
            throw e;
        }
    }

    public void checkDelete(String userCommand) throws DukeException {
        if (userCommand.length() <= 7) {     //generate error when receiving invalid Delete input
            DukeException e = new DukeException();
            throw e;
        } else if (!isInt(userCommand.valueOf(7))) {
            DukeException e = new DukeException();
            throw e;
        }
    }

    public boolean isInt(String input) {
        try {                               //generate error when receiving non-integer input
            Integer.parseInt(input);        //for the delete command
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public void checkCommand(ArrayList<String> output, ArrayList<Integer> taskStatus,
                             ArrayList<String> arrayInput, ArrayList<String> taskType, File file) {
        Scanner userInput = new Scanner(System.in);
        String userCommand = userInput.nextLine();
        int inputCount = 0;

        TaskLists taskLists = new TaskLists(output, taskStatus,
                arrayInput, file, taskType, inputCount, userCommand);

        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                System.out.println("status = " + taskStatus.get(0));
                taskLists.printList(taskType, taskStatus, arrayInput, inputCount);
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("done")) {
                output = taskLists.getUpdateDone(output, taskStatus, arrayInput, file, userCommand,
                        taskType, inputCount);
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("todo")) {
                if (!taskLists.isValidTodo(userCommand)) {
                    userCommand = userInput.nextLine();
                    continue;
                }
                inputCount = taskLists.printTodo(arrayInput, userCommand, inputCount);
                output = taskLists.getUpdateTodo(output, taskStatus, arrayInput, file, taskType, inputCount);
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("deadline")) {
                if (!taskLists.isValidDeadline(userCommand)) {
                    userCommand = userInput.nextLine();
                    continue;
                }
                inputCount = taskLists.printDeadline(arrayInput, userCommand, inputCount);
                output = taskLists.getUpdateDeadline(output, taskStatus, arrayInput, file, taskType, inputCount);
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("event")) {
                if (!taskLists.isValidEvent(userCommand)) {
                    userCommand = userInput.nextLine();
                    continue;
                }
                inputCount = taskLists.printEvent(arrayInput, userCommand, inputCount);
                output = taskLists.getUpdateEvent(output, taskStatus, arrayInput, file, taskType, inputCount, "E");
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("delete")) {        //add the delete function
                if (!taskLists.isValidDelete(userCommand)) {
                    userCommand = userInput.nextLine();
                    continue;
                }
                inputCount = taskLists.printDelete(taskType, taskStatus, arrayInput, inputCount, userCommand);
                output = taskLists.getUpdateDelete(output, taskStatus, arrayInput, file, taskType, inputCount);
                userCommand = userInput.nextLine();
                continue;
            } else if (!userCommand.equals("bye")) {        //invalid input detected
                taskLists.printInvalid();
                System.out.println(ui.LINE);
                userCommand = userInput.nextLine();
                continue;
            }
        }
        if (userCommand.equals("bye")) {        //terminate the loop
            taskLists.printBye();
        }
    }
}
