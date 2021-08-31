import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        //echo();
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        while (true) {
            String userInput = in.nextLine();
            processuserInput(list, userInput);
        }
    }

    public static void processuserInput(Task[] list, String userInput) {
        Scanner in = new Scanner(System.in);
        userInput = userInput.trim();
        String[] userInputWords = userInput.split(" ");
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
                list[Task.getNumberOfTasks()] = new Task(taskDescription);
                break;
            case "deadline":
                if (taskContentWords.length == 0) {
                    printEmptyTaskErrorMessage();
                    break;
                }
                int indexOfBy = -1;
                for (int i = 0; i < taskContentWords.length; i++) {
                    if (taskContentWords[i].equals("/by")) {
                        indexOfBy = i;
                        break;
                    }
                }
                if (indexOfBy == -1 || indexOfBy == taskContentWords.length - 1) {
                    printMissingDeadlineErrorMessage();
                    break;
                }
                if (indexOfBy == 0) {
                    printEmptyTaskErrorMessage();
                    break;
                }
                taskDescriptionWords = Arrays.copyOf(taskContentWords, indexOfBy);
                taskDescription = String.join(" ", taskDescriptionWords);
                String[] taskDeadlineWords = Arrays.copyOfRange(taskContentWords,
                        indexOfBy + 1, taskContentWords.length);
                String taskDeadline = String.join(" ", taskDeadlineWords);
                list[Task.getNumberOfTasks()] = new Deadline(taskDescription, taskDeadline);
                break;
            case "event":
                if (taskContentWords.length == 0) {
                    printEmptyTaskErrorMessage();
                    break;
                }
                int indexOfAt = -1;
                for (int i = 0; i < taskContentWords.length; i++) {
                    if (taskContentWords[i].equals("/at")) {
                        indexOfAt = i;
                        break;
                    }
                }
                if (indexOfAt == -1 || indexOfAt == taskContentWords.length - 1) {
                    printMissingTimeErrorMessage();
                    break;
                }
                if (indexOfAt == 0) {
                    printEmptyTaskErrorMessage();
                    break;
                }
                taskDescriptionWords = Arrays.copyOf(taskContentWords, indexOfAt);
                taskDescription = String.join(" ", taskDescriptionWords);
                String[] taskTimeWords = Arrays.copyOfRange(taskContentWords,
                        indexOfAt + 1, taskContentWords.length);
                String taskTime = String.join(" ", taskTimeWords);
                list[Task.getNumberOfTasks()] = new Event(taskDescription, taskTime);
                break;
            case "list":
                if (taskContentWords.length == 0) {
                    Task.printList(list);
                    break;
                } else {
                    printConfirmationToListMessage();
                    String userConfirmation = in.nextLine().toLowerCase(Locale.ROOT);
                    if (userConfirmation.equals("y")) {
                        Task.printList(list);
                    }
                    break;
                }
            case "done":
                try {
                    int index = Integer.parseInt(taskContentWords[0]);
                    list[index - 1].markAsDone();
                    break;
                } catch (NumberFormatException ex) {
                    printDoneFormatErrorMessage();
                    break;
                } catch (NullPointerException ex) {
                    printOutOfBoundErrorMessage();
                    break;
                } catch (ArrayIndexOutOfBoundsException ex) {
                    printDoneFormatErrorMessage();
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

    private static void printConfirmationToListMessage() {
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
