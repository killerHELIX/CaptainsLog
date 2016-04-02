import java.io.IOException;
import java.util.ArrayList;

public class User extends Menu {

    private String username;
    private String displayName;
    private String[] photo;
    private ArrayList<Transmission> history;
    private ArrayList<User> blacklist;
    private ArrayList<User> followers;
    private ArrayList<User> following;
    private String password;
    
	public User (String usrnm, String dspnm, String passwd) {
		history = new ArrayList<>();
		blacklist = new ArrayList<>();
		followers = new ArrayList<>();
		following = new ArrayList<>();
		
		photo = null;
		username = usrnm;
		displayName = dspnm;
<<<<<<< HEAD
                password = passwd;
=======
		password = passwd;
>>>>>>> 42d0b29b35ba211607ba7fb45708bb808cda80fb
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
	
	public String[] getPhoto() {
		return photo;
	}
	
	public void setPhoto(String filename) {
		try {
			photo = getASCIIArt(filename, true);
		} catch (IOException e) {
			photo = null;
		}
	}
<<<<<<< HEAD
    
    public ArrayList<Transmission> getHistory()
    {
        return history;
    }
    
    public void addToHistory(Transmission tr)
    {
        
    }
=======
	
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
	
	public ArrayList<Transmission> getHistory() {
		return history;
	}
>>>>>>> 42d0b29b35ba211607ba7fb45708bb808cda80fb
}
