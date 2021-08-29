import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String LOGO =  " _______  __   __  ______   _______  _______  _______  __   __  _______\n" +
                        "|       ||  | |  ||      | |       ||       ||   _   ||  |_|  ||   _   |\n" +
                        "|    ___||  | |  ||  _    ||    ___||_     _||  |_|  ||       ||  |_|  |\n" +
                        "|   | __ |  |_|  || | |   ||   |___   |   |  |       ||       ||       |\n" +
                        "|   ||  ||       || |_|   ||    ___|  |   |  |       ||       ||       |\n" +
                        "|   |_| ||       ||       ||   |___   |   |  |   _   || ||_|| ||   _   |\n" +
                        "|_______||_______||______| |_______|  |___|  |__| |__||_|   |_||__| |__|\n";
        String SEPARATOR = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";

        System.out.println(SEPARATOR);
        System.out.println("Hi... from GUDETAMA... so sleepy\n" + LOGO);
        System.out.println("Give me five more minutes..... What can I do for you?");
        System.out.println(SEPARATOR + "\n");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        Task[] userInputs = new Task[100];
        int userInputCount = 0;

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println(SEPARATOR + "\n\tTasks to do... so lazy:");

                for (int i = 0; i < userInputCount; i++) {
                    System.out.println("\t" + (i + 1) + ".[" + userInputs[i].getStatusIcon() + "] "+
                                        userInputs[i].getDescription());
                }
                System.out.println(SEPARATOR);
            } else if (line.startsWith("done")) {
                int taskIndexDone = Integer.parseInt(line.split(" ")[1]) - 1;
                userInputs[taskIndexDone].markAsDone();

                System.out.println(SEPARATOR + "\n\tfinished this task... I need a break:\n\t\t[" +
                                    userInputs[taskIndexDone].getStatusIcon() + "] " +
                                    userInputs[taskIndexDone].getDescription());
                System.out.println(SEPARATOR);
            } else {
                System.out.println(SEPARATOR + "\n\tadded: " + line + "\n" + SEPARATOR);
                userInputs[userInputCount] = new Task(line);
                userInputCount++;
            }
            line = in.nextLine();
        }

        System.out.println(SEPARATOR + "\nBye. I'm going back to sleep... zzz");
    }
}
