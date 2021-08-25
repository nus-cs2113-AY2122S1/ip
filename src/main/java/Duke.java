import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String greetings = " Hello! I'm Duke\n" + " What can I do for you?\n";
        System.out.println(greetings);
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        boolean flag = true;
        do{
            String str= sc.nextLine();
            if(str.equals("bye")){
                str = "Bye. Hope to see you again soon!";
                flag = false;
            }
            int length = str.length();
            System.out.print("      ");
            for(int i = 0; i < length; i++) System.out.print("_");
            System.out.println("");
            System.out.println("     |" + str + "|");
            System.out.print("     |");
            for(int i = 0; i < length; i++) System.out.print("_");
            System.out.println("|");
        }while(flag);
    }
}
