/** Main class for CaptainsLog.
 *  @author James Murphy
 *  @author Ryan Harris
 *  @author Josh Williams
 *  @author Stephen Wilson
 */

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

        // initialize required lists and users
        Menu menu = new Menu();
        User currentUser = new User();
        User searchedUser = new User();
        ArrayList<User> masterUserList = new ArrayList<>();
        ArrayList<Transmission> masterTransmissionList = new ArrayList<>();

        // load individual User info from file
        masterUserList = IO.loadUsers("usersFile");
        for (User u : masterUserList){
            u.loadUserLists(masterUserList);
        }

        // make every user follow themselves
        // before writing this info to file, remove u from following list to prevent duplicates
        for (User u : masterUserList){
            u.addFollowing(u);
        }

        // load transmissions from file
        masterTransmissionList = IO.loadTransmissions(
			"transmissionsFile", masterUserList);

        boolean isInMenu = true;
        boolean isLoggedIn = false;

        // get login status before displaying menu
        if (!isLoggedIn){

            currentUser = menu.login(in, masterUserList);

            if (currentUser != null){
                isLoggedIn = true;
            } else {
                System.out.println("Access denied.  Exiting...");
            }
        }

        // bulk of menu begins here

        while (isInMenu && isLoggedIn){
            System.out.println(
				"You have access to the following commands: ");
            System.out.print("compose \t\t delete \n" +
                    "sortByTime \t\t sortByPopularity \n" +
                    "searchForUser \t searchForTransmission" +
					"\t searchByHashtag \n" +"modifySettings \t logout \n");

            System.out.print("Enter your choice: \n>");
            switch(in.nextLine()) {

                // prints all transmissions and all users
                case "debug":
                    for (Transmission t : masterTransmissionList) {

                        System.out.println(t);
                    }

                    for (User u : masterUserList) {
                        System.out.println(u);
                        System.out.println();
                    }
                    Main.sleep(500);
                    break;

                // compose a transmission
                case "compose":
                    System.out.print("You're now creating a" +
					" transmission.\n What do you want to transmit? (reminder: double spacing is against the law)\n>");
                    String input = in.nextLine();
					
					// enforce charcount limit
					if (input.length() > 140) {
						System.out.println("Message too long!" +
							" Keep it under 140, ya pirate!");
						break;
					}

                    // add this transmission to the master list
                    masterTransmissionList.add(
						new Transmission(input, currentUser,
						true, masterUserList));

                    System.out.println("Transmission complete.  Returning to main menu.");
                    Main.sleep(500);
                    break;

                // display all currentUser transmissions then select for deletion
                case "delete":
                    System.out.println("Here are all of your" +
						" transmissions: ");

                    for (Transmission t : masterTransmissionList){
                        if (t.getAuthor().equals(currentUser)){

                            System.out.printf("[%s] %s: %s %n",
								masterTransmissionList.indexOf(t),
								t.getTimestamp(), t.getMessage());

                        }
                    }

                    System.out.print("Enter the index you want to " +
						"delete (-1 to cancel): \n>");
                    int index = Integer.valueOf(in.nextLine());

                    if (index == -1) {
                        System.out.println("Cancelling deletion. " +
							" Returning to main menu.");
                        break;

                    } else if (index <= masterTransmissionList.size()){
                        System.out.printf("Removing index [%d]: '%s'... %n",
							index,
							masterTransmissionList.get(index).getMessage());
                        masterTransmissionList.remove(index);
                        System.out.println("Removed.\n");
                    } else {
                        System.out.println("No transmission matches" +
							" the selected index.");
                    }
                    Main.sleep(500);
                    break;

                // standard view in chronological order
                case "sortByTime":
                    menu.sortByTime(currentUser, masterTransmissionList, in);
                    Main.sleep(500);
                    break;

                // view sorted by amount of favorites
                case "sortByPopularity":
                    menu.sortByPopularity(currentUser,
						masterTransmissionList, in);
                    Main.sleep(500);
                    break;

                // search for an exact username then change relationship with found user
                case "searchForUser":
                    searchedUser = menu.searchForUser(masterUserList, in);
                    if (searchedUser == null){

                        System.out.println("User not found.  Make sure the username is correct.");
                        break;
                    }

                    System.out.println("What would you like to do?\n" +
						"You have the following options: ");
                    System.out.print("follow \t unfollow \t block \t" +
						"unblock \n" + "viewHistory \n \n>");

                    // double switch statements are the new norm
                    // secondary menu
                    switch(in.nextLine()){

                        // currentUser follows searchedUser
                        case "follow":
                            if (!currentUser.getFollowing().contains(
								searchedUser)){
                                currentUser.addFollowing(searchedUser);
                                System.out.printf("Followed %s! %n", searchedUser.getDisplayName());
                            } else {
                                System.out.println(
									"You're already following this user.");
                            }
                            break;

                        // currentUser unfollows searchedUser
                        case "unfollow":
                            if (currentUser.getFollowing().contains(
								searchedUser)) {
                                currentUser.removeFollowing(searchedUser);
                                System.out.printf("Unfollowed %s! %n", searchedUser.getDisplayName());
                            } else {
                                System.out.println(
									"You're not following this user.");
                            }
                            break;

                        // currentUser blocks searchedUser
                        case "block":
                            if (!currentUser.getBlacklist().contains(
								searchedUser)) {

                                currentUser.block(searchedUser);
                                System.out.printf("Blocked %s! %n", searchedUser.getDisplayName());
                            } else {
                                System.out.println(
								"This user is already blocked.");
                            }
                            break;

                        // currentUser unblocks searchedUser
                        case "unblock":
                            if (currentUser.getBlacklist().contains(
								searchedUser)) {
                                currentUser.unblock(searchedUser);
                                System.out.printf("Unblocked %s! %n", searchedUser.getDisplayName());
                            } else {
                                System.out.println(
									"This user isn't blocked.");
                            }
                            break;

                        // view searchedUser's history
                        case "viewHistory":
                            for (Transmission t : masterTransmissionList){
                                if (t.getAuthor().equals(searchedUser)){
                                    System.out.println(t);
                                }
                            }
                            break;

                        default:
                            System.out.println("Command not recognized.");
                            break;
                    }

                    Main.sleep(500);
                    break;

                // search the master transmission list and return any that contain the search
                case "searchForTransmission":
                    System.out.print("Enter what you'd like to" +
						" search for: \n>");
                    menu.searchForTransmission(in.nextLine(),
						currentUser, masterTransmissionList);

                    Main.sleep(500);
                    break;

                // search master transmission list by a specific hashtag
                case "searchByHashtag":
                    menu.searchByHashtag(masterTransmissionList, in);

                    Main.sleep(500);
                    break;

                // logout and write info to files before exiting
                case "logout":

                    if (menu.logout(in)){
                        currentUser = null;

                        try {
                            IO.saveTransmissions("transmissionsFile",
								masterTransmissionList);

                            // remove user from their own following list to prevent duplicates
                            // then call saveUsers
                            for (User u : masterUserList){
                                u.removeFollowing(u);
                            }

                            IO.saveUsers("usersFile",
                                    masterUserList);

                        } catch (FileNotFoundException |
							UnsupportedEncodingException ex) {
                            System.err.println("Exception: " + ex);
                        }
                        System.exit(0);
                    }
                    break;

                // modify currentUser settings
                case "modifySettings":
                    menu.modifySettings(currentUser, in,
						masterTransmissionList);

                    Main.sleep(500);
                    break;

                default:
                    System.out.println("Command not recognized.");
                    System.out.println("");
                    Main.sleep(500);
            }
        }
    }
}