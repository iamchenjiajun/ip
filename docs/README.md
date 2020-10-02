# Duke User Guide

Duke is an interactive task management program which allows the user to store and manage tasks using a command-line
interface.

## Launching Duke

Requirements: `Java 11`

After navigating to the folder containing `Duke.jar` in the shell, run the following command.

`java -jar Duke.jar`

Duke saves the task lists to a file with the relative directory `./data/duke.txt`.

:triangular_flag_on_post: If the directory and/or file does not exist when Duke launches, Duke creates the directory and/or file before
loading the file.

:triangular_flag_on_post: If the ticks and crosses are not displayed correctly, try running the command `chcp 65001` on Windows systems before
running `Duke.jar` with `java -Dfile.encoding=UTF-8 -jar Duke.jar`.

## Features 

### Adding Tasks: `todo`/`deadline`/`event`

Adds a task to the list of tasks.

There are several types of tasks that can be added, such as a Todo, Deadline or Event.

Usage:

`todo <description>` - Adds a todo to the list.

`deadline <description> /by <yyyy-MM-dd HH:mm>` - Adds a deadline to the list.

`event <description> /at <yyyy-MM-dd HH:mm>` - Adds an event to the list.

Example of adding a Todo:

`todo read book`

Output:

```
>>>>++----------------------------------
Added read book as a Todo.
[T][✗] read book
>>>>++----------------------------------
```

Example of adding a Deadline:

`deadline ip final submission /by 2020-10-02 23:59`

Output:

```
>>>>++----------------------------------
Added ip final submission as a Deadline.
[D][✗] ip final submission (by: Oct 02 2020 23:59)
>>>>++----------------------------------
```

Example of adding an Event:

`event consultation meeting with prof /at 2020-10-07 16:00`

Output:

```
>>>>++----------------------------------
Added consultation meeting with prof as an Event.
[E][✗] consultation meeting with prof (at: Oct 07 2020 16:00)
>>>>++----------------------------------
```

### Listing Tasks: `list`

Lists all the tasks that are tracked by Duke.

Usage:

`list` - Lists all tasks.

Example:

`list`

Output:

```
>>>>++----------------------------------
1.[E][✓] cs2113 meeting (at: Dec 05 2020 11:30)
2.[E][✗] consultation meeting with prof (at: Oct 07 2020 16:00)
3.[T][✗] set up a meeting with academic advisor
4.[T][✓] submit budget request
5.[D][✗] ip final submission (by: Oct 02 2020 23:59)
>>>>++----------------------------------
```

### Marking Tasks as "Done": `done`

Marks a task as "Done" at a given index.

:triangular_flag_on_post: The list is **one-indexed** and corresponds to the indexes on `list` command output.

Usage:

`done <index>` - Marks the task at the given index as "Done".

Example:

`done 1`

Output:

```
>>>>++----------------------------------
Nice! I've marked this task as done:
[E][✓] cs2113 meeting (at: Dec 05 2020 11:30)
>>>>++----------------------------------
```

### Deleting Tasks: `delete`

Deletes a task at a given index.

:triangular_flag_on_post: The list is **one-indexed** and corresponds to the indexes on `list` command output.

Usage:

`delete <index>` - Deletes the task at the given index.

Example:

`delete 7`

Output:

```
>>>>++----------------------------------
Noted. I've removed this task:
[T][✗] read book
Now you have 8 tasks in the list
>>>>++----------------------------------
```

### Finding Tasks: `find`

Prints a list of tasks with description matching a keyword.

:triangular_flag_on_post: The command uses **case-insensitive** matching.

Usage:

`find <search string>` - Searches the list for tasks that contains the search string in the description.

Example:

`find meeting`

Output:

```
>>>>++----------------------------------
Here's the tasks containing 'meeting':
1.[E][✓] cs2113 meeting (at: Dec 05 2020 11:30)
2.[E][✗] consultation meeting with prof (at: Oct 07 2020 16:00)
3.[T][✗] set up a meeting with academic advisor
>>>>++----------------------------------
```

### Listing tasks with given date: `date`

Prints an overview of tasks matching the given date.

Usage:

`date <yyyy-MM-dd>` - Searches the list for tasks that has a date yyyy-MM-dd.

Example:

`date 2020-10-07`

Output:

```
>>>>++----------------------------------
Here's an overview of Oct 07 2020
[E][✗] consultation meeting with prof (at: Oct 07 2020 16:00)
[E][✗] collect lab kit (at: Oct 07 2020 14:00)
>>>>++----------------------------------
```

### Exiting Duke: `bye`

Saves the existing list of tasks to a file and terminates the program.

Usage:

`bye` - Saves the task list and exits the program.

Example:

`bye`

Output:

```
>>>>++----------------------------------
Bye. Hope to see you again soon!
>>>>++----------------------------------
```

## Quick Command Reference

Action | Command | Example
------------ | ------------ | -------------
Add todo | `todo <description>` | `todo read book`
Add deadline | `deadline <description> /by <yyyy-MM-dd HH:mm>` | `deadline ip final submission /by 2020-10-02 23:59`
Add event | `event <description> /at <yyyy-MM-dd HH:mm>` | `event consultation meeting with prof /at 2020-10-07 16:00`
Listing tasks | `list` | `list`
Mark task as done | `done <index>` | `done 1`
Delete task | `delete <index>` | `delete 7`
Finding tasks | `find <search string>` | `find meeting`
Date overview | `date <yyyy-MM-dd>` | `date 2020-10-7`
Exit Duke | `bye` | `bye`
