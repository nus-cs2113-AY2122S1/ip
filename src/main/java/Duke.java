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
        formattedMsg += underscores + "     Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); ++i) {
            Task curTask = tasks.get(i);
            formattedMsg += "     " + (i + 1) + ".[" + curTask.getStatusIcon() + "] "
                    + curTask.getDescription() + "\n";
        }
        formattedMsg += underscores;
        System.out.println(formattedMsg);
    }

    public static boolean intChecker(String[] inputArr, int size) {
        try {
            int taskNumber = Integer.parseInt(inputArr[1]);
            if ((taskNumber > size) || (taskNumber < 1) || inputArr.length != 2) {
                printMessage("     Please input a valid task number!\n");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            printMessage("     Please input an INTEGER as your task number!\n");
        }
        return false;
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

            if (userInput.strip().toUpperCase().equals("BYE")) {
                break;
            }

            if (userInput.toUpperCase().equals("LIST")) {
                printTasks(tasks);
                continue;
            }

            if (userInput.toUpperCase().startsWith("DONE")) {
                String[] inputArr = userInput.split(" ");
                boolean isValid = intChecker(inputArr, tasks.size());

                if (!isValid) {
                    continue;
                }

                int taskNumber = Integer.parseInt(inputArr[1]);
                Task curTask = tasks.get(taskNumber - 1);
                curTask.setDone();

                String doneMessage = "     Nice! I've marked this task as done:\n"
                        + "       [" + curTask.getStatusIcon() + "] "
                        + curTask.getDescription() + "\n";
                printMessage(doneMessage);
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
