package twitter;

//COMPOSITE PATTERN: UserComponent is the component, implemented in User through Subject and UserGroup
public interface UserComponent extends Visitable {
    public void setID(String newID);
    public String getID();
    public String getCreationTime(); //A3 Requirement 2: Add Creation Time Attribute to User and UserGroup
}
