import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    /**
     * Calls the appropriate command based on the input by the user.
     * @param in scanner that takes in the input
     * @param line the line of input given by the user
     * @param words the array consisting of the words in the line of input
     */
    static void programLogic(Scanner in, String line, String[] words) {
        while (!words[0].equals("bye")) {
            try {
                switch (words[0]) {
                case "todo":
                    TaskList.prepareToAddTodo(line);
                    break;
                case "deadline":
                    TaskList.prepareToAddDeadline(line);
                    break;
                case "event":
                    TaskList.prepareToAddEvent(line);
                    break;
                case "list":
                    TaskList.listTasks();
                    break;
                case "done":
                    int markedIndex = Integer.parseInt(words[1]) - 1;
                    TaskList.markAsDone(markedIndex);
                    break;
                case "delete":
                    int deleteIndex = Integer.parseInt(words[1]) - 1;
                    TaskList.deleteTask(deleteIndex);
                    break;
                case "find":
                    TaskList.findTasks(line.substring(4).trim());
                    break;
                case "filter":
                    TaskList.filterDates(line);
                    break;
                default:
                    TaskList.printInvalid();
                    break;
                }
            } catch (IOException e) {
                TaskList.printInvalid();
            } catch (NumberFormatException e) {
                Duke.printLine();
                System.out.println("\tThe index has to be an integer.");
                Duke.printLine();
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                Duke.printLine();
                System.out.println("\tThis is not a valid date and time. " +
                        "Deadline and Event tasks require" + System.lineSeparator() +
                        "\tdate and time (24hr format) in the following format: ");
                System.out.println("\tyyyy/mm/dd hhmm");
                Duke.printLine();
            }
            line = Ui.getLine(in);
            words = Ui.getWords(line);
        }
    }
}
