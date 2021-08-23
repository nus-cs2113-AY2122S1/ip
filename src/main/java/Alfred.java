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
        Scanner scanner = new Scanner(System.in);
        boolean isAppRunning = true;
        while (isAppRunning) {
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
            case "bye":
                isAppRunning = false;
                System.out.println(
                        "____________________________________________________________\n" +
                        " Very well sir, I shall leave you to your own devices.\n" +
                        "____________________________________________________________\n"
                );
                break;
            case "get the batmobile":
                System.out.println(
                        "____________________________________________________________\n" +
                        " At once, sir. I look forward to seeing you back in one piece, I hope.\n" +
                        "____________________________________________________________\n"
                );
                break;
            default:
                System.out.println(
                        "____________________________________________________________\n" +
                        " I'm sorry sir, I do not quite get what you mean by \"" +
                        userInput + "\".\n" +
                        "____________________________________________________________\n"
                );
            }
        }
    }
}
