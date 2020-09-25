package duke;

import duke.exception.FileFormatException;
import duke.exception.InvalidArgumentException;
import duke.exception.UnknownCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {
    public static final String FILE_LOCATION = "./data/";
    public static final String FILE_NAME = "duke.txt";

    private TaskManager taskManager;
    private final Ui ui;
    private final Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_LOCATION, FILE_NAME);

        ui.greet();

        try {
            storage.checkFileExists();
            taskManager = new TaskManager(storage.loadTasks());
            ui.showLoadSuccessful();
        } catch (IOException e) {
            ui.showLoadError();
            taskManager = new TaskManager();
        } catch (FileFormatException e) {
            ui.showFileFormatError();
            taskManager = new TaskManager();
        } finally {
            ui.showDivider();
        }
    }

    public void run() {
        boolean isBye;
        Parser parser = new Parser(taskManager, ui, storage);

        do {
            String line = ui.readCommand();
            isBye = line.equals(Parser.COMMAND_BYE);

            ui.showDivider();
            try {
                parser.parseCommand(line);
            } catch (UnknownCommandException e) {
                System.out.println(e.getErrorMessage());
            } catch (InvalidArgumentException e) {
                System.out.println(e.getErrorMessage());
            }
            ui.showDivider();
        } while (!isBye);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
