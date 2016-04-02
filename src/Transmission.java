import java.time.LocalDateTime;
import java.util.ArrayList;

public class Transmission {

    private String message;
    private ArrayList<String> hashtags;
    private boolean visibility = true;
    private User target = null;
    private User author;
    private ArrayList<User> favoritedBy;
    private String timeCreated;

    /**
     *
     * @param message the content of the transmission
     * @param target the targets meant to receive the transmission
     * @param author the User who created the transmission
     */

    Transmission (String message, User target, User author){
        this.message = message;
		
		// populate hashtags arraylist
		String[] tokens = message.split(" ");
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].charAt(0) == '#') {
				this.hashtags.add(tokens[i]);
			}
		}

        this.target = target;
        this.author = author;
        this.timeCreated = LocalDateTime.now().toString();
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

    public boolean isPublic(){

        return visibility;
    }
}
