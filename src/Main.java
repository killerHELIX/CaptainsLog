import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void sleep(int millis){

        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex){
        }
    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Menu menu = new Menu();
        User currentUser = new User();
        User searchedUser = new User();
        ArrayList<User> masterUserList = new ArrayList<>();
        ArrayList<Transmission> masterTransmissionList = new ArrayList<>();

        User user = new User ("username", "displayname", "password");
        User user2 = new User ("username2", "displayname2", "password2");
        User user3 = new User ("username3", "displayname3", "password3");
		
        masterUserList.add(user);
        masterUserList.add(user2);
        masterUserList.add(user3);
        
        user.addFollowing(user2);
        user3.addFollowing(user2);
        user2.addFollowing(user3);

        masterTransmissionList = IO.loadTransmissions("transmissionsFile", masterUserList);

/*        Transmission t1 = new Transmission("message1 from user to @user2",
                user, true, masterUserList);
        Transmission t2 = new Transmission("message2 from user2 to @user3",
                user2, true, masterUserList);
        Transmission t3 = new Transmission("message3 from user3 to @user",
                user3, true, masterUserList); 


        masterTransmissionList.add(t1);
        masterTransmissionList.add(t2);
        masterTransmissionList.add(t3); */

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

            System.out.println("You have access to the following commands: ");
            System.out.print("compose \n" +
                    "sortByTime \t\t sortByPopularity \n" +
                    "searchForUser \t searchForTransmission \t searchByHashtag \n" +
                    "modifySettings \t logout \n");

            System.out.println("Enter your choice: ");
            switch(in.next()){

                case "test":

                    for (Transmission t : masterTransmissionList){

                        System.out.println(t.getMessage());

                    }

                    break;

                case "compose":

                    break;

                case "sortByTime":
                    menu.sortByTime(currentUser, masterTransmissionList);
                    Main.sleep(500);
                    break;

                case "sortByPopularity":
                    menu.sortByPopularity(currentUser, masterTransmissionList);
                    Main.sleep(500);
                    break;

                case "searchForUser":
                    menu.searchForUser(masterUserList, in);
                    Main.sleep(500);
                    break;

                case "searchForTransmission":
                    System.out.println("Enter what you'd like to search for: ");
                    menu.searchForTransmission(in.next(), currentUser, masterTransmissionList);

                    break;

                case "searchByHashtag":
                    menu.searchByHashtag(masterTransmissionList, in);
                    break;

                case "logout":

                    if (menu.logout(in)){
                        currentUser = null;

                        try {
                            IO.saveTransmissions("transmissionsFile", masterTransmissionList);
                        } catch (FileNotFoundException|UnsupportedEncodingException ex){
                            System.err.println("Exception: " + ex);
                        }
                        System.exit(0);
                    }
                    break;

                case "modifySettings":

                    menu.modifySettings(currentUser, in);

                    break;

                default:

                    System.out.println("Command not recognized.");
                    System.out.println();
                    Main.sleep(500);

            }

        }
    }
}
