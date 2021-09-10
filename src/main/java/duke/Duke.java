package duke;

import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.TaskManager;

import java.util.Scanner;

public class Duke {

    private static final Scanner IN = new Scanner(System.in);

    private enum Cmd {
        BYE, LIST, DONE;

        @Override
        public String toString(){
            return super.toString().toLowerCase();
        }
        private String getRegex(){
            return String.format("(?i)%s", this.toString());
        }
    }

    private static String getUserInput() {
        return IN.nextLine();
    }

    public static void main(String[] args) {
        Message.begin();
        while (true) {
            String userInput = getUserInput();
            try {
                if (userInput.matches(Cmd.BYE.getRegex())) {
                    break;
                } else if (userInput.matches(Cmd.LIST.getRegex())) {
                    TaskManager.printTasks();
                } else if (userInput.matches(Cmd.DONE.getRegex())) {
                    if(!userInput.matches(Cmd.DONE.getRegex()+" \\d+$")){
                        String message = "Wrong argument(s). Usage: %s <task number>";
                        message = String.format(message, Cmd.DONE.toString().toLowerCase());
                        throw new InvalidCommandException(message);
                    }
                    int id = Integer.parseInt(userInput.split(" ")[1]);
                    //id entered with index starting from '1' instead of '0'
                    TaskManager.taskDone(id - 1);
                } else if (userInput.matches(Task.Types.getTypesRegex())) {
                    TaskManager.newTask(userInput);
                } else {
                    String message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
                    throw new InvalidCommandException(message);
                }
            }
            catch (InvalidCommandException ive){
                Message.printWithSpacers(ive.getMessage());
            }

        }
        Message.end();
    }
}
