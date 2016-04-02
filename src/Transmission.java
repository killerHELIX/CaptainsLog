import java.util.ArrayList;

public class Transmission {

    private String message;
    private ArrayList<String> hashtags = new ArrayList<>();
    private boolean visibility = true;
    private User target = null;
    private User author;
    private ArrayList<User> favoritedBy = new ArrayList<>();

    /**
     *
     * @param message the content of the transmission
     * @param hashtag the hashtags for the transmission
     * @param target the targets meant to receive the transmission
     * @param author the User who created the transmission
     */
    Transmission (String message, String hashtag, User target, User author){
        this.message = message;
        this.hashtags.add(hashtag);
        this.target = target;
        this.author = author;
    }

    Transmission (String message, String[] hashtags, User target, User author){
        this.message = message;

        for (int i = 0; i < hashtags.length; i++){
            this.hashtags.add(hashtags[i]);
        }

        this.target = target;
        this.author = author;
    }

    /**
     *
     * @param message the content of the transmission
     * @param target the hashtags for the transmission
     * @param author the User who created the transmission
     */
    Transmission (String message, User target, User author){

        this.message = message;
        this.target = target;
        this.author = author;
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
