import java.util.Scanner;

public class Duke {

    public static String horizontal = ("____________________________________________________________\n");

    public static void sayHello() {
        System.out.println(horizontal + "Hello! I'm Duke\n" + "What can I do for you?\n" + horizontal);
    }

    public static void sayBye() {
        System.out.println(horizontal + "Bye. Hope to see you again soon!\n" + horizontal);
    }

    public static void addTask(String task, String[] taskList, int numberOfTasks) {
        System.out.println(horizontal + "added: " + task + "\n" + horizontal);
        taskList[numberOfTasks] = task;
    }

    public static void listTasks(String[] taskList, int numberOfTasks) {
        System.out.print(horizontal);
        for (int i = 1; i <= numberOfTasks; i++) {
            System.out.println(i + ". " + taskList[i - 1]);
        }
        System.out.println(horizontal);
    }

    public static void main(String[] args) {
        String[] taskList = new String[100];
        int numberOfTasks = 0;
        sayHello();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                listTasks(taskList, numberOfTasks);
            } else {
                addTask(line, taskList, numberOfTasks);
                numberOfTasks++;
            }
            line = in.nextLine();
        }
        sayBye();
    }
}
