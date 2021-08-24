package main.java;


import java.util.Scanner;

class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while(!input.equals("bye")) {
            System.out.println(input);
            input = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
