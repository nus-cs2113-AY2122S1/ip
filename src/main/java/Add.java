import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Add {
    private static String addString ="";
    public static String[] commandList = new String[100];

    public static int getListLength() {
        return listLength;
    }

    private static int listLength;

    public static String getAddString() {
        return addString;
    }

    public static void addCommand(){
        while (!Objects.equals(addString, "bye") ) {
            Scanner in = new Scanner(System.in);
            addString = in.nextLine();
            if (!Objects.equals(addString, "list") && !Objects.equals(addString, "bye") && !addString.contains("done")) {

                System.out.println();
                System.out.println("added:" + addString);
                System.out.println();
                listLength++;
                commandList[listLength - 1] = addString;
                Task.taskNotDone(commandList[listLength - 1]);
            }

            if (Objects.equals(addString, "bye")) {
                Bye.bye();
            } else if(Objects.equals(addString, "list")){
                List.printList();
            }
            else if(addString.contains("done")){
                Task.taskDone(commandList[Task.getListNumber()-1]);
            }
        }




    }
}
