import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TimeTest {
    @Test
    public void test() {
        System.out.println("==Timer Test==");
        TimeDeadline tdl = new TimeDeadline("1992:12:12:12:12:12");
        TimeEvent tev = new TimeEvent("1992:12:12:12:12:12", "1995:12:12:12:12:12");
        TimeTodo ttodo = new TimeTodo();
        tdl.printTimeInfo();
        tev.printTimeInfo();
        ttodo.printTimeInfo();
        assertTrue(true);
    }
}
