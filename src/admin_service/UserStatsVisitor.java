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

	
	// visits user node
	@Override
	public void visit(User user) {
		totalUserCount++;	// increment total number of users
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
	    return NewPost.getTotalPostCount();
	}
	
	// helper method: calculate positivity post percentage
	public double calculatePercentage() {
	    if(NewPost.getTotalPostCount() == 0) {
	    	return 0.0;
	    }
	    
	    return ((double) NewPost.getTotalPositiveCount() / NewPost.getTotalPostCount()) * 100;
	}
	
}
