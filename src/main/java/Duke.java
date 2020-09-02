import java.util.Scanner;

public class Duke {
    public static void showDivider() {
        System.out.println(">>>>++----------------------------------");
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showDivider();
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        showDivider();
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isBye = false;
        TaskManager taskManager = new TaskManager();

        greet();

        while (!isBye) {
            String line = in.nextLine();
            String command = line.split(" ")[0];
            String argumentString = line.replaceFirst(command + " ", "");
            String description;

            showDivider();

            switch (command) {
            case "list":
                taskManager.printTasks();
                break;
            case "bye":
                isBye = true;
                bye();
                break;
            case "done":
                int listNumber = Integer.parseInt(line.split(" ")[1]);
                taskManager.markAsDone(listNumber - 1);
                break;
            case "todo":
                taskManager.addTodo(argumentString);
                break;
            case "deadline":
                String by = argumentString.split(" /by ")[1];
                description = argumentString.replace(" /by " + by, "");
                taskManager.addDeadline(description, by);
                break;
            case "event":
                String at = argumentString.split(" /at ")[1];
                description = argumentString.replace(" /at " + at, "");
                taskManager.addEvent(description, at);
                break;
            default:
                taskManager.addTask(line);
                break;
            }

            showDivider();
        }
    }
}
