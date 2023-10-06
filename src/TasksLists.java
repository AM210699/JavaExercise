import model.Task;
import model.TaskList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class TasksLists {

    private Reader reader = new Reader();
    private ArrayList<TaskList> taskList = new ArrayList<>();
    private TaskManager taskManager = new TaskManager();
    private static final String FILE_NAME = "Lists.txt";


    public void displayTaskMenuOptions() {

        System.out.println("\n==Choose an option==\n");
        System.out.println("1. Add a new Task");
        System.out.println("2. Remove a Task");
        System.out.println("3. Check the task if is done");
        System.out.println("4. Go back");

    }

    public void createListOfTasks(){

        System.out.println("Create Task List Name: ");
        String taskListName = reader.readOptionString();

        TaskList TaskListName = new TaskList(taskListName);
        taskList.add(TaskListName);

        try {
            fillData();
        } catch (IOException e) {
            System.out.println("Error al guardar los datos en el archivo " + FILE_NAME);
        }

    }

    public void checkTasksListsStored() {
        System.out.println("Check Tasks  Lists");

        if(!validateListExistence()) {
            return;
        }

        for(int i = 0; i < taskList.size(); i++) {

            System.out.println("See Tasks Lists");
            System.out.println( i +1  + " Indicator " + " and " + " List Name: " + taskList.get(i).getTaskListName());
            System.out.printf("%d - %s%n", (i + 1), taskList.get(i).getTaskListName());

        }

    }

    public void visualizeTasksOfTheList() {

        System.out.println("See the tasks of the list: ");

        int index = validateIndex();

        if (index == 0) {
            return;
        }

        TaskList listOfAllTheTasks = taskList.get(index -1);

        if (listOfAllTheTasks.numberOfTasks() == 0) {
            System.out.println("There isn't any task in the list");
        }

        listOfAllTheTasks.showTasks();
    }


    public void updateListOfTasks() {

        int index = validateIndex();

        if(index == 0){
            return;
        }

        TaskList currentList = taskList.get(index -1);

        displayTaskMenuOptions();

        try {
        int selectedOption = reader.readOptionInt();

            switch (selectedOption){
                case 1:
                    Task newTask = taskManager.createNewTask();
                    currentList.addTask(newTask);

                    try {
                        fillData();
                    } catch (IOException e) {
                        System.out.println("Unable to save data in file: " + FILE_NAME);
                    }
                    break;
                case 2:
                    Task t1 = taskManager.removeTask(currentList);
                    if(t1 != null) {
                        System.out.println("the task was removed " + t1.getTaskName());
                        System.out.printf("The task was eliminated %s%n", t1.getTaskName());

                        try {
                            fillData();
                        } catch (IOException e) {
                            System.out.println("Error al guardar los datos en el archivo " + FILE_NAME);
                        }

                    }
                    else {
                        System.out.println("Unable to remove the task");
                    }
                    break;
                case 3:
                    Task t2 = taskManager.checkTaskAsDone(currentList);
                    if(t2 != null) {
                        System.out.println("The task: " + t2.getTaskName() + " was completed in " + t2.getDateRealization());
                        System.out.printf("The task %s was completed in %2$te of %2$tB of %2$tY%n", t2.getTaskName(), t2.getDateRealization());

                        try {
                            fillData();
                        } catch (IOException e) {
                            System.out.println("Unable to save data in file: " + FILE_NAME);
                        }

                    }else{
                        System.out.println("Unable to mark the task as completed");
                    }
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Unknow option");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: InputMismatchException - type a valid option.");
        }

        try {
            fillData();
        } catch (IOException e) {
            System.out.println("Unable to save data in file: " + FILE_NAME);
        }

    }

    public void removeListOfTasks() {
        System.out.println("Remove List of Tasks");

        int index = validateIndex();

        if(index == 0){
            return;
        }

        TaskList removedList = taskList.remove((index - 1));

        System.out.println("The list of tasks was removed: " + removedList.getTaskListName());
        System.out.printf("The list was removed successfully: %s%n", removedList.getTaskListName());

        try {
            fillData();
        } catch (IOException e) {
            System.out.println("Unable to save data in file:  " + FILE_NAME);
        }

    }



    private int validateIndex() {
        int index = 0;

        if (!validateListExistence()) {
            return index;
        }

        System.out.println("Indicate the index of the list.");
        index = reader.readOptionInt();

        if (index > taskList.size() || index < 0) {
            System.out.println(" The list index doesn't exist");
            index = 0;

        }

        return index;

    }

    private boolean validateListExistence() {
        if (taskList != null && !taskList.isEmpty()) {
            return true;
        }

        System.out.println(" There isn't any list  :c");
        return false;
    }

    public void fillData() throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, StandardCharsets.UTF_8))) {
            for (TaskList taskList : taskList) {
                // Escribir el nombre de la lista en el archivo
                writer.write("List: " + taskList.getTaskListName() + "\n");

                for (Task task : taskList.getTasks()) {
                    // Escribir el nombre de la tarea y su estado en el archivo
                    writer.write("Task: " + task.getTaskName()  + "\n");
                    writer.write(" Date Realization: " + task.getDateRealization() + "\n");


                    if(task.getIsDone() != null) {
                        writer.write(" State: " + (task.getIsDone() ? "Completed" : "Pending") + "\n");

                    }
                }
            }

            System.out.println("Data saved in: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Unable to save the data in the file" + FILE_NAME);
        }


    }



}
