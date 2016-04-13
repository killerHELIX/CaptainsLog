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

    public void sortByPopularity(User currentUser, ArrayList<Transmission> masterTransmissionList) {

        ArrayList<Transmission> visibleTransmissions = getVisibleTransmissions(currentUser, masterTransmissionList);

        Collections.sort(visibleTransmissions, new TCompByPopularity());

        for (Transmission t : visibleTransmissions){

            System.out.printf("%s (%s): %s %n", t.getAuthor().getDisplayName(), t.getTimestamp(), t.getMessage());
        }
    }

    public void sortByTime(User currentUser, ArrayList<Transmission> masterTransmissionList) {

        ArrayList<Transmission> visibleTransmissions = getVisibleTransmissions(currentUser, masterTransmissionList);

        Collections.sort(visibleTransmissions, new TCompByTime());

        for (Transmission t : visibleTransmissions){
            System.out.println(t.getTimestamp() + ": " + t.getMessage());
        }
    }
	
    public User searchForUser(ArrayList<User> masterUserList, Scanner t) {

        System.out.println("Please enter the username of the user that you would like to search for: ");
        String nm = t.next();
            
        for (User u : masterUserList){
            
            if (u.getUsername().equals(nm)){
                System.out.println("found user: " + u.getUsername());
                return u;               
            }
        }
		return null;
    }
    public Transmission searchForTransmission(String transmission, User currentUser, ArrayList<Transmission> mtl) {

        ArrayList<Transmission> visibleTransmissions = getVisibleTransmissions(currentUser, mtl);
        boolean foundTransmission = false;

        for (Transmission t : visibleTransmissions) {

            if (t.getMessage().contains(transmission)) {

                System.out.println(t.getAuthor().getDisplayName() + " at " + t.getTimestamp() + ": " + t.getMessage());
                foundTransmission = true;
            }
        }

        if (!foundTransmission) {
            System.out.println("No transmission found containing your search.");
        }

		return null; // added null return for compilation
    }
	
    public boolean searchByHashtag(ArrayList<Transmission> masterTransmissionList, Scanner t) {
		
        System.out.println("Please enter the hashtag of the transmission that you would like to search for: ");
        String hashtag = t.next();
        
        for (Transmission h : masterTransmissionList) {
            
            if (h.getMessage().contains(hashtag)){
                
                System.out.println(h.getAuthor().getDisplayName() + " composed a transmission with your entered hashtag, and their transmission stated: " + h.getMessage());
                return true;
            }     
            else {
                
                System.out.println("The hashtag that you have entered: " + hashtag + " has not been used by any other users within CaptainsLog.");
                return false;
            }
        }
                return false;
    }
    
    public User login(Scanner y, ArrayList<User> masterUserList) {

        System.out.println("Welcome.  Enter your username: ");
        String input = y.next();

        for (User u : masterUserList){

            if (u.getUsername().equals(input)){

                System.out.println("User successfully found.  Enter your password: ");
                input = y.next();

                if (u.isPasswordMatched(input)){

                    System.out.println("Password successfully matched.  Welcome, " + u.getUsername() + ".");
                    System.out.println();
                    return u;

                } else {

                    System.out.println("Password not matched.");
                }
            }
        }

        return null;
    }
    
    public boolean logout(Scanner y) {
		
                System.out.println("Are you sure you want to logout? (yes/no) ");
                String inp = y.next();
                    
                    if (inp.equals("yes")) {
                        
                        System.out.println("Logout successful.  See you, space cowboy.");
                        return true;
                    } else if (inp.equals("no")) {
                    
                        System.out.println("Returning to main menu.");
                        return false;
                    }
                return false;             
    }
    
    public void modifySettings(User currentUser, Scanner in) {

                System.out.println("You have access to the following commands: \n" +
                        "changeDisplayname \t changePassword \t changePhoto \n" +
                        "currentInfo \n" +
                        "Which one do you want to change? ");

                switch(in.next()) {

                    case "currentInfo":

                        System.out.println("Username: " + currentUser.getUsername());
                        System.out.println("Display name: " + currentUser.getDisplayName());
                        System.out.println("Photo: " + currentUser.getPhoto());
                        System.out.println("Followers: " + currentUser.getFollowers());
                        System.out.println("Users Following: " + currentUser.getFollowing());
                        System.out.println("History: " + currentUser.getHistory());

                        break;


                    case "changeDisplayname":

                         System.out.println("Current display name: " + currentUser.getDisplayName());
                         System.out.println("Enter the display name you want: ");
                         currentUser.setDisplayName(in.next());
                         System.out.println("Your new display name is " + currentUser.getDisplayName());

                         break;

                    case "changePassword":

                         System.out.println("Enter your current password: ");
                         String oldPass = in.next();

                         if (currentUser.isPasswordMatched(oldPass)){

                             System.out.println("Password successfully matched.");
                             System.out.println("Enter your new password: ");
                             String newPass = in.next();

                             System.out.println("Re-enter your new password: ");
                             String confirm = in.next();

                             if (newPass.equals(confirm)){

                                 currentUser.setPassword(confirm);
                                 System.out.println("New password accepted.");

                             } else {
                                 System.out.println("New password does not match in both cases!");
                             }
                         }

                         break;
                     
                    case "changePhoto":
                         System.out.println("Enter the filepath for a new ASCII photo: ");
                         String filepath = in.next();
                         try {
                             String[] newPhoto = IO.getASCIIArt(filepath, true);

                         } catch (IOException ex) {

                             System.err.println(ex);
                         }

                         System.out.println("Enter new Photo");
                         break;

                    default:

                        System.out.println("Command not recognized.");
                        break;
                }
    }

    private ArrayList<Transmission> getVisibleTransmissions(User currentUser,
                                                            ArrayList<Transmission> masterTransmissionList){

        ArrayList<Transmission> visibleTransmissions = new ArrayList<>();

        for (User u : currentUser.getFollowing()){

            for (Transmission t : u.getHistory()){

                visibleTransmissions.add(t);
            }
        }

        return visibleTransmissions;
    }
}
