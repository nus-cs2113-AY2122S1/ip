package duke;
import java.util.Scanner;
import static duke.Storage.saveData;
import static duke.TaskList.*;

public class Parser {

    //Creates scanner, takes in user input & filters it to different methods
    public static void inputSort() throws DukeException {
        System.out.println("Enter your wish: " + "\n" + line);
        while (quitFlag == 0) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            String actionWord = input.split(" ")[0];
            switch (actionWord) {
            case "bye":
                sayBye(input);
                break;
            case "list":
                sayList(input);
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
                sayDeadline(input);
                saveData(t);
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
