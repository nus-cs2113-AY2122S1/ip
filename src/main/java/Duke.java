import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static String[] FilterNulls(String[] toDos) {
        String[] isFilteredNull = new String[100];
        int count = 0;
        for (int i = 0; i < 100; i++) {
            if (toDos[i] != null) {
                isFilteredNull[count] = toDos[i];
                count++;
            }
        }
        return Arrays.copyOf(isFilteredNull, count);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "\t____________________________________________________________\n\t";
        String greeting = line
                + "Hello! I'm Duke\n\t"
                + "What can I do for you?\n"
                + line;
        String bye = line
                + "Bye. Hope to you again soon!\n"
                + line;

        String command;
        Scanner in = new Scanner(System.in);

        String[] unfilteredToDos = new String[100];
        int unfilteredCounter = 0;

        System.out.println("\tHello from\n" + logo);
        System.out.println(greeting);
        command = in.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("\n")){
                command = in.nextLine();
            }
            else if (!command.equals("list")) {
                System.out.println(line + "added: " + command + "\n" + line);
                unfilteredToDos[unfilteredCounter] = command;
                unfilteredCounter++;
                command = in.nextLine();
            } else if (command.equals("list")) {
                String[] filteredNull = FilterNulls(unfilteredToDos);
                int count = 0;
                if (filteredNull[0] == null) {
                    System.out.println(line + "\tAll DONE!\n" + line);
                    command = in.nextLine();
                } else {
                    System.out.println(line);
                    for (String ToDo : filteredNull) {
                        count++;
                        System.out.println(count + "." + ToDo);
                    }
                    System.out.println(line);
                    command = in.nextLine();
                }
            }
        }

        System.out.println(bye);
    }
}
