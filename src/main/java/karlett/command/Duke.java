package karlett.command;

import karlett.task.Deadline;
import karlett.task.Event;
import karlett.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
            processUserInput(userInput);
        }
    }

    public static void processUserInput(String userInput) {
        Scanner in = new Scanner(System.in);
        userInput = userInput.trim();
        String[] userInputWords = userInput.split(" ");
        // userInputWords = typeOfTask (first word) + taskContent (the rest)
        //                = typeOfTask (first word) + taskDescription (+ /at OR /by + time)
        String typeOfTask = userInputWords[0];
        String[] taskContentWords = {};
        if (userInputWords.length > 1) {
            taskContentWords = Arrays.copyOfRange(userInputWords, 1, userInputWords.length);
        }
        String taskDescription = "";
        String[] taskDescriptionWords;

        switch (typeOfTask) {
        case "todo":
            if (taskContentWords.length == 0) {
                printEmptyTaskErrorMessage();
                break;
            }
            taskDescription = String.join(" ", taskContentWords);
            list.add(Task.getNumberOfTasks(), new Task(taskDescription));
            break;
        case "deadline":
            if (taskContentWords.length == 0) {
                printEmptyTaskErrorMessage();
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
                printEmptyTaskErrorMessage();
                break;
            }
            boolean hasNoKeywordBy = (indexOfBy == -1);
            boolean hasNoDeadlineAfterBy = (indexOfBy == taskContentWords.length - 1);
            if (hasNoKeywordBy | hasNoDeadlineAfterBy) {
                printMissingDeadlineErrorMessage();
                break;
            }
            /*-----------------------valid input cases------------------------*/
            taskDescriptionWords = Arrays.copyOf(taskContentWords, indexOfBy);
            taskDescription = String.join(" ", taskDescriptionWords);
            String[] taskDeadlineWords = Arrays.copyOfRange(taskContentWords,
                    indexOfBy + 1, taskContentWords.length);
            String taskDeadline = String.join(" ", taskDeadlineWords);
            list.add(Task.getNumberOfTasks(), new Deadline(taskDescription, taskDeadline));
            break;
        case "event":
            if (taskContentWords.length == 0) {
                printEmptyTaskErrorMessage();
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
                printEmptyTaskErrorMessage();
                break;
            }
            boolean hasNoKeywordAt = (indexOfAt == -1);
            boolean hasNoTimeAfterAt = (indexOfAt == taskContentWords.length - 1);
            if (hasNoKeywordAt | hasNoTimeAfterAt) {
                printMissingTimeErrorMessage();
                break;
            }
            /*-----------------------valid input cases------------------------*/
            taskDescriptionWords = Arrays.copyOf(taskContentWords, indexOfAt);
            taskDescription = String.join(" ", taskDescriptionWords);
            String[] taskTimeWords = Arrays.copyOfRange(taskContentWords,
                    indexOfAt + 1, taskContentWords.length);
            String taskTime = String.join(" ", taskTimeWords);
            list.add(Task.getNumberOfTasks(), new Event(taskDescription, taskTime));
            break;
        case "list":
            if (taskContentWords.length == 0) {
                Task.printList(list);
                break;
            } else {
                printPendingConfirmationToListMessage();
                String userConfirmation = in.nextLine().toLowerCase(Locale.ROOT);
                if (userConfirmation.equals("y")) {
                    Task.printList(list);
                }
                break;
            }
        case "done":
            try {
                int index = Integer.parseInt(taskContentWords[0]);
                list.get(index - 1).markAsDone();
                break;
            } catch (NumberFormatException ex) {
                printDoneFormatErrorMessage();
                break;
            } catch (IndexOutOfBoundsException ex) {
                printOutOfBoundErrorMessage();
                break;
            }
        case "delete":
            try {
                int index = Integer.parseInt(taskContentWords[0]);
                Task.remove(list, index);
                break;
            } catch (NumberFormatException ex) {
                printDeleteFormatErrorMessage();
                break;
            } catch (IndexOutOfBoundsException ex) {
                printOutOfBoundErrorMessage();
                break;
            }
        case "bye":
            Task.exit();
        default:
            printGeneralErrorMessage();
        }
    }

    private static void printMissingTimeErrorMessage() {
        Task.drawDivider();
        System.out.println("Karlett needs to know the date/time for this event meow(๑•́ᆽ•̀๑✿)");
        Task.drawDivider();
    }

    private static void printMissingDeadlineErrorMessage() {
        Task.drawDivider();
        System.out.println("Karlett needs to know your deadline for this task meow(๑•́ᆽ•̀๑✿)");
        Task.drawDivider();
    }

    private static void printDoneFormatErrorMessage() {
        Task.drawDivider();
        System.out.println("Karlett was expecting an index after \"done\" meow(๑•́ᆽ•̀๑✿)");
        Task.drawDivider();
    }

    private static void printDeleteFormatErrorMessage() {
        Task.drawDivider();
        System.out.println("Karlett was expecting an index after \"delete\" meow(๑•́ᆽ•̀๑✿)");
        Task.drawDivider();
    }

    private static void printOutOfBoundErrorMessage() {
        Task.drawDivider();
        if (Task.getNumberOfTasks() == 0) {
            System.out.println("You have done everything! Time to relax with Karlett meow ʕ♡ﻌ♡ʔ");
        } else {
            System.out.println("Karlett can only remember " + Task.getNumberOfTasks() + " tasks(๑•́ᆽ•̀๑✿)");
            System.out.println("Here they are meow:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("ฅ" + (i + 1) + " " + list.get(i));
            }
        }
        Task.drawDivider();
    }

    private static void printGeneralErrorMessage() {
        Task.drawDivider();
        System.out.println("You are confusing Karlett meowwww(๑•́ᆽ•̀๑✿)");
        Task.drawDivider();
    }

    private static void printPendingConfirmationToListMessage() {
        Task.drawDivider();
        System.out.println("Do you want Karlett to list the tasks?(๑•́ᆽ•̀๑✿) [y/n]");
        Task.drawDivider();
    }

    public static void greet() {
        String logo = " /\\_/\\\n"
                + "( o.o )\n"
                + " > ^ <";
        System.out.println(logo);
        System.out.println("Meow~ I'm Karlett!(◕▿◕✿)");
        System.out.println("What can I do for you meow?");
    }

    private static void printEmptyTaskErrorMessage() {
        Task.drawDivider();
        System.out.println("Karlett doesn't know what you need to do meow?(๑•́ᆽ•̀๑✿)");
        Task.drawDivider();
    }
}
