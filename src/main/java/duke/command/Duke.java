package duke.command;

import java.util.Scanner;
import duke.task.*;

public class Duke {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + "Hello! I'm duke.command.Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Task[] todo = new Task[100];
        int todo_index = 0;
        while (true) {
            if (input.substring(0, 4).equals("done")) {
                int i = Integer.parseInt(input.substring(5));
                todo[i - 1].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(todo[i - 1].toString());
                System.out.println("____________________________________________________________");
            } else {
                switch (input) {
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < todo_index; i++) {
                        System.out.println(i + 1 + ". " + todo[i].toString());
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "blah":
                    logo = "____________________________________________________________\n"
                            + "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                            + "____________________________________________________________\n";
                    System.out.println(logo);
                    break;
                case "bye":
                    logo = "____________________________________________________________\n"
                            + "Bye. Hope to see you again soon!\n"
                            + "____________________________________________________________\n";
                    System.out.println(logo);
                    break;
                default:
                    if(input.substring(0, 4).equals("todo")){
                        try {
                            String description = input.substring(5);
                            todo[todo_index] = new Task(description);
                            System.out.println("____________________________________________________________");
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(todo[todo_index].toString());
                            todo_index += 1;
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println("____________________________________________________________");
                            System.out.println("OOPS!!! The description of a todo cannot be empty.");
                            System.out.println("____________________________________________________________");
                        }
                    }else if(input.startsWith("deadline")){
                        int descriptionIndex = 9;
                        String task =  "deadline";
                        try {
                            ExceptionTry(task,input);
                            String description = input.substring(input.indexOf("deadline") + descriptionIndex, input.indexOf("/by") - 1);
                            String by = input.substring(input.indexOf("/by") + 4);
                            todo[todo_index] = new Deadline(description, by);
                            System.out.println("____________________________________________________________");
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(todo[todo_index].toString());
                            todo_index += 1;
                        } catch (DukeException e) {
                            System.out.println("____________________________________________________________");
                            System.out.println("OOPS!!! The description of a deadline must have /by.");
                            System.out.println("____________________________________________________________");
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println("____________________________________________________________");
                            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                            System.out.println("____________________________________________________________");
                        }

                    }else if(input.startsWith("event")){
                        int descriptionIndex = 6;
                        String task =  "event";
                        try {
                            ExceptionTry(task,input);
                            String description = input.substring(input.indexOf("event") + descriptionIndex, input.indexOf("/at") - 1);
                            String at = input.substring(input.indexOf("/at") + 4);
                            todo[todo_index] = new Event(description, at);
                            System.out.println("____________________________________________________________");
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(todo[todo_index].toString());
                            todo_index += 1;
                        }catch (DukeException e) {
                            System.out.println("____________________________________________________________");
                            System.out.println("OOPS!!! The description of a event must have /at.");
                            System.out.println("____________________________________________________________");
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println("____________________________________________________________");
                            System.out.println("OOPS!!! The description of a event cannot be empty.");
                            System.out.println("____________________________________________________________");
                        }

                    }
                    System.out.println("Now you have "+ todo_index  +" tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                }
            }
            input = sc.nextLine();
        }
    }

    private static void ExceptionTry(String task, String input) throws DukeException {
        //throw duke.command.DukeException here if descriptor don't have /by or /at
        switch (task){
        case "deadline":
            if(!input.contains("/by")){
                throw new DukeException();
            }
            break;
        case "event":
            if(!input.contains("/at")){
                throw new DukeException();
            }
            break;
        default:
            break;
        }
    }
}
