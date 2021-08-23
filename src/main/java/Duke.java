import java.util.Scanner;

public class Duke {
    public static void line(){
        System.out.println("____________________________________________________________");
    }
    private static Boolean isFinished = false;
    private static int itemCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] items = new String[100];

        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line();
        System.out.println();

        while(!isFinished){
            String userInput = sc.nextLine();
            if (userInput.equals("bye")){
                isFinished = true;
                break;
            }
            else if (userInput.equals("list")){
                for (int i = 0; i < itemCount; i++){
                    System.out.println("\t" + (i+1) + ". " + items[i]);
                }
            }
            else {
                items[itemCount] = userInput;
                System.out.println("\tadded: " + userInput);
                itemCount++;
            }
        }

        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();

    }
}
