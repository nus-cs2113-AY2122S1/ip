import java.util.Scanner;

public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description =  description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }
        public void markAsDone() {
            this.isDone = true;
        }

    }

    static String exitTrigger = "bye";
    static String listTrigger = "list";
    static String doneTrigger = "done";
    static Task[] commands = new Task[100];

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?\n");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void add(String command, int item_num) {
        commands[item_num] = new Task(command);
        System.out.println("added: " + command);
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:\n");
        for(int i = 0; commands[i] != null; i++) {
            int bullet_num = i+1;
            String statusIcon = commands[i].getStatusIcon();
            System.out.print(bullet_num +". ");
            System.out.print("["+ statusIcon +"] ");
            System.out.println(commands[i].description);
        }
    }

    public static boolean mark_done(String command){
        //command will be "done X"
        String cmd[] = command.split(" ", 2);
        String firstword = cmd[0];
        if (firstword.equals(doneTrigger)) {
            int index = Integer.parseInt(cmd[1])-1;
            commands[index].markAsDone();
            System.out.println("Nice!I've marked this task as done: \n" +
                    "\t[X] " + commands[index].description);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        greet();

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        boolean should_exit = command.equals(exitTrigger);

        for(int i = 0; !command.equals(exitTrigger) ; i++) {
            if (mark_done(command)) {
                i--;
            }
            else if (command.equals(listTrigger)) {
                list();
                i--;
            }
            else {
                add(command, i);
            }
            command = in.nextLine();
        }

        exit();
    }
}
