package twitter;

//VISITOR PATTERN: Used in ControlPanel.java to get total user count
public class AllUsers implements Visitable{
    @Override
    public String accept(iVisitor visitor) {
        return visitor.visit(this);
    }
}
