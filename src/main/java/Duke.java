import java.util.Arrays;
import java.util.Scanner;

public class Duke {

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

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
        choose.printHorizontalLine();
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
                checkMarked = choose.checkMarker(tobeCheckeds, doneNumber, counter + 1);
                String[] listeds = choose.taskLister(todos);
                System.out.println("Here are the tasks in your list:");
                for (String listed : listeds) {
                    System.out.println(listedcount + "." + "[" + checkMarked[listedcount] + "]" + "[" + checkMarked[listedcount] + "]" + " " + listed);
                    listedcount++;
                }
                tobeCheckeds = checkMarked;
                choose.printHorizontalLine();
            } else if (line.equals("list")) {
                //String[] listeds = taskLister(todos);
                choose.list();
            } else {
                choose.printHorizontalLine();
                System.out.println("added: " + line);
                choose.printHorizontalLine();
                choose.addTask(line);
//                todos[counter] = line; //save text input to array
//                counter++;  // prepare to take in the next array item

            }
            choose.printHorizontalLine();

        }


        //printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println();
        choose.printHorizontalLine();

    }
}

