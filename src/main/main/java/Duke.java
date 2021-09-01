package main.java;

import java.util.Scanner;

public class Duke {

    public static final String HORIZONTAL_LINE_W_NL = "_________________________J.A.R.V.I.S.________________________\n";
    public static final String HORIZONTAL_LINE = "_________________________J.A.R.V.I.S.________________________";
    public static final int MAX_TASKS = 100;
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";

    public static void main(String[] args) {

        greetCommand();
        Scanner in = new Scanner(System.in);
        String line;
        Task[] list = new Task[MAX_TASKS];  //create an array of Tasks
        int listCount = 0;  //keep position count for array

        do {
            line = in.nextLine();
            if (line.toLowerCase().contains(COMMAND_LIST)){  //display list if user says list
                System.out.println(HORIZONTAL_LINE_W_NL + "Here are the tasks in your list Sir:");
                for(int i = 0; i < listCount; i++){
                    System.out.println((i+1) + "." + list[i].toString());
                }
                System.out.println(HORIZONTAL_LINE_W_NL);
                continue;
            } else if (line.toLowerCase().contains(COMMAND_BYE)){  //exit loop if user says bye
                break;
            } else if (line.toLowerCase().contains(COMMAND_DONE)) {  //mark task as done if user says done
                int dividerPosition = line.indexOf(" ");  //find divider pos between done and int
                int taskNum = Integer.parseInt(line.substring(dividerPosition + 1)); //convert to int from str
                list[taskNum - 1].markAsDone();  //mark the task as done
                System.out.println(HORIZONTAL_LINE_W_NL
                        + "Good one Sir! I've marked this task as done:\n"
                        + " " + list[taskNum - 1].toString() + "\n"
                        + HORIZONTAL_LINE_W_NL);
                continue;
            } else if (line.toLowerCase().contains(COMMAND_TODO)) {  //add task to list
                int todoPosition = line.indexOf(COMMAND_TODO);  //find todo position in string
                String description = line.substring(todoPosition+5);  //extract description in string
                Todo t = new Todo(description);
                list[listCount] = t;
                listCount++;
            } else if (line.toLowerCase().contains(COMMAND_DEADLINE)) {  //add task with deadline to list
                int byPosition = line.indexOf("/by");  //find by keyword
                int deadlinePosition = line.indexOf("deadline");  //find deadline keyword
                String description = line.substring(deadlinePosition+9,byPosition);  //extract description in string
                String by = line.substring(byPosition+4);  //extract due date in string
                Deadline t = new Deadline(description,by);
                list[listCount] = t;
                listCount++;
            } else if (line.toLowerCase().contains(COMMAND_EVENT)) {  //add task with time to list
                int atPosition = line.indexOf("/at");  //find at keyword
                int eventPosition = line.indexOf("event");   //find event keyword
                String description = line.substring(eventPosition+6, atPosition);  //extract description in string
                String at = line.substring(atPosition+4);  //extract time in string
                Event t = new Event(description,at);
                list[listCount] = t;
                listCount++;
            } else {
                invalidInput();  //if user input is unknown
                continue;
            }
            printAddedTask(list[listCount - 1], listCount);
        } while (!line.toLowerCase().contains(COMMAND_BYE));
        byeCommand();
    }

    private static void invalidInput() {
        System.out.println(HORIZONTAL_LINE_W_NL
                + "I don't quite get what you said Sir.\n"
                + "Could you please repeat your command?\n"
                + HORIZONTAL_LINE_W_NL);
    }

    private static void printAddedTask(Task task, int listCount) {
        System.out.println(HORIZONTAL_LINE_W_NL
                + "Noted Sir. I've added this task:\n"
                + " " + task.toString() + "\n"
                + "You currently have " + listCount + " tasks in your list Sir.\n"
                + HORIZONTAL_LINE_W_NL);
    }

    private static void byeCommand() {
        System.out.println(HORIZONTAL_LINE_W_NL
                + "As always Sir, a great pleasure watching you work!\n"
                + HORIZONTAL_LINE);
    }

    private static void greetCommand() {
        System.out.println(HORIZONTAL_LINE_W_NL
                + "Good day Sir! J.A.R.V.I.S. here \n"
                + "What would you like me to do Sir?\n"
                + HORIZONTAL_LINE_W_NL);
    }
}
