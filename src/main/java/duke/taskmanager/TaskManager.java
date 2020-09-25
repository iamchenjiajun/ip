package duke.taskmanager;

import duke.exception.InvalidArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks.get(i));
        }
    }

    public void deleteTask(int index) throws InvalidArgumentException {
        try {
            Task removedTask = tasks.get(index);
            tasks.remove(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            System.out.println("Now you have " + getTasksCount() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException(Ui.ERROR_DELETE_ARGUMENT);
        }
    }

    public void markAsDone(int index) throws InvalidArgumentException {
        try {
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException((Ui.ERROR_DONE_ARGUMENT));
        }
    }

    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
        System.out.println("Added " + description + " as a Deadline.");
        System.out.println(tasks.get(tasks.size() - 1));
    }

    public void addTodo(String description) {
        tasks.add(new Todo(description));
        System.out.println("Added " + description + " as a Todo.");
        System.out.println(tasks.get(tasks.size() - 1));
    }

    public void addEvent(String description, String at) {
        tasks.add(new Event(description, at));
        System.out.println("Added " + description + " as an Event.");
        System.out.println(tasks.get(tasks.size() - 1));
    }

    public int getTasksCount() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
