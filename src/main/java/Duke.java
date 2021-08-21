import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void printLine() {
        for(int x =0;x <= 50;x++){
            System.out.print("~");
        }
        System.out.println(" ");
    }

    public static void echo(String line){
        System.out.println("Owl: I've remembered that!\nOwl: You said this: " + line);
    }

    public static void list(String[] datas){
        int dataIndex = 0;
        int index = 1;
        System.out.println("This is all the things I've remembered for you:");
        for(String data:datas) {
            System.out.println(index + ". " + datas[dataIndex]);
            index++;
            dataIndex++;
        }
    }

    public static void main(String[] args) {

        String logo = " ______   _       _   _\n"
                + "|  __  | | | ___ | | | | \n"
                + "| |  | | | |/   \\| | | | \n"
                + "| |__| | |   / \\   | | |____\n"
                + "|______| |__/   \\__| |______|\n";
        System.out.println(logo);
        printLine();

        Scanner in = new Scanner(System.in);

        System.out.println("SQUAWK!!!");
        System.out.println("How can I help you?");
        printLine();

        String line;
        String[] datas = new String[100];
        int dataIndex = 0;
        line = in.nextLine();
        while(!line.equals("bye")){//while the command is not bye it keeps asking for more
            if(line.equals("list")){
                if(dataIndex > 0) {
                    list(Arrays.copyOf(datas, dataIndex));
                }
            }
            if(!line.isEmpty() && !line.equals("list")) {
                datas[dataIndex] = line;
                dataIndex++;
                printLine();
                echo(line);
                printLine();
            }
            line = in.nextLine();
        }
        printLine();
        System.out.println("SQUAWK! See you next time! :)");
        printLine();
    }
}
