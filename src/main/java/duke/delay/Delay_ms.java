package duke.delay;

/**
 * This class lets the user specify the amount of time which the thread will
 * do "sleep" for a specified duration in ms and then return the control to the
 * caller.
 *
 * @author YEOWEIHNGWHYELAB
 */

public class Delay_ms {
    /**
     * Causes a delay in execution of code for specified duration
     * before returning control back to the caller of this method.
     *
     * @param ms The amount of millisecond to delay execution.
     */
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
