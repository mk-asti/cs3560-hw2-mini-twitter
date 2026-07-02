/*
 * coordinator class
 * 	> handles logic for user actions (posting tweets, following other users) 
 */

package user_functions.profile;

import user_functions.feed.NewPost;

public class UserAction {
	// project 3 additions
	private static User lastPoster = null;
	// - end
	
	// creates new post by a given user
	public static void newTweet(User user, String text) {
		NewPost post = new NewPost();
		post.setText(text);
		post.setUser(user);
		user.getUserFeed().addPost(post);
		
		// project 3 additions
		user.setLastUpdateTime(System.currentTimeMillis());
		lastPoster = user;
		// - end
		
		user.notifyObservers(post);
		
		user.notifyUi();
		
	}
	
	// user follows another user based on userID
	public static void followUser(User user, String targetID) {
		User target = User.findUser(targetID);
        if(target != null && !user.getFollowing().contains(targetID)) {
            user.getFollowing().add(targetID);
            target.getFollowers().add(user.getID());
            target.attach(user);
            
            user.notifyFollowUi();
            target.notifyFollowUi();
        }
	}
	
	// project 3 additions
	public static User getLastPoster() {
	    
		return lastPoster;
	}
	// - end
}
