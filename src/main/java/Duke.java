import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
    }

    public static void showList(Task list[], int x){
        if(x == 0)  System.out.println("\n____________________________________________________________");
        else{
            System.out.println("\n____________________________________________________________");
            for(int i = 0; i < x; i++){
                System.out.println("[" + list[i].getStatusIcon() + "]" + list[i].getDescription());
            }
            System.out.println("\n____________________________________________________________");
        }
    }

    public static void doneTask(Task list[], String order){
        String dummy = order.trim();
        int index = Integer.parseInt(dummy.substring(5,dummy.length()));
        list[index-1].markAsDone();
        System.out.println("\n____________________________________________________________");
        System.out.println("Nice Work! I've marked it as done");
        System.out.println("[" + list[index-1].getStatusIcon() + "]" + list[index-1].getDescription());
        System.out.println("\n____________________________________________________________");
    }

    public static void greeting () {
        Task list[] = new Task[100];
        int maxcount = 0;

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        String order = getMission();

        while(!order.equals("bye")){
            if (order.equals("list")){
                showList(list, maxcount);
                order = getMission();
                continue;
            }
            else if(order.contains("done")){
                doneTask(list, order);
                order = getMission();
                continue;
            }
            System.out.println("____________________________________________________________");
            System.out.println("added: " + order);
            System.out.println("____________________________________________________________\n");
            list[maxcount] = new Task(order);
            maxcount++;
            order = getMission();
        }
        System.out.println("Bye.Hope to see you again soon!\n");
        System.out.println("____________________________________________________________");
    }

    public static String getMission(){
        String mission;
        Scanner in = new Scanner(System.in);
        mission = in.nextLine();
        return mission;
    }
}
