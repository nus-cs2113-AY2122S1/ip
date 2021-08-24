import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

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
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numOfUserItems; i++) {
            System.out.println(i + 1 + "." + userItems[i].getStatus() + " " + userItems[i].getDescription());
        }
        printLine();
    }

    private static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {

        System.out.println("Hello from\n" + LOGO);
        printLine();
        System.out.print("Hello! I'm BATMAN\n" + "What can I do for you?\n");
        printLine();
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
                    printLine();
                    System.out.print("Invalid command.\n");
                    printLine();
                    continue;
                }
                int number;
                try {
                    number = Integer.parseInt(words[1]);
                    if (number > numOfUserItems || number <= 0) {
                        printLine();
                        System.out.print("Invalid number.\n");
                        printLine();
                        continue;
                    }
                } catch (Exception e) {
                    printLine();
                    System.out.print("Invalid number.\n");
                    printLine();
                    continue;
                }
                userItems[number - 1].markDone();
                printLine();
                System.out.println("Cool I have eliminated this task:");
                System.out.println(userItems[number - 1].getStatus() + " " + userItems[number - 1].getDescription());
                printLine();
                continue;
            }
            userItems[numOfUserItems] = new Task(line);
            numOfUserItems++;
            printLine();
            System.out.print("added: " + line + "\n");
            printLine();
        }
        printLine();
        System.out.print("Bye. Hope to see you again soon!\n");
        printLine();
    }
}
