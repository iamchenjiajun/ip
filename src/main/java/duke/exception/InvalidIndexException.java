package duke.exception;

public class InvalidIndexException extends DukeException {
    private String errorMessage = null;

    public InvalidIndexException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
