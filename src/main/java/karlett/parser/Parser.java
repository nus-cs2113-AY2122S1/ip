package karlett.parser;

import karlett.Duke;
import karlett.task.Deadline;
import karlett.task.Event;
import karlett.task.Task;
import karlett.ui.TextUi;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Parser {
    public static void parseCommand(String userInput) throws IOException {
        Scanner in = new Scanner(System.in);
        userInput = userInput.trim();
        String[] userInputWords = userInput.split(" ");
        String taskType = userInputWords[0];
        String[] taskContentWords = {};
        if (userInputWords.length > 1) {
            taskContentWords = Arrays.copyOfRange(userInputWords, 1, userInputWords.length);
        }
        String taskDescription;
        String[] taskDescriptionWords;

        switch (taskType) {
        case "todo":
            if (taskContentWords.length == 0) {
                TextUi.printEmptyTaskErrorMessage();
                break;
            }
            taskDescription = String.join(" ", taskContentWords);
            Duke.list.add(Duke.list.size(), new Task(taskDescription));
            break;
        case "deadline":
            if (taskContentWords.length == 0) {
                TextUi.printEmptyTaskErrorMessage();
                break;
            }
            /*-------------locate keyword "/by" in the user input-------------*/
            int indexOfBy = -1;
            for (int i = 0; i < taskContentWords.length; i++) {
                if (taskContentWords[i].equals("/by")) {
                    indexOfBy = i;
                    break;
                }
            }
            /*----------------------invalid input cases-----------------------*/
            boolean hasNoTaskBeforeBy = indexOfBy == 0;
            if (hasNoTaskBeforeBy) {
                TextUi.printEmptyTaskErrorMessage();
                break;
            }
            boolean hasNoKeywordBy = (indexOfBy == -1);
            boolean hasNoDeadlineAfterBy = (indexOfBy == taskContentWords.length - 1);
            if (hasNoKeywordBy | hasNoDeadlineAfterBy) {
                TextUi.printMissingDeadlineErrorMessage();
                break;
            }
            /*-----------------------valid input cases------------------------*/
            taskDescriptionWords = Arrays.copyOf(taskContentWords, indexOfBy);
            taskDescription = String.join(" ", taskDescriptionWords);
            String[] taskDeadlineWords = Arrays.copyOfRange(taskContentWords,
                    indexOfBy + 1, taskContentWords.length);
            String taskDeadline = String.join(" ", taskDeadlineWords);
            Duke.list.add(Duke.list.size(), new Deadline(taskDescription, taskDeadline));
            break;
        case "event":
            if (taskContentWords.length == 0) {
                TextUi.printEmptyTaskErrorMessage();
                break;
            }
            /*-------------locate keyword "/at" in the user input-------------*/
            int indexOfAt = -1;
            for (int i = 0; i < taskContentWords.length; i++) {
                if (taskContentWords[i].equals("/at")) {
                    indexOfAt = i;
                    break;
                }
            }
            /*----------------------invalid input cases-----------------------*/
            boolean hasNoTaskBeforeAt = (indexOfAt == 0);
            if (hasNoTaskBeforeAt) {
                TextUi.printEmptyTaskErrorMessage();
                break;
            }
            boolean hasNoKeywordAt = (indexOfAt == -1);
            boolean hasNoTimeAfterAt = (indexOfAt == taskContentWords.length - 1);
            if (hasNoKeywordAt | hasNoTimeAfterAt) {
                TextUi.printMissingTimeErrorMessage();
                break;
            }
            /*-----------------------valid input cases------------------------*/
            taskDescriptionWords = Arrays.copyOf(taskContentWords, indexOfAt);
            taskDescription = String.join(" ", taskDescriptionWords);
            String[] taskTimeWords = Arrays.copyOfRange(taskContentWords,
                    indexOfAt + 1, taskContentWords.length);
            String taskTime = String.join(" ", taskTimeWords);
            Duke.list.add(Duke.list.size(), new Event(taskDescription, taskTime));
            break;
        case "list":
            if (taskContentWords.length == 0) {
                Task.printList(Duke.list);
            } else {
                TextUi.printPendingConfirmationToListMessage();
                String userConfirmation = in.nextLine().toLowerCase(Locale.ROOT);
                if (userConfirmation.equals("y")) {
                    Task.printList(Duke.list);
                }
            }
            break;
        case "done":
            try {
                int index = Integer.parseInt(taskContentWords[0]);
                Duke.list.get(index - 1).markAsDone(index - 1);
                break;
            } catch (NumberFormatException ex) {
                TextUi.printDoneFormatErrorMessage();
                break;
            } catch (IndexOutOfBoundsException ex) {
                TextUi.printOutOfBoundErrorMessage();
                break;
            }
        case "delete":
            try {
                int index = Integer.parseInt(taskContentWords[0]);
                Task.remove(Duke.list, index);
                break;
            } catch (NumberFormatException ex) {
                TextUi.printDeleteFormatErrorMessage();
                break;
            } catch (IndexOutOfBoundsException ex) {
                TextUi.printOutOfBoundErrorMessage();
                break;
            }
        case "bye":
            Task.exit();
        default:
            TextUi.printGeneralErrorMessage();
        }
    }
}
