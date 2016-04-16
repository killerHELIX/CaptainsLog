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

            System.out.printf("[%d favorites] %s",
				t.getNumFavorites(), t.toString());
        }
		System.out.println("");
    }

    public void sortByTime(User currentUser,
		ArrayList<Transmission> masterTransmissionList) {

        ArrayList<Transmission> visibleTransmissions =
			getVisibleTransmissions(currentUser, masterTransmissionList);

        Collections.sort(visibleTransmissions, new TCompByTime());

        for (Transmission t : visibleTransmissions){
            System.out.println(t.toString());
        }
		System.out.println("");
    }
	
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
				" your search.\n");
        }

		return null; // added null return for compilation
    }
	
    public void searchByHashtag(ArrayList<Transmission>
		masterTransmissionList, Scanner t) {

        boolean notFound = true;

        System.out.print("Enter the hashtag you'd like searched" +
			" transmissions to contain: #");
        String hashtag = "#" + t.nextLine();
<<<<<<< HEAD
=======

        // padding
        System.out.println();

>>>>>>> 435b6eb5e8b8e61107cbf219c400e0f4c26a91b7
        for (Transmission h : masterTransmissionList) {
            if (h.getHashtags().contains(hashtag)) {
                System.out.printf("%s (%s): %s %n\n",
					h.getAuthor().getDisplayName(), h.getTimestamp(),
					h.getMessage());
                notFound = false;
            }
        }

        if (notFound) {

            System.out.printf("%s has not been used by anybody yet.\n",
				hashtag);
        }
    }
    
    public User login(Scanner y, ArrayList<User> masterUserList) {

        System.out.print("Welcome.  Enter your username" +
<<<<<<< HEAD
			" (or 0 if you're not a user yet)\n> ");
=======
			" (or 0 if you're not a user yet): \n> ");
>>>>>>> 435b6eb5e8b8e61107cbf219c400e0f4c26a91b7
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
                }
            }
        }

<<<<<<< HEAD
        System.out.print("Username not found.  " +
        	"Would you like to register for an account?" +
			" (yes/no)\n> ");

        input = y.nextLine().toLowerCase();
        switch(input){
            case "yes":
                System.out.print("Good choice.  What do you want" +
					" your username to be? (IT IS PERMANENT) \n> ");
                String username = y.nextLine();

                System.out.print("What about your display name?\n> ");
                String displayname = y.nextLine();

                System.out.print("Final thing.  Enter your" +
					" password: \n> ");
                String password = y.nextLine();
=======
            System.out.print("Username not found.  " +
                    "Would you like to register for an account?" +
					" (yes/no)\n> ");

            input = y.nextLine();
            switch(input){
                case "yes":

                    System.out.print("Good choice.  What do you want" +
						" your username to be? (IT IS PERMANENT) \n> ");
                    String username = y.nextLine();

                    System.out.print("What about your display name? \n>");
                    String displayname = y.nextLine();

                    System.out.print("Final thing.  Enter your" +
						" password: \n>");
                    String password = y.nextLine();
>>>>>>> 435b6eb5e8b8e61107cbf219c400e0f4c26a91b7

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
