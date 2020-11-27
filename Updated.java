package twitter;

//VISITOR PATTERN: Used in ControlPanel.java to return the most updated user
public class Updated implements Visitable {
    @Override
    public String accept(iVisitor visitor) { return visitor.visit(this); }
}
