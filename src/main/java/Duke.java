import java.util.Random;
import java.util.Scanner;

public class Duke {
    public static String addTaskSalutation(String item) {
        Random rand = new Random();
        String out = "";
        switch(rand.nextInt(3)) {
        case 0:
            out = "Right away sir. I have added " + item + " to your list of tasks.\n";
            break;
        case 1:
            out = "Understood sir. " + item + " has been added to your list of tasks.\n";
            break;
        case 2:
            out = "Sir, I can confirm that " + item + " has been added to your list of tasks.\n";
            break;
        default:
            break;
        }
        return out;
    }
    public static void line() {
        System.out.println("\t____________________________________________________________\n");
    }

    public static void showList(Tasks user) {
        for (int i = 0; i < user.listLength; i++) {
            System.out.println("\t" + (i + 1) + ". " + user.list[i] + "\n");
        }
    }
    public static void addTodo(String input, Tasks user) {
        user.list[user.listLength] = new Todo(input.substring(5));
        System.out.println("\t" + addTaskSalutation(input.substring(5)));
        user.listLength ++;
        user.tasksIncomplete ++;
        System.out.println("\tNow you have " + user.listLength + " tasks in the list, " + user.tasksIncomplete + " incomplete tasks.");
    }
    public static void addDeadline(String input, Tasks user) {
        user.list[user.listLength] = new Deadline(input.substring(9, input.indexOf("/") - 1), input.substring(input.indexOf("/") + 1));
        System.out.println("\t" + addTaskSalutation(input.substring(9, input.indexOf("/") - 1)));
        user.listLength ++;
        user.tasksIncomplete ++;
        System.out.println("\tNow you have " + user.listLength + " tasks in the list, " + user.tasksIncomplete + " incomplete tasks.");
    }
    public static void addEvent(String input, Tasks user) {
        user.list[user.listLength] = new Event(input.substring(6, input.indexOf("/") - 1), input.substring(input.indexOf("/") + 1));
        System.out.println("\t" + addTaskSalutation(input.substring(6, input.indexOf("/") - 1)));
        user.listLength ++;
        user.tasksIncomplete ++;
        System.out.println("\tNow you have " + user.listLength + " tasks in the list, " + user.tasksIncomplete + " incomplete tasks.");
    }
    public static void markDone(String input, Tasks user) throws ArrayIndexOutOfBoundsException, MultiMarkDoneException {
        int index = Integer.parseInt(input.substring(5)) - 1;
        if (index < user.listLength) {
            if (user.list[index].complete) {
                throw new MultiMarkDoneException();
            }
            user.list[index].markComplete();
            user.tasksIncomplete --;
            System.out.println("\tAs you wish sir. I have marked this task as done:\n\t[X] " + user.list[index].item + "\n\tNow you have " + user.tasksIncomplete + " incomplete tasks.");
        } else {
            throw new ArrayIndexOutOfBoundsException();

        }
    }
    public static void response(String input, Tasks user) throws IllegalCommandException, IllegalTaskException, DueDateFormatException, MultiMarkDoneException {
        String[] inputArr = input.split(" ");
        switch (inputArr[0]) {
        case ("list"):
            showList(user);
            break;
        case ("deadline"):
            if (inputArr.length < 2) {
                throw new IllegalTaskException();
            } else if (!input.contains("/")) {
                throw new DueDateFormatException();
            }
            addDeadline(input, user);
            break;
        case ("todo"):
            if (inputArr.length < 2) {
                throw new IllegalTaskException();
            }
            addTodo(input, user);
            break;
        case ("event"):
            if (inputArr.length < 2) {
                throw new IllegalTaskException();
            } else if (!input.contains("/")) {
                throw new DueDateFormatException();
            }
            addEvent(input, user);
            break;
        case ("done"):
            markDone(input, user);
            break;
        default:
            throw new IllegalCommandException();
        }
    }
    public static void greet(String logo) {
        System.out.println("\n" + logo);
        line();
        System.out.println("\tHello Sir,\n" + "\tWhat can I do for you?\n"); // greet
        line();
    }
    public static void exit() {
        System.out.println("Goodbye Sir. I shall be at your service again whenever you require.\n"); //exit message
        line();
    }
    public static void main(String[] args) {
        String logo = "                       ./((((((((((((((/.                    \n" +
                "                  /(((/.  .,*////*,.   *((((                \n" +
                "               (#(*  (.       ,((*.     #. ,#(#             \n" +
                "            .((/ .*    (*     ,   ./((((,  ,, *((,          \n" +
                "           ((/ /   .(        (##        (((,  ( *((.        \n" +
                "         .(( ..  .,         #####         .((,  , #(*       \n" +
                "        .(( ,   #          ####( ,,         /((  / ((,      \n" +
                "        ((  ,  (         ,####* /##(         *(( .. ((      \n" +
                "        (( /  ..        (####,  #####         #(. # ((.     \n" +
                "        /              %####     #####        #(.    #      \n" +
                "        ,  *   ,                  /####,      ((  (  /      \n" +
                "         (  *  /    ,###################(    ((* ,  ,       \n" +
                "         .   ,  *  /######################  ((* ..  ,       \n" +
                "           ,  #   (                       (((  (   .        \n" +
                "            (   (   ,/                 (((/  *.  #          \n" +
                "              (   *,    ,(*.  ,/((#((((,  ./   #            \n" +
                "                 #    (*              ,(    (.              \n" +
                "                    ,#                  (*                  \n" +
                "                           .,.   ,.                         \n" +
                "                                                            \n" +
                "                                                            \n" +
                "      ,/,     ,//      //////*    //      */. */.    */////*\n" +
                "      /@*    (@#@&            @@  .@&    %@*  #@*  &@       \n" +
                "      /@*   #@* .@@    @@@@@@@@*   .@@  @@,   #@*  .@@@@@@@.\n" +
                "      @@.  %@,    @@   @@   #@(      @@&@.    #@*         @&\n" +
                " */**.    */.      /*  **    ./*      */      ,*.  ,******  ";
        greet(logo);
        Tasks user = new Tasks();
        Scanner sc= new Scanner(System.in);
        String input = sc.nextLine();
        line();
        while (!input.equals("bye")) {
            try {
                response(input, user);
            } catch (IllegalCommandException e) {
                System.out.println("I am sorry sir. I am afraid I do not understand what you mean by " + input + ".");
            } catch (IllegalTaskException e) {
                System.out.println("I am sorry sir. However, I am unable to add an empty task.");
            } catch (DueDateFormatException e) {
                System.out.println("Sir, may i suggest putting a date to your task with the / indicator.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Sir, I am afraid that task you are trying to delete does not exist.");
            } catch (MultiMarkDoneException e) {
                System.out.println("Sir, this task has already been marked as done.");
            }
            line();
            input = sc.nextLine();
            line();
        }
        exit();
    }
}
