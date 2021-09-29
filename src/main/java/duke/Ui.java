import java.util.Scanner;

public class Ui {
    private static Scanner in = new Scanner(System.in);

    private static void showLine(){
        System.out.println("____________________________________________________________");
    }

    private void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "Hello I'm Duke\n"
                + "What can I do for you?";
        System.out.println("Hello from\n" + logo);
        System.out.println(greet);
    }

    private String readCommand(){
        return in.nextLine();
    }

    private void showError(){
        return;
    }

    private void showBye(){
        String exit = "Bye. Hope to see you again soon!";
        System.out.println(exit);
    }


}
