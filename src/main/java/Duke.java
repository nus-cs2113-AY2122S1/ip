
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Welcome Message

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("--------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");
        System.out.println("--------------------");

        // Echo Chamber

        boolean isBye = false;
        String input;
        Scanner in = new Scanner(System.in);
        int counter = 0;
        Task[] list = new Task[100];

        while(!isBye){
            input = in.nextLine();
            if(!input.equals("bye") && !input.equals("list") && !input.contains("done") ){
                //simply echo and register input into list
                System.out.println("--------------------");
                System.out.println("added: " + input);
                System.out.println("--------------------");
                Task t = new Task(input);
                list[counter] = t;
                counter += 1;
            } else if (input.equals("list")) {
                //prints the list
                System.out.println("--------------------");
                if (list[0] != null){
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < counter; i += 1){
                        System.out.println(i + 1 + ".[" + list[i].getStatusIcon() + "] " + list[i].getDescription() );
                    }
                } else {
                    //when list is empty and input contains 'list'
                    System.out.println("List is empty. Time to get productive!");
                }
                System.out.println("--------------------");
            } else if (input.contains("done") && list[0] != null ) {
                //when input contains done
                System.out.println("--------------------");
                int donePos = input.indexOf("done"); //finds pos of 'done'
                //when input contains done but no number
                if(input.length() < donePos + 5){
                    System.out.println("Please specify which task is done.");
                    break;
                }
                //when input contains done and specified number
                String itemNumDone = input.substring(donePos + 5, donePos + 6);
                int itemNum = Integer.parseInt(itemNumDone);
                list[itemNum - 1].setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println( itemNum + ".[" + list[itemNum - 1].getStatusIcon() + "] " + list[itemNum - 1].getDescription() );
                System.out.println("--------------------");
            } else if (input.contains("done") && list[0] == null) {
                //when list is empty and input contains 'done'
                System.out.println("--------------------");
                System.out.println("Unable to tick off list.");
                System.out.println("List is empty. Time to get productive!");
                System.out.println("--------------------");
            } else if (input.equals("bye")){
                //when input says bye
                isBye = true;
            }
        }

        // Goodbye Message

        System.out.println("--------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("");
        System.out.println("--------------------");
    }
}
