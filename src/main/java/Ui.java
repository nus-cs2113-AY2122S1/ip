
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Ui {

    private static final String DIVIDER = "    ——————————————————————————————————————————————————————————————";
    private static final String SPACE = "    ";
    private static final String LIST_MESSAGE = "     Here are the tasks in your list:";
    private static final String ADD_MESSAGE = "     Now you have %d tasks in the list.";
    private static final int INDEX_OFFSET = 1;
    private static final String POINT = ".";


    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcomeMessage() {
        out.println(DIVIDER);
        out.println("     Hello! I'm Duke\n     What can I do for you?");
        out.println(DIVIDER);
    }

    public void showGoodbyeMessage() {
        out.println(DIVIDER);
        out.println("     Bye. Hope to see you again soon!");
        out.println(DIVIDER);
    }

    public void showMessages(String ... message) {
        for(String m: message) {
            out.println(m);
        }
    }
    public void showResultToUser(CommandResult result) {
        if(result.feedback.equals("bye")) {
        } else if (result.feedback.contains("☹ OOPS!!!")) {
            showMessages(DIVIDER, result.feedback, DIVIDER);
        } else if (result.feedback.equals("list")) {
            showTasksList(result.relevantTasks);
        } else if (result.feedback.equals("     Nice! I've marked this task as done:")) {
            showMessages(DIVIDER, result.feedback);
            out.println("     " + result.relevantTask);
            showMessages(DIVIDER);
        } else {
            showMessages(DIVIDER, result.feedback);
            out.println("      " + result.relevantTask);
            showMessages(String.format(ADD_MESSAGE, result.size), DIVIDER);
        }
    }

    public void showTasksList (List<Task> tasksList) {
        int taskIndex = INDEX_OFFSET;
        out.println(DIVIDER);
        out.println(LIST_MESSAGE);
        for(Task task: tasksList) {
            out.print(SPACE + taskIndex++ + POINT);
            out.println(task);
        }
        out.println(DIVIDER);
    }

    public String getUserCommand() {
        String inputLine;
        inputLine = in.nextLine();
        return inputLine;
    }
}
