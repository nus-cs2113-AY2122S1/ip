import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static ToDo[] FilterNulls(ToDo[] toDos) {
        ToDo[] isFilteredNull = new ToDo[100];
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

        ToDo[] unfilteredToDos = new ToDo[100];
        int unfilteredCounter = 0;

        System.out.println("\tHello from\n" + logo);
        System.out.println(greeting);
        command = in.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("\n")) {
                System.out.println(line);
                command = in.nextLine();
            } else if (command.contains("done")) {
                System.out.println(line + "Nice! I've marked this task as done:");
                String[] removeWords = command.split(" ");
                int toDoNumber = Integer.parseInt(removeWords[1]);
                ToDo completedToDo = unfilteredToDos[(toDoNumber - 1)];
                completedToDo.markAsDone();
                System.out.println("\t\t" + completedToDo.getStatusIcon() + completedToDo.getTask() + "\n" + line);
                command = in.nextLine();
            } else if (!command.equals("list")) {
                System.out.println(line + "added: " + command + "\n" + line);
                unfilteredToDos[unfilteredCounter] = new ToDo(command);
                unfilteredCounter++;
                command = in.nextLine();
            } else if (command.equals("list")) {
                ToDo[] filteredNull = FilterNulls(unfilteredToDos);
                int count = 0;
                if (filteredNull[0] == null) {
                    System.out.println(line + "\tAll DONE!\n" + line);
                    command = in.nextLine();
                } else {
                    System.out.println(line + "Here are the tasks in your list:\n");
                    for (ToDo toDo : filteredNull) {
                        count++;
                        System.out.println(count + "." + toDo.getStatusIcon() + toDo.getTask());
                    }
                    System.out.println(line);
                    command = in.nextLine();
                }
            }
        }

        System.out.println(bye);
    }
}
