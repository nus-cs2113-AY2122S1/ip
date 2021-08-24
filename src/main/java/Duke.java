package main.java;


import java.util.Scanner;

class Duke {

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int index = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(tasks[i].getStatus() + (i+1) + ". " + tasks[i].getTaskName()
                    );
                }
            }
            else if (input.contains("done")) {
                input = input.trim();
                int completedIndex = Integer.parseInt(input.substring(input.length() - 1));
                tasks[completedIndex - 1].finishTask();
                System.out.println("Task " + completedIndex + " mark as done!");
            }
            else {
                Task temp = new Task(input);
                tasks[index] = temp;
                System.out.println("added: " + input);
                index++;
            }
            input = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
