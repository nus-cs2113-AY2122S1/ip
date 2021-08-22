import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        String line = "____________________________________________________________";

        //Duke himself
        System.out.println("⠄⠰⠛⠋⢉⣡⣤⣄⡉⠓⢦⣀⠙⠉⠡⠔⠒⠛⠛⠛⠶⢶⣄⠘⢿⣷⣤⡈⠻⣧");
        System.out.println("⢀⡔⠄⠄⠄⠙⣿⣿⣿⣷⣤⠉⠁⡀⠐⠒⢿⣿⣿⣿⣶⣄⡈⠳⢄⣹⣿⣿⣾⣿");
        System.out.println("⣼⠁⢠⡄⠄⠄⣿⣿⣿⣿⡟⠄⡐⠁⡀⠄⠈⣿⣿⣿⣿⣿⣷⣤⡈⠻⣿⣿⣿⣿");
        System.out.println("⢻⡀⠈⠄⠄⣀⣿⣿⣿⡿⠃⠄⡇⠈⠛⠄⠄⣿⣿⣿⣿⣿⣿⠟⠋⣠⣶⣿⣿⣿");
        System.out.println("⠄⢉⡓⠚⠛⠛⠋⣉⣩⣤⣤⣀⠑⠤⣤⣤⣾⣿⣿⣿⡿⠛⢁⣤⣾⣿⣿⣿⣿⣿");
        System.out.println("⠄⠈⠙⠛⠋⣭⣭⣶⣾⣿⣿⣿⣷⣦⢠⡍⠉⠉⢠⣤⣴⠚⢩⣴⣿⣿⣿⣿⣿⣿");
        System.out.println("⠄⢴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣭⣭⣭⣥⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        System.out.println("⠄⣴⣶⡶⠶⠶⠶⠶⠶⠶⠶⠶⣮⣭⣝⣛⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        System.out.println("⠄⠙⣿⡄⠄⠄⢀⡤⠬⢭⣝⣒⢂⠭⣉⠻⠿⣷⣶⣦⣭⡛⣿⣿⣿⣿⣿⣿⣿⣿");
        System.out.println("⠄⠄⠸⣿⡇⠄⠸⣎⣁⣾⠿⠉⢀⠇⣸⣿⣿⢆⡉⠹⣿⣿⢸⣿⣿⣿⣿⣿⣿⣿");
        System.out.println("⠄⠄⠄⣿⡇⠄⢀⡶⠶⠶⠾⠿⠮⠭⠭⢭⣥⣿⣿⣷⢸⣿⢸⣿⣿⣿⣿⣿⣿⣿");
        System.out.println("⠄⠄⠄⣿⡇⠄⠈⣷⠄⠄⠄⣭⣙⣹⢙⣰⡎⣿⢏⣡⣾⢏⣾⣿⣿⣿⣿⣿⣿⣿");
        System.out.println("⠄⠄⢰⣿⡇⠄⠄⢿⠄⠄⠈⣿⠉⠉⣻⣿⡷⣰⣿⡿⣡⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        System.out.println("⠄⠄⢸⣿⡇⠄⠄⠘⠿⠤⠤⠿⠿⠿⢤⣤⣤⡿⣃⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        System.out.println("⠄⠄⠘⢿⣷⣤⣄⣀⣀⣀⣀⣀⣠⣴⣾⡿⢋⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋");
        System.out.println(line);
        System.out.println("Poggers! I'm Duke");
        System.out.println("What can I do for you?\n");
        System.out.println(line);

        int listIndex = 0;
        int listLength = 100;
        Task[] Tasks = new Task[listLength];

        while (true) {
            command = in.nextLine();

            if (command.equalsIgnoreCase("list")) {
                //no tasks in list
                if (listIndex == 0) {
                    System.out.println("No tasks added yet! :)");
                } else {
                    //when there are tasks in list
                    for (int i = 1; i <= listIndex; i++) {
                        System.out.println(i + ".[" + Tasks[i - 1].getStatusIcon() + "] "
                                + Tasks[i - 1].getDescription());
                    }
                }
            } else if (command.equalsIgnoreCase("bye")) {
                //end the program
                System.out.println(line);
                System.out.println("Bye my little pogchamp! Hope to see you again soon!");
                System.out.println(line);
                System.exit(0);
            } else if(command.toLowerCase().contains("done")) {
                //extract particular list item to mark as done
                String taskdone = command.replaceAll("[^0-9]","");
                int index = Integer.parseInt(taskdone);
                //index cannot be <= 0
                if (index <= 0) {
                    System.out.println("Invalid index");
                } else if(index <= listIndex) {
                    //valid index
                    Tasks[index - 1].markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + Tasks[index - 1].getDescription());
                } else {
                    //not enough tasks to make it to that index
                    System.out.println("No task at that index");
                }
            } else {
                //add task
                if (listIndex < listLength) {
                    Tasks[listIndex] = new Task(command);
                    System.out.println(line);
                    System.out.println("added: " + Tasks[listIndex].getDescription());
                    System.out.println(line);
                    listIndex++;
                } else {
                    //>= 100 tasks
                    System.out.println("List is full! :(");
                }
            }
        }
    }
}
