/*
 * logic class for the news feed section of User View
 * 	> handles posting new posts and providing feed data to UV_FeedUI
 * 	> registers a UI refresh listener on the User for live post refresh
 */

package user_interface;

import user_functions.profile.User;
import user_functions.profile.UserAction;
import user_functions.feed.NewPost;
import java.util.List;

public class UV_FeedLogic {
    
    private final User user;
    private UV_FeedUI feedUI;
    
    public UV_FeedLogic(User user) {
        this.user = user;
        
        this.user.addUiRefreshListener(() -> {
            if (feedUI != null) {
                feedUI.refresh();
            }
        });
    }
    
    public void setFeedUI(UV_FeedUI feedUI) {
        this.feedUI = feedUI;
    }
    
    public void postTweet(String text) {
        if(text == null || text.isBlank()) {
        	return;
        }
        UserAction.newTweet(user, text);
        
    }
    
    public List<NewPost> getFeed() {
        return user.getUserFeed().getPostHistory();
    }
}