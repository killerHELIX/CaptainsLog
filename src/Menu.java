import java.util.ArrayList;

public class Menu {

    public void sortByTime(User currentUser,
		ArrayList<User> followingUsers) {

        ArrayList<Transmission> viewableTransmissions = new ArrayList<>();

        // Populate viewableTransmissions arrayList
        for (User u : followingUsers) {
            for (Transmission t : u.getHistory()) {
                viewableTransmissions.add(t);
            }
        }

        for (Transmission t : viewableTransmissions) {
            System.out.println(t.getMessage());
        }
    }
    public void sortByPopularity(User currentUser) {
		// TODO
    }
	
    public User searchForUser(String user) {
		// TODO
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
