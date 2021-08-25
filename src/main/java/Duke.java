import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String Logo = " _____         _____        \n"
                + "|     \\ _____ |     \\ _____ \n"
                + "|  o  /|     ||  o  /|     |\n"
                + "|  o  \\|  o  ||  o  \\|  o  |\n"
                + "|_____/|_____||_____/|_____|\n";
        String HORIZONTAL_LINE = "____________________________________________________________";
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
            } else {
                System.out.println(HORIZONTAL_LINE);
                System.out.println(line);
                System.out.println(HORIZONTAL_LINE);
            }
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Ok. Bye bye!");
        System.out.println(HORIZONTAL_LINE);
    }
}
