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

        String[] toDoList = new String[100];
        int listIndex = 0;
        Scanner scanner = new Scanner(System.in);
        boolean isAppRunning = true;
        while (isAppRunning) {
            String userInput = scanner.nextLine();
            switch (userInput) {
            case "BYE":
            case "bye":
                isAppRunning = false;
                System.out.println(
                        "____________________________________________________________\n" +
                        " Very well sir, I shall leave you to your own devices.\n" +
                        "____________________________________________________________\n"
                );
                break;
            case "list":
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < listIndex; i++) {
                    System.out.print(i+1);
                    System.out.println(". " + toDoList[i]);
                }
                System.out.println("____________________________________________________________\n");
                break;
            default:
                toDoList[listIndex] = userInput;
                listIndex++;
                System.out.println(
                        "____________________________________________________________\n" +
                        " I shall put this in your schedule, Master Wayne: \"" +
                        userInput + "\".\n" +
                        "____________________________________________________________\n"
                );
            }
        }
    }
}
