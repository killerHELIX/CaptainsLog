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

        String nm = t.next();
            
        for (User u : masterUserList){
            
            if (u.getUsername().equals(nm)){

                System.out.println("found user: " + u.getUsername());
                
            }
        }
		return null; // added null return for compilation
    }
    public Transmission searchForTransmission(String transmission) {

		return null; // added null return for compilation
    }
	
    public void searchByHashtag(String hashtag) {
		// TODO
    }
    public boolean login(String password, User selectedUser) {
		// TODO
		return false; // added false return for compilation
    }
    public boolean logout() {
		// TODO
		// XXX does this need a boolean return value?
		return false; // added false return for compilation
    }
    public void modifySettings(User currentUser) {
		// TODO
    }

    private ArrayList<Transmission> getVisibleTransmissions(User currentUser,
                                                            ArrayList<Transmission> masterTransmissionList){

        ArrayList<Transmission> visibleTransmissions = new ArrayList<>();

        for (User u : currentUser.getFollowing()){

            if (u.getHistory().isEmpty()){
                System.out.println(u.getDisplayName() + " has no Transmissions");
            }

            for (Transmission t : u.getHistory()){

                visibleTransmissions.add(t);
            }
        }

        return visibleTransmissions;
    }
}
