import java.util.ArrayList;

public class User extends Menu {

	private String username;
	private String displayName;
	private String[] photo;
	private ArrayList<Transmission> history;
	private ArrayList<User> blacklist;
	private ArrayList<User> followers;
	private ArrayList<User> following;
	private int password;
	

	public User(String usrnm, String dspnm, Object passwd) {
		history = new ArrayList<>();
		blacklist = new ArrayList<>();
		followers = new ArrayList<>();
		following = new ArrayList<>();
		
		photo = null;
		username = usrnm;
		displayName = dspnm;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String dN) {
		displayName = dN;
	}
	
	public ArrayList<String[]> getPhoto() {
		return photo;
	}
	
	public void setPhoto(String filename) {
		try {
			photo = getASCIIart(filename, true);
		} catch (IOException e) {
			photo = null;
		}
	}
<<<<<<< HEAD
	
	public void sortByTime(User currentUser) {

	} 
	public void sortByPopularity(User currentUser) {

	}
	public User searchForUser(String user) {

	}
	public Transmission searchForTransmission(String transmission){

	}
	public void searchByHashtag(String hashtag){

	}
	public boolean login(String password, User selectedUser){

	}
	public boolean logout(){

	}
	public void modifySettings(User currentUser){

	}
=======
    
    public ArrayList<Transmission> getHistory()
    {
        return history;
    }
>>>>>>> 0b980a0b818166d41570d5174fde14ca4a9d8267
}
