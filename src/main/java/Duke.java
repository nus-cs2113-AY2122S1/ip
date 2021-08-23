import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------------------------------");

        String input = "";
        int i = 0;
        Task[] tasks = new Task[100];

        do {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            System.out.println("---------------------------------------------------------------------");

            if (!input.equals("bye")) {
                if (!input.equals("list")) {
                    tasks[i] = new Task(input);
                    System.out.println("added: " + input);
                    System.out.println("---------------------------------------------------------------------");
                    i++;
                } else {
                    for (int j = 0; j < i; j++){
                        System.out.println(j+1 + ". " + tasks[j].description);
                    }
                    System.out.println("---------------------------------------------------------------------");
                }
            }
        } while (!input.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------------------------------");
    }
}
