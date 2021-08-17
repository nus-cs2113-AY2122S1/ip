import java.util.Scanner;

public class Shika {

    public static String line = "______________________________________________________\n";

    public static void main(String[] args) {
        String logo = "  _________.__    .__ __            \n"
                + " /   _____/|  |__ |__|  | _______   \n"
                + " \\_____  \\ |  |  \\|  |  |/ /\\__  \\  \n"
                + " /        \\|   Y  \\  |    <  / __ \\_\n"
                + "/_______  /|___|  /__|__|_ \\(____  /\n"
                + "        \\/      \\/        \\/     \\/ \n";
        System.out.println(logo + "\nHello, friend! Shika at your service!\n");
        Task[] taskList = new Task[100];
        Task.count = 0;
        scan(taskList);
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static void scan(Task[] taskList) {
        Scanner in = new Scanner(System.in);
        String text;
        text = in.nextLine().trim();
        if (text.equals("bye")) {
            System.out.println(line + "> Bye friend!\n> See you again! :3\n" + line);
            return;
        } else if (text.equals("list")) {
            printTasks(taskList);
        } else if (text.trim().startsWith("done")) {
            String str = text.substring(text.indexOf("done") + 4).trim();
            if (isInteger(str)) {
                int index = Integer.parseInt(str);
                if (index > Task.count) {
                    System.out.print(line + "> Oopsie! That task does not exist... yet!\n" + line);
                }
                else if (index < 1) {
                    System.out.print(line + "> ...Stop trying to break me :<\n" + line);
                }
                else {
                    taskList[index - 1].markAsDone();
                    System.out.println(line + "> Otsukare! You've done:");
                    taskList[index - 1].printTask();
                    System.out.print(line);
                }
            }
            else {
                System.out.print(line + "> Uh-oh! Exception occurred! Please key in a number :p\n" + line);
            }
        }
        else {
            taskList[Task.count] = new Task(text, Task.count);
            System.out.print(line + "> Added: " + text + "!\n" + line);
            Task.count += 1;
        }
        scan(taskList);
    }

    public static void printTasks(Task[] taskList) {
        System.out.println(line + "> Here is your list of tasks:") ;
        for (int i = 0; i < Task.count; i++) {
            taskList[i].printTask();
        }
        System.out.print(line);
    }
}


