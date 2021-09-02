import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Task[] todo = new Task[100];
        int todo_index = 0;
        while (true) {
            if (input.substring(0, 4).equals("done")) {
                int i = Integer.parseInt(input.substring(5));
                todo[i - 1].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(todo[i - 1].toString());
                System.out.println("____________________________________________________________");
            } else {
                switch (input) {
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < todo_index; i++) {
                        System.out.println(i + 1 + ". " + todo[i].toString());
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "blah":
                    logo = "____________________________________________________________\n"
                            + "blah\n"
                            + "____________________________________________________________\n";
                    System.out.println(logo);
                    break;
                case "bye":
                    logo = "____________________________________________________________\n"
                            + "Bye. Hope to see you again soon!\n"
                            + "____________________________________________________________\n";
                    System.out.println(logo);
                    break;
                default:
                    if(input.substring(0, 4).equals("todo")){
                        String description = input.substring(5);
                        todo[todo_index] = new Task(description);
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(todo[todo_index].toString());
                        todo_index += 1;

                    }else if(input.startsWith("deadline")){
                        String description = input.substring(input.indexOf("deadline") + 9, input.indexOf("/by") - 1);
                        String by = input.substring(input.indexOf("/by") + 4);
                        todo[todo_index] = new Deadline(description, by);
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(todo[todo_index].toString());
                        todo_index += 1;
                    }else if(input.startsWith("event")){
                        String description = input.substring(input.indexOf("event") + 6, input.indexOf("/at") - 1);
                        String at = input.substring(input.indexOf("/at") + 4);
                        todo[todo_index] = new Event(description, at);
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(todo[todo_index].toString());
                        todo_index += 1;
                    }
                    System.out.println("Now you have "+ todo_index  +" tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                }
            }
            input = sc.nextLine();
        }
    }
}
