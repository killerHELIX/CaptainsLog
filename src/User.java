/** User class for CaptainsLog.
 *  @author James Murphy
 *  @author Ryan Harris
 *  @author Josh Williams
 *  @author Stephen Wilson
 */

import java.io.IOException;
import java.util.ArrayList;

public class User extends Menu {

	private String username;
	private String displayName;
	private String[] photo;
	private ArrayList<User> blacklist;
	private String[] blacklistLoad;
	private ArrayList<User> followers;
	private String[] followersLoad;
	private ArrayList<User> following;
	private String[] followingLoad;
	private String password;

    public User() {
        blacklist = new ArrayList<>();
        followers = new ArrayList<>();
        following = new ArrayList<>();

		blacklistLoad = null;
		followersLoad = null;
		followingLoad = null;

        photo = null;
        username = "";
        displayName = "";
        password = "";

    }

    /**
     * @param usrnm the desired username
     * @param dspnm the desired displayname
     * @param passwd the desired password
     */
	public User (String usrnm, String dspnm, String passwd) {
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
	
	public void setPhoto(String[] photo) {
		this.photo = photo;
	}
		
	public ArrayList<User> getFollowers() {
		return followers;
	}
		
	public void addFollower(User fo) {
		followers.add(fo);
	}
	
	public void removeFollower(User fo) {
		followers.remove(fo);
	}
	
	public ArrayList<User> getBlacklist() {
		return blacklist;
	}
	
	public void block(User u) {
		blacklist.add(u);
	}
	
	public void unblock(User u) {
		blacklist.remove(u);
	}
		
	public ArrayList<User> getFollowing() {
		return following;
	} 
	
	public void addFollowing(User us) {

        if (us != this){
            us.addFollower(this);
		    following.add(us);
        } else {
            following.add(us);
        }
	}

	public void removeFollowing(User us) {
		following.remove(us);
	}

    /**
     * Check if passwords match without compromising password security
     * @param in the string to check
     * @return true if password is equal to in, otherwise false
     */
	public boolean isPasswordMatched (String in) {
        return (in.equals(this.password));
    }

    /**
     * Small override to simplify code in the menu
     * @param u the user to compare to
     * @return true if usernames are equal
     */
	public boolean equals(User u) {
		return this.getUsername() == u.getUsername();
	}
	
	public void setPassword(String in){
        this.password = in;
    }

	@Override
	public String toString() {

        String portrait = "";
        if (photo != null) {
            portrait = String.join("\n", photo) + "\n";
        }
		return portrait +  username + " (" + displayName + ")";
	}
	
    /**
     * Creates record string for file storage
     * @return a string containing all information related to this user
     * @throws IndexOutOfBoundsException
     */
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
		
		if (this.following.size() != 0) {
			subscriptions = this.following.get(0).getUsername();
			for (int i = 1; i < this.following.size(); i++) {
				subscriptions += "," + this.following.get(i).getUsername();
			}
		}
		
		if (this.photo != null) {
			photo = String.join(",", this.photo);
		}
		
		if (blacklist.size() > 0) {
			blacklisted = "";
			for (int i = 0; i < blacklist.size(); i++) {
				blacklisted += blacklist.get(i).getUsername();
				if (i < blacklist.size() - 1) {
					blacklisted += ",";
				}
			}
		}
		
		return this.username + "\f" + this.displayName + "\f" + photo +
			"\f" + blacklisted + "\f" + followers + "\f" + subscriptions +
			"\f" + this.password;
	}
	
	// parse a line from the record file

    /**
     * Parses a line from a record file
     * @param record the line to be parsed
     * @return the User created from record info
     */
	public static User fromRecord(String record) {
		String[] fields = record.split("\f");
		String[] photo = fields[2].split(",");
		String[] blacklisted = fields[3].split(",");
		String[] followers = fields[4].split(",");
		String[] following = fields[5].split(",");
		
		User usr = new User(fields[0], fields[1], fields[6]);
		if (!photo[0].equals("null")) {
			usr.setPhoto((String[]) null);
		}
		usr.setUserLists(blacklisted, followers, following);
		return usr;
	}
	
	// load relational data after users and transmissions are loaded

    /**
     * Loads relational data after users and transmissions are loaded
     * @param blacklisted the list of blacklisted users
     * @param followers the list of followers
     * @param following the list of users following
     */
	private void setUserLists(String[] blacklisted, String[] followers,
		String[] following) {
		if (!blacklisted[0].equals("null")) {
			this.blacklistLoad = blacklisted;
		}
		if (!followers[0].equals("null")) {
			this.followersLoad = followers;
		}
		if (!following[0].equals("null")) {
			this.followingLoad = following;
		}
	}
	
	// call this in main() for each user in the master user list

    /**
     * @param userList the master list of users to search
     */
	public void loadUserLists(ArrayList<User> userList) {
		if (blacklistLoad != null) {
			for (String s : blacklistLoad) {
				for (User u : userList) {
					if (u.getUsername().equals(s)) {
						this.blacklist.add(u);
					}
				}
			}
		}
		
		if (followersLoad != null) {
			for (String s : followersLoad) {
				for (User u : userList) {
					if (u.getUsername().equals(s)) {
						this.followers.add(u);
					}
				}
			}
		}
		
		if (followingLoad != null) {
			for (String s: followingLoad) {
				for (User u : userList) {
					if (u.getUsername().equals(s)) {
						this.following.add(u);
					}
				}
			}
		}
	}
	
}
