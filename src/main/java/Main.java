import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner in = new Scanner(System.in);
        String input;
        String[] words;

        duke.greet();
        while (true) {
            input = in.nextLine();
            words = input.split(" ");

            if (input.equalsIgnoreCase("bye")) {
                duke.exit();
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                duke.listTasks();
            } else if (words[0].equalsIgnoreCase("done")) {
                duke.markTaskAsDone(Integer.parseInt(words[1]));
            } else {
                duke.addTask(input);
            }
        }
    }
}
