import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] list = new String[100];
        int listIndex = 0;

        String logo = " \n" +
                "                        .-\"\"\"-.\n" +
                "                       / .//\". \\\n" +
                "                       \\/ o o \\/\n" +
                "                       ( \\___/ )\n" +
                "          ________oooo__\\_____/_____________\n" +
                "         /                                  \\\n" +
                "        |         Hello! I'm Bobby :)        |\n" +
                "        |     What can I can do for you?     |\n" +
                "         \\______________________oooo________/\n" ;
        System.out.println(logo);
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equals("bye")) {
            // if user inputs list, showcase the list
            if (input.equals("list")){
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < listIndex; i ++){
                    System.out.printf("    %s\n", list[i]);

                }
                System.out.println("    ____________________________________________________________");
            }
            // else is a task to be added to list
            else {
                System.out.println("    ____________________________________________________________");
                System.out.printf("    Okay I've added %s into the list!", input);
                System.out.println("\n    ____________________________________________________________");
                list[listIndex] = String.format("%d. %s", listIndex + 1, input);
                listIndex ++;
            }

            //inserting tasks into the list
//            list[listIndex] = String.format("%d. %s", listIndex + 1, input);
//            listIndex ++;
            input = in.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.print("    ");
        System.out.println("Bye :(, my friend. hehe");
        System.out.println("    ____________________________________________________________");

    }
}
