import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        line = in.nextLine();
        String[] list = new String[100];
        int size = 0;


        while ( !line.equals("bye") ) {

            if( line.equals("list")) {
                for( int i = 0 ; i < size ; i ++ ) {
                    int no = i + 1;
                    System.out.println( no + ". " + list[i] );
                }
            }
            else {
                System.out.println("     added: " + line);
                list[ size ] = line;
                size ++;
            }

            line = in.nextLine();

        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
