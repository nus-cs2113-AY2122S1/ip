public class List {
    public static void printList(){
        System.out.println();
        for(int i = 0;i<Add.getListLength();i++){
            System.out.println(i+1 +"."+Add.commandList[i]);
        }
        System.out.println();
    }
}
