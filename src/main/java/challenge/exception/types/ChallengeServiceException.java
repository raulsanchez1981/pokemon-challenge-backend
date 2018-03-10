package challenge.exception.types;


public class ChallengeServiceException extends BaseException {

    public ChallengeServiceException(String message) {
        super(message);
    }

    public ChallengeServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
