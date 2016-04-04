import java.time.LocalDateTime;
import java.util.ArrayList;

public class Transmission {

    private String message;
    private ArrayList<String> hashtags;
    private boolean visible; // if true, don't send only to target
    private User target;
    private User author;
    private ArrayList<User> favoritedBy;
    private LocalDateTime timeCreated;

    /**
     *
     * @param message the content of the transmission
     * @param target the targets meant to receive the transmission
     * @param author the User who created the transmission
     */

    public Transmission (String message, User target, User author,
		boolean isPrivate) {
        this.message = message;
		
		// populate hashtags arraylist
		String[] tokens = message.split(" ");
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].charAt(0) == '#') {
				this.hashtags.add(tokens[i]);
			}
		}
		
		visible = !isPrivate;
		
		this.target = target;
        this.author = author;
        this.timeCreated = LocalDateTime.now();
    }

    public User viewAuthor(){
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
}
