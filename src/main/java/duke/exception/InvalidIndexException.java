package duke.exception;

/**
 * Represents an exception thrown when accessing an invalid index in the Duke program.
 */
public class InvalidIndexException extends DukeException {
    private String errorMessage = null;

    public InvalidIndexException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns the error message.
     *
     * @return Error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
