import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    private static final String LOGO = "\n" +
            "██████╗░░█████╗░████████╗███╗░░░███╗░█████╗░███╗░░██╗\n" +
            "██╔══██╗██╔══██╗╚══██╔══╝████╗░████║██╔══██╗████╗░██║\n" +
            "██████╦╝███████║░░░██║░░░██╔████╔██║███████║██╔██╗██║\n" +
            "██╔══██╗██╔══██║░░░██║░░░██║╚██╔╝██║██╔══██║██║╚████║\n" +
            "██████╦╝██║░░██║░░░██║░░░██║░╚═╝░██║██║░░██║██║░╚███║\n" +
            "╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝";

    private static final Task[] userItems = new Task[100];
    private static int numOfUserItems = 0;

    private static void printList() {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numOfUserItems; i++) {
            System.out.println(i + 1 + "." + userItems[i].getStatus() + " " + userItems[i].getDescription());
        }
        System.out.print(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {

        System.out.println("Hello from\n" + LOGO);
        System.out.print(HORIZONTAL_LINE + "Hello! I'm BATMAN\n" + "What can I do for you?\n" + HORIZONTAL_LINE);

        while (true) {
            String line;
            Scanner sc = new Scanner(System.in);
            line = sc.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                printList();
                continue;
            }
            String[] words = line.split(" ");
            if (words[0].equals("done")) {
                if (words.length == 1) {
                    System.out.print(HORIZONTAL_LINE + "Invalid command.\n" + HORIZONTAL_LINE);
                    continue;
                }
                int number = Integer.parseInt(words[1]);
                if (number > numOfUserItems) {
                    System.out.print(HORIZONTAL_LINE + "Invalid number.\n" + HORIZONTAL_LINE);
                    continue;
                }
                userItems[number - 1].markDone();
                System.out.print(HORIZONTAL_LINE);
                System.out.println("Cool I have eliminated this task:");
                System.out.println(userItems[number - 1].getStatus() + " " + userItems[number - 1].getDescription());
                System.out.print(HORIZONTAL_LINE);
                continue;
            }
            userItems[numOfUserItems] = new Task(line);
            numOfUserItems++;
            System.out.print(HORIZONTAL_LINE + "added: " + line + "\n" + HORIZONTAL_LINE);
        }

        System.out.print(HORIZONTAL_LINE + "Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE);
    }
}
