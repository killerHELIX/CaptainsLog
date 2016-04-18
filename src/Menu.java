/** Menu class for CaptainsLog.  Responsible for most functionality regarding the menus.
 *  @author James Murphy
 *  @author Ryan Harris
 *  @author Josh Williams
 *  @author Stephen Wilson
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Menu {

    /**
     * @param currentUser the current user
     * @param masterTransmissionList the master transmission list
     * @param in a Scanner
     * @return true if display is successful, otherwise false
     */
    public boolean sortByPopularity(User currentUser,
		ArrayList<Transmission> masterTransmissionList, Scanner in) {

        Collections.sort(masterTransmissionList, new TCompByPopularity());

        ArrayList<Transmission> visibleTransmissions =
			getVisibleTransmissions(currentUser, masterTransmissionList);


        if (visibleTransmissions.isEmpty()){
            System.out.println("No transmissions to see here.  Go follow somebody!");
            return false;
        }

        for (Transmission t : visibleTransmissions){

            System.out.printf("[%d] [%d favorites] %n%s", visibleTransmissions.indexOf(t), t.getNumFavorites(),
                    t.toString());
        }

        System.out.print("Enter the number of the transmission you want to favorite/unfavorite " +
                "(-1 to return to main menu) \n>");

            int index = Integer.valueOf(in.nextLine());

            if (index == -1){
                System.out.println("Returning to main menu...");

            } else if (index <= visibleTransmissions.size()){

                if (visibleTransmissions.get(index).getUsersWhoLike().contains(currentUser)){

                    visibleTransmissions.get(index).isNoLongerLikedBy(currentUser);

                    System.out.println("Unfavorited!");

                } else {

                    visibleTransmissions.get(index).isLikedBy(currentUser);

                    System.out.println("Favorited!");
                }

            } else {
                System.out.println("No transmission matches this index!");

            }

        return true;
        }


    /**
     * @param currentUser the current user
     * @param masterTransmissionList the master transmission list
     * @param in a Scanner
     * @return true if display is successful, otherwise false
     */
    public boolean sortByTime(User currentUser,
		ArrayList<Transmission> masterTransmissionList, Scanner in) {

        ArrayList<Transmission> visibleTransmissions =
			getVisibleTransmissions(currentUser, masterTransmissionList);

        Collections.sort(visibleTransmissions, new TCompByTime());

        if (visibleTransmissions.isEmpty()){
            System.out.println("No transmissions to see here.  Go follow somebody!");
            return false;
        }

        for (Transmission t : visibleTransmissions){

            System.out.printf("[%d] %n%s", visibleTransmissions.indexOf(t),
                    t.toString());
        }

        System.out.print("Enter the number of the transmission you want to favorite/unfavorite " +
                "(-1 to return to main menu) \n>");

        int index = Integer.valueOf(in.nextLine());

        if (index == -1){
            System.out.println("Returning to main menu...");

        } else if (index <= visibleTransmissions.size()){

            if (visibleTransmissions.get(index).getUsersWhoLike().contains(currentUser)){

                visibleTransmissions.get(index).isNoLongerLikedBy(currentUser);
                System.out.println("Unfavorited!");

            } else {

                visibleTransmissions.get(index).isLikedBy(currentUser);
                System.out.println("Favorited!");
            }

        } else {
            System.out.println("No transmission matches this index!");

        }

        return true;
    }

    /**
     *
     * @param masterUserList the master user list
     * @param t a Scanner
     * @return the searched user or null if not found
     */
    public User searchForUser(ArrayList<User> masterUserList, Scanner t) {

        System.out.print("\nPlease enter the username of the user\n" +
			"that you would like to search for: \n>");
        String nm = t.nextLine();
        System.out.println(""); // padding
        for (User u : masterUserList){
            if (u.getUsername().equals(nm)){
                System.out.println("found user: " + u.toString() + "\n");
                return u;
            }
        }
		return null;
    }

    /**
     *
     * @param transmission the string to search for
     * @param currentUser the current user
     * @param mtl the master transmission list
     */
    public void searchForTransmission(String transmission,
		User currentUser, ArrayList<Transmission> mtl) {
		
		System.out.println("");
		
        ArrayList<Transmission> visibleTransmissions =
			getVisibleTransmissions(currentUser, mtl);
        boolean foundTransmission = false;

        for (Transmission t : visibleTransmissions) {
            if (t.getMessage().contains(transmission)) {
                System.out.println(t.toString());
                foundTransmission = true;
            }
        }

        if (!foundTransmission) {
            System.out.println("No transmission found containing" +
				" your search.\n");
        }
    }

    /**
     * @param masterTransmissionList the master transmission list
     * @param t a Scanner
     */
    public void searchByHashtag(ArrayList<Transmission>
		masterTransmissionList, Scanner t) {

        boolean notFound = true;

        System.out.print("Enter the hashtag you'd like searched" +
			" transmissions to contain: #");
        String hashtag = "#" + t.nextLine();
        // padding
        System.out.println();
        for (Transmission h : masterTransmissionList) {
            if (h.getHashtags().contains(hashtag)) {
                System.out.printf("%s (%s): %s %n\n",
					h.getAuthor().getDisplayName(), h.getTimestamp(),
					h.getMessage());
                notFound = false;
            }
        }

        if (notFound) {

            System.out.printf("%s hasn't been used by anybody...yet.  Be the first.\n",
				hashtag);
        }
    }

    /**
     * @param y a Scanner
     * @param masterUserList the master user list
     * @return the searched user or null if not found
     */
    public User login(Scanner y, ArrayList<User> masterUserList) {

        System.out.print("Welcome.  Enter your username" +
			" (or 0 if you're not a user yet)\n> ");
        String input = y.nextLine();

        for (User u : masterUserList) {

            if (u.getUsername().equals(input)) {
                System.out.print("User successfully found. " +
                        " Enter your password: \n>");
                input = y.nextLine();

                if (u.isPasswordMatched(input)) {
                    System.out.println("Password successfully matched. " +
                            " Welcome, " + u.getUsername() + ".");
                    System.out.println();
                    return u;

                } else {
                    System.out.println("Password not matched.");

                    return null;
                }
            }
        }

        if (!input.equals("0")){
        System.out.println("Username not found.");
        }

        System.out.print("Would you like to register for an account?" +
        " (yes/no)\n> ");

        input = y.nextLine().toLowerCase();
        switch(input) {
            case "yes":
                System.out.print("Good choice.  What do you want" +
                        " your username to be? (IT IS PERMANENT) \n> ");
                String username = y.nextLine();

                System.out.print("What about your display name?\n> ");
                String displayname = y.nextLine();

                System.out.print("Final thing.  Enter your" +
                        " password: \n> ");
                String password = y.nextLine();

                User newUser = new User(username, displayname,
                        password);
                masterUserList.add(newUser);
                return newUser;

            case "no":
                System.out.println("Well, you're missing out." +
                        " Later, nerd.");
                return null;

            default:

                return null;

        }
    }

    /**
     * @param y a Scanner
     * @return true if confirmed to logout, otherwise false
     */
    public boolean logout(Scanner y) {
    	System.out.print("Are you sure you want to log out? (yes/no) \n> ");
        String inp = y.nextLine();
        if (inp.equals("yes")) {
			System.out.println("Logout successful. " +
				" See you, space cowboy.");
            return true;
        } else if (inp.equals("no")) {
        	System.out.println("Returning to main menu.");
        }
        return false;             
    }

    /**
     *
     * @param currentUser the current user
     * @param in a Scanner
     * @param mtl the master transmission list
     */
    public void modifySettings(User currentUser, Scanner in,
		ArrayList<Transmission> mtl) {

		System.out.print("You have access to the following commands: \n" +
        	    "changeDisplayname \t changePassword \t changePhoto \n" +
                "currentInfo \n exit \n" +
                "Which one do you want to change? \n> ");

                switch(in.nextLine()) {
                    case "currentInfo":
                        System.out.println("Username: " +
							currentUser.getUsername());

                        System.out.println("Display name: " +
							currentUser.getDisplayName());

                        System.out.println("Photo: " +
							currentUser.getPhoto());

                        System.out.println("Followers: " +
							currentUser.getFollowers());

                        System.out.println("Users Following: " +
							currentUser.getFollowing());

                        System.out.print("History: ");
                        for (Transmission t : mtl){
                            if (t.getAuthor().equals(currentUser)){
                                System.out.printf("%s: %s %n",
									t.getTimestamp(), t.getMessage());
                            }
                        }
						System.out.println("");
                        break;

                    case "changeDisplayname":
                        System.out.println("Current display name: " +
						currentUser.getDisplayName());
                        System.out.print("Enter the display name you" +
							" want: \n>");
                        currentUser.setDisplayName(in.nextLine());
                        System.out.println("Your new display name is " +
						currentUser.getDisplayName());
						System.out.println("");
                        break;

                    case "changePassword":
                         System.out.print("Enter your current" +
							"password: \n>");
                         String oldPass = in.nextLine();
                         if (currentUser.isPasswordMatched(oldPass)){
                             System.out.println("Password successfully " +
								"matched.");

                             System.out.print("Enter your new " +
								"password: \n>");
                             String newPass = in.nextLine();

                             System.out.print("Re-enter your new " +
								"password: \n>");
                             String confirm = in.nextLine();

                             if (newPass.equals(confirm)){

                                 currentUser.setPassword(confirm);
                                 System.out.println("New password " +
									"accepted.");

                             } else {
                             	System.out.println("New password does " +
									"not match in both cases!");
                             }
                         }
						 System.out.println("");
                         break;
                     
                    case "changePhoto":
                        System.out.print("Enter the filepath for " +
						"a new ASCII photo: \n>");
                        String filepath = in.nextLine();

                        currentUser.setPhoto(filepath);

                        System.out.println(currentUser + "\n");
                        break;

                    case "exit":
                        System.out.println("Returning to main menu.\n");
                        break;

                    default:
                        System.out.println("Command not recognized.\n");
                        break;
                }
    }

    /**
     *
     * @param currentUser the current user
     * @param masterTransmissionList the master transmission list
     * @return the list of visible transmissions specific to current user
     */
    private ArrayList<Transmission> getVisibleTransmissions(
		User currentUser, ArrayList<Transmission> masterTransmissionList){

        ArrayList<Transmission> visibleTransmissions = new ArrayList<>();

        // populate visible transmissions based on currentUser following
        for (User u : currentUser.getFollowing()){
            for (Transmission t : masterTransmissionList){
				if (t.getAuthor().equals(u) &&
					!currentUser.getBlacklist().contains(u)) {
                	visibleTransmissions.add(t);
				}
            }
        }

        // then populate visible transmissions based on @target from specific transmissions
        for (Transmission t : masterTransmissionList){

            if (t.getTargets().contains(currentUser) &&
                    !currentUser.getBlacklist().contains(t.getAuthor()) && !visibleTransmissions.contains(t)){
                visibleTransmissions.add(t);
            }
        }

        return visibleTransmissions;
    }
}
