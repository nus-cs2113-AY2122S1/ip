import java.util.Scanner;

public class Duke {
    public static void greeting(boolean isStart) {
        if (isStart) {
            //        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
            String greeting = "____________________________________________________________\n" +
                    " Hello! I'm Duke\n" +
                    " What can I do for you?\n" +
                    "____________________________________________________________\n";
            System.out.println(greeting);
        }
    }

    public static void echo(String x) {
        String output = "____________________________________________________________\n" + " " +
                 x + "\n" +
                "____________________________________________________________\n";
        System.out.println(output);
    }
    public static void main(String[] args) {
        greeting(true);
        Scanner sc = new Scanner(System.in);
        String[] List = new String[100];
        String LINES = "____________________________________________________________\n";
        int listCount = 0;
        boolean isBye = false;
        while (!isBye) {
            String input = sc.nextLine();
            //echo function
            if (input.equals("echo")) {
                System.out.println(LINES + " echoing begins\n" + LINES);
                boolean echoState = true;
                while (echoState) {
                    String s = sc.nextLine();
                    if (s.equals("quit")) {
                        System.out.println(LINES + " quit echoing\n" + LINES);
                        echoState = false;
                    }
                    else {
                        echo(s);
                    }
                }
            }
            else if (input.equals("bye")) {
                String byeMsg = LINES +
                        " Bye. Hope to see you again soon!\n" +
                        LINES;
                System.out.println(byeMsg);
                isBye = true;
            }
            else if (input.equals("list")){
                System.out.print(LINES);
                for (int i = 0; i < listCount; i++) {
                    int j = i + 1; //
                    System.out.println(j + ". " + List[i]);
                }
                System.out.print(LINES);
            }
            else {
                List[listCount] = input;
                listCount += 1;
                String addMsg = LINES + " added: " + input + "\n" + LINES;
                System.out.println(addMsg);
            }
        }
    }
}
