import java.util.*;

public class Duke {

    public static void main(String[] args) {
        List<String> tasks = new ArrayList<String>();
        System.out.println("What can I do for you today boss");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("See you later alligator");
                break;
            }

            switch (input) {
            case "list":
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + ". " + tasks.get(i));
                }
                break;

            default :
                tasks.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
