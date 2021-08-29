import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    private static final int LINE_WIDTH = 60;

    //to draw a straight line
    private static void printHorizontalLine() {
        System.out.println("_".repeat(LINE_WIDTH));
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Level0
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
        printHorizontalLine();
        String line = "";
        Scanner in = new Scanner(System.in);

        while(!(
                line.equals("bye") ||
                        line.equals("Bye"))){

            line = in.nextLine();
            printHorizontalLine();
            if(!line.equals("bye" )){
                System.out.println(line);
                printHorizontalLine();}
        }
        //printHorizontalLine();
        System.out.println( "Bye. Hope to see you again soon!");
        System.out.println();
        printHorizontalLine();

    }
}
