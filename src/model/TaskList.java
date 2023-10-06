package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class TaskList {

    private String taskListName;

    private List<Task> tasks = new ArrayList<>();
    private final LocalDate dateCreation;



    public TaskList(String name) {
        this.taskListName = name;
    }


    {
        this.dateCreation = LocalDate.now();
    }


    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int indice) {
        if (indice > tasks.size()) {
            System.out.println("-- The task is not located in the List --");
            return null;
        }

        return tasks.remove(indice);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int numberOfTasks() {
        return tasks.size();
    }

    public void showTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + 1 + " - " + task.getTaskName() + " Date realization: " +  task.getDateRealization());

            if (task.getIsDone() != null ) {
                System.out.println( " [" + (task.getIsDone() ? "█" : " ")  + "] " + (task.getIsDone() ? task.getDateRealization() : ""));
            }
            else {
                System.out.println((i + 1) + " - Task is null");
            }
        }
    }

    public String getTaskListName() {
        return taskListName;

    }

    public void setTaskListName(String taskListName) {
        this.taskListName = taskListName;
    }


    public LocalDate getDateCreation() {
        return dateCreation;
    }

}
