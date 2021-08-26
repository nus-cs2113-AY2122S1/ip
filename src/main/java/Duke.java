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
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            System.out.println(border + line + '\n' + border);
        }
        while(!line.matches("bye"));
            System.out.println("chat again next time!\n" + border);
        }

       // System.out.println("Hello from\n" + logo);
}
