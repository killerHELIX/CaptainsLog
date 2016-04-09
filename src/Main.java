import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Menu menu = new Menu();
        User currentUser;

        ArrayList<User> masterUserList = new ArrayList<>();
        ArrayList<Transmission> masterTransmissionList = new ArrayList<>();

        boolean isInMenu = true;
        boolean isLoggedIn = false;

        if (!isLoggedIn){

            currentUser = menu.login(in, masterUserList);

            if (currentUser != null){
                isLoggedIn = true;
            } else {
                System.out.println("Access denied.  Exiting...");
            }
        }

        while (isInMenu && isLoggedIn){

            System.out.println("Enter your choice: ");
            switch(in.next()){

                case "sortByTime":
                    System.out.println("you chose 'a' ");
                    break;

                case "sortByPopularity":
                    System.out.println("you chose 'b' ");
                    break;

                case "searchForUser":
                    // TODO

                    break;

                case "searchForTransmission":
                    // TODO

                    break;

                case "searchByHashtag":
                    // TODO

                    break;

                case "logout":
                    // TODO

                    break;

                case "modifySettings":
                    // TODO

                    break;

            }

        }
	}
}
