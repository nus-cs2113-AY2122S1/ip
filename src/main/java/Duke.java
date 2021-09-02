import java.util.Scanner;

public class Duke {
    static final String line_separator = "     _______________________\n";
    static final String spacing = "     ";
    public static void main(String[] args) {
        int MAXIMUM = 100;
        Task[] task_list = new Task[MAXIMUM];
        int task_num = 0;
        String GREETINGS = line_separator
                + spacing + "Hello! I'm Duke\n"
                + spacing + "What can I do for you?\n"
                + line_separator;

        System.out.println(GREETINGS);
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            switch (line.split(" ")[0]) {
            case "list":
                list(task_num, task_list);
                break;
            case "done":
                int current_task = Integer.parseInt(line.split(" ")[1]) - 1;
                task_list[current_task].markAsDone();
                String update = line_separator
                        + spacing + "Nice! I've marked this task as done: \n"
                        + spacing + task_list[current_task].toString() + "\n"
                        + line_separator;
                System.out.println(update);
                break;
            case "deadline":
                String task_name = line.substring("deadline ".length(), line.indexOf("/by "));
                String task_ddl = line.substring(line.indexOf("/by ") + "/by ".length());
                task_list[task_num] = new Deadline(task_name, task_ddl);
                task_num += 1;
                String echo = line_separator
                        + spacing + "added: " + task_list[task_num - 1] + "\n"
                        + line_separator;
                System.out.println(echo);
                break;
            case "event":
                task_name = line.substring("event ".length(), line.indexOf("/at "));
                String task_time = line.substring(line.indexOf("/at ") + "/at ".length());
                task_list[task_num] = new Event(task_name, task_time);
                task_num += 1;
                echo = line_separator
                        + spacing + "added: " + task_list[task_num - 1] + "\n"
                        + line_separator;
                System.out.println(echo);
                break;
            case "todo":
                task_list[task_num] = new ToDo(line);
                task_num += 1;
                echo = line_separator
                        + spacing + "added: " + task_list[task_num - 1] + "\n"
                        + line_separator;
                System.out.println(echo);
                break;
            default:
                task_list[task_num] = new Task(line);
                task_num += 1;
                echo = line_separator
                        + spacing + "added: " + task_list[task_num - 1] + "\n"
                        + line_separator;
                System.out.println(echo);
            }
        }

        String Bye = line_separator
                + spacing + "Bye. Hope to see you again soon!\n"
                + line_separator;
        System.out.println(Bye);
    }


    public static void list(int task_num, Task[] task_list ) {
        String list_output = line_separator;
        for (int i = 0; i < task_num; i++) {
            list_output += spacing + (i+1) + ". "
                    + task_list[i].toString() + "\n";
        }
        list_output += line_separator;
        System.out.println(list_output);

    }
//    public boolean mode(String input) {
//
//    }


}
