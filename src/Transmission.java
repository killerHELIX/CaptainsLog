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
     * @param author the User who created the transmission
     * @param isPrivate if the transmission can only be seen by @targets
     * @param userList the master user list
     */

    public Transmission (String message, User author, boolean isPrivate,
		ArrayList<User> userList) {
        this.message = message;
		
		// populate hashtags arraylist
        this.hashtags = new ArrayList<>();
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
	
	/** User u likes this transmission. */
	public void isLikedBy(User u) {
		this.favoritedBy.add(u);
	}
	
	private void setTime(LocalDateTime t) {
		timeCreated = t;
	}
	
	public String getTimestamp() {
		return timeCreated.toString();
	}
	
	@Override
	public String toString() {
		String portrait = String.join("\n",
			Arrays.asList(author.getPhoto())) + "\n";
		
		return portrait + author.getDisplayName() + " (" +
			author.getUsername() + ")\n" + message + "\n";
	}
	
	/** For writing to files for storage - not to be printed to end user!*/
	public String toRecord() {
		Object[] fbarray = favoritedBy.toArray();
		User[] appreciators = new User[fbarray.length];
		for (int i = 0; i < fbarray.length; i++) {
			appreciators[i] = (User) fbarray[i];
		}

        String app = "";

        if (fbarray.length == 0) {
            app = "null";
        } else {
            app = appreciators[0].toString();
            for (int i = 1; i < appreciators.length; i++) {
                app += "," + appreciators[i].toString();
            }
        }
		
		return author.toString()+"\f"+message+"\f"+
			Boolean.toString(visible)+"\f"+app+"\f"+timeCreated.toString();
	}
	
	// parse a line of text as a transmission
	public static Transmission fromRecord(String record,
		ArrayList<User> users) throws Exception {
		
		String[] fields = record.split("\f");
		User author = null;
		// name match to find author
		for (User u : users) {
			if (fields[0].split(" ")[0].equals(u.getUsername())) {
				author = u;
				break;
			}
		}
		
		if (author == null) {
			throw new Exception("Author of message '" + fields[1] +
				"' does not exist!");
		}
		
		boolean vis = Boolean.parseBoolean(fields[2]);
		LocalDateTime timestamp = LocalDateTime.parse(fields[4]);
		
		// create the new transmission object
		Transmission tr = new Transmission(fields[1], author, vis, users);
		tr.setTime(timestamp);
		
		// parse favoriters of message
		String[] favoritedBy = fields[3].split(",");
		for (String s : favoritedBy) {
			for (User u : users) {
				if (s.split(" ")[0].equals(u.getUsername())) {
					tr.isLikedBy(u);
					break; // only breaks from inner loop
				}
			}
		}
		
		return tr;
	}
	
}
