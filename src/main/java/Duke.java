
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.print("Hello from\n" + logo);

        String line = "----------------------------------\n";

        System.out.println(line + "Good Evening Sir! I'm J.A.R.V.I.S");
        System.out.println("How can I help sir?");

        String echoLine = null;
        Scanner in = new Scanner(System.in);

        while (true) {
            echoLine = in.nextLine();

            if (echoLine.equals("Initiate Power Down Mode - CODE: 10")) {
                System.out.println("CODE ACKNOWLEDGED" + System.lineSeparator() + "Understood sir! I'll close shop for now.");
                break;
            }
            System.out.println(echoLine);
        }
    }
}

