import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Greeting.openingGreet();
        echo();
        Greeting.closingGreet();
    }
    private static void echo() {
        Scanner in=new Scanner(System.in);
        String divider = "\t__________________________________________________________";
        String userInput;
        do {
            System.out.println();
            userInput = in.nextLine();
            System.out.println(divider);
            if (userInput.equals("bye")){
                break;
            }
            System.out.println("\t" + userInput);
            System.out.println(divider);
        } while(true);
    }
}
