package twitter;

import java.util.ArrayList;
import java.util.List;

//OBSERVER PATTERN: implemented in User.java, User is a concrete subject and observer (Follower)
public abstract class Subject implements UserComponent, Follower {
    public List<User> following = new ArrayList<User>(), followers = new ArrayList<User>();

    public void follow (User user) {
        following.add(user);
        System.out.println(this.getID() + " is now following " + user.getID());
        user.followers.add((User) this);
    }

    public void notifyAllFollowers() {
        for (User i: followers) {
            update(i);
        }
    }

    public void update(User user) {
        user.getTimelineList().add(user.getTweet());
        System.out.println("Tweet added to " + user.getID() + "'s timeline");
    }

}
