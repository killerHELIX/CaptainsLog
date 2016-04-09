/** Transmission data member for CaptainsLog.
 *  @author Ryan Harris
 *  @author James Murphy
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.lang.Boolean;

public class Transmission {

    private String message;
    private ArrayList<String> hashtags;
    private boolean visible; // if true, don't send only to target
    private ArrayList<User> targets;
    private User author;
    private ArrayList<User> favoritedBy;
    private LocalDateTime timeCreated;

    /** Constructor for Transmission.
     *
     * @param message the content of the transmission
     * @param target the targets meant to receive the transmission
     * @param author the User who created the transmission
     */

    public Transmission (String message, User author, boolean isPrivate,
		ArrayList<User> userList) {
        this.message = message;
		
		// populate hashtags arraylist
		String[] tokens = message.split(" ");
		
		targets = new ArrayList<>();
		hashtags = new ArrayList<>();
		
		// populated @targets and #hashtags
		tokens = message.split(" ");
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].charAt(0) == '@') {
				// awful linear search algorithm
				for (User u : userList) {
					if (u.getUsername().equals(tokens[i].substring(1))) {
						this.targets.add(u);
						break;
					}
				}
			}
			if (tokens[i].charAt(0) == '#') {
				this.hashtags.add(tokens[i]);
			}
		}
		
		visible = !isPrivate;
		
		favoritedBy = new ArrayList<>(0);
        this.author = author;
        this.timeCreated = LocalDateTime.now();
        author.addToHistory(this);
    }

    public User getAuthor(){
        return author;
    }

    public String getMessage(){
        return message;
    }

    public ArrayList<String> getHashtags(){
        return hashtags;
    }
	
	public int getNumFavorites() {
		return favoritedBy.size();
	}
	
    public boolean isPublic(){
        return visible;
    }
	
	public LocalDateTime getTime() {
		return timeCreated;
	}
	
	public String getTimestamp() {
		return timeCreated.toString();
	}
	
	@Override
	public String toString() {
		return author.toString() + ": " + message;
	}
	
	/** For writing to files for storage - not to be printed to end user!*/
	public String toRecord() {
		Object[] fbarray = favoritedBy.toArray();
		User[] appreciators = new User[fbarray.length];
		for (int i = 0; i < fbarray.length; i++) {
			appreciators[i] = (User) fbarray[i];
		}
		
		String app = appreciators[0].toString();
		for (int i = 1; i < appreciators.length; i++) {
			app += "," + appreciators[i].toString();
		} 
		
		return author.toString()+"\f"+Boolean.toString(visible)+"\f"+
			app+"\f"+timeCreated.toString();
	}
}
