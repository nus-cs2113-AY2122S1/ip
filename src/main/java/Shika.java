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
        String[] storage = new String[100];
        int index = 0;
        echo(storage, index);
    }

    public static void echo(String[] storage, int index) {
        String line = "_________________________________________________\n";
        Scanner in = new Scanner(System.in);
        String text;
        text = in.nextLine();
        if (text.equals("bye")) {
            System.out.println(line + "> Bye friend!\n> See you again! :3\n" + line);
            return;
        }
        else if (text.equals("list")) {
            printList(storage, index);
        }
        else {
            storage[index] = text;
            index += 1;
            System.out.println(line + "Added: " + text + "!\n" + line);
        }
        echo(storage, index);
    }

    public static void printList(String[] storage, int index) {
        for (int i = 0; i < index; i++) {
            System.out.println(i + 1 + ". " + storage[i]);
        }
    }

}
