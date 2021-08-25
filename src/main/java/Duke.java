import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greet);

        String exit = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        Scanner in = new Scanner(System.in);
        String line;
        String[] list = new String[100];
        int listCount = 0;

        do{
            line = in.nextLine();
            if (line.toLowerCase().equals("bye")){
                System.out.println(exit);
                break;
            } else if (line.toLowerCase().equals("list")){
                System.out.println("____________________________________________________________\n");
                for(int i = 0; i < listCount; i++){
                    System.out.println((i+1) + ". " + list[i]);
                }
                System.out.println("____________________________________________________________\n");
                continue;
            }
            System.out.println("____________________________________________________________\n"
                    + "added: " + line + "\n"
                    + "____________________________________________________________\n");
            list[listCount] = line;
            listCount++;
        } while (!line.toLowerCase().equals("bye"));

    }
}
