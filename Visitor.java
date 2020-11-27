package twitter;

import java.util.*;

//VISITOR PATTERN: Implements visit methods for all objects
public class Visitor implements iVisitor {

    @Override
    public String visit(AllUsers allUsers) {
        ControlPanel control = ControlPanel.getInstance();
        String userList = "";
        for (User i: control.getAllUsers()) {
            userList += "\n" + i.getID();
        }
        return "Total number of users: " + control.getAllUsers().size() + "\n\nUsers are: " + userList;
    }

    @Override
    public String visit(AllGroups allGroups) {
        ControlPanel control = ControlPanel.getInstance();
        String groupList = "";
        for (UserGroup i: control.getAllGroups()) {
            groupList += "\n" + i.getID();
        }
        return "Total number of user groups: " + control.getAllGroups().size() + "\n\nUser groups are: " + groupList;
    }

    @Override
    public String visit(AllTweets allTweets) {
        ControlPanel control = ControlPanel.getInstance();
        return "Total number of tweets: " + control.getAllTweets().size();
    }

    @Override
    public String visit(PosTweets posTweets) {
        ControlPanel control = ControlPanel.getInstance();
        int happyTweets = 0;
        for(String i: control.getAllTweets()) {
            if (i.contains("happy") || i.contains("good")) {
                happyTweets++;
            }
        }
        float percentage = (float)happyTweets/control.getAllTweets().size()*100;
        return percentage + "% of total messages are positive.";
    }

    @Override
    public String visit(Validate validate) {
        ControlPanel control = ControlPanel.getInstance();
        String validation = "";
        int count = 0;

        List<UserComponent> allIDs = new ArrayList<>();
        allIDs.addAll(control.getAllUsers());
        allIDs.addAll(control.getAllGroups());

        Set<String> uniqueIDs = new HashSet<String>();
        for (int i = 0; i < allIDs.size(); i++) {
            if (!uniqueIDs.add(allIDs.get(i).getID()) || allIDs.get(i).getID().contains(" ")) {
                count++;
                validation += "\n" + allIDs.get(i).getID();
            } else {

            }
        }

        if (count > 0) {
            return "There are " + count + " invalid IDs:\n" + validation;
        } else {
            return "All IDs are valid";
        }
    }

    @Override
    public String visit(Updated updated) {
        ControlPanel control = ControlPanel.getInstance();

        Map.Entry<String, Long> maxVal = null;
        for (User i: control.getAllUsers()) {
            for (Map.Entry<String, Long> j : i.getTimeline().entrySet()) {
                if (maxVal == null || j.getValue().compareTo(maxVal.getValue()) > 0) {
                    maxVal = j;
                }
            }
        }
        
        String lastUpdatedUser = maxVal.getKey().substring(0, maxVal.getKey().indexOf(":"));
        return lastUpdatedUser;
    }
}
