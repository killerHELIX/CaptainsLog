import java.util.ArrayList;
import java.util.Comparator;

public class Menu implements Comparator<Transmission> {

    public void sortByTime(User currentUser, ArrayList<User> followingUsers){

        ArrayList<Transmission> viewableTransmissions = new ArrayList<>();

        // Populate viewableTransmissions arrayList
        for (User u : followingUsers){

            for (Transmission t : u.getHistory()) {

                viewableTransmissions.add(t);
            }
        }

        for (Transmission t : viewableTransmissions) {

            System.out.println(t.getMessage());
        }
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
