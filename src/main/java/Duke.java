import java.util.Scanner;

public class Duke {
    private static String[] text = new String[100];

    public static void addTask(String text, String[] arr, int count){
        arr[count] = text;
        System.out.println("added: " + arr[count]);
    }

    public static void requestList (int count) {
        for (int i = 1; i < count; i++) {
            System.out.println(i + "." + text[i]);
        }
    }

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        int taskCount = 1;
        boolean isBye = false;

        while (!isBye){
            if (input.equalsIgnoreCase(("bye"))){
                isBye = true;
            }
            else if (input.equalsIgnoreCase("list")){
                requestList(taskCount);
                input = in.nextLine();
            }
            else {
                addTask(input, text, taskCount);
                taskCount++;
                input = in.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
