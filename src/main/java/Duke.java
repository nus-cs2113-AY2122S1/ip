import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Generated ASCII Art - https://patorjk.com/software/taag/#p=display&f=Dancing%20Font&t=Duke
        String logo = "  ____     _   _    _  __  U _____ u \n"
                + " |  _\"\\ U |\"|u| |  |\"|/ /  \\| ___\"|/ \n"
                + "/| | | | \\| |\\| |  | ' /    |  _|\"\n"
                + "U| |_| |\\ | |_| |U/| . \\u   | |___\n"
                + " |____/ u<<\\___/   |_|\\_\\   |_____|\n"
                + "  |||_  (__) )(  ,-,>> \\\\,-.<<   >>\n"
                + " (__)_)     (__)  \\.)   (_/(__) (__)";

        System.out.println(logo);

        // Level-0. Skeletal Barebone
        String greeting = "____________________________________________________________\n"
                + " Hello! Iron Man has probably the best assistant called Jarvis and Spiderman has hmmm, his tingly spidey senses.\n"
                + " But don't worry! You have me, Duke! I am your personal Sidekick that does \"something\"!\n"
                + " What is \"something\" you want me to do?\n"
                + "____________________________________________________________";
        String farewell = "____________________________________________________________\n"
                + " Bye, have a nice day! From your friendly neighbourhood assistant, Duke~ (it's time to DUDUDUKEEEE)\n"
                + "____________________________________________________________";
        System.out.println(greeting);

        // Level-1. Greet, Echo, Exit
        Scanner sc = new Scanner(System.in); // initialize the user input "scanner"
        String userCommand = "";
        String returnOutput = "";
        while (true){
            System.out.print(" What is your command : ");
            userCommand = sc.nextLine();
            if (userCommand.equalsIgnoreCase("Bye")){
                break;
            }
            returnOutput = "____________________________________________________________\n"
                    + " Here you go...\n "
                    + userCommand
                    + "\n"
                    + "____________________________________________________________";
            System.out.println(returnOutput);
        }

        System.out.println(farewell);
    }
}
