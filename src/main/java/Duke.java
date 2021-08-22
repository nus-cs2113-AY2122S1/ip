import java.util.Scanner;

public class Duke {

    public static void printList( Task[] list, int size ) {
        int no = 1;
        for( int i = 0 ; i < size ; i ++ ) {
            System.out.print( no + ". ");
            no ++;

            if( list[i].getDone() ) {
                System.out.println("[X] " + list[i].getDescription());
            }
            else {
                System.out.println("[ ] " + list[i].getDescription());
            }

        }
    }

    public static void markTaskDone( Task[] list , String line ) {
        //get number
        String[] words = line.split(" ");
        int no = Integer.parseInt(words[1]);
        if (no > list.length) {
            return;
        }
        //mark task as done
        int index = no - 1;
        list[ index ].setDone();
        //print notification
        System.out.println("Nice I've marked this task as done:");
        System.out.println("  [X] " + list[ index ].getDescription());

    }

    public static void addTask( Task[] list, String line, int size) {
        System.out.println("     added: " + line);
        //add task to list
        Task newTask = new Task( line, size );
        list[size] = newTask;
    }


    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line = in.nextLine();

        Task[] list = new Task[100];
        int size = 0;

        while ( !line.equals("bye") ) {

            if( line.equals("list")) {
                printList( list , size);
            }
            else if ( line.substring(0, 4).equals("done") ) {
                markTaskDone( list, line);
            }
            else {
                addTask( list, line, size);
                size ++;
            }

            line = in.nextLine();

        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
