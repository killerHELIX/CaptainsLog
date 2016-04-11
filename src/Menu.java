/** Menu class for CaptainsLog.
 *  @author James Murphy
 *  @author Ryan Harris
 *  @author Josh Williams
 *  @author Stephen Wilson
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Menu {

    public void sortByPopularity(User currentUser, ArrayList<Transmission> masterTransmissionList) {

        ArrayList<Transmission> visibleTransmissions = getVisibleTransmissions(currentUser, masterTransmissionList);

        Collections.sort(visibleTransmissions, new TCompByPopularity());

        for (Transmission t : visibleTransmissions){
            System.out.println(t.getMessage());
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
            else {
                System.out.println("The user in which you have entered cannot be found.");
                return null;
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
	
    public void searchByHashtag(ArrayList<Transmission> masterTransmissionList, Scanner t) {
		
        System.out.println("Please enter the hashtag of the transmission that you would like to search for: ");
        String hashtag = t.next();
        
        for (Transmission h : masterTransmissionList) {
            
            if (h.getMessage().contains(hashtag)){
                
                System.out.println(h.getAuthor().getDisplayName() + " composed a transmission with your entered hashtag, and their transmission stated: " + h.getMessage());
            }     
            else {
                System.out.println("The hashtag that you have entered: " + hashtag + " has not been used by any other users within CaptainsLog.");
                System.exit(0);
            }
        }
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
		
                System.out.println("Are you sure you want to logout?");
                String inp = y.next();
                    
                    if (inp.equals("yes")) {
                        
                        System.out.println("Logout was successful");
                        return true;
                    } else if (inp.equals("no")) {
                    
                        System.out.println("Continue browsing through CaptainsLog");
                        return false;
                    }
                return false;             
    }
    
    public void modifySettings(User currentUser, Scanner in, String DisplayName, 
            String Password, Boolean Photo) {
            
                System.out.println("Select a setting to Modify: Change Display "
                        + "Name.  Change Password.  Change Photo");
                switch(in.next())
                {
                     case "Change Display Name":
                         System.out.println("What would you like your new Display"
                                 + "Name to be?");
                         DisplayName = in.next();
                         System.out.println("Your new display name is " + DisplayName);
                         break;
                         
                     case "Change Password":
                         System.out.println("Enter new password");
                         Password = in.next();
                        
                         break;
                     
                     case "Change Photo":
                         break;
                         
                     
                }
                
                
                
       
       
		// TODO
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
