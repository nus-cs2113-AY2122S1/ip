package karlett.parser;

import karlett.commands.*;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        String[] arguments = Arrays.copyOfRange
                (userInputInWords, 1, userInputInWords.length);
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
            String[] taskDeadlineInWords = Arrays.copyOfRange(arguments,
                    indexOfBy + 1, arguments.length);

            String taskDeadlineInString = String.join(" ", taskDeadlineInWords);
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            try {
                LocalDateTime taskDeadline = LocalDateTime.parse(taskDeadlineInString, inputFormatter);
                return new AddCommand(command, taskDescription, taskDeadline);
            } catch (DateTimeParseException e) {
                ui.printIncorrectTimeFormat();
                break;
            }
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
            String[] taskTimeInWords = Arrays.copyOfRange(arguments,
                    indexOfAt + 1, arguments.length);
            String taskTimeInString = String.join(" ", taskTimeInWords);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            try {
                LocalDateTime taskTime = LocalDateTime.parse(taskTimeInString, formatter);
                return new AddCommand(command, taskDescription, taskTime);
            } catch (DateTimeParseException e) {
                ui.printIncorrectTimeFormat();
                break;
            }
        case "list":
            return new ListCommand();
        case "on":
            String[] inputTimeInWords = Arrays.copyOfRange(arguments, 0, arguments.length);
            String inputTimeInString = String.join(" ", inputTimeInWords);
            DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            try {
                LocalDateTime time = LocalDateTime.parse(inputTimeInString, inputTimeFormatter);
                return new OnCommand(time);
            } catch (DateTimeParseException e) {
                ui.printIncorrectTimeFormat();
                break;
            }
        case "find":
            if (arguments.length == 0) {
                ui.printEmptyKeywordMessage();
                break;
            }
            if (arguments.length >1) {
                ui.printInvalidKeywordMessage();
                break;
            }
            String keyWord = arguments[0];
            return new FindCommand(keyWord);
        case "done":
            try {
                return new DoneCommand(Integer.parseInt(arguments[0]));
            } catch (NumberFormatException e) {
                ui.printIncorrectIndexFormatMessage(tasks);
                break;
            }
        case "delete":
            try {
                return new DeleteCommand(Integer.parseInt(arguments[0]));
            } catch (NumberFormatException e) {
                ui.printIncorrectIndexFormatMessage(tasks);
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
