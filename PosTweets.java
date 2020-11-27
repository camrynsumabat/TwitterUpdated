package twitter;

//VISITOR PATTERN: Used in ControlPanel.java to get percentage of positive tweets
public class PosTweets implements Visitable{
    @Override
    public String accept(iVisitor visitor) {
        return visitor.visit(this);
    }
}
