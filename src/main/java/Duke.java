import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<String> items = new ArrayList<String>();
    private static String divider = "\t__________________________________________________________";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Greeting.openingGreet();
        talkToUser();
        Greeting.closingGreet();
    }
    private static void talkToUser() {
        Scanner in=new Scanner(System.in);
        String userInput;
        do {
            System.out.println();
            userInput = in.nextLine();
            System.out.println(divider);
            if(userInput.equals("list")){
                printList();
                continue;
            }
            else if (userInput.equals("bye")){
                break;
            }
            addToList(userInput);
            System.out.println("\tadded: " + userInput);
            System.out.println(divider);
        } while(true);
    }
    private static void addToList(String userInput){
        items.add(userInput);
    }
    private static void printList(){
        for (int i=0; i<items.size();i++){
            System.out.println((i+1)+". "+items.get(i));
        }
        System.out.println(divider);
    }
}
