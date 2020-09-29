package duke.exception;

public class DateTimeFormatException extends DukeException {
    private String errorMessage = null;

    public DateTimeFormatException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
