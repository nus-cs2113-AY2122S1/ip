import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // create a list of tasks
        Task[] list = new Task[100];
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
                System.out.println("    Task List:");
                for (int i = 0; i < listIndex; i ++){
                    System.out.printf("    %d.[%s] %s\n", i + 1, list[i].getStatusIcon(),list[i].getDescription());

                }
                System.out.println("    ____________________________________________________________");
            }

            //if user marks a task as done
            else if (input.startsWith("done")){
                // split the input string into array
                String[] inputSplit = input.split(" ");

                // if theres nothing after done or too many items after done
                if (inputSplit.length != 2)
                    System.out.println("    invalid command :( Please specify a proper task number to be marked as done.");

                // there is something after done
                else {
                    // use try to check if the next word after done is a number, if not throw an error message
                    try {
                        int doneIndex = Integer.parseInt(inputSplit[1]);
                        // if number in the input is not in the range of list, throw error and ask for input again
                        if (doneIndex < 1 || doneIndex > listIndex){
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    invalid command :( task number is not found. Please try again.");
                            System.out.println("    ____________________________________________________________");
                            input = in.nextLine();
                            // skip this iteration after getting input
                            continue;
                        }
                        // no errors, the input is valid
                        else if (list[doneIndex - 1].getIsDone()){
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    Task is already marked as done!");
                            System.out.println("    ____________________________________________________________");
                        }
                        else {
                            list[doneIndex - 1].markAsDone();
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    Alright! I've marked this task as done! :)");
                            System.out.printf("    [%s] %s\n", list[doneIndex - 1].getStatusIcon(), list[doneIndex - 1].getDescription());
                            System.out.println("    ____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Command is not valid :( Please specify a valid task number to be marked as done.");
                        System.out.println("    ____________________________________________________________");
                    }
                }
            }

            // else is a task to be added to list
            else {
                System.out.println("    ____________________________________________________________");
                System.out.printf("    Okay I've added %s into the list!\n", input);
                System.out.println("    ____________________________________________________________");

                //insert input task into list of tasks
                list[listIndex] = new Task(input);
                listIndex ++;
            }

            //get the new input
            input = in.nextLine();
        }

        //if bye is the input
        System.out.println("    ____________________________________________________________");
        System.out.print("    ");
        System.out.println("Bye, my friend :( hehe");
        System.out.println("    ____________________________________________________________");

    }
}
