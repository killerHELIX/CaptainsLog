
public interface Menu {

    void sortByTime(User currentUser);
    void sortByPopularity(User currentUser);
    User searchForUser(String user);
    Transmission searchForTransmission(String transmission);
    void searchByHashtag(String hashtag);
    boolean login(String password, User selectedUser);
    boolean logout();
    void modifySettings(User currentUser);
}
