import java.util.Scanner;

public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;
        protected char taskType;

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
        String logo = "       ___                             | '  \\\n" +
                "   ___  \\ /  ___         ,'\\_           | .-. \\        /|\n" +
                "   \\ /  | |,'__ \\  ,'\\_  |   \\          | | | |      ,' |_   /|\n" +
                " _ | |  | |\\/  \\ \\ |   \\ | |\\_|    _    | |_| |   _ '-. .-',' |_   _\n" +
                "// | |  | |____| | | |\\_|| |__    //    |     | ,'_`. | | '-. .-',' `. ,'\\_\n" +
                "\\\\_| |_,' .-, _  | | |   | |\\ \\  //    .| |\\_/ | / \\ || |   | | / |\\  \\|   \\\n" +
                " `-. .-'| |/ / | | | |   | | \\ \\//     |  |    | | | || |   | | | |_\\ || |\\_|\n" +
                "   | |  | || \\_| | | |   /_\\  \\ /      | |`    | | | || |   | | | .---'| |\n" +
                "   | |  | |\\___,_\\ /_\\ _      //       | |     | \\_/ || |   | | | |  /\\| |\n" +
                "   /_\\  | |           //_____//       .||`  _   `._,' | |   | | \\ `-' /| |\n" +
                "        /_\\           `------'        \\ |               `.\\  | |  `._,' /_\\\n" +
                "                                       \\|                    `.\\\n" +
                "  S. Lu - I solemnly swear that I am up to no good.\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?\n");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    //index is the position in string, index+1 is the bullet number
    public static void add(String command, int index) {
        commands[index] = new Task(command);
        commands[index].taskType = getTask(command,index);
        System.out.println("Got it. I've added this task: \n" +
                "\t[" + commands[index].taskType + "]" + "[ ] " +
                commands[index].description + "\n" +
                "Now you have " + (index+1) + (index == 0?" task":" tasks") + " in the list."
                );
        }

    public static void list() {
        System.out.println("Here are the tasks in your list:\n");
        for(int i = 0; commands[i] != null; i++) {
            int bullet_num = i+1;
            String statusIcon = commands[i].getStatusIcon();
            char taskType = commands[i].taskType;
            System.out.print(bullet_num +". ");
            System.out.print("[" + taskType +"]" + "["+ statusIcon +"] ");
            System.out.println(commands[i].description);
        }
    }

    public static boolean mark_done(String command){
        //command will be "done X"
        String cmd[] = command.split(" ", 2);
        String firstword = cmd[0];
        //command.contains(word) is not used to obtain the number behind
        if (firstword.equals(doneTrigger)) {
            int index = Integer.parseInt(cmd[1])-1;
            commands[index].markAsDone();
            System.out.println("Nice!I've marked this task as done: \n" +
                    "\t[X] " + commands[index].description);
            return true;
        }
        return false;
    }

    public static char getTask(String command, int i) {
        //.replaceAll method remove word or char from string
        //to obtain strings after certain words, we can use
        //StringUtil.substringAfter(String,word)
        //however, to avoid external library download, i use a more tedious method here
        if (command.contains("todo")) {
            commands[i].description = command.replaceAll("todo","");
            return 'T';
        }
        if (command.contains("deadline")) {
            String time = command.substring(command.indexOf("/by")+4);
            String task = command.substring(command.indexOf("deadline")+9,command.indexOf("/by"));
            commands[i].description = task + "(by: " + time + ")";
            return 'D';
        }
        if (command.contains("event")) {
            String time = command.substring(command.indexOf("/at")+4);
            String task = command.substring(command.indexOf("event")+6,command.indexOf("/at"));
            commands[i].description = task + "(at: " + time + ")";
            return 'E';
        }
        return '?';
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
