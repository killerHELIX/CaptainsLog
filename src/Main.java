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

        masterTransmissionList = IO.loadTransmissions("../transmissionsFile", masterUserList);

 /*       Transmission t1 = new Transmission("message1 from user to @user2 #fun",
                user, true, masterUserList);
        Transmission t2 = new Transmission("message2 from user2 to @user3 #bikes",
                user2, true, masterUserList);
        Transmission t3 = new Transmission("message3 from user3 to @user #sand",
                user3, true, masterUserList); 


        masterTransmissionList.add(t1);
        masterTransmissionList.add(t2);
        masterTransmissionList.add(t3);*/

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
            System.out.print("compose \t\t delete \n" +
                    "sortByTime \t\t sortByPopularity \n" +
                    "searchForUser \t searchForTransmission \t searchByHashtag \n" +
                    "modifySettings \t logout \n");

            System.out.println("Enter your choice: ");
            switch(in.nextLine()){

                case "test":

                    for (Transmission t : masterTransmissionList){

                        System.out.printf("[%s] %s %n", masterTransmissionList.indexOf(t), t.getMessage());

                    }

                    Main.sleep(500);
                    break;

                case "compose":

                    System.out.println("You're now creating a transmission.  What do you want to transmit?");
                    String input = in.nextLine();

                    masterTransmissionList.add(new Transmission(input, currentUser, true, masterUserList));

                    Main.sleep(500);
                    break;

                case "delete":

                    System.out.println("Here are all of your transmissions: ");

                    for (Transmission t : masterTransmissionList){

                        if (t.getAuthor().equals(currentUser)){

                            System.out.printf("[%s] %s: %s %n", masterTransmissionList.indexOf(t), t.getTimestamp(), t.getMessage());

                        }
                    }

                    System.out.println("Enter the index you want to delete (-1 to cancel): ");
                    int index = Integer.valueOf(in.nextLine());

                    if (index == -1){
                        System.out.println("Cancelling deletion.  Returning to main menu.");
                        break;

                    } else if (index <= masterTransmissionList.size()){

                        System.out.printf("Removing index [%d]: '%s'...", index, masterTransmissionList.get(index).getMessage());
                        masterTransmissionList.remove(index);
                        System.out.println("Removed.");

                    } else {

                        System.out.println("No transmission matches the selected index.");
                    }

                    Main.sleep(500);
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
                    searchedUser = menu.searchForUser(masterUserList, in);

                    System.out.println("What would you like to do? You have the following options: ");
                    System.out.print("follow \t unfollow \t block \t unblock \n" +
                            "viewHistory \n \n");

                    switch(in.nextLine()){

                        case "follow":

                            if (!currentUser.getFollowing().contains(searchedUser)){

                                currentUser.addFollowing(searchedUser);

                            } else {

                                System.out.println("You're already following this user.");
                            }

                            break;

                        case "unfollow":

                            if (currentUser.getFollowing().contains(searchedUser)){

                                currentUser.removeFollowing(searchedUser);
                            } else {

                                System.out.println("You're not following this user.");
                            }

                            break;

                        case "block":

                            if (!currentUser.getBlacklist().contains(searchedUser)){

                                currentUser.block(searchedUser);

                            } else {

                                System.out.println("This user is already blocked.");
                            }

                            break;

                        case "unblock":

                            if (currentUser.getBlacklist().contains(searchedUser)) {

                                currentUser.unblock(searchedUser);
                            } else {

                                System.out.println("This user isn't blocked.");
                            }

                            break;

                        case "viewHistory":

                            for (Transmission t : masterTransmissionList){

                                if (t.getAuthor().equals(searchedUser)){

                                    System.out.printf("%s: %s %n", t.getTimestamp(), t.getMessage());
                                }
                            }

                            break;

                        default:

                            System.out.println("Command not recognized.");
                            break;
                    }


                    Main.sleep(500);
                    break;

                case "searchForTransmission":
                    System.out.println("Enter what you'd like to search for: ");
                    menu.searchForTransmission(in.next(), currentUser, masterTransmissionList);

                    Main.sleep(500);
                    break;

                case "searchByHashtag":
                    menu.searchByHashtag(masterTransmissionList, in);

                    Main.sleep(500);
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

                    menu.modifySettings(currentUser, in, masterTransmissionList);

                    Main.sleep(500);
                    break;

                default:

                    System.out.println("Command not recognized.");
                    System.out.println();
                    Main.sleep(500);

            }

        }
    }
}
