package by.epam.spring.hometask.exception;

/**
 * Abstract exception class
 * It will inherit by all
 * application exception classes
 */
public class AbstractApplicationException extends Exception{

    /**
     * @param message
     *            exception message
     */
    public AbstractApplicationException(String message) {
        super(message);
    }

    /**
     * @param message
     *            exception message
     * @param innerEx
     *            Throwable inner exception instance, which is the cause of this
     *            exception
     */
    public AbstractApplicationException(String message, Throwable innerEx) {
        super(message, innerEx);
        initCause(innerEx);
    }

    /**
     * @param e
     *            exception
     */
    public AbstractApplicationException(Exception e) {
        super(e);
    }
}

