import java.util.Scanner;


public class Duke {
    public static final String LINES = "____________________________________________________________\n";

    //Intro message
    public static void greeting(boolean isStart) {
        if (isStart) {
            String greeting = LINES + " HeLLO! I'm Jim, a real person who definitely passes reCaptchas!\n" +
                    " What can I do for you today?\n" + LINES;
            System.out.println(greeting);
        }
    }

    //echo function
    public static void echo(String x) {
        String output = LINES + " " + x + "\n" + LINES;
        System.out.println(output);
    }

    //list function
    public static void list(Task[] tasks) {
        System.out.print(LINES);
        for (int i = 0; tasks[i] != null; i++) {
            int j = i + 1;
            System.out.println(j + "." + tasks[i]);
        }
        System.out.print(LINES);
    }

    //adds task
    public static int addTask(Task[] tasks, String input, int listCount) {
        if (input.toUpperCase().startsWith("TODO")) {
            tasks[listCount] = new Todo(input.substring(5));
            System.out.println(LINES + " Got it. I've added this task:\n   " + tasks[listCount]);
            listCount += 1;
            System.out.println(" Now you have " + listCount + " tasks in the list.\n" + LINES);
        }
        else if (input.toUpperCase().startsWith("DEADLINE")) {
            int slash = input.indexOf("/");
            tasks[listCount] = new Deadline(input.substring(9, slash-1), input.substring(slash+1));
            System.out.println(LINES + " Got it. I've added this task:\n   " + tasks[listCount]);
            listCount += 1;
            System.out.println(" Now you have " + listCount + " tasks in the list.\n" + LINES);
        }
        else if (input.toUpperCase().startsWith("EVENT")) {
            int slash = input.indexOf("/");
            tasks[listCount] = new Event(input.substring(6, slash-1), input.substring(slash+1));
            System.out.println(LINES + " Got it. I've added this task:\n   " + tasks[listCount]);
            listCount += 1;
            System.out.println(" Now you have " + listCount + " tasks in the list.\n" + LINES);
        }
        else {
            System.out.println(LINES + "I don't quite understand :/\n" + LINES);
        }
        return listCount;
    }

    public static void main(String[] args) {
        greeting(true);
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
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
            else if (input.toUpperCase().contains("BIRTHDAY")) {
                System.out.println(LINES + " ^o^ Happy birthday to you! ^o^\n" + LINES);
            }

            //show list
            else if (input.equalsIgnoreCase("list")) {
                list(tasks);
            }

            //mark as done
            else if (input.toLowerCase().startsWith("done ")) {
                //isolate 'x' from 'done x', where x is a number
                int index = Integer.parseInt(input.substring(5));
                if (index > listCount) {
                    System.out.println(LINES + "No such task! You're not THAT productive...\n" + LINES);
                }
                else {
                    tasks[index - 1].markAsDone();
                    System.out.print(LINES + "Nice! You're a real champ for finishing this: \n"
                            + tasks[index-1] + "\n" + LINES);
                }
            }

            //exit function
            else if (input.equalsIgnoreCase("bye")) {
                String byeMsg = LINES +
                        " Bye! Remember, stay out of fire, suuuuuuper high level tactic yea?\n" +
                        LINES;
                System.out.println(byeMsg);
                isBye = true;
            }

            //adding to list
            else {
                int newCount = addTask(tasks, input, listCount);
                listCount = newCount;
            }
        }
    }
}