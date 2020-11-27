package twitter;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

//Used to display the user view
public class UserView {

    private String userViewID = "";
    private ListView<String> timelineListView = new ListView<String>();
    private ControlPanel control = ControlPanel.getInstance();

    public UserView(String ID) {
        this.userViewID = ID;
        control.addUserView(this);
    }

    public String getUserViewID() {
        return userViewID;
    }

    public ListView<String> getTimelineListView() {
        return timelineListView;
    }

    public void display(User user) {

        Stage window = new Stage();

        window.setTitle(user.getID());
        window.setMinWidth(250);
        window.setMaxHeight(500);

        //label
        Label creationTimeLabel = new Label();
        creationTimeLabel.setText(user.getCreationTime());

        //text fields
        TextField followUserText = new TextField();
        TextField tweetText = new TextField();

        //list views
        ListView<String> followingListView = new ListView<>();
        followingListView.getItems().add("Following: ");
        List<String> followingList = user.getFollowingID();
        for (String i: followingList) {
            followingListView.getItems().add(i);
        }

        this.getTimelineListView().getItems().add("Newsfeed");
        List<String> timelineList = user.getTimelineList();
        Collections.reverse((List<?>) timelineList);
        for (String i: timelineList) {
            this.getTimelineListView().getItems().add(i);
        }

        //buttons
        Button followUserButton = new Button("Follow User");
        followUserButton.setOnAction(e -> {
            String followUserID = followUserText.getText();
            if (followUserID.trim().length() > 0) {
                user.follow(control.getUser(followUserID));
                followingListView.getItems().add(followUserID);
            } else {
                Alert.display("Invalid input", "Please enter an existing user to follow");
            }
        });

        Button postTweetButton = new Button("Post tweet");
        postTweetButton.setOnAction(e -> {
            String tweet = tweetText.getText();
            if (tweet.trim().length() > 0) {
                tweet = user.getID() + ": " + tweet + "\n-posted at " + user.getLastUpdatedTime(user.getLastUpdatedTime());
                user.setTweet(tweet);
                control.addTweet(tweet);
                timelineListView.getItems().add(1, tweet);
                for (String i: user.getFollowersID()) {
                    control.getUserView(i).timelineListView.getItems().add(1, tweet);
                }
            } else {
                Alert.display("Invalid input", "Please enter a tweet before posting");
            }
        });

        //layout
        VBox layout = new VBox(10);
        layout.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
        HBox creationTime = new HBox(10, creationTimeLabel);
        HBox followUser = new HBox(10, followUserText, followUserButton);
        HBox following = new HBox(followingListView);
        HBox tweet = new HBox(10, tweetText, postTweetButton);
        HBox timeline = new HBox(timelineListView);

        layout.getChildren().addAll(creationTime, followUser, following, tweet, timeline);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
        }
    }
