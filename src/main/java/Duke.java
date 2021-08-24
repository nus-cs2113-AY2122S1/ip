import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Program currProgram = new Program();


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm HAL 2113");
        System.out.println("What can I do for you? You can enter the following commands\n1. 'task' (replace task with any agenda you wish to add to the list)\n2. list\n3. bye (to exit the program!");

        while (!currProgram.getTerminateHal()) {
            String input;
            Scanner sc= new Scanner(System.in);
            input = sc.nextLine();

            //get rid of white space
            input = input.trim();
            currProgram.executeTask(input);
//            System.out.println(input);

        }
        System.out.println("Bye boss! HAL will always be there to help you!\nSummon me by running this program again");
        System.out.println("____________________________________________________________");
    }
}
