public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("Hope to see you again soon!");
         */

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        if (args[0].equals("bye") == false){
            System.out.println(args[0]);
        }
        else{
            System.out.println("Bye. Hope to see you again soon!");
            return;
        }
    }


}
