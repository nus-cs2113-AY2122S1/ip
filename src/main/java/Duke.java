import java.util.Scanner;
public class Duke {

    public static void printLine() {
        for(int x =0;x <= 50;x++){
            System.out.print("~");
        }
        System.out.println(" ");
    }

    public static void echo(String line){
        System.out.println("OWL: " + line);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String logo2 = " ______   _       _   _\n"
                + "|  __  | | | ___ | | | | \n"
                + "| |  | | | |/   \\| | | | \n"
                + "| |__| | |   / \\   | | |____\n"
                + "|______| |__/   \\__| |______|\n";




        String line;
        System.out.println(logo2);
        printLine();
        Scanner in = new Scanner(System.in);

        System.out.println("SQUAWK!!!");
        System.out.println("How can I help you?");
        printLine();
        line = in.nextLine();
        while(!line.equals("bye")){//while the command is not bye it keeps asking for more
            if(!line.isEmpty()) {
                printLine();
                echo(line);
                printLine();
            }
            line = in.nextLine();
        }
        printLine();
        System.out.println("SQUAWK! See you next time!");
        printLine();
    }
}
