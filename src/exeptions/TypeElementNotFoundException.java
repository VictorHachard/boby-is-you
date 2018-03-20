package exeptions;

import model.TypeElement;

/**
 *
 * @author Glaskani
 */
public class TypeElementNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ExceptionsElements</code> without detail
     * message.
     */
    public TypeElementNotFoundException() {
                
    }

    /**
     * Constructs an instance of <code>ExceptionsElements</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TypeElementNotFoundException(String msg) {
        super(msg);
    }
}
