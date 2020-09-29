package duke.task;

import duke.exception.DateTimeFormatException;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline created by the user.
 */
public class Deadline extends Task implements DatedTask {
    protected LocalDateTime byDateTime;

    public Deadline(String description, String by) throws DateTimeFormatException {
        super(description);
        try {
            this.byDateTime = LocalDateTime.parse(by, DATETIME_PARSE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(Ui.ERROR_DATETIME_FORMAT);
        }
    }

    /**
     * Returns the string representation of the {@code Deadline} to be saved.
     *
     * @return String representation of the {@code Deadline} to be saved.
     */
    @Override
    public String toSaveString() {
        String isDoneString = (isDone ? "1" : "0");
        String dateTimeString = byDateTime.format(DATETIME_PARSE_FORMATTER);
        return "D | " + isDoneString + " | " + description + " | " + dateTimeString;
    }

    /**
     * Returns the string representation of the {@code Deadline} to be displayed.
     *
     * @return String representation of the {@code Deadline} to be displayed.
     */
    @Override
    public String toString() {
        String dateTimeString = byDateTime.format(DATETIME_PRINT_FORMATTER);
        return "[D]" + super.toString() + " (by: " + dateTimeString + ")";
    }

    @Override
    public LocalDate getLocalDate() {
        return byDateTime.toLocalDate();
    }
}
