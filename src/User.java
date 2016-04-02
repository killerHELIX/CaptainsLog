import java.util.ArrayList;

public class User implements Menu {

    private String username;
    private String displayName;
    private String[] photo;
    private ArrayList<Transmission> history;
    private ArrayList<User> blacklist;
    private ArrayList<User> followers;
    private ArrayList<User> following;
    private int password;
    
	public User(String usrnm, String dspnm, passwd) {
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
        dN = displayName;
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
}
