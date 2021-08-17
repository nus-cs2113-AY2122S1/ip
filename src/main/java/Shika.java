import java.util.Scanner;

public class Shika {
    public static void main(String[] args) {
        String logo = "  _________.__    .__ __            \n"
                + " /   _____/|  |__ |__|  | _______   \n"
                + " \\_____  \\ |  |  \\|  |  |/ /\\__  \\  \n"
                + " /        \\|   Y  \\  |    <  / __ \\_\n"
                + "/_______  /|___|  /__|__|_ \\(____  /\n"
                + "        \\/      \\/        \\/     \\/ \n";
        System.out.println(logo + "\nHello, friend! Shika at your service!\n");
        echo();
    }

    public static void echo() {
        String line = "_________________________________________________\n";
        Scanner in = new Scanner(System.in);
        String text;
        text = in.nextLine();
        if (text.equals("bye")) {
            System.out.println(line + "> Bye friend!\n> See you again! :3\n" + line);
            return;
        }
        else {
            System.out.println(text);
        }
        echo();
    }
}
