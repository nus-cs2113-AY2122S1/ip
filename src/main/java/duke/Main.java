package duke;
import duke.task.*;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String line;
        Duke duke = new Duke();
        //duke.greet();
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
                if(line.contains("done")) {
                    duke.markDone(line);
                    break;
                }
                if(line.contains("todo")) {
                    line = line.substring(5);
                    duke.addList(new Todo(line));
                    break;
                }
                if(line.contains("deadline")) {
                    String time = line.substring(line.indexOf("/")+1);
                    line = line.substring(9,line.indexOf("/")) + "(" + time + ")";
                    duke.addList(new Deadline(line,time));
                    break;
                }
                if(line.contains("event")) {
                    String time = line.substring(line.indexOf("/")+1);
                    line = line.substring(6,line.indexOf("/")) + "(" + time + ")";
                    duke.addList(new Event(line,time));
                    break;
                }
                else {
                    duke.unknownAction();
                }
            }
        }while(duke.getStatus());
    }
}
