/** Menu class for CaptainsLog.
 *  @author James Murphy
 *  @author Ryan Harris
 *  @author Josh Williams
 *  @author Stephen Wilson
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Menu {

    public void sortByPopularity(User currentUser,
		ArrayList<Transmission> masterTransmissionList) {

        ArrayList<Transmission> visibleTransmissions =
			getVisibleTransmissions(currentUser, masterTransmissionList);

        Collections.sort(visibleTransmissions, new TCompByPopularity());

        for (Transmission t : visibleTransmissions){

            System.out.printf("[%d favorites] %s", t.getNumFavorites(), t.toString());
        }
    }

    public void sortByTime(User currentUser,
		ArrayList<Transmission> masterTransmissionList) {

        ArrayList<Transmission> visibleTransmissions =
			getVisibleTransmissions(currentUser, masterTransmissionList);

        Collections.sort(visibleTransmissions, new TCompByTime());

        for (Transmission t : visibleTransmissions){
            System.out.println(t.toString());
        }
    }
	
    public User searchForUser(ArrayList<User> masterUserList, Scanner t) {

        System.out.println("Please enter the username of the user\n" +
			"that you would like to search for: ");
        String nm = t.nextLine();
        System.out.println(""); // padding
        for (User u : masterUserList){
            if (u.getUsername().equals(nm)){
                System.out.println("found user: " + u.toString());
                return u;
            }
        }
		return null;
    }
    public Transmission searchForTransmission(String transmission,
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
				" your search.");
        }

		return null; // added null return for compilation
    }
	
    public void searchByHashtag(ArrayList<Transmission> masterTransmissionList, Scanner t) {

        boolean notFound = true;

        System.out.print("Enter the hashtag you'd like searched" +
			" transmissions to contain: #");
        String hashtag = "#" + t.nextLine();

        for (Transmission h : masterTransmissionList) {

            if (h.getHashtags().contains(hashtag)) {
                System.out.printf("%s (%s): %s %n",
					h.getAuthor().getDisplayName(), h.getTimestamp(),
					h.getMessage());
                notFound = false;
            }
        }

        if (notFound) {

            System.out.printf("%s has not been used by anybody yet.",
				hashtag);
        }
    }
    
    public User login(Scanner y, ArrayList<User> masterUserList) {

        System.out.println("Welcome.  Enter your username" +
			" (or 0 if you're not a user yet): ");
        String input = y.nextLine();

        for (User u : masterUserList) {

            if (u.getUsername().equals(input)) {
                System.out.println("User successfully found. " +
                        " Enter your password: ");
                input = y.nextLine();

                if (u.isPasswordMatched(input)) {
                    System.out.println("Password successfully matched. " +
                            " Welcome, " + u.getUsername() + ".");
                    System.out.println();
                    return u;

                } else {
                    System.out.println("Password not matched.");
                }
            }
        }

            System.out.println("Username not found.  " +
                    "Would you like to register for an account?" +
					" (yes/no)\n> ");

            input = y.nextLine();
            switch(input){
                case "yes":

                    System.out.println("Good choice.  What do you want" +
						" your username to be? (IT IS PERMANENT) ");
                    String username = y.nextLine();

                    System.out.println("What about your display name?");
                    String displayname = y.nextLine();

                    System.out.println("Final thing.  Enter your" +
						" password: ");
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

                    System.out.println("You didn't select 'yes' or 'no'." +
						"  The gods frown upon you.  Begone, demon.");
                    System.exit(2);
                    break;

            }
        return null;
    }
    
    public boolean logout(Scanner y) {
    	System.out.println("Are you sure you want to log out? (yes/no) ");
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
                        System.out.println("Enter the display name you" +
							" want: ");
                        currentUser.setDisplayName(in.nextLine());
                        System.out.println("Your new display name is " +
						currentUser.getDisplayName());
						System.out.println("");
                        break;

                    case "changePassword":
                         System.out.println("Enter your current" +
							"password: ");
                         String oldPass = in.nextLine();
                         if (currentUser.isPasswordMatched(oldPass)){
                             System.out.println("Password successfully " +
								"matched.");

                             System.out.println("Enter your new " +
								"password: ");
                             String newPass = in.nextLine();

                             System.out.println("Re-enter your new " +
								"password: ");
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
                        System.out.println("Enter the filepath for " +
						"a new ASCII photo: ");
                        String filepath = in.nextLine();

                        currentUser.setPhoto(filepath);

                        System.out.println(currentUser);
                        break;

                    case "exit":
                        System.out.println("Returning to main menu.\n");
                        break;

                    default:
                        System.out.println("Command not recognized.\n");
                        break;
                }
    }

    private ArrayList<Transmission> getVisibleTransmissions(
		User currentUser, ArrayList<Transmission> masterTransmissionList){

        ArrayList<Transmission> visibleTransmissions = new ArrayList<>();

        for (User u : currentUser.getFollowing()){
            for (Transmission t : masterTransmissionList){
				if (t.getAuthor().equals(u) &&
					!currentUser.getBlacklist().contains(u)) {
                	visibleTransmissions.add(t);
				}
            }
        }

        return visibleTransmissions;
    }
}
