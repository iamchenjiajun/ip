package duke.exception;

import duke.ui.Ui;

/**
 * Represents an exception thrown when the user input contains an unknown command.
 */
public class UnknownCommandException extends DukeException {
    /**
     * Returns the error message.
     *
     * @return Error message.
     */
    public String getErrorMessage() {
        return Ui.ERROR_UNKNOWN_COMMAND;
    }
}
