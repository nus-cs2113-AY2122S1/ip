package karlett.parser;

import karlett.commands.*;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;
import java.util.Arrays;

public class Parser {
    private static TextUi ui = new TextUi();

    /**
     * Make sense of the user input and return a command correspondingly.
     *
     * @param userInput a String of words
     * @param tasks a TaskList that is already stored
     * @return a Command corresponding to the user input
     * @throws IOException input or output exception
     */
    public static Command parse(String userInput, TaskList tasks) throws IOException {
        userInput = userInput.trim();
        String[] userInputInWords = userInput.split(" ");
        String command = userInputInWords[0];
        String[] arguments = {};
        arguments = Arrays.copyOfRange(userInputInWords, 1, userInputInWords.length);
        String taskDescription;
        String[] taskDescriptionInWords;

        switch (command) {
        case "todo":
            if (arguments.length == 0) {
                ui.printEmptyTaskErrorMessage();
                break;
            }
            taskDescription = String.join(" ", arguments);
            return new AddCommand(command, taskDescription, null);
        case "deadline":
            if (arguments.length == 0) {
                ui.printEmptyTaskErrorMessage();
                break;
            }
            /*-------------locate keyword "/by" in the user input-------------*/
            int indexOfBy = -1;
            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i].equals("/by")) {
                    indexOfBy = i;
                    break;
                }
            }
            /*----------------------invalid input cases-----------------------*/
            boolean hasNoTaskBeforeBy = indexOfBy == 0;
            if (hasNoTaskBeforeBy) {
                ui.printEmptyTaskErrorMessage();
                break;
            }
            boolean hasNoKeywordBy = (indexOfBy == -1);
            boolean hasNoDeadlineAfterBy = (indexOfBy == arguments.length - 1);
            if (hasNoKeywordBy | hasNoDeadlineAfterBy) {
                ui.printMissingDeadlineErrorMessage();
                break;
            }
            /*-----------------------valid input cases------------------------*/
            taskDescriptionInWords = Arrays.copyOf(arguments, indexOfBy);
            taskDescription = String.join(" ", taskDescriptionInWords);
            String[] taskDeadlineWords = Arrays.copyOfRange(arguments,
                    indexOfBy + 1, arguments.length);
            String taskDeadline = String.join(" ", taskDeadlineWords);
            return new AddCommand(command, taskDescription, taskDeadline);
        case "event":
            if (arguments.length == 0) {
                ui.printEmptyTaskErrorMessage();
                break;
            }
            /*-------------locate keyword "/at" in the user input-------------*/
            int indexOfAt = -1;
            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i].equals("/at")) {
                    indexOfAt = i;
                    break;
                }
            }
            /*----------------------invalid input cases-----------------------*/
            boolean hasNoTaskBeforeAt = (indexOfAt == 0);
            if (hasNoTaskBeforeAt) {
                ui.printEmptyTaskErrorMessage();
                break;
            }
            boolean hasNoKeywordAt = (indexOfAt == -1);
            boolean hasNoTimeAfterAt = (indexOfAt == arguments.length - 1);
            if (hasNoKeywordAt | hasNoTimeAfterAt) {
                ui.printMissingTimeErrorMessage();
                break;
            }
            /*-----------------------valid input cases------------------------*/
            taskDescriptionInWords = Arrays.copyOf(arguments, indexOfAt);
            taskDescription = String.join(" ", taskDescriptionInWords);
            String[] taskTimeWords = Arrays.copyOfRange(arguments,
                    indexOfAt + 1, arguments.length);
            String taskTime = String.join(" ", taskTimeWords);
            return new AddCommand(command, taskDescription, taskTime);
        case "list":
            /*if (arguments.length != 0) {
                ui.printPendingConfirmationToListMessage();
                String userConfirmation = in.nextLine().toLowerCase(Locale.ROOT);
                if (userConfirmation.equals("y")) {
                    return new ListCommand();
                }
                return new
            }*/
            return new ListCommand();
        case "done":
            try {
                int index = Integer.parseInt(arguments[0]);
                return new DoneCommand(index);
            } catch (NumberFormatException ex) {
                ui.printDoneFormatErrorMessage();
                break;
            } catch (IndexOutOfBoundsException ex) {
                ui.printOutOfBoundErrorMessage(tasks);
                break;
            }
        case "delete":
            try {
                int index = Integer.parseInt(arguments[0]);
                return new DeleteCommand(index);
            } catch (NumberFormatException ex) {
                ui.printDeleteFormatErrorMessage();
                break;
            } catch (IndexOutOfBoundsException ex) {
                ui.printOutOfBoundErrorMessage(tasks);
                break;
            }
        case "bye":
            return new ExitCommand();
        default:
            ui.printGeneralErrorMessage();
            break;
        }
        return new ErrorCommand();
    }
}
