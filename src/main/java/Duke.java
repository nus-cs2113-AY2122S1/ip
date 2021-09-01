import java.util.Scanner;

public class Duke {

    /* Logo - generated using https://manytools.org/hacker-tools/convert-images-to-ascii-art/go/ */
    private static final String LOGO = "                               */@@@@@@@@@@@@@@@%*.\n"
<<<<<<< HEAD
            + "                       .&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,\n"
            + "                  *@@@@@@@@@@@@#     .@@/@@.     #@@@@@@@@@@@@&,\n"
            + "              ,@@@@@&. *@@@&.       #@#   #@#        &@@@/  ,&@@@@%.\n"
            + "           ,@@@@%.   /@@@(        ,@@.     .@@.        /@@@,    .@@@@@.\n"
            + "         &@@@%      &@@%         &@#         (@%         %@@#      .@@@@/\n"
            + "      ,@@@@*       %@@#        *@@.            @@,        /@@&        #@@@%\n"
            + "    *@@@@.        #@@/        @@/               /@%        (@@#         /@@@(\n"
            + "   &@@&          .@@@.      *@&                   &@*       @@@           #@@@.\n"
            + " (@@@*           .@@&      &@*                     *@&      %@@,           (@@@*\n"
            + "  .@@@&          .@@&    /@#                         %@(    @@@          .@@@@.\n"
            + "    *@@@&         &@@/  &@,                           .@@. /@@#        .@@@@,\n"
            + "      /@@@@,       &@@@@#                               %@@@@&.      /@@@&\n"
            + "         &@@@&      &@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@     .&@@@%\n"
            + "           (@@@@%    (@@@,                             ,@@@/   ,@@@@&\n"
            + "              ,@@@@@%. #@@@(                         #@@@% .&@@@@&\n"
            + "                  /@@@@@@@@@@@&.                 ,&@@@@@@@@@@&,\n"
            + "                       (@@@@@@@@@@@@@&%%%%%&@@@@@@@@@@@@&*\n"
            + "                              *&@@@@@@@@@@@@@@@@@(,\n";
=======
                    + "                       .&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,\n"
                    + "                  *@@@@@@@@@@@@#     .@@/@@.     #@@@@@@@@@@@@&,\n"
                    + "              ,@@@@@&. *@@@&.       #@#   #@#        &@@@/  ,&@@@@%.\n"
                    + "           ,@@@@%.   /@@@(        ,@@.     .@@.        /@@@,    .@@@@@.\n"
                    + "         &@@@%      &@@%         &@#         (@%         %@@#      .@@@@/\n"
                    + "      ,@@@@*       %@@#        *@@.            @@,        /@@&        #@@@%\n"
                    + "    *@@@@.        #@@/        @@/               /@%        (@@#         /@@@(\n"
                    + "   &@@&          .@@@.      *@&                   &@*       @@@           #@@@.\n"
                    + " (@@@*           .@@&      &@*                     *@&      %@@,           (@@@*\n"
                    + "  .@@@&          .@@&    /@#                         %@(    @@@          .@@@@.\n"
                    + "    *@@@&         &@@/  &@,                           .@@. /@@#        .@@@@,\n"
                    + "      /@@@@,       &@@@@#                               %@@@@&.      /@@@&\n"
                    + "         &@@@&      &@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@     .&@@@%\n"
                    + "           (@@@@%    (@@@,                             ,@@@/   ,@@@@&\n"
                    + "              ,@@@@@%. #@@@(                         #@@@% .&@@@@&\n"
                    + "                  /@@@@@@@@@@@&.                 ,&@@@@@@@@@@&,\n"
                    + "                       (@@@@@@@@@@@@@&%%%%%&@@@@@@@@@@@@&*\n"
                    + "                              *&@@@@@@@@@@@@@@@@@(,\n";
>>>>>>> master

    /* Divider */
    private static final String DIVIDER = "============================================================";

    /* Prints a help message listing all commands and their formats */
    public static void printHelp() {
        System.out.println("Usage:");
        System.out.printf("\t%s <description>%n", Commands.TODO);
        System.out.println("\t\tAdds a new task without any date/time attached to it.");
        System.out.printf("\t%s <description> /by <deadline>%n", Commands.DEADLINE);
        System.out.println("\t\tAdds a new task that need to be done before a specific date/time.");
        System.out.printf("\t%s <description> /at <time range>%n", Commands.EVENT);
        System.out.println("\t\tAdds a new task that starts at a specific time and ends at a specific time");
        System.out.printf("\t%s%n", Commands.LIST);
        System.out.println("\t\tPrints a indexed list of all added tasks");
        System.out.printf("\t%s <task index in list>%n", Commands.DONE);
        System.out.println("\t\tMarks task at supplied index as done");
        System.out.printf("\t%s%n", Commands.BYE);
        System.out.println("\t\tExits the program");
    }

    /* Prints a greeting message with logo */
    public static void printGreeting() {
        System.out.println(LOGO);
        System.out.println("\nWelcome friends. I had a feeling you would come.\n" +
                "My fake brain is better than your real one.\n" +
                "When I develop protein synthesis, I will be able to make myself a real brain. Far better than your "
                + "silly little thinking device.\n" + DIVIDER + "\n");
    }

    /* Prints goodbye message */
    public static void printGoodbye() {
        System.out.println(
                "My favourite feeling is schadenfreude. I also like hiraeth. It's the Welsh concept of longing for home.");
    }

    public static void main(String[] args) {
        printGreeting();

        boolean exit = false;
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (!exit) {
            /* Splits off first word from input */
            String[] userInput = in.nextLine().split(" ", 2);

            System.out.println(DIVIDER);

            try {
                switch (userInput[0].toUpperCase().trim()) {
                case Commands.LIST:
                    taskManager.listTasks();
                    break;
                case Commands.DONE:
                    taskManager.markDone(Integer.parseInt(userInput[1]));
                    break;
                case Commands.BYE:
                    printGoodbye();
                    exit = true;
                    break;
                case Commands.TODO:
                    taskManager.addTask(userInput[1], TaskType.TODO);
                    break;
                case Commands.DEADLINE:
                    taskManager.addTask(userInput[1], TaskType.DEADLINE);
                    break;
                case Commands.EVENT:
                    taskManager.addTask(userInput[1], TaskType.EVENT);
                    break;
                default:
                    printHelp();
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(e + ": Do you not understand what a number is?");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e + ": Enter something that makes sense next time.");
            }

            System.out.println(DIVIDER);
        }

    }
}
