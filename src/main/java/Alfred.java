import java.util.Scanner;

public class Alfred {
    public static void main(String[] args) {
        String logo =
                "**********************************\n" +
                "*     _    _  __              _  *\n" +
                "*    / \\  | |/ _|_ __ ___  __| | *\n" +
                "*   / _ \\ | | |_| '__/ _ \\/ _` | *\n" +
                "*  / ___ \\| |  _| | |  __/ (_| | *\n" +
                "* /_/   \\_\\_|_| |_|  \\___|\\__,_| *\n" +
                "**********************************\n";
        System.out.println(logo);
        System.out.println(
                "____________________________________________________________\n" +
                " Welcome back, Master Wayne.\n" +
                " How may I be of service to you?\n" +
                "____________________________________________________________\n"
        );

        Task[] toDoList = new Task[100];
        int listIndex = 0;
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (userInput != "BYE" || userInput != "bye") {
            if (userInput == "BYE" || userInput == "bye") {
                System.out.println(
                        "____________________________________________________________\n" +
                        " Very well sir, I shall leave you to your own devices.\n" +
                        "____________________________________________________________\n"
                );
                return;
            }

            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________\n");
                if (listIndex == 0) {
                    System.out.println("Your schedule is clear, Master Wayne.");
                }
                for (int i = 0; i < listIndex; i++) {
                    System.out.print(i+1);
                    System.out.println(".[" + toDoList[i].getStatusIcon() + "] " + toDoList[i].getDescription());
                }
                System.out.println("____________________________________________________________\n");
            } else if (userInput.startsWith("done")) {
                String[] destructuredInput = userInput.split(" ");
                int index = Integer.parseInt(destructuredInput[1]) - 1;
                toDoList[index].setTaskDone();
                System.out.println("____________________________________________________________\n");
                System.out.println(" Duly noted on completion of task, sir.");
                System.out.println("   [X] " + toDoList[index].getDescription());
                System.out.println("____________________________________________________________\n");
            } else {
                Task t = new Task(userInput);
                toDoList[listIndex] = t;
                listIndex++;
                System.out.println(
                        "____________________________________________________________\n" +
                        " I shall put this in your schedule, Master Wayne: \"" +
                        userInput + "\".\n" +
                        "____________________________________________________________\n"
                );
            }

            userInput = scanner.nextLine();
        }
    }
}
