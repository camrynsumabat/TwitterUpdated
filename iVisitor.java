package twitter;

//VISITOR PATTERN: Implemented in Visitor.java
public interface iVisitor {
    String visit(AllUsers allUsers);
    String visit(AllGroups allGroups);
    String visit(AllTweets allTweets);
    String visit(PosTweets posTweets);
    String visit(Validate val);
    String visit(Updated updated);
}
