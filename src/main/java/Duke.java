import java.util.Scanner;

public class Duke {
    private static int entryCount = 0;
    private static String[] entries = new String[100];

    public static void reply(String input) {
        if (input.equals("list")) {
            getAndPrintList();
        } else {
            printReply(addEntry(input));
        }
    }

    public static void printReply(String input) {
        System.out.println("   ____________________________________________________________\n" +
                "       " + input + "\n" +
                "   ____________________________________________________________");
    }

    public static String addEntry(String input) {
        entries[entryCount] = input;
        entryCount++;
        return "added: " + input;
    }

    public static void getAndPrintList() {
        //String fullList = "";
        int i = 0;

        System.out.println("   ____________________________________________________________");
        for (String entry : entries) {
            if (i >= entryCount) {
                System.out.println("   ____________________________________________________________");
                return;
            }
            //fullList += Integer.toString(i) + ". " + entry + "\n";
            System.out.println("       " + i + ". " + entry);
            i++;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greetStart = "   ____________________________________________________________\n" +
                "       Hello! I'm Duke\n" +
                "       What can I do for you?\n" +
                "   ____________________________________________________________";

        String greetEnd = "   ____________________________________________________________\n" +
                "       Bye. Hope to see you again soon!\n" +
                "   ____________________________________________________________";

        System.out.println(greetStart);
        line = in.nextLine();
        while (!line.equals("bye")) {
            reply(line);
            line = in.nextLine();
        }
        System.out.println(greetEnd);
    }
}
