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
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String LINE = "\t____________________________________________________________\n\t";
        String greeting = LINE
                + "Hello! I'm Anderson\n\t"
                + "What do you need to do?\n"
                + LINE;
        String BYE = LINE
                + "Bye. try not to procrastinate!\n"
                + LINE;

        String command;
        Scanner in = new Scanner(System.in);

        ToDo[] unfilteredToDos = new ToDo[100];
        int unfilteredCounter = 0;

        System.out.println("\tHello from\n" + LOGO);
        System.out.println(greeting);
        command = in.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("\n")) {
                System.out.println(LINE);
                command = in.nextLine();
            } else if (command.contains("done")) {
                System.out.println(LINE + "Nice! I've marked this task as done:");
                String[] removeWords = command.split(" ");
                int toDoNumber = Integer.parseInt(removeWords[1]);
                ToDo completedToDo = unfilteredToDos[(toDoNumber - 1)];
                completedToDo.markAsDone();
                System.out.println("\t\t" + completedToDo.getStatusIcon() + completedToDo.getTask() + "\n" + LINE);
                command = in.nextLine();
            } else if (!command.equals("list")) {
                System.out.println(LINE + "added: " + command + "\n" + LINE);
                unfilteredToDos[unfilteredCounter] = new ToDo(command);
                unfilteredCounter++;
                command = in.nextLine();
            } else if (command.equals("list")) {
                ToDo[] filteredNull = FilterNulls(unfilteredToDos);
                int count = 0;
                if (filteredNull[0] == null) {
                    System.out.println(LINE + "\tAll DONE!\n" + LINE);
                    command = in.nextLine();
                } else {
                    System.out.println(LINE + "Here are the tasks in your list:\n");
                    for (ToDo toDo : filteredNull) {
                        count++;
                        System.out.println("\t" + count + "." + toDo.getStatusIcon() + toDo.getTask());
                    }
                    System.out.println(LINE);
                    command = in.nextLine();
                }
            }
        }

        System.out.println(BYE);
    }
}
