import java.util.ArrayList;

public class User {
    
    private String username;
    private String displayName;
    private ArrayList<String[]> photo = new ArrayList<>();
    private ArrayList<Transmission> history = new ArrayList<>();
    private ArrayList<User> blacklist = new ArrayList<>();
    private ArrayList<User> followers = new ArrayList<>();
    private ArrayList<User> following = new ArrayList<>();
    private int password;
    
    
}
