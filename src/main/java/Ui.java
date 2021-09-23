public class Ui {

    /**
     * Prints welcome message when user logs into Duke. Also checks if flag "phase" is set to 1,
     * before printing prompt for user to access storage
     *
     *
     * @return two different print statements which will be printed if requirements are met
     */
    public static void welcomeMessage(){
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        if(StorageUI.phase == 1){
            System.out.println("Please type 'list' to access memory!");
        }
    }
}
