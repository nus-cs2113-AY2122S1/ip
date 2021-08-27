import java.util.Scanner;

/**
 * Chatbot program Duke that tracks user tasks.
 *
 * @author Leow Yuan Yang
 * @version 1.0
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

        // Introduction of chatbot
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
            String command;
            command = in.nextLine();

            if (command.startsWith("done")) {
                // Find item number and update item status
                int itemNum = Integer.parseInt(command.replaceAll("[^0-9]", "")) - 1;
                taskList[itemNum].setDone();

                // Print output
                System.out.println(SEPARATOR);
                System.out.println("\tBrainy Smurf: ahhh another thing done\n");
                System.out.printf("\t  [%s] %s\n", taskList[itemNum].getStatusIcon(),
                        taskList[itemNum].getDescription());
                System.out.println(SEPARATOR);
            } else {
                switch (command) {
                case "bye":
                    // Exit bot
                    isDone = true;

                    // Print output
                    System.out.println(SEPARATOR);
                    System.out.println("\toh shucks! Gargamel is here..we gotta hide");
                    System.out.println(SEPARATOR);
                    break;
                case "list":
                    // Print output
                    System.out.println(SEPARATOR);
                    System.out.println("\t\"Tracker Smurf!! I need you here!!\"\n");
                    for (int i = 0; i < Task.getNumOfTasks(); i++) {
                        System.out.printf("\t%d.[%s] %s\n", i + 1, taskList[i].getStatusIcon(),
                                taskList[i].getDescription());
                    }
                    System.out.println(SEPARATOR);
                    break;
                default:
                    // Add task
                    taskList[Task.getNumOfTasks()] = new Task(command);

                    // Print output
                    System.out.println(SEPARATOR);
                    System.out.println("\tHandy Smurf is here to give you a hand!\n");
                    System.out.println("\tadded: " + command);
                    System.out.println(SEPARATOR);
                    break;
                }
            }
        } while (!isDone);
    }
}
