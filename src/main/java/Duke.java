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
                    System.out.println((i + 1) + ". ["+ list[i].getStatusIcon() + "] " + list[i].getDescription());
                }
            }else if(echoLower.equals("done")) {
                int taskDone = Integer.parseInt(in.nextLine());
                list[taskDone-1].markAsDone();
                for(int i = 0; i < listIndex; i ++){
                    System.out.println((i + 1) + ". ["+ list[i].getStatusIcon() + "] " + list[i].getDescription());
                }
            }else {
                Task t = new Task(echoLower);
                list[listIndex] = t;
                listIndex += 1;
                System.out.println("____________________________________________________________\n" + "added: " + echo + System.lineSeparator() + "____________________________________________________________");
            }
        }
    }
}
