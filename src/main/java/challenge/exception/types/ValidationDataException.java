package challenge.exception.types;

public class ValidationDataException extends BaseException {

    public ValidationDataException(String message) {
        super(message);
    }

    public ValidationDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
