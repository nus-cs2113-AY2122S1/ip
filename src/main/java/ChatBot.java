//import java.util.Scanner;
//
//public class ChatBot {
//    private static final Scanner in = new Scanner(System.in);
//
//    private static final String DIVIDER = "____________________________________________________________";
//    private static final int TASK_SIZE = 100;
//    private static final String MESSAGE_WELCOME = "How can I assist you? Type something below! :D";
//
//    /**
//     * Prints a divider for ChatBot
//     */
//    private void printDivider() {
//        System.out.println(DIVIDER);
//    }
//
//    private void startChat() {
//
//    }
//
//    /**
//     * Begins the task state until exit is called
//     */
//    public void startToDo() {
//        printDivider();
//        System.out.println("You have entered todo mode! Type exit to quit this mode.");
//        System.out.println("Current commands: list, done (number)");
//        printDivider();
//        Task[] tasks = new Task[TASK_SIZE];
//        int idx = 0;
//
//        while (true) {
//            String chatInput = in.nextLine();
//            printDivider();
//            String[] keywords = chatInput.toLowerCase().split(" ");
//            switch (keywords[0]) {
//            case "exit":
//                System.out.println("Thanks for using todo mode!");
//                printDivider();
//                return;
//            case "list":
//                for (int i = 0; i < list.length; i++) {
//                    if (list[i] == null) {
//                        break;
//                    }
//                    System.out.println((i + 1) + ". "
//                            + printDone(list[i].isDone()) + " "
//                            + list[i].getToDoItem());
//                }
//                break;
//            case "done":
//                int taskNumber = Integer.parseInt(keywords[1]) - 1;
//                try {
//                    list[taskNumber].setDone();
//                } catch (Exception e) {
//                    int range = 0;
//                    for (range = 0; range < list.length; range++) {
//                        if (list[range] == null) {
//                            break;
//                        }
//                    }
//                    System.out.println("Index not found! "
//                            + "Please enter a valid index from 1 to " + range);
//                    break;
//                }
//                String output = "Nice! I've marked this task as done!\n"
//                        + printDone(list[taskNumber].isDone())
//                        + " " + list[taskNumber].getToDoItem();
//                System.out.println(output);
//                break;
//            default:
//                list[idx] = new ListItem(chatInput);
//                idx++;
//                System.out.println("added: " + chatInput);
//                break;
//
//            }
//            printDivider();
//        }
//    }
//
//    /**
//     * Starts the chat and allows user to choose
//     * the functionality in this state.
//     *
//     * @param logo Logo/name of the bot to be printed
//     */
//    public void chatFunction(String logo) {
//
//        System.out.println("Hello from\n" + logo);
//        System.out.println("How can I assist you? Type something below! :D");
//
//        while (true) {
//            System.out.println("Current commands: todo, deadline, or just type bye to exit");
//            printDivider();
//
//            String chatInput = in.nextLine();
//            String[] keywords = chatInput.toLowerCase().split(" ");
//            switch (keywords[0]) {
//            case "bye":
//                printDivider();
//                System.out.println("Bye. Hope to see you again soon!");
//                printDivider();
//                return;
//            case "todo":
//                startToDo();
//                break;
//            case "echo":
//                startEcho();
//                break;
//            default:
//                printDivider();
//                System.out.println("Wrong command! Please select a mode.");
//            }
//        }
//    }
//}
