import java.util.ArrayList;

public class User implements Menu {

    private String username;
    private String displayName;
    private ArrayList<String[]> photo = new ArrayList<>();
    private ArrayList<Transmission> history = new ArrayList<>();
    private ArrayList<User> blacklist = new ArrayList<>();
    private ArrayList<User> followers = new ArrayList<>();
    private ArrayList<User> following = new ArrayList<>();
    private int password;

    public void sortByTime(User currentUser){

    }
    public void sortByPopularity(User currentUser){

    }
    public User searchForUser(String user){

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
