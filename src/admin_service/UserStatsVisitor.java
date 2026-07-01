/*
 * visitor pattern (concrete visitor implementation)
 * 	> traverses entire user/usergroup composite tree
 * 	> collects statistics (total user count, total group count, 
 * 	> total post count, positive post percentage)
 */

package admin_service;

import java.util.ArrayList;
import java.util.List;

import user_functions.feed.NewPost;
import user_functions.profile.User;
import user_functions.profile.UserComponent;
import user_functions.profile.UserGroup;

public class UserStatsVisitor implements Visitor {
	
	private int totalUserCount = 0;
	private int totalGroupCount = 0;
	private int totalPostCount = 0;
	private int totalPositivityCount = 0;

	
	// visits user node
	@Override
	public void visit(User user) {
		totalUserCount++;	// increment total number of users
		
		List<NewPost> userFeed = user.getUserFeed().getPostHistory();
		totalPostCount += userFeed.size();	// increment post count
		
		for(NewPost tweet : userFeed) {
			if(checkPositive(tweet.getText())){
				totalPositivityCount++;		// increment post with positive words count
			}
		}
	}

	// visits usergroup node
	@Override
	public void visit(UserGroup userGroup) {
		totalGroupCount++;	// increment group count
		for(UserComponent member : userGroup.getMembers()) {
			((Visitable) member).accept(this);	// recursively visit each member in all groups
		}
		
	}
	
	public int getUserCount() {
		
		return totalUserCount;
	}

	public int getGroupCount() {
		
		return totalGroupCount;
	}

	public int getPostCount() {
		
		return totalPostCount;
	}

	public int getPositivityCount() {
		
		return totalPositivityCount;
	}
	
	
	// helper method: check for positive words
	private boolean checkPositive(String postText) {
		String text = postText.toLowerCase();
		for(String word : listPositiveWords()) {
			if(text.contains(word)) {
				return true;
			}
		}
		
		return false;
	}
	
	// helper method: create list of positive words
	private List<String> listPositiveWords(){
		List<String> pos = new ArrayList<>();
		
		pos.add("good");
		pos.add("happy");
		pos.add("thank");
		pos.add("thankful");
		pos.add("great");
		pos.add("fire");
		pos.add("bet");
		pos.add("sick");
		pos.add("cool");
		
		return pos;
	}
	
	// helper method: calculate positivity post percentage
	public double calculatePercentage() {
		if(totalPostCount == 0) {
			return 0.0;
		}
		
		return ((double)totalPositivityCount / totalPostCount) * 100;
	}
	
}
