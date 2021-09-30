package duke;
import java.util.Scanner;
/*
Ui reads the users input and prints the responses
*/
public class Ui {
    public Ui() {

    }

    public void greet(){
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void unknownError(){
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void fileNotFound(){
        System.out.println("File not found");
    }

    public String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void findWord(){
        System.out.println("Here are the matching tasks in your list:");
    }
 
}
