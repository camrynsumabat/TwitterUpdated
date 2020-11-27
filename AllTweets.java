package twitter;

//VISITOR PATTERN: Used in ControlPanel.java to get total tweet count
public class AllTweets implements Visitable {
    @Override
    public String accept(iVisitor visitor) {
        return visitor.visit(this);
    }
}
