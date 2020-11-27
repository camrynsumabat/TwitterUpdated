package twitter;

import javafx.scene.control.TreeItem;
import java.util.ArrayList;
import java.util.List;

//SINGLETON PATTERN: ControlPanel is a singleton
//VISITOR PATTERN: getUserTotal(), getGroupTotal(), getTweetTotal(), getPositiveMsgPercent() all use the visitor pattern
public class ControlPanel {

    private static ControlPanel instance = null;
    private Visitor visitor = new Visitor();
    private List<UserView> allUserViews = new ArrayList<UserView>();
    private List<User> allUsers = new ArrayList<User>();
    private List<UserGroup> allGroups = new ArrayList<UserGroup>();
    private List<String> allTweets = new ArrayList<String>();

    private ControlPanel() {}

    public static ControlPanel getInstance() {
        if(instance == null) {
            System.out.println("This is a new instance");
            instance = new ControlPanel();
        } else {
            System.out.println("Control Panel exists");
        }
        return instance;
    }

    //Add object to respective list for easy parsing
    public void addUserView(UserView userView) { allUserViews.add(userView); }

    public TreeItem<String> addUser(String userID, TreeItem<String> parent) {
        User user = new User(userID);
        allUsers.add(user);
        return makeBranch(user.getID(), parent);
    }

    public TreeItem<String> addGroup(String groupID, TreeItem<String> parent) {
        UserGroup group = new UserGroup(groupID);
        allGroups.add(group);
        return makeBranch(group.getID(), parent);
    }

    public void addTweet(String tweet) {
        allTweets.add(tweet);
    }

    //Get specific object given their String ID
    public UserView getUserView(String userViewID) {
        for (UserView i: allUserViews) {
            if(i.getUserViewID().equals(userViewID)) {
                return i;
            }
        }
        return null;
    }

    public User getUser(String userID) {
        for (User i: allUsers) {
            if(i.getID().equals(userID)) {
                return i;
            }
        }
        return null;
    }

    //Get all objects created by type
    public List<User> getAllUsers() { return allUsers; }

    public List<UserGroup> getAllGroups() { return allGroups; }

    public List<String> getAllTweets() { return allTweets; }

    //VISITOR PATTERN
    public String getUserTotal() {
        AllUsers users = new AllUsers();
        return users.accept(visitor);
    }

    public String getGroupTotal() {
        AllGroups groups = new AllGroups();
        return groups.accept(visitor);
    }

    public String getTweetTotal() {
        AllTweets tweets = new AllTweets();
        return tweets.accept(visitor);
    }

    public String getPositiveMsgPercent() {
        PosTweets pos = new PosTweets();
        return pos.accept(visitor);
    }

    public String getValidation() {
        Validate val = new Validate();
        return val.accept(visitor);
    }

    public String getUpdated() {
        Updated updated = new Updated();
        return updated.accept(visitor);
    }

    //Create branches on tree view
    public TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<String>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
}