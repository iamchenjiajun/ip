package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[✓]" : "[✗]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public abstract String toSaveString();

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
