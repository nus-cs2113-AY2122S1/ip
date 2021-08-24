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
        while(true){
            switch (input){
                case "list":
                    logo = "____________________________________________________________\n"
                    + "list\n"
                    + "____________________________________________________________\n";
                    System.out.println(logo);
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
                    break;
            }
            input = sc.nextLine();
        }
    }
}
