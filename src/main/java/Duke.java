import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greeting();
        //echo();
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        int total = 0;
        while (true) {
            String command = in.nextLine();
            if (command.equals("list")) {
                list(list, total);
                continue;
            } else if (command.startsWith("done")) {
                String[] words = command.split(" ");
                int index = Integer.parseInt(words[1]);
                markAsDone(list, index-1);
            } else if (command.equals("bye")) {
                break;
            } else {
                store(list, total, command);
                total++;
            }
        }
        exit();
    }

    private static void markAsDone(Task[] list, int index) {
        list[index].markAsDone();
        drawDivider();
        System.out.println("Meow~ Karlett has marked this task as done:");
        System.out.println(" [" + list[index].getStatusIcon() + "] "
                + list[index].getDescription());
    }

    private static void list(Task[] list, int total) {
        drawDivider();
        if (total == 0) {
            System.out.println("Karlett can't remember anything...(ФДФ)");
        }
        for (int i = 0; i < total; i++) {
            System.out.println("ฅ" + (i + 1) + " [" + list[i].getStatusIcon() + "] "
                    + list[i].getDescription());
        }
        drawDivider();
    }

    public static void store(Task[] list, int index, String command) {
        drawDivider();
        System.out.println("Karlett remembers: " + command);
        drawDivider();
        Task t = new Task(command);
        list[index] = t;
    }

    public static void greeting() {
        String logo = " /\\_/\\\n"
                + "( o.o )\n"
                + " > ^ <";
        System.out.println(logo);
        System.out.println("Meow~ I'm Karlett!(◕▿◕✿)");
        System.out.println("What can I do for you meow?");
    }

    public static void echo() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                break;
            }
            drawDivider();
            System.out.println(command + " meow!");
            drawDivider();
        }
    }

    public static void exit() {
        drawDivider();
        System.out.println("Bye~Bye~ヾ(￣▽￣) Hope to see you again soon meow.");
        drawDivider();
    }

    public static void drawDivider() {
        int n = 4;
        while (n > 0) {
            System.out.print("ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ");
            n--;
        }
        System.out.println();
    }
}
