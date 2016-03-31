import java.util.ArrayList;

/**
 * Created by ryanharris on 3/28/16.
 */
public class Transmission {

    private String message;
    private ArrayList<String> hashtag = new ArrayList<>();
    private boolean visibility = true;
    private User target = null;
    private User author;
    private ArrayList<User> favoritedBy = new ArrayList<>();

    public User viewAuthor(){

        return author;
    }

    public String getMessage(){

        return message;
    }

    public ArrayList<String> getHashtag(){

        return hashtag;
    }

    public boolean isPublic(){

        return visibility;
    }
}
