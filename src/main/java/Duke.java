import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        String line;
        String[] list = new String[100];
        int count = 0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!(line.equals("bye"))){
            if(line.equals("list")){
                int num = 1;
                for(String value : list){
                    if(value==null){
                        break;
                    }
                    System.out.println(num + ". " + value);
                    num++;
                }
            }
            System.out.println("added: " + line);
            list[count] = line;
            count++;
            line = in.nextLine();
        }

        System.out.println(" Bye. Hope to see you again soon!");
    }
}
