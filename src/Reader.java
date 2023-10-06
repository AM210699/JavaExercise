import java.util.Scanner;

public class Reader {

    Scanner keyboard = new Scanner(System.in);

    public int readOptionInt() {
        int option = keyboard.nextInt();
        keyboard.nextLine();
        return option;
    }


    public String readOptionString() {
        String menuOptionSelected = keyboard.nextLine();

        return menuOptionSelected;
    }



}
