import java.util.*;

public class Duke {
    private static String[] List = new String[100];

    /** echo and store inputs in List **/
    public static void add(String args, int i) {
        List[i] = args;
        System.out.println("added: " + args);
        System.out.println("_____________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        /** greet **/
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");

        /** to read input on each new line, Duke constantly scans input in this loop **/
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while(true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                /** exit **/
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_____________________________");
                break;
            } else if (str.equals("list")) {
                /** list **/
                for (int j = 0; List[j] != null; j++) {
                    System.out.print((j + 1) + ": ");
                    System.out.println(List[j]);
                }
                System.out.println("_____________________________");
            } else {
                add(str, i);
                i++;
                //continue;
            }
        }
    }
}
