package duke.task;

/**
 * Represents a deadline created by the user.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the {@code Deadline} to be saved.
     *
     * @return String representation of the {@code Deadline} to be saved.
     */
    @Override
    public String toSaveString() {
        String isDoneString = (isDone ? "1" : "0");
        return "D | " + isDoneString + " | " + description + " | " + by;
    }

    /**
     * Returns the string representation of the {@code Deadline} to be displayed.
     *
     * @return String representation of the {@code Deadline} to be displayed.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
