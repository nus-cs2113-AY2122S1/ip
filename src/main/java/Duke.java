import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =     "        _|       _|_|        _|_|_|      _|      _|    _|_|_|        _|_|_|\n"
                        + "        _|     _|    _|      _|    _|    _|      _|      _|        _|\n"
                        + "        _|     _|_|_|_|      _|_|_|      _|      _|      _|          _|_|\n"
                        + " _|     _|     _|    _|      _|    _|      _|  _|        _|              _|\n"
                        + "  _| _|    _|  _|    _|  _|  _|    _|  _|    _|    _|  _|_|_|  _|  _|_|_|\n";
        System.out.print("Hello from\n" + logo);
        String line = "\033[3m----------------------------------Just a rather very intelligent system---------\033[0m\n";

        System.out.println(line + "Good Evening Sir! I'm J.A.R.V.I.S");
        System.out.println("How can I help sir?");

        int numItems = 0;
        boolean saidBye = false;

        String[] tasks = new String[100];
        Scanner in = new Scanner(System.in);

        while (!saidBye) {

            String command = in.nextLine();

            if (command.equals("bye")) {
                saidBye = true;
                System.out.println("Thank you, have a nice day!");
            }
            else if (command.equals("list")) {
                for (int count = 0; count < numItems; count++) {
                    System.out.println(count + 1 + "." + tasks[count]);
                }
            }
            else {
                tasks[numItems] = command;
                System.out.println("Added: " + command);
                numItems++;
            }
        }
    }
}