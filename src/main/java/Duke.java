import java.util.Scanner;

public class Duke {

    static String[] addtoList(String[] list, int size, String input) {
        String[] newlist = new String[size];
        if (size == 1) {
            newlist[0] = "[ ] " + input;
        } else {
            for (int i = 0; i < (size-1); i++) {
                newlist[i] = list[i];
            }
            newlist[size - 1] = "[ ] " + input;
        }
        return newlist;
    }

    public static void main(String[] args) {
        int size = 0;
        String[] list = new String[size];

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("type in your command please");
        System.out.println("____________________________________________________________");
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        while (true) {
            if (command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.equals("echo")){
                System.out.println("____________________________________________________________");
                System.out.println("Enter echo mode! please note that typing quit will exit the mode");
                System.out.println("____________________________________________________________");
                while (true) {
                    String echo = in.nextLine();
                    if (echo.equals("quit")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("exit current mode");
                        System.out.println("____________________________________________________________");
                        break;
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println(echo);
                        System.out.println("____________________________________________________________");
                    }
                }
                System.out.println("type in your command please");
                System.out.println("____________________________________________________________");
                command = in.nextLine();
            } else if (command.equals("add to list")) {
                while (true) {
                    System.out.println("____________________________________________________________");
                    System.out.println("what do you want to add? please note that typing quit will exit this mode");
                    System.out.println("____________________________________________________________");
                    String add = in.nextLine();
                    if (add.equals("quit")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("exit current mode");
                        System.out.println("____________________________________________________________");
                        break;
                    } else {
                        size++;
                        String[] transit = new String[size];
                        transit = addtoList(list, size, add);
                        list = new String[size];
                        for (int i = 0; i < size; i++) {
                            list[i] = transit[i];
                        }
                        System.out.println("____________________________________________________________");
                        System.out.println("added: " + add);
                    }
                }
                System.out.println("type in your command please");
                System.out.println("____________________________________________________________");
                command = in.nextLine();
            } else if (command.equals("show list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here is your list:");
                for (int i = 1; i <= size; i++) {
                    System.out.println(i + ". " + list[i - 1]);
                }
                System.out.println("____________________________________________________________");
                System.out.println("type in your command please");
                System.out.println("____________________________________________________________");
                command = in.nextLine();
            } else if (command.startsWith("done ")) {
                String no_of_string = command.substring(5);
                int no_of_int = Integer.parseInt(no_of_string);
                if (no_of_int <= size) {
                    list[no_of_int-1] = list[no_of_int-1].replace("[ ]", "[√]");
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list[no_of_int-1]);
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Ops! This is not in your list!");
                }
                System.out.println("____________________________________________________________");
                System.out.println("type in your command please");
                System.out.println("____________________________________________________________");
                command = in.nextLine();
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("invalid command, pls try again");
                System.out.println("____________________________________________________________");
                command = in.nextLine();
            }
        }
    }
}

