import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Menu {

    public void sortByPopularity(User currentUser, ArrayList<Transmission> masterTransmissionList) {

        ArrayList<Transmission> visibleTransmissions = new ArrayList<>();

        // populate visibleTransmissions list
        for (Transmission t : masterTransmissionList){
            if (currentUser.getFollowing().contains(t.getAuthor())){

                visibleTransmissions.add(t);
            }
        }

        Collections.sort(visibleTransmissions, new TCompByPopularity());

        for (Transmission t : visibleTransmissions){
            System.out.println(t.getMessage());
        }
    }
	
    public User searchForUser(String username, ArrayList<User> masterUserList) {

        for (User u : masterUserList){

            if (u.getUsername().equals(username)){

                System.out.println("found user: " + u.getUsername());

            }
        }
		return null; // added null return for compilation
    }
    public Transmission searchForTransmission(String transmission) {
		// TODO
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
}
