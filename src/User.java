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
    private int password;
    
	public User (String usrnm, String dspnm, String passwd) {
		history = new ArrayList<>();
		blacklist = new ArrayList<>();
		followers = new ArrayList<>();
		following = new ArrayList<>();
		
		photo = null;
		username = usrnm;
		displayName = dspnm;
                password = passwd;
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
        	photo = IO.getASCIIArt(filename, true);
    	} catch (IOException e) {
			photo = null;
		}
	}
}
