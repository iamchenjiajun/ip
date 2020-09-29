package duke.task;

/**
 * Represents an event created by the user.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toSaveString() {
        String isDoneString = (isDone ? "1" : "0");
        return "E | " + isDoneString + " | " + description + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
