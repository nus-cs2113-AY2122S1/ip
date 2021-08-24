import java.util.Scanner;

public class Duke {
    private static void handleOneInputLine(String line) {
        System.out.println(line);
    }

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        String greeting = "Hello! I'm Duke";
        String assist = "What can I do for you?";
        String farewell = "Bye. Hope to see you again soon!";
        String hline = "_".repeat(61);
        System.out.println(hline);
        System.out.println(greeting);
        System.out.println(assist);
	    while (sc.hasNextLine()) {
	        handleOneInputLine(sc.nextLine());
	    }
        System.out.println(hline);
        System.out.println(farewell);
        System.out.println(hline);
    }
}
