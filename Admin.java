package twitter;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//DRIVER CLASS
public class Admin extends Application {

    Stage admin;
    Button addUserButton, addGroupButton, userViewButton, userTotalButton,
            groupTotalButton, msgTotalButton, posMsgPercentButton, validateButton, lastUpdatedUserButton;
    TreeView<String> tree;
    ControlPanel control = ControlPanel.getInstance();
    String selected;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        admin = primaryStage;
        admin.setTitle("Twitter");

        //testing singleton pattern
        control.getInstance();

        //root
        TreeItem<String> hiddenRoot = new TreeItem<>("Hidden Root");
        hiddenRoot.setExpanded(true);

        TreeItem<String> root = control.addGroup("AllUsersAndGroups", hiddenRoot);

        control.addUser("John", root);
        control.addUser("Bob", root);
        control.addUser("Stevie", root);

        TreeItem<String> CS3560 = control.addGroup("CS3560", root);
        control.addUser("stu1", CS3560);
        control.addUser("stu2", CS3560);

        TreeItem<String> CS3560Session1 = control.addGroup("CS3560Session1", CS3560);
        control.addUser("stu4", CS3560Session1);
        control.addUser("stu5", CS3560Session1);

        control.addUser("oostu", root);
        control.addUser("ppstu2", root);

        //create tree
        tree = new TreeView<>(hiddenRoot);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if(newValue != null) {
                selected = newValue.getValue();
                System.out.println(selected);
            }
        });

        //text fields
        TextField addUserText = new TextField();
        TextField addGroupText = new TextField();

        //buttons
        addUserButton = new Button("Add User");
        addUserButton.setOnAction(e -> {
            String newUserID = addUserText.getText();
            if (newUserID.trim().length() > 0) {
                control.addUser(newUserID, tree.getSelectionModel().getSelectedItem());
            } else {
                Alert.display("Invalid input", "Please enter a new user ID before adding");
            }
        });

        addGroupButton = new Button("Add Group");
        addGroupButton.setOnAction(e -> {
            String newGroupID = addGroupText.getText();
            if (newGroupID.trim().length() > 0) {
                control.addGroup(newGroupID, tree.getSelectionModel().getSelectedItem());
            } else {
                Alert.display("Invalid input", "Please enter a new group ID before adding");
            }
        });

        userViewButton = new Button("Open User View");
        userViewButton.setOnAction(e -> {
            UserView userView = new UserView(selected);
            userView.display(control.getUser(selected));
        });

        userTotalButton = new Button("Show User Total");
        userTotalButton.setOnAction(e -> Alert.display("User Total", control.getUserTotal()));

        groupTotalButton = new Button("Show Group Total");
        groupTotalButton.setOnAction(e -> Alert.display("User Group Total", control.getGroupTotal()));

        msgTotalButton = new Button("Show Message Total");
        msgTotalButton.setOnAction(e -> Alert.display("Tweet Total", control.getTweetTotal()));

        posMsgPercentButton = new Button("% Positive Msgs");
        posMsgPercentButton.setOnAction(e -> Alert.display("Percentage of Positive Tweets", control.getPositiveMsgPercent()));

        //A3 requirement 1: User/group ID verification
        validateButton = new Button("Validate Users/Groups");
        validateButton.setOnAction(e -> Alert.display("Validating User and Group IDs", control.getValidation()));

        //A3 requirement 4: Find the last updated user
        lastUpdatedUserButton = new Button("Last Updated User");
        lastUpdatedUserButton.setOnAction(e -> Alert.display("Last Updated User", control.getUpdated()));

        //layout
        HBox layout = new HBox(10);
        layout.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));

        VBox treeView = new VBox(10, tree);
        VBox otherButtons = new VBox(10);
        otherButtons.setSpacing(20);

        HBox addUser = new HBox(10, addUserText, addUserButton);
        HBox addGroup = new HBox(10, addGroupText, addGroupButton);
        HBox openUserView = new HBox(userViewButton);
        HBox userAndGroupTotals = new HBox(10, userTotalButton, groupTotalButton);
        HBox msgAndPosTotals = new HBox(10, msgTotalButton, posMsgPercentButton);
        HBox validateAndUpdated = new HBox(10, validateButton, lastUpdatedUserButton);
        otherButtons.getChildren().addAll(addUser, addGroup, openUserView, userAndGroupTotals, msgAndPosTotals, validateAndUpdated);

        layout.getChildren().addAll(treeView, otherButtons);
        Scene scene = new Scene(layout, 500, 350);
        admin.setScene(scene);
        admin.show();
    }
}
