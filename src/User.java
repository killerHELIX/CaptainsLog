import java.io.IOException;
import java.util.ArrayList;

public class User extends Menu {

	private String username;
	private String displayName;
	private String[] photo;
	private ArrayList<User> blacklist;
	private ArrayList<User> followers;
	private ArrayList<User> following;
	private String password;

    public User() {
        history = new ArrayList<>();
        blacklist = new ArrayList<>();
        followers = new ArrayList<>();
        following = new ArrayList<>();

        photo = null;
        username = "";
        displayName = "";
        password = "";

    }
	
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
		
	public ArrayList<User> getFollowers() {
		return followers;
	}
		
	public void addFollower(User fo) {
		followers.add(fo);
	}
		
	public ArrayList<User> getFollowing() {
		return following;
	} 

	public void addFollowing(User us) {
                us.addFollower(this);
		following.add(us);
	}

	public void removeFollowing(User us) {
		following.remove(us);
	}

	public boolean isPasswordMatched (String in) {
        return (in.equals(this.password));
    }
	
	@Override
	public String toString() {
		return username + " (" + displayName + ")";
	}
	
	public String toRecord() throws IndexOutOfBoundsException {
		String followers = "null", subscriptions = "null";
		String photo = "null";
		String blacklisted = "null";
		
		if (this.followers.size() != 0) {
			followers = this.followers.get(0).getUsername();
			for (int i = 1; i < this.followers.size(); i++) {
				followers += "," + this.followers.get(i).getUsername();
			}
		}
		
		if (this.follwing.size() != 0) {
			subscriptions = this.following.get(0).getUsername();
			for (int i = 1; i < this.following.size(); i++) {
				subscriptions += "," + this.following.get(i).getUsername();
			}
		}
		
		if (photo != null) {
			photo = String.join(",", this.photo);
		}
		
		if (blacklist.size() > 0) {
			blacklisted = "";
			for (int i = 0; i < blacklist.size(); i++) {
				blacklisted += blacklist[i].getUsername();
				if (i < blacklist.size() - 1) {
					blacklisted += ",";
				}
			}
		}
		
		return this.username + "\f" + this.displayName + "\f" + photo +
			"\f" + blacklisted + "\f" + followers + "\f" + subscriptions +
			"\f" + this.password;
	}
	
	public Author fromRecord(String record) {
		
	}
	
/*
	private String username;
	private String displayName;
	private String[] photo;
	private ArrayList<Transmission> history;
	private ArrayList<User> blacklist;
	private ArrayList<User> followers;
	private ArrayList<User> following;
	private String password;
*/
}
