import java.util.Scanner;

public class Duke {

    // Print logo
    public static void printLogo(){
        // Generated ASCII Art - https://patorjk.com/software/taag/#p=display&f=Dancing%20Font&t=Duke
        String logo = "  ____     _   _    _  __  U _____ u \n"
                + " |  _\"\\ U |\"|u| |  |\"|/ /  \\| ___\"|/ \n"
                + "/| | | | \\| |\\| |  | ' /    |  _|\"\n"
                + "U| |_| |\\ | |_| |U/| . \\u   | |___\n"
                + " |____/ u<<\\___/   |_|\\_\\   |_____|\n"
                + "  |||_  (__) )(  ,-,>> \\\\,-.<<   >>\n"
                + " (__)_)     (__)  \\.)   (_/(__) (__)";
        System.out.println(logo);
    }

    // Print Level-0. Skeletal Barebone, Greeting and Farewell
    public static void printGreeting(){
        String greeting = "____________________________________________________________\n"
                + " Hello! You probably know that Iron Man has the best AI-assistant called Jarvis and Spiderman has hmmm, maybe his tingly spidey senses?\n"
                + " But don't worry! You have me, Duke! I am your personal SIDEKICK that does \"something\"!\n"
                + " What is \"something\" you want me to do?\n"
                + "____________________________________________________________";
        System.out.println(greeting);
    }

    public static void printFarewell(){
        String farewell = "____________________________________________________________\n"
                + " Bye, have a nice day! From your friendly neighbourhood assistant, Duke~ (it's time to DUDUDUKEEEE)\n"
                + "____________________________________________________________";
        System.out.println(farewell);
    }

    public static void main(String[] args) {
        printLogo();
        printGreeting();
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
        printFarewell();
    }
}
