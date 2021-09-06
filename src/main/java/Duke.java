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
    private static Task[] taskList = new Task[100];

    public static void main(String[] args) {
        boolean isDone = false;
        Scanner in = new Scanner(System.in);

        printWelcomeMessage();

        do {
            // Read inputs and split them into substrings
            String[] userInputs = readUserInput(in);
            String command = userInputs[0];

            switch (command) {
            case "bye":
                isDone = true;
                printExitMessage();
                break;
            case "list":
                printTaskList(taskList);
                break;
            case "done":
                try {
                    executeDone(userInputs);
                } catch (Exception exception) {
                    printErrorMessage();
                }
                break;
            case "todo":
                try {
                    executeTodo(userInputs);
                } catch (EmptyDescriptionException exception) {
                    printEmptyDescriptionErrorMessage();
                } catch (Exception exception) {
                    printErrorMessage();
                }
                break;
            case "deadline":
                try {
                    executeDeadline(userInputs);
                } catch (EmptyTimeFieldException exception) {
                    printEmptyTimeFieldErrorMessage();
                } catch (EmptyDescriptionException exception) {
                    printEmptyDescriptionErrorMessage();
                } catch (Exception exception) {
                    printErrorMessage();
                }
                break;
            case "event":
                try {
                    executeEvent(userInputs);
                } catch (EmptyTimeFieldException exception) {
                    printEmptyTimeFieldErrorMessage();
                } catch (EmptyDescriptionException exception) {
                    printEmptyDescriptionErrorMessage();
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



    private static void executeEvent(String[] userInputs) throws EmptyDescriptionException, EmptyTimeFieldException {
        if (userInputs.length < 2) {
                throw new EmptyDescriptionException();
        }
        if (!userInputs[1].contains("/at")) {
            throw new EmptyTimeFieldException();
        }
        String eventDescription = userInputs[1]
                .substring(0, userInputs[1].indexOf("/"))
                .strip();
        if (eventDescription.isBlank()) {
            throw new EmptyDescriptionException();
        }
        String eventAt = userInputs[1].substring((userInputs[1]
                        .indexOf("/") + 3))
                .strip();
        taskList[Task.getNumOfTasks()] = new Event(eventDescription, eventAt);
        printAddTask(taskList[Task.getNumOfTasks() - 1]);
    }

    private static void executeDeadline(String[] userInputs) throws EmptyDescriptionException, EmptyTimeFieldException {
        if (userInputs.length < 2) {
                throw new EmptyDescriptionException();
        }
        if (!userInputs[1].contains("/by")) {
            throw new EmptyTimeFieldException();
        }
        String deadlineDescription = userInputs[1]
                .substring(0, userInputs[1].indexOf("/"))
                .strip();
        if (deadlineDescription.isBlank()) {
            throw new EmptyDescriptionException();
        }
        String deadlineBy = userInputs[1]
                .substring((userInputs[1].indexOf("/") + 3))
                .strip();
        taskList[Task.getNumOfTasks()] = new Deadline(deadlineDescription, deadlineBy);
        printAddTask(taskList[Task.getNumOfTasks() - 1]);
    }

    private static void executeTodo(String[] userInputs) throws EmptyDescriptionException {
        if (userInputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String todoDescription = userInputs[1].strip();
        taskList[Task.getNumOfTasks()] = new Todo(todoDescription);
        printAddTask(taskList[Task.getNumOfTasks() - 1]);
    }

    private static void executeDone(String[] userInputs) {
        int itemNum = Integer.parseInt(userInputs[1].replaceAll("[^0-9]", ""));
        taskList[itemNum - 1].setDone();
        printDoneTask(taskList[itemNum - 1], itemNum);
    }

    private static String[] readUserInput(Scanner in) {
        System.out.println("\tCall out a smurf to do a job for you!");
        String userInput = in.nextLine();
        return userInput.strip().split(" ", 2);
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

    private static void printEmptyDescriptionErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\tHey you needa gimme a description!!");
        System.out.println(SEPARATOR);
    }

    private static void printEmptyTimeFieldErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\tHey you didn't provide a time :(");
        System.out.println(SEPARATOR);
    }

    private static void printExitMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\toh shucks! Gargamel is here..we gotta hide");
        System.out.println(SEPARATOR);
    }
}
