import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println( logo +
                "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        int listIndex = 0;
        while(true) {
            String echo = in.nextLine();
            String echoLower = echo.toLowerCase();
            if (echoLower.equals("bye")) {
                System.out.println(
                        "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________");
                break;
            }else if(echoLower.equals("list")) {
                for(int i = 0; i < listIndex; i ++){
                    System.out.println((i + 1) + ". " + list[i].toString());
                }
            }else if(echoLower.equals("done")) {
                int taskDone = Integer.parseInt(in.nextLine());
                list[taskDone - 1].markAsDone();
                for (int i = 0; i < listIndex; i++) {
                    System.out.println((i + 1) + ". " + list[i].toString());
                }
            }else if(echoLower.startsWith("event")){
                int startOfTime = echoLower.indexOf("/");
                String description = echoLower.substring(6,startOfTime);
                String time = echoLower.substring(startOfTime + 1);
                Task t = new Event(description, time);
                list[listIndex] = t;
                listIndex += 1;
                System.out.println("Got it. I've added this task: " + System.lineSeparator() + t.toString() + System.lineSeparator() + "Now you have " + listIndex+" tasks in the list.");
            }else if (echoLower.startsWith("todo")){
                String description = echoLower.substring(5);
                Task t = new Todo(description);
                list[listIndex] = t;
                listIndex += 1;
                System.out.println("Got it. I've added this task: " + System.lineSeparator() + t.toString() + System.lineSeparator() + "Now you have " + listIndex +" tasks in the list.");
            }else if(echoLower.startsWith("deadline")){
                int startOfDeadline = echoLower.indexOf("/");
                String description = echoLower.substring(9,startOfDeadline);
                String deadline = echoLower.substring(startOfDeadline + 1);
                Task t = new Deadline(description, deadline);
                list[listIndex] = t;
                listIndex += 1;
                System.out.println("Got it. I've added this task: " + System.lineSeparator() + t.toString() + System.lineSeparator() + "Now you have " + listIndex +" tasks in the list.");
            }
        }
    }
}
