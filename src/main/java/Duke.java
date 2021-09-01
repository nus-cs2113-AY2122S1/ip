import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        int MAXIMUM = 100;
        Task[] task_list = new Task[MAXIMUM];
        int task_num = 0;
        String GREETINGS = "     _______________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "     _______________________\n";

        System.out.println(GREETINGS);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            switch (line.split(" ")[0]) {
            case "list":
                list(task_num, task_list);
                break;
            case "done":
                int current_task = Integer.parseInt(line.split(" ")[1]) - 1;
                task_list[current_task].markAsDone();
                String update = "     _______________________\n"
                        + "     Nice! I've marked this task as done: \n"
                        + "     " + task_list[current_task].toString() + "\n"
                        + "     _______________________\n";
                System.out.println(update);
                break;
            case "deadline":
                String task_name = line.substring("deadline ".length(), line.indexOf("/by "));
                String task_ddl = line.substring(line.indexOf("/by ") + "/by ".length());
                task_list[task_num] = new Deadline(task_name, task_ddl);
                task_num += 1;
                String echo = "     _______________________\n"
                        + "     added: " + task_list[task_num - 1] + "\n"
                        + "     _______________________\n";
                System.out.println(echo);
                break;
            case "event":
                task_name = line.substring("event ".length(), line.indexOf("/at "));
                String task_time = line.substring(line.indexOf("/at ") + "/at ".length());
                task_list[task_num] = new Event(task_name, task_time);
                task_num += 1;
                echo = "     _______________________\n"
                        + "     added: " + task_list[task_num - 1] + "\n"
                        + "     _______________________\n";
                System.out.println(echo);
                break;
            case "todo":
                task_list[task_num] = new ToDo(line);
                task_num += 1;
                echo = "     _______________________\n"
                        + "     added: " + task_list[task_num - 1] + "\n"
                        + "     _______________________\n";
                System.out.println(echo);
                break;
            default:
                task_list[task_num] = new Task(line);
                task_num += 1;
                echo = "     _______________________\n"
                        + "     added: " + task_list[task_num - 1] + "\n"
                        + "     _______________________\n";
                System.out.println(echo);
            }
            line = in.nextLine();
        }

        String Bye = "     _______________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "     _______________________\n";
        System.out.println(Bye);
    }


    public static void list(int task_num, Task[] task_list ) {
        String list_output = "     _______________________\n";
        for (int i = 0; i < task_num; i++) {
            list_output += "     " + (i+1) + ". "
                    + task_list[i].toString() + "\n";
        }
        list_output += "     _______________________\n";
        System.out.println(list_output);

    }
//    public boolean mode(String input) {
//
//    }


}
