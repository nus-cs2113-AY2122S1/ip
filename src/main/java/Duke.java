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
                        + "       [X] " + task_list[current_task].getContent() + "\n"
                        + "     _______________________\n";
                System.out.println(update);
                break;
            default:
                task_list[task_num] = new Task(line);
                task_num += 1;
                String echo = "     _______________________\n"
                        + "     added: " + line + "\n"
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

    public static class Task {
        protected String content;
        protected Boolean isDone;

        public Task(String content) {
            this.content = content;
            isDone = false;
        }

        public String TaskStatus() {
            return (isDone? "X":" ");
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public String getContent() {
            return this.content;
        }
    }

    public static void list(int task_num, Task[] task_list ) {
        String list_output = "     _______________________\n";
        for (int i = 0; i < task_num; i++) {
            list_output += "     " + (i+1) + ". "
                    + "[" + task_list[i].TaskStatus() + "]"
                    + task_list[i].getContent() + "\n";
        }
        list_output += "     _______________________\n";
        System.out.println(list_output);

    }
//    public boolean mode(String input) {
//
//    }


}
