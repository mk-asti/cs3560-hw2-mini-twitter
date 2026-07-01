/*
 * stores user feed history for a single user
 * 	> data container: holds all relevant NewPost objects
 * 	> (user's own posts and feed posts they receive from following)
 */

package user_functions.feed;

import java.util.ArrayList;
import java.util.List;

public class UserFeed {
	
	private List<NewPost> postHistory = new ArrayList<>();
	
	
	// creates new post by the user
	public void addPost(NewPost post) {
		postHistory.add(post);
	}
	
	// return a list of NewPost type of all the user's posts
	public List<NewPost> getPostHistory(){
		return postHistory;
	}

}
