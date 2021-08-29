import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    private static final int LINE_WIDTH = 60;

    //to draw a straight line
    private static void printHorizontalLine() {
        System.out.println("_".repeat(LINE_WIDTH));
    }

    public static String[] filterAmounts(String [] todos) {
       // String[] words = String todos[];
        int todoCount = 0;
        String[] result = new String[100];
        for (String todo : todos) {
            //System.out.println(word);
            if (todo != null) {
                result[todoCount] = todo;
                todoCount++;
            }
            else {
                break;

            }
        }
        return Arrays.copyOf(result, todoCount);
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

        //initialise the 100 todos items array
        String[] todos = new String[100];


        int counter=0;
        while(!(line.equals("bye") || line.equals("Bye"))) {
            line = in.nextLine();
            line = line.toLowerCase();
            if (line.equals("list")) {
                String[] listeds = filterAmounts(todos);
                int listedcount=1;
                for (String listed : listeds)
                {
                    System.out.println(listedcount + ". "+ listed);
                    listedcount++;
                }
                printHorizontalLine();
            } else if (!line.equals("bye")) {
                printHorizontalLine();
                System.out.println("added: " + line);
                printHorizontalLine();
                todos[counter] = line; //save text input to array
                counter++;  // prepare to take in the next array item
            } else {
                printHorizontalLine();
            }
        }


        //printHorizontalLine();
        System.out.println( "Bye. Hope to see you again soon!");
        System.out.println();
        printHorizontalLine();

    }
}
