package duke;

import duke.exception.FileFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class TaskManager {
    public static final String FILE_LOCATION = "./data/";
    public static final String FILE_NAME = "duke.txt";
    public static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int tasksCount;

    public TaskManager() {
        tasks = new Task[MAX_TASKS];
        tasksCount = 0;
    }

    public void checkFileExists() throws IOException {
        if (!Files.exists(Path.of(FILE_LOCATION))) {
            Files.createDirectories(Path.of(FILE_LOCATION));
        }
        if (!Files.exists(Path.of(FILE_LOCATION + FILE_NAME))) {
            Files.createFile(Path.of(FILE_LOCATION + FILE_NAME));
        }
    }

    public void loadTasks() throws FileNotFoundException, FileFormatException {
        File file = new File(FILE_LOCATION + FILE_NAME);
        Scanner in = new Scanner(file);
        while(in.hasNext()) {
            String line = in.nextLine();
            String[] taskDetails = line.split(" \\| ");

            // add the existing tasks
            switch(taskDetails[0]) {
            case "T":
                addTodo(taskDetails[2], false);
                break;
            case "D":
                addDeadline(taskDetails[2], taskDetails[3], false);
                break;
            case "E":
                addEvent(taskDetails[2], taskDetails[3], false);
                break;
            default:
                throw new FileFormatException();
                // Fallthrough
            }

            // mark task as done
            if (taskDetails[1].equals("1")) {
                markAsDone(getTasksCount() - 1, false);
            }
        }
    }

    public void saveTasks() throws IOException {
        FileWriter fw = new FileWriter(FILE_LOCATION + FILE_NAME);
        for (int i=0; i<getTasksCount(); i++) {
            fw.write(tasks[i].toSaveString() + System.lineSeparator());
        }
        fw.close();
    }

    public void printTasks() {
        for (int i = 0; i < tasksCount; i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks[i]);
        }
    }

    public void markAsDone(int index, boolean shouldPrintMessage) {
        tasks[index].markAsDone();
        if (shouldPrintMessage) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[index]);
        }
    }

    public void addDeadline(String description, String by, boolean shouldPrintMessage) {
        tasks[tasksCount] = new Deadline(description, by);
        if (shouldPrintMessage) {
            System.out.println("Added " + description + " as a Deadline.");
            System.out.println(tasks[tasksCount]);
        }
        tasksCount++;
    }

    public void addTodo(String description, boolean shouldPrintMessage) {
        tasks[tasksCount] = new Todo(description);
        if (shouldPrintMessage) {
            System.out.println("Added " + description + " as a Todo.");
            System.out.println(tasks[tasksCount]);
        }
        tasksCount++;
    }

    public void addEvent(String description, String at, boolean shouldPrintMessage) {
        tasks[tasksCount] = new Event(description, at);
        if (shouldPrintMessage) {
            System.out.println("Added " + description + " as an Event.");
            System.out.println(tasks[tasksCount]);
        }
        tasksCount++;
    }

    public int getTasksCount() {
        return tasksCount;
    }
}
