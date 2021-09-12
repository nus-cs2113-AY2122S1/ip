import java.util.Arrays;
import java.util.Scanner;

public class Duke {
        public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
        choose.printHorizontalLine();
        String line = "";
        Scanner in = new Scanner(System.in);

        while (!(line.equals("bye") || line.equals("Bye"))) {
            line = in.nextLine();
            line = line.toLowerCase();

            if (line.equals("list")) {
                choose.list();
            } else if((line.split(" ")[0].equals("done"))){
                int doneNumber= Integer.parseInt((line.split(" ")[1]));
                choose.setDone(doneNumber);
            } else if (line.split(" ")[0].equals("deadline") && line.contains("/by")) {
                choose.setDeadline(line.split("deadline ")[1], line.split("/by")[1]);
            }else if (line.split(" ")[0].equals("event")&& line.contains("/at")) {
                choose.setEvent(line.split("event ")[1], line.split("/at")[1]);
            }else if ((line.split(" ")[0].equals("todo"))){
                choose.setTodo(line.split("todo ")[1]);
            }
            else {
                choose.printHorizontalLine();
                System.out.println("added: " + line);
                choose.addTask(line);
            }
            choose.printHorizontalLine();

        }
        //printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println();
        choose.printHorizontalLine();

    }
}

