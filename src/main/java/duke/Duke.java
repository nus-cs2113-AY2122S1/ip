package duke;

import java.util.Scanner;

public class Duke {

    protected int count = 0;
    protected Task[] list = new Task[100];
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
            for(Task task: list){
                task.printTask();
                if (task.index == count) {
                    break;
                }
            }
            return 0;
        case "done":
            int index = Integer.parseInt(description);
            for (Task task : list) {
                if (task.index == index) {
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + task.getStatusIcon() + "] " + task.description);
                    break;
                } else if (task.index == count) {
                    throw new StringIndexOutOfBoundsException();
                }
            }
            return 0;
        case "todo":
            count++;
            Todo todo = new Todo(description, count);
            if (todo.description==null) {
                throw new DukeException("nullTodo");
            }
            printAdded(todo,count);
            list[count-1] = todo;
            return 0;
        case "deadline":
            count++;
            Deadline deadline = new Deadline(description, count, time);
            if (deadline.deadline==null) {
                throw new DukeException("nullDeadline");
            }
            printAdded(deadline,count);
            list[count-1] = deadline;
            return 0;
        case "event":
            count++;
            Event event = new Event(description, count, time);
            if (event.event==null) {
                throw new DukeException("nullEvent");
            }
            printAdded(event,count);
            list[count-1] = event;
            return 0;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            return -1;
        }
        throw new DukeException("unknownCommand");
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
                if (e.error.equals("unknownCommand")) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else if (e.error.equals("nullTodo")) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } else if (e.error.equals("nullDeadline")) {
                    System.out.println("☹ OOPS!!! The time of a deadline cannot be empty.");
                } else if (e.error.equals("nullEvent")) {
                    System.out.println("☹ OOPS!!! The time of a event cannot be empty.");
                } else {
                    System.out.println("☹ OOPS!!! There is an unknown error.");
                }
            } catch (NullPointerException e) {
                System.out.println("☹ OOPS!!! There are no items in the list.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! There is no such item to be done.");
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Please input the index of the item to be done.");
            }
            input = in.nextLine();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
