package duke.task;

import java.time.LocalDate;

/**
 * Represents a {@code Task} that contains a LocalDateTime field.
 */
public interface DatedTask {
    /**
     * Returns a LocalDate object obtained from the LocalDateTime field.
     *
     * @return LocalDate object.
     */
    LocalDate getLocalDate();
}
