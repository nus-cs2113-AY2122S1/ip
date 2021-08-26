import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        int listSize = 100;
        String[]lineStorage = new String[100];
        int currentIndex = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        while (lineStorage[currentIndex] != "bye") {
            currentIndex++;
            Scanner in = new Scanner(System.in);
            lineStorage[currentIndex] = in.nextLine();
            System.out.println("____________________________________________________________\n");
            if(lineStorage[currentIndex].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________\n");
                return;
            }
            if(lineStorage[currentIndex].equals("list")) {
                for(int i = 1; i < currentIndex; i++){
                    System.out.println(i + ". " + lineStorage[i]);
                }
                System.out.println("____________________________________________________________\n");
            }else{
                System.out.println("added: " + lineStorage[currentIndex]);
                System.out.println("____________________________________________________________\n");
            }   
        }
    }
}
