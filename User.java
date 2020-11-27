package twitter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//COMPOSITE PATTERN: User is the leaf (implements UserComponent)
//OBSERVER PATTERN: User is a subject (implements Subject) and observer (implements Follower)
public class User extends Subject {

    private String userID = "", tweet;
    private long creationTime, lastUpdatedTime;
    private HashMap<String, Long> timeline = new HashMap<String, Long>();

    public User(String newID) {
        setID(newID);
        creationTime = lastUpdatedTime = System.currentTimeMillis();
        System.out.println("New user added called " + newID + "created at " + creationTime);
    }

    public void setID(String newID) {
        userID = newID;
    }

    public String getID() {
        return userID;
    }

    public String getCreationTime() {return "Created at " + formatTime(creationTime);}

    private String formatTime(long creationTime) {
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
        Date result = new Date(creationTime);
        return simple.format(result);
    }

    public List<String> getFollowingID() {
        List<String> followingID = new ArrayList<String>();
        for (User i: following) {
            followingID.add(i.getID());
        }
        return followingID;
    }

    public List<String> getFollowersID() {
        List<String> followersID = new ArrayList<String>();
        for (User i: followers) {
            followersID.add(i.getID());
        }
        return followersID;
    }

    public void setTweet(String newTweet) {
        lastUpdatedTime = System.currentTimeMillis();
        this.tweet = newTweet;
        notifyAllFollowers();
        timeline.put(tweet, lastUpdatedTime);
        System.out.println("Tweet added to " + this.getID() + "'s timeline");
    }

    public String getTweet() {
        return tweet;
    }

    //A3 Requirement 3: Add last update time attribute to User
    public String getLastUpdatedTime(long lastUpdatedTime) {
        return formatTime(lastUpdatedTime);
    }

    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public List<String> getTimelineList() {
        System.out.println(this.getID() + "'s timeline");
        for(String i: timeline.keySet()) {
            System.out.println(i);
        }

        List<String> result = new ArrayList<>(timeline.keySet());
        return result;
    }

    public HashMap<String, Long> getTimeline() {
        return timeline;
    }

    @Override
    public String accept(iVisitor visitor) {
        return "";
    }
}
