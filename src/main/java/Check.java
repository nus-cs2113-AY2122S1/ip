//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import exceptions.NoDateDescException;
import exceptions.NoDateException;
import exceptions.NoDescException;

public class Check {
    public Check() {
    }

    public static void checkTodo(String content) throws NoDescException {
        if (content.equalsIgnoreCase("todo")) {
            throw new NoDescException();
        }
    }

    public boolean handleTodoException(String content) {
        try {
            checkTodo(content);
            return true;
        } catch (NoDescException var3) {
            System.out.println("There is no description in the todo input!");
            return false;
        }
    }

    public static void checkDeadline(String content) throws NoDescException, NoDateException, NoDateDescException {
        int byIndex = content.indexOf("/by");
        if (content.equalsIgnoreCase("deadline")) {
            throw new NoDateDescException();
        } else if (byIndex == -1) {
            throw new NoDateException();
        } else if (content.substring(0, byIndex - 1).equalsIgnoreCase("")) {
            throw new NoDescException();
        }
    }

    public boolean handleDeadlineException(String content) {
        try {
            checkDeadline(content);
            return true;
        } catch (NoDescException var3) {
            System.out.println("There is no description in the deadline input!");
            return false;
        } catch (NoDateException var4) {
            System.out.println("There is no date in the deadline input!");
            return false;
        } catch (NoDateDescException var5) {
            System.out.println("There is no date and description in the deadline input!");
            return false;
        }
    }

    public static void checkEvent(String content) throws NoDescException, NoDateException, NoDateDescException {
        int atIndex = content.indexOf("/at");
        if (content.equalsIgnoreCase("event")) {
            throw new NoDateDescException();
        } else if (atIndex == -1) {
            throw new NoDateException();
        } else if (content.substring(0, atIndex - 1).equalsIgnoreCase("")) {
            throw new NoDescException();
        }
    }

    public boolean handleEventException(String content) {
        try {
            checkEvent(content);
            return true;
        } catch (NoDescException var3) {
            System.out.println("There is no description in the event input!");
            return false;
        } catch (NoDateException var4) {
            System.out.println("There is no date in the event input!");
            return false;
        } catch (NoDateDescException var5) {
            System.out.println("There is no date and description in the event input!");
            return false;
        }
    }
}
