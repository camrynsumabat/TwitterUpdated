package twitter;

//OBSERVER PATTERN: implemented in User through Subject
public interface Follower {
    public void update(User user);
}
