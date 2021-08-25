public class Duke {


    public static void main(String[] args) {


        String logo = " _ __ _   _  __ _ _ __ \n"
                + "| '__| | | |/ _` | '_ \\\n"
                + "| |  | |_| | (_| | | | |\n"
                + "|_|   \\__, |\\__,_|_| |_|\n"
                + "       __/ |            \n"
                + "      |___/             ";


        System.out.println("Hello from\n" + logo);
        System.out.println("How can I assist you? Type something below! :D\n");
        ChatBot ryan = new ChatBot();
        ryan.chatFunction();

    }
}
