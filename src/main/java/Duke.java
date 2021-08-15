public class Duke {

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___| inc.\n";
    private final String spacer = "____________________________________________________________\n";
    private final String introMsg = "Hello! I'm Duke\nWhat can I do for you?\n";
    private final String endMsg = "Bye. Hope to see you again soon!\n";

    void begin(){
        System.out.print(logo+spacer+introMsg+spacer);
    }
    void end(){
        System.out.print(endMsg+spacer);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.begin();
        duke.end();
    }
}
