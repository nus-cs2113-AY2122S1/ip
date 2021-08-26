import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        Duke duke = new Duke();
        duke.greet();
        do {
            line = in.nextLine();
            line = line.toLowerCase();

            switch(line) {
            case "bye":
            case "end":
                duke.endDuke();
                break;

            case "hi":
            case "hello":
                duke.greet();
                break;
            case "list":
                duke.listOut();
                break;
            default:
                duke.addList(line);
                break;
            //default:
            //    duke.unknownAction();
            }
        }while(duke.getStatus());
    }
}
