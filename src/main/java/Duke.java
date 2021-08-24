import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_____________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________________________");
        task();

    }

    public static void task(){
        int count = 0; //number of task
        String[] task = new String[100];
        Scanner in = new Scanner(System.in);
        String inputCommand = in.nextLine();
        while(!(inputCommand.equals("bye"))){
            if(inputCommand.equals("list")){
                System.out.println("_____________________________________________");
                for(int i = 0; i< count; i++){
                    System.out.println((i+1) + "." +task[i]);
                }
                System.out.println("_____________________________________________");
            }

            else{
                System.out.println("_____________________________________________");
                task[count] = inputCommand;
                count++;
                System.out.println("added: " + inputCommand);
                System.out.println("_____________________________________________");
            }
            inputCommand = in.nextLine();
        }
        System.out.println("_____________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_____________________________________________");
    }


}
