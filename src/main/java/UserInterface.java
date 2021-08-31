import java.util.Scanner;

/**
 * UserInterface interacts with the user for input and output.
 */
public class UserInterface {

    /**
     * Returns input given from the user.
     * If the position is unset, NaN is returned.
     *
     * @return input from the user.
     */
    public String getInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    /**
     * Prints out message from programme to user.
     * If the position is unset, NaN is returned.
     *
     * @return output to the user.
     */
    public void printMessage(String commandMessage) {
        System.out.println(Message.printLine());
        System.out.println(commandMessage);
        System.out.println(Message.printLine());
    }


}
