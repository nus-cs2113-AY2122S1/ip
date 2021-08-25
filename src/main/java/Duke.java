import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Implementing Increment Level-0: Initial skeleton of Duke
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        while(true){
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(!line.split(" ")[0].equals("bye")){
                System.out.println(line);
            }else{
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
