package duke;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import static duke.Storage.saveData;
import static duke.TaskList.*;

/**
 * Class responsible for filtering out user commands and determining the flow of the program.
 *
 * @author pragyan01
 */
public class Parser {

    /**
     * Method that determines the control flow of the duke bot based on user input
     *
     * @throws DukeException custom error message when list is asked to be printed but empty
     * @throws DateTimeParseException if format entered by user is not correct
     */
    public static void inputSort() throws DukeException {
        while (quitFlag == 0) {
            System.out.println("Enter your wish: " + "\n" + line);
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            String actionWord = input.split(" ")[0];
            switch (actionWord) {
            case "bye":
                sayBye(input);
                break;
            case "list":
                try {
                    sayList();
                } catch (DukeException e) {
                    System.out.println(line + "\n" + "Hold your horses, you didn't even tell me about your wishes yet!" + "\n" + line + "\n");
                }
                break;
            case "done":
                sayDone(input);
                saveData(t);
                break;
            case "todo":
                sayTodo(input);
                saveData(t);
                break;
            case "deadline":
                try {
                    sayDeadline(input);
                    saveData(t);
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter in the format: deadline (desc) /by yyyy-mm-dd");
                }
                break;
            case "event":
                sayEvent(input);
                saveData(t);
                break;
            case "delete":
                sayDelete(input);
                saveData(t);
                break;
            case "find":
                sayFind(input);
                break;
            default:
                System.out.println(line + "\n☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
            }
        }
    }
}
