import java.util.Scanner;

public class Duke {
    private static final String divisionLine = "____________________________________________________________\n";
    private static final String greetings = divisionLine + " Hello! I'm Duke\n" +
            " What can I do for you?\n" + divisionLine;
    private static final String bye = divisionLine + " Bye. Hope to see you again soon!\n" + divisionLine;

    public static void main(String[] args) {
        System.out.println(greetings);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(divisionLine + " " + userInput + "\n" + divisionLine);
            userInput = sc.nextLine();
        }
        System.out.println(bye);
    }
}
