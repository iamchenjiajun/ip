package duke.exception;

/**
 * Represents an exception thrown when the user input contains invalid arguments.
 */
public class InvalidArgumentException extends DukeException {
    private String errorMessage = null;

    public InvalidArgumentException(String errorMessage) {
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
