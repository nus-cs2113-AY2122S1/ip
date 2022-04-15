package Duke;
import Duke.Command;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        /*read information from text*/
        try {
            Scanner in = new Scanner(new File("lines.txt"));
            String command;
            while(in.hasNext()) {
                command = in.nextLine();
                if(command.charAt(3) == 'T'){
                    Command.addTodo(command);
                }
                if(command.charAt(3) == 'E'){
                    Command.addEvent(command);
                }
                if(command.charAt(3) == 'D'){
                    Command.addDeadline(command);
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        Ui.printLogo();
        Ui.greeting();
        Scanner userInput = new Scanner(System.in);
        boolean flag = true;
        String command;
        do{
            command = userInput.nextLine();
            flag = Parser.parse(command);
        } while (flag);
    }
}
