
import java.util.InputMismatchException;

public class Main {

    public static boolean isProceeding = true;
    public  static final int OPTION_1 = 1;
    public  static final int  OPTION_2 = 2;
    public  static final  int OPTION_3 = 3;
    public  static final int OPTION_4 = 4;
    public  static final int  OPTION_5 = 5;


    private static TasksLists tasksLists = new TasksLists();


    public static void main(String... args) {
        while(isProceeding) {
            menu();

        }
    }

    public static void menu() {
        System.out.println("------------------------------------------");
        System.out.println(" Create your list of tasks for today :)");
        System.out.println("------------------------------------------");

        System.out.println(" 1. Create a new list of Tasks ");
        System.out.println(" 2. Check the List of Tasks ");
        System.out.println(" 3. Visualize the Tasks of the list");
        System.out.println(" 4. Update the List of Tasks ");
        System.out.println(" 5. Remove the List of Tasks ");
        System.out.println(" 6. Finish ");
        System.out.println("------------------------------------------");


        System.out.println("------------------------------------------");
        System.out.println(" Please enter an option: ");
        System.out.println("------------------------------------------");

        Reader reader = new Reader();
        try {
            chooseMenuOption(reader.readOptionInt());
        } catch (InputMismatchException e) {
            System.out.println("Error: InputMismatchException - Type a valid option.");

        }

    }


    public static void chooseMenuOption(int optionSelected) {
        System.out.printf("%n%nLa opción seleccionada es: %d%n", optionSelected);


        switch (optionSelected) {
            case OPTION_1:
                tasksLists.createListOfTasks();
                break;

            case OPTION_2:
                tasksLists.checkTasksListsStored();
                break;

            case OPTION_3:
                tasksLists.visualizeTasksOfTheList();

                break;

            case OPTION_4:
                tasksLists.updateListOfTasks();

                break;

            case OPTION_5:
                tasksLists.removeListOfTasks();

                break;


            default:
                System.out.println(" Option 6 selected - Finish");
                continueWithTheOperations();
                break;
        }

    }

    private static boolean continueWithTheOperations() {
        System.out.println(" Do you want to continue? Yes or No");

        Reader reader = new Reader();
        String continueWithTheOperations = reader.readOptionString();


        if(isValidTheOption(continueWithTheOperations)) {
            isProceeding = true;
        } else {
            isProceeding = false;
            System.out.println("Finishing App.....");
            System.out.println("Bye bye :) ");
        }

        return isProceeding;

    }


    private static boolean isValidTheOption(String continueWithTheOperations) {
        return continueWithTheOperations.toLowerCase().contentEquals("yes");
    }


}