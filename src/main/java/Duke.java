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
                " Hello! I'm Duke, the list robot!\n" +
                " Tell me what you want added! Type 'list' to display all. Type 'bye' to exit.\n" + dashes);

        String line;
        String[] list = new String[100];
        int listCount = 0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            System.out.println(dashes);
            if (line.equals("list")) {
                int itemCount = 1;
                for (String item : list) {
                    if (item != null) {
                        System.out.println(itemCount + ". " + item);
                        itemCount++;
                    }
                }
            } else {
                list[listCount] = line;
                listCount++;
                System.out.println("added: " + line);
            }
            System.out.println(dashes);
            line = in.nextLine();
        }

        System.out.println(dashes +
                " Bye. Do visit next time! More upgrades coming soon :-)\n" + dashes);
    }
}
