package duke.task;

/**
 * Represents a task created by the user.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes the description of the task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the String representing the state of the task.
     * A tick is returned if the task is done,
     * and a cross is returned if the task is not done.
     *
     * @return String showing if the task was done.
     */
    public String getStatusIcon() {
        return (isDone ? "[✓]" : "[✗]");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the task to be saved.
     *
     * @return String representation of the task to be saved.
     */
    public abstract String toSaveString();

    /**
     * Returns the string representation of the task to be displayed.
     *
     * @return String representation of the task to be displayed.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
