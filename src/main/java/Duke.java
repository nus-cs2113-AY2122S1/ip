import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        echo();

        bye();
    }

    public static void greet(){
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void bye(){
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine(){
        for(int i = 0; i <30 ; i ++){
            System.out.print("-");
        }
        System.out.println();
    }

    public static void echo(){
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("What do you want me to repeat?");
        line = in.nextLine();
        if(line.equals("bye")){
            return;
        }
        System.out.println("You said: " + line);
        echo();
    }

}
