import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    private static final int LINE_WIDTH = 60;

    //to draw a straight line
    private static void printHorizontalLine() {
        System.out.println("_".repeat(LINE_WIDTH));
    }

    public static String[] taskLister(String[] todos) {

        int todoCount = 0;
        String[] result = new String[100];
        for (String todo : todos) {
            //System.out.println(word);
            if (todo != null) {
                result[todoCount] = todo;
                todoCount++;
            } else {
                break;

            }
        }
        return Arrays.copyOf(result, todoCount);
    }

    public static boolean splitString(String line) {
        String[] words = line.split(" ");
        for (String word : words) {
            if (word.equals("done")) {
                {

                    return words[1].matches(".*\\d.*");

                }

            } else return false;
        }

        return false;
    }


    public static String[] checkMarker(String[] tobeCheckeds, int doneNumber, int listedcount) {
        String[] checkedMarks = new String[tobeCheckeds.length];
        //int checkMarkCounter = 0;
        for (int i =0; i<listedcount; i++ ) {
            if (tobeCheckeds[i]== null ) { //&& !tobeCheckeds[i].equals("X")
                checkedMarks[i] = " ";
            } else
                {
                    checkedMarks[i] = tobeCheckeds[i];
                }
            }

        checkedMarks[doneNumber] = "X";
        return checkedMarks;
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
        String[] checkMarked = new String[100];
        Arrays.fill(checkMarked, " ");
        String[] tobeCheckeds = new String[100];

        int counter = 0;

        while (!(line.equals("bye") || line.equals("Bye"))) {
            line = in.nextLine();
            line = line.toLowerCase();
            if (splitString(line)) {
                int listedcount = 1;
                int doneNumber = 0; //no choice have to make 0 if not ide wont let me run
                String[] findNumbers = line.split(" ");
                doneNumber = Integer.parseInt(findNumbers[1]);
                checkMarked = checkMarker(tobeCheckeds, doneNumber, counter+1);
                String[] listeds = taskLister(todos);
                System.out.println("Here are the tasks in your list:");
                for (String listed : listeds) {
                    System.out.println(listedcount + "." + "[" + checkMarked[listedcount] + "]" + " " + listed);
                    listedcount++;
                }
                tobeCheckeds=checkMarked;
                printHorizontalLine();
            } else if (line.equals("list")) {
                String[] listeds = taskLister(todos);
                int listedcount = 1;
                for (String listed : listeds) {
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(listedcount + "." + "[" + checkMarked[listedcount] + "]" + " " + listed);
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
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println();
        printHorizontalLine();

    }
}
