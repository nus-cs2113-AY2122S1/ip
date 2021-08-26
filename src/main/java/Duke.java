import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =  " _______  __   __  ______   _______  _______  _______  __   __  _______\n" +
                        "|       ||  | |  ||      | |       ||       ||   _   ||  |_|  ||   _   |\n" +
                        "|    ___||  | |  ||  _    ||    ___||_     _||  |_|  ||       ||  |_|  |\n" +
                        "|   | __ |  |_|  || | |   ||   |___   |   |  |       ||       ||       |\n" +
                        "|   ||  ||       || |_|   ||    ___|  |   |  |       ||       ||       |\n" +
                        "|   |_| ||       ||       ||   |___   |   |  |   _   || ||_|| ||   _   |\n" +
                        "|_______||_______||______| |_______|  |___|  |__| |__||_|   |_||__| |__|\n";
        String separator = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";

        System.out.println(separator);
        System.out.println("Hi... from GUDETAMA... so sleepy\n" + logo);
        System.out.println("Give me five more minutes..... What can I do for you?");
        System.out.println(separator);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            System.out.println(separator + "\n\t" + line + "\n" + separator);
            line = in.nextLine();
        }

        System.out.println(separator + "\nBye. I'm going back to sleep... zzz");
    }
}
