

import java.util.Scanner;

public class Duke {
    public static final String SEPARATOR = "-----------------------------------";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        int listLength = 0;

        Task task;

        Task[] list = new Task[100];
        String userInput;
        String command;

        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while(!userInput.equals("bye")){
            if(userInput.equals("echo")){
                echo(userInput);
            } else if(userInput.substring(0,3).equals("add")){  // Add the task into the list
                command = userInput.substring(4);
                task = new Task(command);
                list[listLength] = task;
                System.out.println(SEPARATOR);
                System.out.println("added:" + " " + command);
                System.out.println(SEPARATOR);
                listLength++;
            } else if(userInput.equals("list")){      /* Show the list to user which contains indicators to indicate that whether this task is done or have a deadline or is an event with given time */
                System.out.println(SEPARATOR);
                printList(list,listLength);
                System.out.println(SEPARATOR);
            } else if(userInput.substring(0,4).equals("done")){   // Mark the task that needs to be done as done in list
                int listNumber;
                listNumber = Integer.parseInt(userInput.substring(5))-1;
                list[listNumber].taskDone();
                System.out.println(SEPARATOR);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(list[listNumber].toString());
                System.out.println(SEPARATOR);
            } else if(userInput.substring(0,4).equals("todo")){ // Create a Todo object and add it into the task list
                int currentTaskIndex = listLength;
                command = userInput.substring(5);
                Todo todo = new Todo(command);
                list[currentTaskIndex] = todo;
                list[currentTaskIndex].setNeedToDo();
                int taskNumber = listLength + 1;
                System.out.println(SEPARATOR);
                System.out.println("Got it. I've added this task:");
                System.out.println(list[currentTaskIndex].toString());
                System.out.println("Now you have" + " "+ taskNumber + " tasks in the list.");
                System.out.println(SEPARATOR);
                listLength++;
            } else if(userInput.substring(0,8).equals("deadline")){  // Create a deadline object and add it into the task list
                int currentTaskIndex = listLength;
                int slashIndex = userInput.indexOf("/");
                int slashByIndex = userInput.indexOf("/by");
                command = userInput.substring(9,slashIndex); //get the description
                String by = userInput.substring(slashByIndex + 4);
                list[currentTaskIndex] = new Deadline(command,by);
                listLength++;
                System.out.println(SEPARATOR);
                System.out.println("Got it. I've added this task: ");
                System.out.println(list[currentTaskIndex].toString());
                System.out.println("Now you have " + listLength + " tasks in the list");
                System.out.println(SEPARATOR);

            } else if(userInput.substring(0,5).equals("event")){  // Create an event object and add it into the task list
                int currentTaskIndex = listLength;
                int slashIndex = userInput.indexOf("/");
                int slashAtIndex = userInput.indexOf("/at");
                command = userInput.substring(6,slashIndex);
                String at = userInput.substring(slashAtIndex+4);
                list[currentTaskIndex] = new Event(command,at);
                listLength++;
                System.out.println(SEPARATOR);
                System.out.println("Got it. I've added this task: ");
                System.out.println(list[currentTaskIndex].toString());
                System.out.println("Now you have " + listLength + " tasks in the list");
                System.out.println(SEPARATOR);
            }

            in = new Scanner(System.in);
            userInput = in.nextLine();


        }

        /* Operations that will be taken if user enter "bye" to exit the Duke

         */
        if(userInput.equals("bye")){
            System.out.println(SEPARATOR);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(SEPARATOR);
        }
    }

        public static void echo(String command){
            while(!command.equals("bye")){
                Scanner in = new Scanner(System.in);
                String userInput;
                userInput = in.nextLine();
                if(!userInput.equals("bye")){
                    System.out.println(SEPARATOR);
                    System.out.println(userInput);
                    System.out.println(SEPARATOR);
                }

            }


        }

        public static void printList(Task[] list, int listLength){
            for(int i = 0;i< listLength;i++){
                System.out.println(i+1 + "." + list[i].toString());
        }

        }






}
