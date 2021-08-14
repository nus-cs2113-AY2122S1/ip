import java.sql.SQLOutput;

public class Duke {
    final static String line = "____________________________________________________________";
    public static void main(String[] args) {
        System.out.println(line);
        printWelcomeMesage();
        printExitMessage();
    }
    public static void printExitMessage(){
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
    public static void printWelcomeMesage(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }
    public static void printLogoMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
    }
}
