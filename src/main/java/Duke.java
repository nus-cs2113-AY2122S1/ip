import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String dashes = "____________________________________________________________\n";
        System.out.println(logo);
        System.out.println(dashes +
                " Hello! I'm Duke!\n" +
                " I don't have many functions now but I recently bought a new speaker!\n" +
                " Tell me some stuff and I'll repeat them back to you! Type 'bye' to exit.\n" + dashes);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(dashes + line + "\n" + dashes);
            line = in.nextLine();
        }
        System.out.println(dashes +
                " Bye. Do visit next time! Upgrades coming soon :-)\n" +
                dashes);
    }
}
