import model.TaskList;
import model.*;

import java.time.LocalDate;

public class TaskManager {

    private final Reader reader = new Reader();

    public Task createNewTask() {
        System.out.println("New Task");
        System.out.println("Type the number of tasks you want to add into the list:");
        System.out.println("Task name: ");
        String taskNameString = reader.readOptionString();

        return new Task(taskNameString);
    }

    public Task removeTask(TaskList taskList) {
        System.out.println("Remove Task");

        System.out.println("Indicate the index of the task you want to remove");
        int index = reader.readOptionInt();

        if (index > taskList.numberOfTasks()) {
            System.out.println("The task doesn't exist");
            return null;
        }

        return taskList.removeTask(index -1);
    }

    public Task checkTaskAsDone(TaskList taskList) {
        System.out.println("End the task");

        System.out.println("Indicate the Task Index you want to mark as completed");
        int index = reader.readOptionInt();

        if (index > taskList.numberOfTasks() ) {
            System.out.println("The task index doesn't exist");
            return null;
        }

        Task task = taskList.getTasks().get(index -1 );
        task.setDateRealization(LocalDate.now());
        task.setIsDone(true);

        return task;
    }
}
