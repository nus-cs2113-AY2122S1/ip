import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    UI ui = new UI();

    // Check if the 'todo' command is valid
    public void checkTodo(String userCommand) throws DukeException {
        if (userCommand.length() <= 5) {     //generate error when receiving invalid Todo input
            DukeException e = new DukeException();
            throw e;
        }
    }

    // Check if the 'deadline' command is valid
    public void checkDeadline(String userCommand) throws DukeException {
        if (userCommand.length() <= 9) {     //generate error when receiving invalid Deadline input
            DukeException e = new DukeException();
            throw e;
        } else if (!userCommand.contains("/by")) {
            DukeException e = new DukeException();
            throw e;
        }
    }

    // Check if the 'event' command is valid
    public void checkEvent(String userCommand) throws DukeException {
        if (userCommand.length() <= 6) {     //generate error when receiving invalid Event input
            DukeException e = new DukeException();
            throw e;
        } else if (!userCommand.contains("/at")) {
            DukeException e = new DukeException();
            throw e;
        }
    }

    // Check if the 'done' command is valid
    public void checkDone(String userCommand) throws DukeException {
        if (userCommand.length() <= 5) {     //generate error when receiving invalid Delete input
            DukeException e = new DukeException();
            throw e;
        } else if (!isInt(userCommand.substring(5))) {
            DukeException e = new DukeException();
            throw e;
        } else if (Integer.parseInt(userCommand.substring(5)) <= 0) {
            DukeException e = new DukeException();
            throw e;
        }
    }

    // Check if the 'delete' command is valid
    public void checkDelete(String userCommand) throws DukeException {
        if (userCommand.length() <= 7) {     //generate error when receiving invalid Delete input
            DukeException e = new DukeException();
            throw e;
        } else if (!isInt(userCommand.substring(7))) {
            DukeException e = new DukeException();
            throw e;
        } else if (Integer.parseInt(userCommand.substring(7)) <= 0) {
            DukeException e = new DukeException();
            throw e;
        }
    }

    // Check if the 'find' command is valid
    public void checkFind(String userCommand) throws DukeException {
        if (userCommand.length() <= 5) {     //generate error when receiving invalid Delete input
            DukeException e = new DukeException();
            throw e;
        } else if (userCommand.substring(5).equals(" ")) {
            DukeException e = new DukeException();
            throw e;
        }
    }

    // Check if the target char in inputString is an integer
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

    // Check and process the 'userCommand' (userInput)
    public void checkCommand(ArrayList<String> output, ArrayList<Integer> taskStatus,
                             ArrayList<String> taskName, ArrayList<String> taskType, File file) {
        Scanner userInput = new Scanner(System.in);
        String userCommand = userInput.nextLine();
        int inputCount = output.size();

        TaskLists taskLists = new TaskLists(output, taskStatus,
                taskName, file, taskType, inputCount, userCommand);

        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                if (inputCount == 0) {
                    taskLists.printEmptyList();
                } else {
                    taskLists.printList(taskType, taskStatus, taskName, inputCount);
                }
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("done")) {
                if (taskLists.isValidDone(userCommand, inputCount)) {
                    output = taskLists.getUpdateDone(output, taskStatus, taskName, file, userCommand,
                            taskType, inputCount);
                }
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("todo")) {
                if (taskLists.isValidTodo(userCommand)) {
                    inputCount = taskLists.printTodo(taskName, userCommand, inputCount);
                    output = taskLists.getUpdateTodo(output, taskStatus, taskName, file, taskType, inputCount);
                }
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("deadline")) {
                if (taskLists.isValidDeadline(userCommand)) {
                    inputCount = taskLists.printDeadline(taskName, userCommand, inputCount);
                    output = taskLists.getUpdateDeadline(output, taskStatus, taskName, file, taskType, inputCount);
                }
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("event")) {
                if (taskLists.isValidEvent(userCommand)) {
                    inputCount = taskLists.printEvent(taskName, userCommand, inputCount);
                    output = taskLists.getUpdateEvent(output, taskStatus, taskName, file, taskType, inputCount);
                }
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("delete")) {        //add the delete function
                if (taskLists.isValidDelete(userCommand, inputCount)) {
                    inputCount = taskLists.printDelete(taskType, taskStatus, taskName, inputCount, userCommand);
                    output = taskLists.getUpdateDelete(output, taskStatus, taskName, file, taskType, inputCount);
                }
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("find")) {
                if (taskLists.isValidFind(userCommand)) {
                    taskLists.printFind(output, userCommand, inputCount);
                }
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
