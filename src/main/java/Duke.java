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

    public static void printTasks(Task[] tasks, int tasksCount) {
        for (int i = 0; i < tasksCount; i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks[i]);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isBye = false;
        Task[] tasks = new Task[100];
        int tasksCount = 0;

        greet();

        while (!isBye) {
            String line = in.nextLine();
            String command = line.split(" ")[0];
            String argumentString = line.replaceFirst(command + " ", "");
            showDivider();

            if (command.equals("bye")) {
                isBye = true;
                bye();
            } else if (command.equals("list")) {
                printTasks(tasks, tasksCount);
            } else if (command.equals("done")) {
                int listNumber = Integer.parseInt(line.split(" ")[1]);
                tasks[listNumber - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[listNumber - 1]);
            } else if (command.equals("todo")) {
                tasks[tasksCount] = new Todo(argumentString);
                System.out.println("Added " + argumentString + " as a Todo.");
                System.out.println(tasks[tasksCount]);
                tasksCount++;
            } else if (command.equals("deadline")) {
                String by = argumentString.split(" /by ")[1];
                String description = argumentString.replace(" /by " + by, "");
                tasks[tasksCount] = new Deadline(description, by);
                System.out.println("Added " + description + " as a Deadline.");
                System.out.println(tasks[tasksCount]);
                tasksCount++;
            } else if (command.equals("event")) {
                String at = argumentString.split(" /at ")[1];
                String description = argumentString.replace(" /at " + at, "");
                tasks[tasksCount] = new Event(description, at);
                System.out.println("Added " + line + " as an Event.");
                System.out.println(tasks[tasksCount]);
                tasksCount++;
            } else {
                tasks[tasksCount] = new Task(line);
                System.out.println("Added " + line + " as a Task.");
                System.out.println(tasks[tasksCount]);
                tasksCount++;
            }

            showDivider();
        }
    }
}
