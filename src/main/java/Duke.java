import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String todo [] = new String[100];
        int todo_index = 0;
        while(true){
            switch (input){
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < todo_index; i++) {
                        System.out.println(i + 1 + ". " + todo[i]);
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "blah":
                    logo ="____________________________________________________________\n"
                            + "blah\n"
                            + "____________________________________________________________\n";
                    System.out.println(logo);
                    break;
                case "bye":
                    logo = "____________________________________________________________\n"
                            + "Bye. Hope to see you again soon!\n"
                            + "____________________________________________________________\n";
                    System.out.println(logo);
                    break;
                default:
                    todo[todo_index] = input;

                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + input);
                    System.out.println("____________________________________________________________");

                    todo_index+=1;
                    break;
            }
            input = sc.nextLine();
        }
    }
}
