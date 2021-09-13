package karlett.command;

import karlett.task.Deadline;
import karlett.task.Event;
import karlett.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static Task[] list = new Task[100];
    public static String filePath = "./karlett.txt";
    public static File file = new File(filePath);

    public static void main(String[] args) throws IOException {
        greet();
        loadData();
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
            Task.setFilePath(filePath);
            processUserInput(list, userInput);
        }
    }

    public static void greet() {
        String logo = " /\\_/\\\n"
                + "( o.o )\n"
                + " > ^ <";
        System.out.println(logo);
        System.out.println("Meow~ I'm Karlett!(◕▿◕✿)");
    }

    public static void loadData() throws IOException {
        System.out.println("Please wait. I'm loading the data now meow...");
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String task = s.nextLine();
                processFileData(list, task);
            }
            printFinishLoadingDataMessage();
        } catch (FileNotFoundException e) {
            printFileNotFoundMessage();
        }
    }

    private static void printFileNotFoundMessage() throws IOException {
        System.out.print("Uh-oh...Karlett can't find the file in " + filePath + "(=ಠᆽಠ=)\n");
        printFileNotFoundInstructions();
        Scanner in = new Scanner(System.in);

        String r = in.next();
        try {
            int response = Integer.parseInt(r);
            switch (response) {
            case 1:
                file.createNewFile();
                printNewFileCreatedMessage();
                break;
            case 2:
                System.out.print("Please tell me the new file path meow: ");
                String newFilePath = in.next();
                filePath = newFilePath;
                loadData();
                break;
            case 3:
                Task.exit();
                // Fallthrough
            default:
                printFileNotFoundMessage();
            }
        } catch (NumberFormatException e) {
            printFileNotFoundMessage();
        }
    }

    private static void printNewFileCreatedMessage() {
        System.out.print("Meow~ Karlett has created the new file here:\n" +
                file.getAbsolutePath() +
                "\nKarlett can start tracking your tasks now ಇ/ᐠ ̥ᵔ ̮ᵔ ̥ᐟ\\ಇ\n");
    }

    private static void printFileNotFoundInstructions() {
        System.out.println("What can Karlett do for you meow? (Please key in 1/2/3)\n" +
                "  1 Create a new text file called \"karlett.txt\" for me please.\n" +
                "  2 Load data from another file path for me please.\n" +
                "  3 Exit the program now.\n");
    }

    private static void printFinishLoadingDataMessage() {
        System.out.println("Karlett has finished loading data from the file ✧/ᐠ-ꞈ-ᐟ\\");
        if (Task.getNumberOfTasks() == 0) {
            System.out.println("There was no task recorded yet.");
        } else {
            System.out.println("Here are the tasks:");
            for (int i = 0; i < Task.getNumberOfTasks(); i++) {
                System.out.println("ฅ" + (i + 1) + " " + list[i]);
            }
            if (Task.getNumberOfTasks() == 1) {
                System.out.println("You have 1 task in the list now meow (((;꒪ꈊ꒪;)))");
            } else {
                System.out.println("You have " + Task.getNumberOfTasks() +
                        " tasks in the list now meow (((;꒪ꈊ꒪;)))");
            }
        }
        System.out.println("What can I do for you meow?");
    }

    private static void processFileData(Task[] list, String task) throws IOException {
        char taskType;
        char taskStatus;
        String taskDescription;
        int indexOfDelimeter;
        taskType = task.charAt(0);
        taskStatus = task.charAt(4);
        boolean isDone = taskStatus == '1';
        if (taskType == 'T') {
            taskDescription = task.substring(8).trim();
            list[Task.getNumberOfTasks()] = new Task(taskDescription, isDone);
        } else {
            indexOfDelimeter = task.indexOf('|',8);
            taskDescription = task.substring(8,indexOfDelimeter).trim();
            String time = task.substring(indexOfDelimeter + 1).trim();
            if (taskType == 'D') {
                list[Task.getNumberOfTasks()] =
                        new Deadline(taskDescription, time, isDone);
            } else {
                list[Task.getNumberOfTasks()] =
                        new Event(taskDescription, time, isDone);
            }
        }
    }

    public static void processUserInput(Task[] list, String userInput) throws IOException {
        Scanner in = new Scanner(System.in);
        userInput = userInput.trim();
        String[] userInputWords = userInput.split(" ");
        String taskType = userInputWords[0];
        String[] taskContentWords = {};
        if (userInputWords.length > 1) {
            taskContentWords = Arrays.copyOfRange(userInputWords, 1, userInputWords.length);
        }
        String taskDescription = "";
        String[] taskDescriptionWords;

        switch (taskType) {
        case "todo":
            if (taskContentWords.length == 0) {
                printEmptyTaskErrorMessage();
                break;
            }
            taskDescription = String.join(" ", taskContentWords);
            list[Task.getNumberOfTasks()] = new Task(taskDescription);
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
            list[Task.getNumberOfTasks()] =
                    new Deadline(taskDescription, taskDeadline);
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
            list[Task.getNumberOfTasks()] =
                    new Event(taskDescription, taskTime);
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
                list[index - 1].markAsDone(index - 1);
                break;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                printDoneFormatErrorMessage();
                break;
            } catch (NullPointerException ex) {
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

    private static void printOutOfBoundErrorMessage() {
        Task.drawDivider();
        System.out.println("Karlett can only remember " + Task.getNumberOfTasks() + " tasks(๑•́ᆽ•̀๑✿)");
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

    private static void printEmptyTaskErrorMessage() {
        Task.drawDivider();
        System.out.println("Karlett doesn't know what you need to do meow?(๑•́ᆽ•̀๑✿)");
        Task.drawDivider();
    }
}
