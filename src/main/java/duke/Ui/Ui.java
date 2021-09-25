package duke.Ui;


import duke.ArtBot.Logo;

import java.util.Scanner;

public class Ui {

    private static final String MESSAGE_HI = "Hello! I'm Duke\n" + "What can I do for you?\n" + "!help for Command List\n";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!\n";

    public void Greetings(){
        System.out.println(Logo.logo +Logo.divider + MESSAGE_HI + Logo.dividerWithoutNewLine);
    }

    public void Farewell(){
        System.out.println(MESSAGE_BYE + Logo.bye);
    }

    public void showLine(){
        System.out.println(Logo.divider);
    }

    public void showLineWithoutNewLine(){
        System.out.println(Logo.dividerWithoutNewLine);
    }

    public String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine().toLowerCase().trim();
    }

    public void showError(String error){
        System.out.println(error);
    }
}

