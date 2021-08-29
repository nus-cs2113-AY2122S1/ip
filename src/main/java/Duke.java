import java.util.Scanner;

/**
 * Chatbot program Duke that tracks user tasks.
 *
 * @author Leow Yuan Yang
 * @version 4.0
 * @since 2021-08-25
 */
public class Duke {
    private static final String SEPARATOR = "\t==============================================";
    private static final String logoArt = "\t                                        \n" +
            "\t             ..........                 \n" +
            "\t           .,,..,.,,,,                  \n" +
            "\t           ,.,,,,.,...                  \n" +
            "\t          ,.......,*,/& .               \n" +
            "\t          ,..,..,((/**(((,              \n" +
            "\t           *(#%(((#/#((/       *        \n" +
            "\t         /(%#########%##(*,,,((*        \n" +
            "\t              ((#(((((#      (((*       \n" +
            "\t        ((((((   /((((/,., .... #       \n" +
            "\t         (((#    .(((..*...,*///        \n" +
            "\t         ((/      .,,**  ..**///        \n" +
            "\t                  .,*,..  ,**//         \n" +
            "\t                ..,,,..,/ ,*//          \n" +
            "\t             *,,****,        " ;
    private static final String nameArt = "\t _______  __   __  __   __  ______    _______ \n" +
            "\t|       ||  |_|  ||  | |  ||    _ |  |       |\n" +
            "\t|  _____||       ||  | |  ||   | ||  |    ___|\n" +
            "\t| |_____ |       ||  |_|  ||   |_||_ |   |___ \n" +
            "\t|_____  ||       ||       ||    __  ||    ___|\n" +
            "\t _____| || ||_|| ||       ||   |  | ||   |    \n" +
            "\t|_______||_|   |_||_______||___|  |_||___|    ";

    public static void main(String[] args) {
        boolean isDone = false;
        Task[] taskList = new Task[100];
        Scanner in = new Scanner(System.in);

        printWelcomeMessage();

        do {
            // Read inputs and split them into substrings
            String userInput = readUserInput(in);
            String[] userInputSubstrings = userInput.strip().split(" ", 2);
            String command = userInputSubstrings[0];

            switch (command) {
            case "bye":
                isDone = true;
                printExitMessage();
                break;
            case "done":
                try {
                    int itemNum = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
                    taskList[itemNum - 1].setDone();
                    printDoneTask(taskList[itemNum - 1], itemNum);
                } catch (Exception exception) {
                    printErrorMessage();
                }
                break;
            case "list":
                printTaskList(taskList);
                break;
            case "todo":
                try {
                    String todoDescription = userInputSubstrings[1].strip();
                    taskList[Task.getNumOfTasks()] = new Todo(todoDescription);
                    printAddTask(taskList[Task.getNumOfTasks() - 1]);
                } catch (Exception exception) {
                    printErrorMessage();
                }
                break;
            case "deadline":
                try {
                    String deadlineDescription = userInputSubstrings[1]
                            .substring(0, userInputSubstrings[1].indexOf("/"))
                            .strip();
                    String deadlineBy = userInputSubstrings[1]
                            .substring((userInputSubstrings[1].indexOf("/") + 3))
                            .strip();
                    taskList[Task.getNumOfTasks()] = new Deadline(deadlineDescription, deadlineBy);
                    printAddTask(taskList[Task.getNumOfTasks() - 1]);
                } catch (Exception exception) {
                    printErrorMessage();
                }
                break;
            case "event":
                try {
                    String eventDescription = userInputSubstrings[1]
                            .substring(0, userInputSubstrings[1].indexOf("/"))
                            .strip();
                    String eventAt = userInputSubstrings[1].substring((userInputSubstrings[1]
                                    .indexOf("/") + 3))
                            .strip();
                    taskList[Task.getNumOfTasks()] = new Event(eventDescription, eventAt);
                    printAddTask(taskList[Task.getNumOfTasks() - 1]);
                } catch (Exception exception) {
                    printErrorMessage();
                }
                break;
            default:
                printErrorMessage();
                break;
            }
        } while (!isDone);
    }

    private static String readUserInput(Scanner in) {
        System.out.println("\tCall out a smurf to do a job for you!");
        return in.nextLine();
    }

    private static void printWelcomeMessage() {
        System.out.println(logoArt + "\n" + nameArt);
        System.out.println(SEPARATOR);
        System.out.println("\t...la la la la la la sing a happy song\n");
        System.out.println("\tWelcome to Smurf Village.");
        System.out.println("\tStart smurfing now!!");
        System.out.println(SEPARATOR);
    }

    private static void printDoneTask(Task task, int itemNum) {
        System.out.println(SEPARATOR);
        System.out.println("\tBrainy Smurf: aah another thing done\n");
        System.out.printf("\t%d. [%s][%s] %s\n", itemNum, task.getTaskIcon(), task.getStatusIcon(),
                task.getDescription());
        System.out.println(SEPARATOR);
    }

    private static void printAddTask(Task task) {
        System.out.println(SEPARATOR);
        System.out.println("\tHandy Smurf is here to give you a hand!\n");
        System.out.println("\tI have added: ");
        System.out.println("\t" + task);
        System.out.println(SEPARATOR);
    }

    private static void printTaskList(Task[] taskList) {
        System.out.println(SEPARATOR);
        System.out.println("\t\"Tracker Smurf!! I need you here!!\"\n");
        for (int i = 0; i < Task.getNumOfTasks(); i++) {
            System.out.println("\t" + taskList[i]);
        }
        System.out.println(SEPARATOR);
    }

    private static void printErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\toops i can't find a smurf to help you with your task.");
        System.out.println(SEPARATOR);
    }

    private static void printExitMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\toh shucks! Gargamel is here..we gotta hide");
        System.out.println(SEPARATOR);
    }
}
