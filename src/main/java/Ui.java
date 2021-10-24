public class Ui {

    /**
     * Prints welcome message when user logs into Duke. Also checks if flag "phase" is set to 1,
     * before printing prompt for user to access storage
     *
     *
     * @return two different print statements which will be printed if requirements are met
     */
    public static void welcomeMessage(){
        System.out.println("\n" +
                " .----------------.  .----------------.  .----------------.  .----------------. \n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| |  ________    | || | _____  _____ | || |  ___  ____   | || |  _________   | |\n" +
                "| | |_   ___ `.  | || ||_   _||_   _|| || | |_  ||_  _|  | || | |_   ___  |  | |\n" +
                "| |   | |   `. \\ | || |  | |    | |  | || |   | |_/ /    | || |   | |_  \\_|  | |\n" +
                "| |   | |    | | | || |  | '    ' |  | || |   |  __'.    | || |   |  _|  _   | |\n" +
                "| |  _| |___.' / | || |   \\ `--' /   | || |  _| |  \\ \\_  | || |  _| |___/ |  | |\n" +
                "| | |________.'  | || |    `.__.'    | || | |____||____| | || | |_________|  | |\n" +
                "| |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------' \n");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        if(StorageUI.phase == 1){
            System.out.println("Please type 'list' to access memory!");
        }
    }
}
