import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String border = "____________________________________________________________\n";
        System.out.println(border + "Hi bro, my name is Echo");
        System.out.println("What do you want?\n" + border);
        System.out.println("Type bye to exit\n" + border);
        String line;
        String[] items = new String[100];
        int i = 0;
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.matches("list")) {
                int j = 1;
                System.out.println(border);
                for (String item : items) {
                    if (item != null) {
                        System.out.println(j + ". " + item);
                        j++;
                    }
                }
                System.out.println(border);
            } else if (!line.matches("bye")) {
                System.out.println(border + "added: " + line + '\n' + border);
                items[i] = line;
                i++;
            }
        }
        while (!line.matches("bye"));
        System.out.println(border);
        System.out.println("chat again next time!\n" + border);
    }
}
