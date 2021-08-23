import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("**************************************************");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("**************************************************");
        String[] userList = new String[100];
        Scanner scanner = new Scanner(System.in);
        int x = 0;
        String userInput = "";
        while(!userInput.equals("bye")){
            userInput = scanner.nextLine();
            System.out.println("**************************************************");
            if(userInput.equals("list")){
                for(int i = 0; i < x; i++){
                    System.out.println((i+1) + ". " + userList[i]);
                }
                System.out.println("**************************************************");
            } else if(!userInput.equals("bye")){
                userList[x] = userInput;
                System.out.println("Added: "+ userInput);
                x++;
                System.out.println("**************************************************");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("**************************************************");
    }
}
