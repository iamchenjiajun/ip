package duke.exception;

/**
 * Represents an exception thrown when the date is in the wrong format.
 */
public class DateTimeFormatException extends DukeException {
    private String errorMessage = null;

    public DateTimeFormatException(String errorMessage) {
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
