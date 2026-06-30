package user_functions.profile;

import user_functions.feed.NewPost;

public class UserAction {
	public static void newTweet(User user, String text) {
		NewPost post = new NewPost();
		post.setText(text);
		post.setUser(user);
		user.getUserFeed().addPost(post);
		user.notifyObservers(post);
		
		user.notifyUi();
		
	}
	
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
}
