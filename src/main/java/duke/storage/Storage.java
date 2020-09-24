package duke.storage;

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
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String fileLocation;
    private final String fileName;

    public Storage(String fileLocation, String fileName) {
        this.fileLocation = fileLocation;
        this.fileName = fileName;
    }

    public void checkFileExists() throws IOException {
        if (!Files.exists(Path.of(fileLocation))) {
            Files.createDirectories(Path.of(fileLocation));
        }
        if (!Files.exists(Path.of(fileLocation + fileName))) {
            Files.createFile(Path.of(fileLocation + fileName));
        }
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException, FileFormatException {
        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(fileLocation + fileName);
        Scanner in = new Scanner(file);
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] taskDetails = line.split(" \\| ");

            // add the existing tasks
            switch (taskDetails[0]) {
            case "T":
                tasks.add(new Todo(taskDetails[2]));
                break;
            case "D":
                tasks.add(new Deadline(taskDetails[2], taskDetails[3]));
                break;
            case "E":
                tasks.add(new Event(taskDetails[2], taskDetails[3]));
                break;
            default:
                throw new FileFormatException();
                // Fallthrough
            }

            if (taskDetails[1].equals("1")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }

        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(fileLocation + fileName);
        for (Task task : tasks) {
            fw.write(task.toSaveString() + System.lineSeparator());
        }
        fw.close();
    }
}
