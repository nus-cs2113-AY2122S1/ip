import java.util.Scanner;

public class Duke {

    public static void printLine(){
        String line = "\t__________________________________________________";
        System.out.println(line);
    }

    public static void Greet(){
        printLine();
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        printLine();
    }

    public static void Bye(){
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    public static void Echo(String[] words){
        printLine();
        System.out.print("\t");
        for (int i = 0; i < words.length; i += 1){
            System.out.print(words[i] + " ");
        }
        System.out.println("");
        printLine();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] words = line.split(" ");
        boolean isBye = words[0].equals("bye");
        while (!isBye){
            Echo(words);
            line = in.nextLine();
            words = line.split(" ");
            isBye = words[0].equals("bye");
        }
        Bye();
    }
}
