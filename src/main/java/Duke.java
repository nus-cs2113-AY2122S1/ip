import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void printMessage(String msg) {
        String formattedMsg = "";
        String underscores = "    ___________________________________________________________\n";
        formattedMsg += underscores + msg + underscores;
        System.out.println(formattedMsg);
    }

    public static void printTasks(ArrayList<Task> tasks) {
        String formattedMsg = "";
        String underscores = "    ___________________________________________________________\n";
        formattedMsg += underscores;
        for (int i = 0; i < tasks.size(); ++i) {
            Task curTask = tasks.get(i);
            formattedMsg += "     " + (i + 1) + ". " + curTask.getDescription() + "\n";
        }
        formattedMsg += underscores;
        System.out.println(formattedMsg);
    }

    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        String greetMessage = "     Hello! I'm Duke\n"
                + "     What can I do for you?\n";
        printMessage(greetMessage);

        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            if (userInput.strip().equals("bye")) {
                break;
            }

            if (userInput.equals("list")) {
                printTasks(tasks);
                continue;
            }

            tasks.add(new Task(userInput));
            String addedMsg = "     added: " + userInput + "\n";
            printMessage(addedMsg);
        }

        String byeMessage = "     Bye. Hope to see you again soon!\n";
        printMessage(byeMessage);
    }
}
