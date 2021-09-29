public class Ui {

    /**
     * Prints out the goodbye message.
     */
    public void printGoodbyeMessage() {
        String goodbyeMessage = "______________________________\n"
                + "Bye! Hope to see you again soon!\n"
                + "______________________________\n";
        System.out.print(goodbyeMessage);
    }

    /**
     * Prints out the welcome message.
     */
    public void printWelcomeMessage() {
        String welcomeMessage = "______________________________\n"
                + "Hello! I'm Friday\n"
                + "What are you doing today?\n"
                + "______________________________\n";
        System.out.print(welcomeMessage);
    }

    public void separator(){
        String separator = "______________________________\n";
        System.out.print(separator);
    }

    public void keywordListString(){
        separator();
        String introKeywordList = "Here are the matching results: \n";
        System.out.print(introKeywordList);
    }
}
