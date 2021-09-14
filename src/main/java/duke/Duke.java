package duke;

import duke.exception.DirectoryCreationException;
import duke.exception.IllegalCommandException;
import duke.exception.IllegalParameterException;
import duke.task.TaskManager;
import duke.task.TaskType;
import java.util.Scanner;

public class Duke {

    /* Logo - generated using https://manytools.org/hacker-tools/convert-images-to-ascii-art/go/ */
    private static final String LOGO = "                               */@@@@@@@@@@@@@@@%*.\n"
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
        System.out.printf("\t%s <task index in list>%n", Commands.DELETE);
        System.out.println("\t\tDeletes the task at the supplied index");
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

    public static void main(String[] args) throws DirectoryCreationException {
        printGreeting();

        boolean exit = false;
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        taskManager.loadTasklistFromFile();

        while (!exit) {
            /* Splits off first word from input */
            String[] userInput = in.nextLine().split(" ", 2);

            System.out.println(DIVIDER);

            String command = userInput[0].toUpperCase().trim();
            String options = null;

            if (userInput.length >= 2) {
                options = userInput[1];
            }

            try {
                switch (command) {
                case Commands.LIST:
                    taskManager.listTasks();
                    break;
                case Commands.DONE:
                    taskManager.markDone(Integer.parseInt(options));
                    break;
                case Commands.BYE:
                    printGoodbye();
                    exit = true;
                    break;
                case Commands.TODO:
                    taskManager.addTask(options, TaskType.TODO);
                    break;
                case Commands.DEADLINE:
                    taskManager.addTask(options, TaskType.DEADLINE);
                    break;
                case Commands.EVENT:
                    taskManager.addTask(options, TaskType.EVENT);
                    break;
                case Commands.DELETE:
                    taskManager.deleteTask(Integer.parseInt(options));
                    break;
                default:
                    throw new IllegalCommandException("Unidentified command");
                }
            } catch (NumberFormatException e) {
                System.out.println(e + ": Do you not understand what a number is?");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e + ": Enter something that makes sense next time.");
            } catch (IllegalCommandException e) {
                System.out.println(e);
                printHelp();
            } catch (IllegalParameterException e) {
                System.out.println(e + ": Enter some valid parameters next time.");
            }

            System.out.println(DIVIDER);
        }

    }
}
