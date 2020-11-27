package twitter;

//VISITOR PATTERN: Used in ControlPanel.java to validate user/group IDs
public class Validate implements Visitable {
    @Override
    public String accept(iVisitor visitor) {
        return visitor.visit(this);
    }
}
