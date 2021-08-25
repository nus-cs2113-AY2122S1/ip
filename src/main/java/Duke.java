import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String sentence;
            Chat.greet();
            while (true) {
                sentence = sc.nextLine();
                if (sentence.equals("bye")) {
                    Chat.bye();
                    break;
                }
                Chat.parrot(sentence);
            }
        }
    }
}

class Chat {
    static void greet() {
        System.out.printf("____________________________________________________________%n" +
                "Hello! I'm Duke, the parrot.%n" +
                "What can I do for you?%n" +
                "____________________________________________________________%n");
    }

    static void parrot(String sentence) {
        System.out.printf("____________________________________________________________%n" +
                sentence +
                "%n____________________________________________________________%n");

    }

    static void bye() {
        System.out.printf("____________________________________________________________%n" +
                "Bye. Hope to see you again soon!%n" +
                "____________________________________________________________%n");
    }
}
