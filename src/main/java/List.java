public class List {

    /*public static int[] listIcon = new int[100];
    listIcon = {0};*/
    public static void printList(){
        System.out.println();
        for(int i = 0;i<Add.getListLength();i++){
            //Task currentTask = new Task(Add.commandList[i]);
            System.out.println(i+1 +"."+"["+Task.getStatusIcon()+"]"+Add.commandList[i]);
        }
        System.out.println();
    }
}
