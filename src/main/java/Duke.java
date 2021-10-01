import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import seedu.tojava.Duke.*;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
public class Duke {

    private static void writeToFile(String filePath, String textToAdd) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
    }

    /**
     *
     * @param items the ArrayList which save the Todo Deadline Event
     * @param x the num of total amount of items
     */
    public static void showList(ArrayList<Todo> items, int x) {
        String file1 = "C:\\Users\\Demons\\IdeaProjects\\ip\\out\\duke1.txt";

        if (x == 0) System.out.println("\n_____________________________________________________________");
        else {
            System.out.println("\n____________________________________________________________");
            for (int i = 0; i < x; i++) {
                if (items.get(i) instanceof Deadline) {
                    Deadline dummy = (Deadline) items.get(i);
                    System.out.println("[" + items.get(i).returnType() + "] " + "[" + items.get(i).getStatusIcon() + "] " + items.get(i).getDescription() + " (" + dummy.getBy().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
                    try {
                        writeToFile(file1, "[" + items.get(i).returnType() + "] " + "[" + items.get(i).getStatusIcon() + "] " + items.get(i).getDescription() + " (" + dummy.getBy() + ")");
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                }
                else if (items.get(i) instanceof Event) {
                    Event dummy = (Event) items.get(i);
                    System.out.println("[" + items.get(i).returnType() + "] " + "[" + items.get(i).getStatusIcon() + "] " + items.get(i).getDescription() + " (" + dummy.getDuration().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
                    try {
                        writeToFile(file1, "[" + items.get(i).returnType() + "] " + "[" + items.get(i).getStatusIcon() + "] " + items.get(i).getDescription() + " (" + dummy.getDuration() + ")");
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                } else {
                    Todo dummy = (Todo) items.get(i);

                    System.out.println("[" + items.get(i).returnType() + "] " + "[" + items.get(i).getStatusIcon() + "] " + items.get(i).getDescription());
                    try {
                        writeToFile(file1, "[" + items.get(i).returnType() + "] " + "[" + items.get(i).getStatusIcon() + "] " + items.get(i).getDescription());
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }

                }
            }

            System.out.println("\n____________________________________________________________");
        }
    }

    /**
     *
     * @param items the ArrayList which save the Todo Deadline Event
     * @param order the task which marked as done
     */


    public static void doneTodo(ArrayList<Todo> items, String order){
        String dummy = order.trim();
        int index = Integer.parseInt(dummy.substring(5,dummy.length()));
        items.get(index-1).markAsDone();
        System.out.println("\n____________________________________________________________");
        System.out.println("Nice Work! I've marked it as done");

        System.out.println("\n____________________________________________________________");
    }

    public static void greeting () {

        ArrayList<Todo> items = new ArrayList<>();
        int maxcount = 0;
        boolean flag = true;
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        while(flag) {
            try {
                String order = getMission();


                while (!order.equals("bye")) {
                    if (order.equals("list")) {
                        showList(items, maxcount);
                        order = getMission();
                        continue;
                    } else if (order.contains("done")) {
                        doneTodo(items, order);
                        order = getMission();
                        continue;
                    }
                    if (order.contains("todo")) {
                        items.add(new Todo(order.substring(5, order.length())));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("[T][] " + items.get(maxcount).getDescription());
                        System.out.println("Now you have " + (maxcount + 1) + " tasks in the list");
                        System.out.println("____________________________________________________________\n");
                        maxcount++;
                    } else if (order.contains("deadline")) {
                        items.add (new Deadline(order.substring(9, order.length())));
                        Deadline dummy = new Deadline(order.substring(9, order.length()));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("[D][] " + items.get(maxcount).getDescription() + dummy.getBy());
                        System.out.println("Now you have " + (maxcount + 1) + " tasks in the list");
                        System.out.println("____________________________________________________________\n");
                        maxcount++;
                    } else if (order.contains("event")) {
                        items.add(new Event(order.substring(6, order.length())));
                        Event dummy = new Event(order.substring(6, order.length()));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("[E][] " + items.get(maxcount).getDescription() + dummy.getDuration());
                        System.out.println("Now you have " + (maxcount + 1) + " tasks in the list");
                        System.out.println("____________________________________________________________\n");
                        maxcount++;
                    }
                    else if(order.contains("delete")){
                        int index = Integer.parseInt(order.substring(7,order.length()));
                        items.remove(index-1);
                        System.out.println("Noted I've already delete it");
                        maxcount--;
                    }
                    else if(order.contains("find")){
                        String keywords = order.substring(5,order.length()).trim();
                        int flag2 = 0;
                        for(Todo item: items){
                            if(item.getDescription().contains(keywords)){
                                flag2 = 1;
                                if(item instanceof Deadline){
                                    System.out.println("[" + item.returnType() + "] " + "[" + item.getStatusIcon() + "] " + item.getDescription() + " (" + ((Deadline) item).getBy().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
                                }
                                else if(item instanceof Event){
                                    System.out.println("[" + item.returnType() + "] " + "[" + item.getStatusIcon() + "] " + item.getDescription() + " (" + ((Event) item).getDuration().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
                                }
                                else{
                                    System.out.println("[" + item.returnType() + "] " + "[" + item.getStatusIcon() + "] " + item.getDescription());
                                }
                            }
                            if(flag2 == 0) {
                                System.out.println("Not Found !!!");
                            }
                        }
                    }
                    order = getMission();
                }
                System.out.println("Bye.Hope to see you again soon!\n");
                System.out.println("____________________________________________________________");
                flag = false;
            } catch (IllegalFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     *
     * @return return the mission get from the user's input
     * @throws IllegalFormatException if the mission description is illegal
     */
    public static String getMission() throws IllegalFormatException{
        String mission;
        Scanner in = new Scanner(System.in);
        mission = in.nextLine();
        if(!mission.contains("find") && !mission.contains("delete") && !mission.contains("todo") && !mission.contains("list") && !mission.contains("bye") && !mission.contains("deadline") && !mission.contains("event") && !mission.contains("done")){
            throw new IllegalFormatException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        else if(mission.contains("todo")){
            String dummy = mission.trim();
            if(dummy.length() <= 4) {
                throw new IllegalFormatException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        }
        return mission;
    }
}
