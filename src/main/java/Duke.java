import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greeting();
        //echo();
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        while (true) {
            String command = in.nextLine();
            if (command.equals("list")) {
                Task.printList(list);
                continue;
            } else if (command.startsWith("done")) {
                String[] words = command.split(" ");
                int index = Integer.parseInt(words[1]);
                list[index - 1].markAsDone();
            } else if (command.equals("bye")) {
                break;
            } else {
                Task t = new Task(command);
                list[Task.getNumberOfTasks() - 1] = t;
                list[Task.getNumberOfTasks() - 1].printNewTaskAddedMessage();
            }
        }
        Task.exit();
    }

    public static void greeting() {
        String logo = " /\\_/\\\n"
                + "( o.o )\n"
                + " > ^ <";
        System.out.println(logo);
        System.out.println("Meow~ I'm Karlett!(◕▿◕✿)");
        System.out.println("What can I do for you meow?");
    }

    /*public static void echo() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                break;
            }
            Task.drawDivider();
            System.out.println(command + " meow!");
            Task.drawDivider();
        }
    }

     */

}
