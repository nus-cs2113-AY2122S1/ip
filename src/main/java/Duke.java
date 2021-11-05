import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! Im Duke");
        System.out.println("What can I do for you?");
        System.out.println("Input 1 for greet, echo, exit");
        System.out.println("Input 2 for Add,List");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        sc.nextLine();
        switch(input){
            case 1:
                greet();
                break;
            case 2:
                addList();
                break;
            default:
                System.out.println("No such input");
                break;
        }

        System.out.println("Bye> Hope to see you again soon");
    }

    public static void greet(){
        Scanner sc = new Scanner(System.in);
        String input = new String();
        while (!input.toLowerCase().endsWith("bye")){
            input = sc.nextLine();
            System.out.println(input);
        }
    }

    public static void addList() {
        Scanner sc = new Scanner(System.in);
        String input = new String();
        String[] list = new String[100];
        int i=0,y;
        while(!input.toLowerCase().endsWith("list")){
            System.out.println("Enter phrases");
            input = sc.nextLine();
            list[i]=input;
            i++;
        }
        String[] updatedArray = new String[i-1];

        for (y=0; y<i;y++){
            System.out.println(list[y]);
            updatedArray[y] = list[y];
        }
    }
}