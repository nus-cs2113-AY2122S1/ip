import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "___________________" +
            "_________________________________________\n";

    private static final String LOGO = "\n" +
            "██████╗░░█████╗░████████╗███╗░░░███╗░█████╗░███╗░░██╗\n" +
            "██╔══██╗██╔══██╗╚══██╔══╝████╗░████║██╔══██╗████╗░██║\n" +
            "██████╦╝███████║░░░██║░░░██╔████╔██║███████║██╔██╗██║\n" +
            "██╔══██╗██╔══██║░░░██║░░░██║╚██╔╝██║██╔══██║██║╚████║\n" +
            "██████╦╝██║░░██║░░░██║░░░██║░╚═╝░██║██║░░██║██║░╚███║\n" +
            "╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝";

    private static final String[] userInputs = new String[100];
    private static int numOfUserInput = 0;

    private static void printList() {
        System.out.print(HORIZONTAL_LINE);
        for (int i = 0; i < numOfUserInput; i++) {
            System.out.println(i+1 + ". " + userInputs[i]);
        }
        System.out.print(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {

        System.out.println("Hello from\n" + LOGO);
        System.out.print(HORIZONTAL_LINE + "Hello! I'm BATMAN\n" + "What can I do for you?\n" +
                HORIZONTAL_LINE);

        while (true) {
            String line;
            Scanner sc = new Scanner(System.in);
            line = sc.nextLine();
            if (line.equals("bye")) {
                break;
            }
            else if (line.equals("list")) {
                printList();
                continue;
            }
            userInputs[numOfUserInput] = line;
            numOfUserInput++;
            System.out.print(HORIZONTAL_LINE + "added: " + line + "\n" +
                    HORIZONTAL_LINE);
        }

        System.out.print(HORIZONTAL_LINE + "Bye. Hope to see you again soon!\n" +
                HORIZONTAL_LINE);
    }
}
