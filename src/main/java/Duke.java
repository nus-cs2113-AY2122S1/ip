import java.util.Scanner;

public class Main {

    private static String[] l = new String[100];
    private static int listSize = 0;

    public static void addToList(String input) {
        l[listSize] =  input;
        listSize++;
        System.out.println("    ____________________________________________________________\n"+
                "Added: "+input + "\n" +
                "    ____________________________________________________________\n");
    }

    public static void printItems(){
        if (listSize == 0){
            System.out.println("The list is empty.");
        }
        else{
            System.out.println("    ____________________________________________________________\n");
            for (int i = 1; i<=listSize; i++){
                System.out.println(i + ". " + l[i-1]);
            }
            System.out.println("    ____________________________________________________________\n");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        Scanner input = new Scanner(System.in);
        while (true){
            String str = input.nextLine();
            if (str.equals("bye")){
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n");
                break;
            }
            else if (str.equals("list")){
                printItems();
            }
            else {
                addToList(str);
            }
        }


    }
}