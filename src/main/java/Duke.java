import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String Logo = " _____         _____        \n"
                + "|     \\ _____ |     \\ _____ \n"
                + "|  o  /|     ||  o  /|     |\n"
                + "|  o  \\|  o  ||  o  \\|  o  |\n"
                + "|_____/|_____||_____/|_____|\n";
        String HORIZONTAL_LINE = "____________________________________________________________";
        String[] tasks = new String[100];
        int taskPointer = 0;
        String line;
        boolean isRunning = true;
        Scanner in = new Scanner(System.in);

        System.out.println(Logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Bobo!");
        System.out.println("I'm a little blur, but I'd love to help!");
        System.out.println(HORIZONTAL_LINE);

        while (isRunning) {
            line = in.nextLine();
            if (line.equals("bye")) {
                isRunning = false;
            } else if(line.equals("list")) {
                System.out.println(HORIZONTAL_LINE);
                for (int i = 0; i < taskPointer; i++) {
                    System.out.println((i+1) + ". " + tasks[i]);
                }
                System.out.println(HORIZONTAL_LINE);
            } else {
                tasks[taskPointer] = line;
                taskPointer++;
                System.out.println(HORIZONTAL_LINE);
                System.out.println("umm ok added: " + line);
                System.out.println(HORIZONTAL_LINE);
            }
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Ok. Bye bye!");
        System.out.println(HORIZONTAL_LINE);
    }
}
