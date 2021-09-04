import java.util.Scanner;
import java.lang.String;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n");

        list.initializing();
        Scanner input = new Scanner(System.in);
        while (true) {
            String str = input.nextLine();
            //input = NULL
            if (str.trim().equals("")) {
                continue;
            }
            //input = bye
            if (str.equals("bye")) {
                System.out.println("____________________________________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "____________________________________________________________\n");
                break;
            }
            //input = list
            else if (str.equals("list")) {
                System.out.println("____________________________________________________________\n" +
                        "Here are the tasks in your list: ");
                list.printItems();
                System.out.println("____________________________________________________________");
            }
            //input = Done xx
            else if (str.contains("Done ") == true){
                String nums = "";
                for (int i=0;i<str.length();i++){
                    char c = str.charAt(i);
                    if (Character.isDigit(c) == true){
                        nums += c;
                    }
                }
                int index = Integer.parseInt(nums);
                if (index > list.listSize){
                    System.out.println("____________________________________________________________\n" +
                            "The index is out of range.\n" +
                            "____________________________________________________________\n");
                }
                else{
                    list.mark(index);
                    System.out.println("____________________________________________________________\n" +
                            "Nice! I've marked this task as done: ");
                    list.printMarkedItems();
                    System.out.println("____________________________________________________________");
                }
            } else {
                //input = task
                list.addToList(str);
            }
        }
    }
}