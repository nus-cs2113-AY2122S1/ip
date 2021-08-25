import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void printList(String[] list){
        for (int i=0; i < Arrays.stream(list).count(); i++){
            System.out.println((i+1) + ". " + list[i]);
        }
    }

    public static void main(String[] args) {
        //Implementing Increment Level-0: Initial skeleton of Duke
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        String[] list = new String[100];
        int lastItem = 0;
        while(true){
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(line.split(" ")[0].equals("bye")){
                break;
            }else if(line.split(" ")[0].equals("list")){
                printList(Arrays.copyOf(list, lastItem));
            }else{
                System.out.println("added: " + line);
                list[lastItem] = line;
                lastItem++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
