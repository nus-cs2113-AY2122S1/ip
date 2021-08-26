import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        int LIST_SIZE = 100;
        String[] lineStorage = new String[listSize];
        Boolean[] isDone = new Boolean[listSize];
        String line = null;
        int currentIndex = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        while (lineStorage[currentIndex] != "bye") {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(line != null && line.contains("done")){
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]);
                isDone[index] = true;
                System.out.println("Nice! I have marked this task as done!");
                System.out.println("[X] " + lineStorage[index]);
            } else if(line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= currentIndex; i++){
                    if(isDone[i] == false){
                       System.out.println(i + ". [] " + lineStorage[i]);
                    }else if (isDone[i] == true){
                     System.out.println(i + ". [X]" + lineStorage[i]);
                    }        
                }
                System.out.println("____________________________________________________________\n"); 
            } else{
                currentIndex++;
                lineStorage[currentIndex] = line;
                isDone[currentIndex] = false;
                System.out.println("____________________________________________________________\n");
                if(lineStorage[currentIndex].equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________\n");
                    return;
                } else{
                System.out.println("added: " + lineStorage[currentIndex]);
                System.out.println("____________________________________________________________\n");
                }
            }   
        }
    }
}
