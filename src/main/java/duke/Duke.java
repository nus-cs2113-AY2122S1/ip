package duke;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    protected int count = 0;
    ArrayList<Task> list = new ArrayList<>();
    protected Scanner in;

    public static void printAdded(Task task, int count) {
        System.out.println("Got it. I've added this task:");
        task.printTask();
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    public int executeCommand(String input) throws DukeException{
        String command;
        String description = null;
        String time = null;
        String temp;
        if (input.contains(" ")) {
            command = input.substring(0, input.indexOf(" "));
            temp = input.substring(input.indexOf(" ") + 1);
            if (temp.contains("/")) {
                description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                time = input.substring(input.indexOf("/") + 4);
            } else {
                description = temp;
            }
        } else {
            command = input;
        }
        switch(command){
        case "list":
            if (list.isEmpty()) {
                throw new NullPointerException();
            }
            for(Task task: list){
                task.printTask();
            }
            break;
        case "done":
            if (description==null) {
                throw new NumberFormatException();
            }
            int index = Integer.parseInt(description)-1;
            list.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
            break;
        case "todo":
            if (description==null) {
                throw new DukeException("nullTodo");
            }
            count++;
            Todo todo = new Todo(description, count);
            printAdded(todo,count);
            list.add(todo);
            break;
        case "deadline":
            if (description==null) {
                throw new DukeException("nullTodo");
            } else if (time==null) {
                throw new DukeException("nullTime");
            }
            count++;
            Deadline deadline = new Deadline(description, count, time);
            printAdded(deadline,count);
            list.add(deadline);
            break;
        case "event":
            if (description==null) {
                throw new DukeException("nullTodo");
            } else if (time==null) {
                throw new DukeException("nullTime");
            }
            count++;
            Event event = new Event(description, count, time);
            printAdded(event,count);
            list.add(event);
            break;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            return -1;
        case "delete":
            if (description==null) {
                throw new NumberFormatException();
            }
            int index2 = Integer.parseInt(description)-1;
            list.get(index2).printTaskDelete(count-1);
            list.remove(index2);
            count--;
            for (int i=index2; i<count; i++) {
                list.get(i).index--;
            }
            break;
        default:
            throw new DukeException("unknownCommand");
        }
        return 0;
    }

    public void run() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        in = new Scanner(System.in);
        String input = in.nextLine();
        while (true) {
            try {
                if (executeCommand(input) == -1) {
                    break;
                }
            } catch (DukeException e) {
                switch (e.error) {
                case "unknownCommand":
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
                case "nullTodo":
                    System.out.println("☹ OOPS!!! The description cannot be empty.");
                    break;
                case "nullTime":
                    System.out.println("☹ OOPS!!! The time cannot be empty.");
                    break;
                default:
                    System.out.println("☹ OOPS!!! There is an unknown error.");
                    break;
                }
            } catch (NullPointerException e) {
                System.out.println("☹ OOPS!!! There are no items in the list.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! There is no such item to be done.");
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Please input the index of the item.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! There is no such item.");
            }
            input = in.nextLine();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
