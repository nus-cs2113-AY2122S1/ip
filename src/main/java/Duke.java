import java.util.Scanner;

/**
 * Chatbot program Duke that tracks user tasks.
 *
 * @author Leow Yuan Yang
 * @version 4.0
 * @since 2021-08-25
 */
public class Duke {
    public static void main(String[] args) {
        final String SEPARATOR = "\t==============================================";
        String logoArt = "\t                                        \n" +
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

        final String nameArt = "\t _______  __   __  __   __  ______    _______ \n" +
                "\t|       ||  |_|  ||  | |  ||    _ |  |       |\n" +
                "\t|  _____||       ||  | |  ||   | ||  |    ___|\n" +
                "\t| |_____ |       ||  |_|  ||   |_||_ |   |___ \n" +
                "\t|_____  ||       ||       ||    __  ||    ___|\n" +
                "\t _____| || ||_|| ||       ||   |  | ||   |    \n" +
                "\t|_______||_|   |_||_______||___|  |_||___|    ";

        // Introduction of Chatbot
        System.out.println(logoArt + "\n" + nameArt);
        System.out.println(SEPARATOR);
        System.out.println("\t...la la la la la la sing a happy song\n");
        System.out.println("\tWelcome to Smurf Village.");
        System.out.println("\tStart smurfing now!!");
        System.out.println(SEPARATOR);

        boolean isDone = false;
        Task[] taskList = new Task[100];

        do {
            // Read inputs
            System.out.println("\tCall out a smurf to do a job for you!");
            Scanner in = new Scanner(System.in);
            String userInput;
            userInput = in.nextLine();
            String[] userInputSubstrings = userInput.strip().split(" ", 2);
            String command = userInputSubstrings[0];

            switch (command) {
            case "bye":
                // Exit bot
                isDone = true;

                // Print output
                System.out.println(SEPARATOR);
                System.out.println("\toh shucks! Gargamel is here..we gotta hide");
                System.out.println(SEPARATOR);
                break;
            case "done":
                try {
                    // Find item number and update item status
                    int itemNum = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
                    taskList[itemNum - 1].setDone();

                    // Print output
                    System.out.println(SEPARATOR);
                    System.out.println("\tBrainy Smurf: aah another thing done\n");
                    System.out.printf("\t%d. [%s][%s] %s\n", itemNum, taskList[itemNum - 1].getTaskIcon(), taskList[itemNum - 1].getStatusIcon(),
                            taskList[itemNum - 1].getDescription());
                    System.out.println(SEPARATOR);
                } catch (Exception exception) {
                    System.out.println(SEPARATOR);
                    System.out.println("\tOops I can't find a Smurf to help you with your task.");
                    System.out.println(SEPARATOR);
                }
                break;
            case "list":
                // Print output
                System.out.println(SEPARATOR);
                System.out.println("\t\"Tracker Smurf!! I need you here!!\"\n");
                for (int i = 0; i < Task.getNumOfTasks(); i++) {
                    System.out.println("\t" + taskList[i]);
                }
                System.out.println(SEPARATOR);
                break;
            case "todo":
                try {
                    // Add task
                    String todoDescription = userInput.replaceFirst(command, "").strip();
                    taskList[Task.getNumOfTasks()] = new Todo(todoDescription);

                    // Print output
                    System.out.println(SEPARATOR);
                    System.out.println("\tHandy Smurf is here to give you a hand!\n");
                    System.out.println("\tI have added: ");
                    System.out.println("\t" + taskList[Task.getNumOfTasks() - 1]);
                    System.out.println(SEPARATOR);
                } catch (Exception exception) {
                    System.out.println(SEPARATOR);
                    System.out.println("\tOops I can't find a Smurf to help you with your task.");
                    System.out.println(SEPARATOR);
                }
                break;
            case "deadline":
                try {
                    // Add task
                    String deadlineDescription = userInputSubstrings[1]
                            .substring(0, userInputSubstrings[1].indexOf("/"))
                            .strip();
                    String deadlineBy = userInputSubstrings[1]
                            .substring((userInputSubstrings[1].indexOf("/") + 3))
                            .strip();
                    taskList[Task.getNumOfTasks()] = new Deadline(deadlineDescription, deadlineBy);

                    // Print output
                    System.out.println(SEPARATOR);
                    System.out.println("\tHandy Smurf is here to give you a hand!\n");
                    System.out.println("\tI have added: ");
                    System.out.println("\t" + taskList[Task.getNumOfTasks() - 1]);
                    System.out.println(SEPARATOR);
                } catch (Exception exception) {
                    System.out.println(SEPARATOR);
                    System.out.println("\tOops I can't find a Smurf to help you with your task.");
                    System.out.println(SEPARATOR);
                }
                break;
            case "event":
                try {
                    // Add task
                    String eventDescription = userInputSubstrings[1]
                            .substring(0, userInputSubstrings[1].indexOf("/"))
                            .strip();
                    String eventAt = userInputSubstrings[1].substring((userInputSubstrings[1]
                                    .indexOf("/") + 3))
                            .strip();
                    taskList[Task.getNumOfTasks()] = new Event(eventDescription, eventAt);

                    // Print output
                    System.out.println(SEPARATOR);
                    System.out.println("\tHandy Smurf is here to give you a hand!\n");
                    System.out.println("\tI have added: ");
                    System.out.println("\t" + taskList[Task.getNumOfTasks() - 1]);
                    System.out.println(SEPARATOR);
                } catch (Exception exception) {
                    System.out.println(SEPARATOR);
                    System.out.println("\tOops I can't find a Smurf to help you with your task.");
                    System.out.println(SEPARATOR);
                }
                break;
            default:
                // Catch error
                System.out.println(SEPARATOR);
                System.out.println("\tOops I can't find a Smurf to help you with your task.");
                System.out.println(SEPARATOR);
                break;
            }
        } while (!isDone);
    }
}
