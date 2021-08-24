import java.util.Scanner;

public class Duke {

    private static void initialise() {
        String greeting = "Hello! I'm Duke";
        String assist = "What can I do for you?";
        System.out.println(greeting);
        System.out.println(assist);
    }
    private enum inputHandleStatus {
        OK, END, ERROR
    }
    private static inputHandleStatus handleOneInputLine(String line) {
        if (line.equals("bye")) {
            return inputHandleStatus.END;
        }
        System.out.println(line);
        return inputHandleStatus.OK;
    }

    private static void finalise() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    public static void main(String[] args) {
        initialise();
	    Scanner sc = new Scanner(System.in);
	    while (sc.hasNextLine()) {
	        switch (handleOneInputLine(sc.nextLine())) {
                case END:
                    finalise();
                    return;
            }
	    }
	    finalise();
    }
}
