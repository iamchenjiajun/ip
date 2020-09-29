package duke.task;

import java.time.LocalDate;

/**
 * Represents a {@code Task} that contains a LocalDateTime field.
 */
public interface DatedTask {
    LocalDate getLocalDate();
}
