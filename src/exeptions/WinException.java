package exeptions;

/**
 *
 * @author Windows
 */
public class WinException extends Exception {
    /**
     * Creates a new instance of <code>ExceptionsElements</code> without detail
     * message.
     */
    public WinException() {
                
    }

    /**
     * Constructs an instance of <code>ExceptionsElements</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public WinException(String msg) {
        super(msg);
    }
}
