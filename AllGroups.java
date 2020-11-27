package twitter;

//VISITOR PATTERN: Used in ControlPanel.java to get total group count
public class AllGroups implements Visitable {
    @Override
    public String accept(iVisitor visitor) {
        return visitor.visit(this);
    }
}
