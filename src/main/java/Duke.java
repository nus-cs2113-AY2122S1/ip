public class Duke {


    public static void main(String[] args) {

        //Customize logo of bot here
        String logo = " _ __ _   _  __ _ _ __ \n"
                + "| '__| | | |/ _` | '_ \\\n"
                + "| |  | |_| | (_| | | | |\n"
                + "|_|   \\__, |\\__,_|_| |_|\n"
                + "       __/ |            \n"
                + "      |___/             ";

        //Initialize ChatBot ryan
        ChatBot ryan = new ChatBot();
        ryan.chatFunction(logo);

    }
}
