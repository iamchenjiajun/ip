package duke.task;

import duke.exception.DateTimeFormatException;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime atDateTime;

    public Event(String description, String at) throws DateTimeFormatException {
        super(description);
        try {
            this.atDateTime = LocalDateTime.parse(at, DATETIME_PARSE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(Ui.ERROR_DATE_FORMAT);
        }
    }

    public String toSaveString() {
        String isDoneString = (isDone ? "1" : "0");
        String dateTimeString = atDateTime.format(DATETIME_PARSE_FORMATTER);
        return "E | " + isDoneString + " | " + description + " | " + dateTimeString;
    }

    @Override
    public String toString() {
        String dateTimeString = atDateTime.format(DATETIME_PRINT_FORMATTER);
        return "[E]" + super.toString() + " (at: " + dateTimeString + ")";
    }
}
