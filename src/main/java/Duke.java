
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Duke {

    public static void main(String[] args) throws DukeException{
        //Starting the bot
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        ArrayList<Task> List = new ArrayList<Task>();  //Creating a list of tasks
        WriteFile data = new WriteFile("data.txt", true);

        int listSize = 0;

        while(true) {
            System.out.println("--------------------------------------------------------------"); //Formatting
            String line; //Input from user
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            if(line.split(" ")[0].equals("bye")) {
                break;
            } else if(line.split(" ")[0].equals("list")){

                /*for (int i = 0; i < listSize; i++) {
                    System.out.println(i+1 + "." + List[i]);
                }*/
                try {
                    BufferedReader file = new BufferedReader(new FileReader("data.txt"));
                    StringBuffer inputBuffer = new StringBuffer();
                    String newLine;

                    while ((newLine = file.readLine()) != null) {
                        inputBuffer.append(newLine);
                        inputBuffer.append('\n');
                    }
                    file.close();
                    String inputStr = inputBuffer.toString();

                    System.out.println(inputStr);
                } catch (Exception e){}
            } else if(line.split(" ")[0].equals("done")) {
                //When the user keys in done
                int taskNumber = Integer.valueOf(line.split(" ")[1]);
                List.get(taskNumber-1).setDone("X");
            } else if(line.split(" ")[0].equals("todo"))  {
                //When the user adds a todo
                try{
                    List.add(new Todo(line.split("todo ")[1]));
                    System.out.println("Got it. I've added this task:");

                    System.out.println(List.get(listSize));
                    System.out.println("Now you have " + (listSize + 1)  + " tasks in the list.");
                    data.writeToFile(List.get(listSize).toString());

                    listSize++;
                } catch (Exception ex){
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }

            } else if(line.split(" ")[0].equals("deadline")) {
                //When the user adds a deadline
                try {
                    List.add(new Deadline(line.split("deadline ")[1], line.split("/by")[1]));

                    System.out.println("Got it. I've added this task:");
                    System.out.println(List.get(listSize));
                    System.out.println("Now you have " + (listSize + 1) + " tasks in the list.");
                    data.writeToFile(List.get(listSize).toString());
                    listSize++;
                } catch (Exception e){
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if(line.split(" ")[0].equals("event")) {
                //When the user adds an event

                List.add(new Event(line.split("event ")[1], line.split("/at")[1]));

                System.out.println("Got it. I've added this task:");
                System.out.println(List.get(listSize)); //
                System.out.println("Now you have " + (listSize + 1)  + " tasks in the list.");
                listSize++;
            } else if(line.split(" ")[0].equals("delete")) {
                try {
                    System.out.println("Got it. I've added this task:");

                    System.out.println(List.get(listSize));
                    System.out.println("Now you have " + (listSize + 1) + " tasks in the list.");
                    data.writeToFile(List.get(listSize).toString());
                    listSize++;
                } catch (Exception e) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
            } else if(line.split(" ")[0].equals("find")){
                try {
                    BufferedReader file = new BufferedReader(new FileReader("data.txt"));
                    StringBuffer inputBuffer = new StringBuffer();
                    String newLine;

                    while ((newLine = file.readLine()) != null) {
                        boolean test = Arrays.asList(newLine.split(" ")).contains(line.split(" ")[1]);
                        if (test){
                            System.out.println(newLine);
                        }
                    }
                    file.close();

                } catch (Exception e){}
            }
            else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        //Exiting the bot
        System.out.println("Bye. Hope to see you again soon!");
    }



}
