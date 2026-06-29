package user_functions.feed;

import java.util.ArrayList;
import java.util.List;

public class UserFeed {
	
	private List<NewPost> postHistory = new ArrayList<>();
	
	public void addPost(NewPost post) {
		postHistory.add(post);
	}
	
	// return a list of NewPost type of all the user's posts
	public List<NewPost> getPostHistory(){
		return postHistory;
	}

}
