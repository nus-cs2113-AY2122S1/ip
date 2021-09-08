package duke;

import java.util.Scanner;

public class Duke {

    public static final String HORIZONTAL_LINE_W_NL = "_________________________J.A.R.V.I.S.________________________\n";
    public static final String HORIZONTAL_LINE = "_________________________J.A.R.V.I.S.________________________";
    public static final int MAX_TASK_NUM = 100;
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
        Task[] list = new Task[MAX_TASK_NUM];  //create an array of Tasks
        int listCount = 0;  //keep position count for array


        do {
            line = in.nextLine();
            try {
                if (line.toLowerCase().contains(COMMAND_LIST)) {  //display list if user says list
                    System.out.println(HORIZONTAL_LINE_W_NL + "Here are the tasks in your list Sir:");
                    for (int i = 0; i < listCount; i++) {
                        System.out.println((i + 1) + "." + list[i].toString());
                    }
                    System.out.println(HORIZONTAL_LINE_W_NL);
                    continue;
                }
                else if (line.toLowerCase().contains(COMMAND_BYE)) {  //exit loop if user says bye
                    break;
                }
                else if (line.toLowerCase().contains(COMMAND_DONE)) {  //mark task as done if user says done
                    int dividerPosition = line.indexOf(" ");  //find divider pos between done and int
                    int taskNum = Integer.parseInt(line.substring(dividerPosition + 1)); //convert to int from str
                    list[taskNum - 1].markAsDone();  //mark the task as done
                    System.out.println(HORIZONTAL_LINE_W_NL
                            + "Good one Sir! I've marked this task as done:\n"
                            + " " + list[taskNum - 1].toString() + "\n"
                            + HORIZONTAL_LINE_W_NL);
                    continue;
                }
                else if (line.toLowerCase().contains(COMMAND_TODO)) {  //add task to list
                    String[] todoInputs = line.split(" ", 2);
                    if (todoInputs.length < 2) {
                        throw new DukeException();
                    }
                    String description = todoInputs[1];
                    Todo t = new Todo(description);
                    list[listCount] = t;
                    listCount++;
                }
                else if (line.toLowerCase().contains(COMMAND_DEADLINE)) {  //add task with deadline to list
                    String[] deadlineInputs = line.split(" ", 2);
                    if (deadlineInputs.length < 2) {
                        throw new DukeException();
                    }
                    String[] deadlineDescriptions = deadlineInputs[1].split(" /by ");  //extract description in string
                    String description = deadlineDescriptions[0];
                    String by = deadlineDescriptions[1];
                    Deadline t = new Deadline(description, by);
                    list[listCount] = t;
                    listCount++;
                }
                else if (line.toLowerCase().contains(COMMAND_EVENT)) {  //add task with time to list
                    String[] eventInputs = line.split(" ", 2);
                    if (eventInputs.length < 2) {
                        throw new DukeException();
                    }
                    String[] eventDescriptions = eventInputs[1].split(" /at ");  //extract description in string
                    String description = eventDescriptions[0];
                    String at = eventDescriptions[1];
                    Event t = new Event(description, at);
                    list[listCount] = t;
                    listCount++;
                }
                else {
                    throw new IndexOutOfBoundsException();
                }
                printAddedTask(list[listCount - 1], listCount);
            } catch (DukeException e) {
                System.out.println(HORIZONTAL_LINE_W_NL
                        + "OOPS!!! Please include a task description.\n"
                        + HORIZONTAL_LINE);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(HORIZONTAL_LINE_W_NL
                        + "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                        + HORIZONTAL_LINE);
            }
        }
        while (!line.toLowerCase().contains(COMMAND_BYE));
        byeCommand();

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