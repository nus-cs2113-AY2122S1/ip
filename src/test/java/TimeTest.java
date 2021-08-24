import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import time.Time;

class TimeTest {
    @Test
    public void test() {
        System.out.println("==Timer Test==");
        Time t = new Time();
        t.printTimeInfo();
        assertTrue(true);
    }
}
