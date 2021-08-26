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
                    " HeLLO! I'm Jim, a real person who definitely passes reCaptchas!\n" +
                    " What can I do for you today?\n" +
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
        Task[] List = new Task[100];
        String LINES = "____________________________________________________________\n";
        int listCount = 0;
        boolean isBye = false;
        while (!isBye) {
            String input = sc.nextLine();
            //echo function
            if (input.equalsIgnoreCase("echo")) {
                System.out.println(LINES + " Echoing after you!\n" + LINES);
                boolean echoState = true;
                while (echoState) {
                    String s = sc.nextLine();
                    if (s.equalsIgnoreCase("quit")) {
                        System.out.println(LINES + " That was annoying huh...\n" + LINES);
                        echoState = false;
                    } else {
                        echo(s);
                    }
                }
            }
            //birthday
            else if (input.contains("birthday")) {
                System.out.println(LINES + " ^o^ Happy birthday to you! ^o^\n" + LINES);
            }
            //exit function
            else if (input.equalsIgnoreCase("bye")) {
                String byeMsg = LINES +
                        " Bye! Remember, stay out of fire, suuuuuuper high level tactic yea?\n" +
                        LINES;
                System.out.println(byeMsg);
                isBye = true;
            }
            //show list
            else if (input.equalsIgnoreCase("list")) {
                System.out.print(LINES);
                for (int i = 0; i < listCount; i++) {
                    int j = i + 1;
                    System.out.println(j + ".[" + List[i].getStatusIcon() + "] " + List[i].getDescription());
                }
                System.out.print(LINES);
            }
            //mark as done
            else if (input.startsWith("done ")) {
                //isolate 'x' from 'done x', where x is a number
                int index = Integer.parseInt(input.substring(5));
                if (index > listCount) {
                    System.out.println(LINES + "No such task! You're not THAT productive...\n" + LINES);
                }
                else {
                    List[index - 1].markAsDone();
                    System.out.print(LINES);
                    System.out.println("Nice! You're a real champ for finishing this: \n" + "   [" +
                            List[index - 1].getStatusIcon() + "] " + List[index - 1].getDescription());
                    System.out.print(LINES);
                }
            }
            //adding to list
            else {
                List[listCount] = new Task(input);
                listCount += 1;
                String addMsg = LINES + " added: " + input + "\n" + LINES;
                System.out.println(addMsg);
            }
        }
    }
}