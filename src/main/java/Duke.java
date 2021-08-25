import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String greetings = " Hello! I'm Duke\n" + " What can I do for you?\n";
        System.out.println(greetings);
        String[] stringArray = new String[100];
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        boolean flag = true;
        for(int j = 0; j < 100 && flag;){
            String str= sc.nextLine();
            int maxlen = 0;
            if(str.length()>maxlen) maxlen = str.length();
            if(str.equals("bye")){
                str = "Bye. Hope to see you again soon!";
                int length = str.length();
                System.out.print("      ");
                for(int i = 0; i < length; i++) System.out.print("_");
                System.out.println("");
                System.out.println("     |" + str + "|");
                System.out.print("     |");
                for(int i = 0; i < length; i++) System.out.print("_");
                System.out.println("|");
                flag = false;
            }else if(str.equals("list")){
                System.out.print("      ");
                for(int i = 0; i < maxlen + 2; i++) System.out.print("_");
                System.out.println("");
                for(j = 1; stringArray[j-1] != null; j++) {
                    System.out.println("     |" + j + ". " + stringArray[j-1] + "|");
                }
                System.out.print("     |");
                for(int i = 0; i < maxlen + 2; i++) System.out.print("_");
                System.out.println("|");
            }else{
                stringArray[j] = str;
                int length = str.length();
                System.out.print("      ");
                for(int i = 0; i < length + 7; i++) System.out.print("_");
                System.out.println("");
                System.out.println("     |added: " + str + "|");
                System.out.print("     |");
                for(int i = 0; i < length + 7; i++) System.out.print("_");
                System.out.println("|");
                j ++;
            }
        }
    }
}
