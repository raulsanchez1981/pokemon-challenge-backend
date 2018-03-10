package challenge.exception.types;


/**
 * Created by rsanchpa on 16/09/2017.
 */

public abstract class BaseException extends RuntimeException {
    private boolean traceException;

    public BaseException(String message) {
        this(message, true);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.traceException = traceException(cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
        this.traceException = traceException(cause);
    }

    public BaseException(Throwable cause, boolean traceException) {
        super(cause);
        this.traceException = traceException;
    }

    public BaseException(String message, boolean traceException) {
        super(message);
        this.traceException = traceException;
    }

    public BaseException(String message, Throwable cause, boolean traceException) {
        super(message, cause);
        this.traceException = traceException;
    }

    private static boolean traceException(Throwable cause) {
        return cause instanceof BaseException?((BaseException)BaseException.class.cast(cause)).isTraceException():true;
    }

    public boolean isTraceException() {
        return this.traceException;
    }
}