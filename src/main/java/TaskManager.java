public class TaskManager {
    private Task[] tasks;
    private int tasksCount;

    public TaskManager() {
        tasks = new Task[100];
        tasksCount = 0;
    }

    public void printTasks() {
        for (int i = 0; i < tasksCount; i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks[i]);
        }
    }

    public void markAsDone(int index) {
        tasks[index].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index]);
    }

    public void addTask(String description) {
        tasks[tasksCount] = new Task(description);
        System.out.println("Added " + description + " as a Task.");
        System.out.println(tasks[tasksCount]);
        tasksCount++;
    }

    public void addDeadline(String description, String by) {
        tasks[tasksCount] = new Deadline(description, by);
        System.out.println("Added " + description + " as a Deadline.");
        System.out.println(tasks[tasksCount]);
        tasksCount++;
    }

    public void addTodo(String description) {
        tasks[tasksCount] = new Todo(description);
        System.out.println("Added " + description + " as a Todo.");
        System.out.println(tasks[tasksCount]);
        tasksCount++;
    }

    public void addEvent(String description, String at) {
        tasks[tasksCount] = new Event(description, at);
        System.out.println("Added " + description + " as an Event.");
        System.out.println(tasks[tasksCount]);
        tasksCount++;
    }
}
