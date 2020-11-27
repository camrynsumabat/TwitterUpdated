package twitter;

//VISITOR PATTERN: Implemented in Visitable classes - AllUsers, AllGroups, AllTweets, PosTweets
public interface Visitable {
    String accept(iVisitor visitor);
}
