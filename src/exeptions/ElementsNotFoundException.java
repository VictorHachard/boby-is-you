package exeptions;

/**
 *
 * @author Glaskani
 */
public class ElementsNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ExceptionsElements</code> without detail
     * message.
     */
    public ElementsNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ExceptionsElements</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ElementsNotFoundException(String msg) {
        super(msg);
    }
}
