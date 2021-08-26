import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String[] task_list = new String[100];
        int task_num = 0;
        String Greetings = "     _______________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "     _______________________\n";

        System.out.println(Greetings);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                String list_output = "     _______________________\n";
                for (int i=0; i<task_num; i++) {
                    list_output += "     " + (i+1) + ". "
                            + task_list[i]+ "\n";
                }
                list_output += "     _______________________\n";
                System.out.println(list_output);
            }
            else {
                task_list[task_num] = line;
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




}
