import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        String line = "____________________________________________________________";

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
        Task[] list = new Task[listLength];

        while(true){
            command = in.nextLine();

            if(command.equalsIgnoreCase("list")){
                if(listIndex == 0){
                    System.out.println("No tasks added yet! :)");
                }else {
                    for (int i = 1; i <= listIndex; i++) {
                        System.out.println(i + ".[" + list[i - 1].getStatusIcon() + "] " + list[i - 1].getDescription());
                    }
                }
            }else if(command.equalsIgnoreCase("bye")){
                System.out.println(line);
                System.out.println("Bye my little pogchamp! Hope to see you again soon!");
                System.out.println(line);
                System.exit(0);
            }else if(command.toLowerCase().contains("done")){
                String taskdone = command.replaceAll("[^0-9]","");
                int index = Integer.parseInt(taskdone);

                if(index <= 0){
                    System.out.println("Invalid index");
                }else if(index <= listIndex){
                    list[index - 1].markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + list[index - 1].getDescription());
                }else{
                    System.out.println("No task at that index");
                }
            }else{
                if(listIndex < listLength){
                    list[listIndex] = new Task(command);
                    System.out.println(line);
                    System.out.println("added: " + list[listIndex].getDescription());
                    System.out.println(line);
                    listIndex++;
                }else{
                    System.out.println("List is full! :(");
                }
            }
        }
    }
}
