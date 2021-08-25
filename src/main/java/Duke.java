import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static class taskList{
        String[] list = new String[100];
        String[] doneList = new String[100];
        int lastItem = 0;
        public void printList(){
            for (int i=0; i < Arrays.stream(list).count(); i++){
                if (list[i] != null){
                    if (doneList[i] != null){
                        System.out.println((i+1) + ".[" + doneList[i] + "] " + list[i]);
                    }else{
                        System.out.println((i+1) + "[ ]" + list[i]);
                    }

                }else{
                    break;
                }
            }
        }
    }



    public static void main(String[] args) {
        //Implementing Increment Level-0: Initial skeleton of Duke
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        taskList newList = new taskList();
        while(true){
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(line.split(" ")[0].equals("bye")){
                break;
            }else if(line.split(" ")[0].equals("list")){
                newList.printList();
            }else if(line.split(" ")[0].equals("done")){
                int index = Integer.valueOf(line.split(" ")[1]);
                newList.doneList[index-1] = "X";
            }
            else{
                System.out.println("added: " + line);
                newList.list[newList.lastItem] = line;
                newList.lastItem++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
