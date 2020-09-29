package duke.task;

import duke.exception.DateTimeFormatException;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an event created by the user.
 */
public class Event extends Task implements DatedTask {
    protected LocalDateTime atDateTime;

    public Event(String description, String at) throws DateTimeFormatException {
        super(description);
        try {
            this.atDateTime = LocalDateTime.parse(at, DATETIME_PARSE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(Ui.ERROR_DATETIME_FORMAT);
        }
    }

    /**
     * Returns the string representation of the {@code Event} to be saved.
     *
     * @return String representation of the {@code Event} to be saved.
     */
    @Override
    public String toSaveString() {
        String isDoneString = (isDone ? "1" : "0");
        String dateTimeString = atDateTime.format(DATETIME_PARSE_FORMATTER);
        return "E | " + isDoneString + " | " + description + " | " + dateTimeString;
    }

    /**
     * Returns the string representation of the {@code Event} to be displayed.
     *
     * @return String representation of the {@code Event} to be displayed.
     */
    @Override
    public String toString() {
        String dateTimeString = atDateTime.format(DATETIME_PRINT_FORMATTER);
        return "[E]" + super.toString() + " (at: " + dateTimeString + ")";
    }

    @Override
    public LocalDate getLocalDate() {
        return atDateTime.toLocalDate();
    }
}
