import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String HORIZONTAL_LINE = "___________________" +
                "_________________________________________\n";
        final String LOGO = "\n" +
                "██████╗░░█████╗░████████╗███╗░░░███╗░█████╗░███╗░░██╗\n" +
                "██╔══██╗██╔══██╗╚══██╔══╝████╗░████║██╔══██╗████╗░██║\n" +
                "██████╦╝███████║░░░██║░░░██╔████╔██║███████║██╔██╗██║\n" +
                "██╔══██╗██╔══██║░░░██║░░░██║╚██╔╝██║██╔══██║██║╚████║\n" +
                "██████╦╝██║░░██║░░░██║░░░██║░╚═╝░██║██║░░██║██║░╚███║\n" +
                "╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝";

        System.out.println("Hello from\n" + LOGO);
        System.out.println(HORIZONTAL_LINE + "Hello! I'm BATMAN\n" + "What can I do for you?\n" +
                HORIZONTAL_LINE);

        while (true) {
            String line;
            Scanner sc = new Scanner(System.in);
            line = sc.nextLine();
            if (line.equals("bye")) {
                break;
            }
            System.out.println(HORIZONTAL_LINE + line + "\n" +
                    HORIZONTAL_LINE);
        }

        System.out.println(HORIZONTAL_LINE + "Bye. Hope to see you again soon!\n" +
                HORIZONTAL_LINE);
    }
}
