import java.util.Scanner;
import seedu.tojava.Duke.*;
import java.io.FileWriter;
import java.io.IOException;

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


    public static void showList(Todo list[], int x){
        String file1 = "C:\\Users\\Demons\\IdeaProjects\\ip\\out\\duke1.txt";
        if(x == 0)  System.out.println("\n____________________________________________________________");
        else{
            System.out.println("\n____________________________________________________________");
            for(int i = 0; i < x; i++){
                if(list[i] instanceof Deadline){
                    Deadline dummy = (Deadline) list[i];
                    System.out.println("[" + list[i].returnType() + "] " + "[" + list[i].getStatusIcon() + "] " + list[i].getDescription() + " (" + dummy.getBy() + ")");
                    try{
                        writeToFile(file1, "[" + list[i].returnType() + "] " + "[" + list[i].getStatusIcon() + "] " + list[i].getDescription() + " (" + dummy.getBy() + ")");
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                }
                else if(list[i] instanceof Event){
                    Event dummy = (Event)list[i];
                    System.out.println("[" + list[i].returnType() + "] " + "[" + list[i].getStatusIcon() + "] " + list[i].getDescription() + " (" + dummy.getDuration() + ")");
                    try{
                        writeToFile(file1, "[" + list[i].returnType() + "] " + "[" + list[i].getStatusIcon() + "] " + list[i].getDescription() + " (" + dummy.getDuration() + ")");
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                }
                else{
                    System.out.println("[" + list[i].returnType() + "] " + "[" + list[i].getStatusIcon() + "] " +  list[i].getDescription());
                    try{
                        writeToFile(file1, "[" + list[i].returnType() + "] " + "[" + list[i].getStatusIcon() + "] " +  list[i].getDescription());
                    } catch (IOException e){
                        System.out.println("Something went wrong: " + e.getMessage());
                    }

                }
            }

            System.out.println("\n____________________________________________________________");
        }
    }

    public static void doneTodo(Todo list[], String order){
        String dummy = order.trim();
        int index = Integer.parseInt(dummy.substring(5,dummy.length()));
        list[index-1].markAsDone();
        System.out.println("\n____________________________________________________________");
        System.out.println("Nice Work! I've marked it as done");

        System.out.println("\n____________________________________________________________");
    }

    public static void greeting () {
        Todo list[] = new Todo[100];
        int maxcount = 0;
        boolean flag = true;
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        while(flag) {
            try {
                String order = getMission();


                while (!order.equals("bye")) {
                    if (order.equals("list")) {
                        showList(list, maxcount);
                        order = getMission();
                        continue;
                    } else if (order.contains("done")) {
                        doneTodo(list, order);
                        order = getMission();
                        continue;
                    }
                    if (order.contains("todo")) {
                        list[maxcount] = new Todo(order.substring(5, order.length()));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("[T][] " + list[maxcount].getDescription());
                        System.out.println("Now you have " + (maxcount + 1) + " tasks in the list");
                        System.out.println("____________________________________________________________\n");
                    } else if (order.contains("deadline")) {
                        list[maxcount] = new Deadline(order.substring(9, order.length()));
                        Deadline dummy = new Deadline(order.substring(9, order.length()));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("[D][] " + list[maxcount].getDescription() + dummy.getBy());
                        System.out.println("Now you have " + (maxcount + 1) + " tasks in the list");
                        System.out.println("____________________________________________________________\n");
                    } else if (order.contains("event")) {
                        list[maxcount] = new Event(order.substring(6, order.length()));
                        Event dummy = new Event(order.substring(6, order.length()));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("[E][] " + list[maxcount].getDescription() + dummy.getDuration());
                        System.out.println("Now you have " + (maxcount + 1) + " tasks in the list");
                        System.out.println("____________________________________________________________\n");
                    }

                    maxcount++;
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

    public static String getMission() throws IllegalFormatException{
        String mission;
        Scanner in = new Scanner(System.in);
        mission = in.nextLine();
        if(!mission.contains("todo") && !mission.contains("list") && !mission.contains("bye") && !mission.contains("deadline") && !mission.contains("event") && !mission.contains("done")){
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
