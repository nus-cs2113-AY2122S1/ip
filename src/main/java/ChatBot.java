import java.util.Scanner;

public class ChatBot {
    public Scanner in = new Scanner(System.in);

    public void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public String printDone(boolean isDone) {
        String output;
        if (isDone) {
            output = "[X]";
        } else {
            output = "[ ]";
        }
        return output;
    }

    public void startEcho() {
        printDivider();
        System.out.println("You have entered echo mode! Type exit to quit this mode.");
        printDivider();

        while (true) {
            String chatInput = in.nextLine();
            printDivider();
            String[] keyword = chatInput.toLowerCase().split(" ");
            switch (keyword[0]) {
            case "exit":
                System.out.println("Thanks for using echo mode!");
                printDivider();
                return;
            default:
                System.out.println(chatInput);
                break;
            }
            printDivider();
        }
    }

    public void startToDo() {
        printDivider();
        System.out.println("You have entered todo mode! Type exit to quit this mode.");
        printDivider();
        ListItem[] list = new ListItem[100];
        int idx = 0;

        while (true) {
            String chatInput = in.nextLine();
            printDivider();
            String[] keyword = chatInput.toLowerCase().split(" ");
            switch (keyword[0]) {
            case "exit":
                System.out.println("Thanks for using todo mode!");
                printDivider();
                return;
            case "list":
                for (int i = 0; i < list.length; i++) {
                    if (list[i] == null) {
                        break;
                    }
                    System.out.println((i + 1) + ". " + printDone(list[i].isDone()) + " " + list[i].getToDoItem());
                }
                break;
            case "done":
                int taskNumber = Integer.parseInt(keyword[1]) - 1;
                try {
                    list[taskNumber].setDone();
                } catch (Exception e) {
                    int range = 0;
                    for (range = 0; range < list.length; range++) {
                        if (list[range] == null) {
                            break;
                        }
                    }
                    System.out.println("Index not found! Please enter a valid index from 1 to " + range);
                    break;
                }
                String output = "Nice! I've marked this task as done!\n" + printDone(list[taskNumber].isDone()) + " " + list[taskNumber].getToDoItem();
                System.out.println(output);
                break;
            default:
                list[idx] = new ListItem(chatInput);
                idx++;
                System.out.println("added: " + chatInput);
                break;

            }
            printDivider();
        }
    }

    public void chatFunction() {

        while (true) {
            System.out.println("Current commands: todo, echo, or just type bye to exit");
            printDivider();

            String chatInput = in.nextLine();
            String[] keyword = chatInput.toLowerCase().split(" ");
            switch (keyword[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                printDivider();
                return;
            case "todo":
                startToDo();
                break;
            case "echo":
                startEcho();
                break;
            default:
                System.out.println("Wrong command! Please select a mode.");

                printDivider();
            }
        }
    }
}
