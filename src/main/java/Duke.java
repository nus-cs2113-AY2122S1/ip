import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("...................................................");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("...................................................");
        Scanner in = new Scanner(System.in);
        String lineIn = "";
        String[] listIn = new String[100];
        int listCounter = 0;
        while(!lineIn.equals("bye")){
            lineIn = in.nextLine();
            System.out.println("...................................................");
            if(lineIn.equals("list")){
                for(int i = 0; i < listCounter; i++){
                    System.out.println((i+1) + ". " + listIn[i]);
                }
                System.out.println("...................................................");
            } else {
                listIn[listCounter] = lineIn;
                System.out.println("added: "+ lineIn);
                listCounter++;
                System.out.println("...................................................");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("...................................................");
    }
}
