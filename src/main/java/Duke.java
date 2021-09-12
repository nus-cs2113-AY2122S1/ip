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
//        String[] todos = new String[100];
//        String[] checkMarked = new String[100];
//        Arrays.fill(checkMarked, " ");
//        String[] tobeCheckeds = new String[100];

        int counter = 0;

        while (!(line.equals("bye") || line.equals("Bye"))) {
            line = in.nextLine();
            line = line.toLowerCase();

            if (line.equals("list")) {
                //String[] listeds = taskLister(todos);
                choose.list();
            } else if((line.split(" ")[0].equals("done"))){
                int doneNumber= Integer.parseInt((line.split(" ")[1]));
                choose.setDone(doneNumber);
            } else if (line.split(" ")[0].equals("deadline")) {
                choose.setDeadline(line.split("deadline ")[1], line.split("/by")[1]);
            }else if (line.split(" ")[0].equals("event")) {
                choose.setEvent(line.split("event ")[1], line.split("/at")[1]);
            }else if ((line.split(" ")[0].equals("todo"))){
                choose.setTodo(line.split("todo ")[1]);
            }




            else {
                choose.printHorizontalLine();
                System.out.println("added: " + line);
                choose.addTask(line);
//                todos[counter] = line; //save text input to array
//                counter++;  // prepare to take in the next array item(
            }
            choose.printHorizontalLine();

        }


        //printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println();
        choose.printHorizontalLine();

    }
}

