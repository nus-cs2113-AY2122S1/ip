import java.util.Scanner;

public class Duke {
    private static Task[] items = new Task[100];
    private static int i = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String border = "____________________________________________________________\n";
        System.out.println(border + "Hi bro, my name is Echo");
        System.out.println("What do you want?\n" + border);
        System.out.println("Type bye to exit\n" + border);
        String line;

        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.matches("list")) {
                int j = 1;
                System.out.println(border);
                for (Task item : items) {
                    if (item != null) {
                        System.out.println(j + ".[" + item.getStatusIcon() + "] " + item.getDescription());
                        j++;
                    }
                }
                System.out.println(border);

            } else if (line.contains("done")) {
                int dividerPosition = line.indexOf(" ") + 1;
                int endPosition = line.length();
                if (endPosition > 5) {
                    String num = line.substring(dividerPosition, endPosition);
                    int taskNum = Integer.parseInt(num) - 1;
                    items[taskNum].markDone();
                    System.out.println(border + "Nice! task is done " + '\n' + border);
                }
            } else if (!line.matches("bye")) {
                System.out.println(border + "added: " + line + '\n' + border);
                Task newItem = new Task(line);
                items[i] = newItem;
                i++;
            }
        }
        while (!line.matches("bye"));
        System.out.println(border);
        System.out.println("chat again next time!\n" + border);
    }
}
