import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line + "\n Hello! I'm Duke\n What can I do for you?\n" + line);
        Boolean isCompleted = false;
        Task[] list = new Task[100];
        int numItemsAdded = 0;
        while (!isCompleted) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] inputWords = input.split(" ");
            if (input.toLowerCase().equals("bye")) {
                isCompleted = true;
                in.close();
                continue;
            }
            if (input.toLowerCase().equals("list")){
                System.out.println(line);
                if (list[0] == null) {
                    System.out.println("No items added!\n");
                }
                int i = 0;
                while (list[i] != null) {
                    String completed = " ";
                    if (list[i].isDone()) {
                        completed = "X";
                    }
                    System.out.println(i+1 + ".[" + completed + "] " + list[i].getName());
                    i += 1;
                }
                System.out.println(line);
                continue;
            }
            if (inputWords[0].equals("done")) {
                if (Integer.parseInt(inputWords[1]) <= numItemsAdded && Integer.parseInt(inputWords[1]) > 0) {
                    if (list[Integer.parseInt(inputWords[1])-1].isDone()) {
                        System.out.println(line + "\nThat task is already done!\n" + line);
                    } else {
                        System.out.println(line + "\nNice! I've marked this task as done:\n[X] " + list[Integer.parseInt(inputWords[1])-1].getName() + "\n" + line);
                        list[Integer.parseInt(inputWords[1])-1].markAsDone();
                    }
                } else {
                    System.out.println("That task does not exist!\n" + line);
                }
                continue;
            }
            list[numItemsAdded] = new Task(input);
            numItemsAdded += 1;
            System.out.println(line + "\nadded: " + input + "\n" + line);
        }
        System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);
    }
}