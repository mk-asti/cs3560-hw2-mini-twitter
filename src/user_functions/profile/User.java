/*
 * composite pattern (leaf node)
 * 	> represents an individual user
 * 	> extends Subject so followers can be notified for new posts instantaneously
 * 	> implements Observer, UserComponent, Visitable
 */

package user_functions.profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

import user_functions.feed.*;
import admin_service.ID_Validator;
import admin_service.Visitable;
import admin_service.Visitor;

public class User extends Subject implements Observer, UserComponent, Visitable {
	private static Map<String, User> userRegistry = new HashMap<>();
	private String userID;
	private String userName;
	private UserGroup currentGroup;
	private List<String> following;
	private List<String> followers;
	private UserFeed userFeed;
	
	// project 3 additions
	private long creationTime;
	private long lastUpdateTime;
	// - end
	
	private final List<Runnable> uiRefreshListeners = new ArrayList<>();
	private final List<Runnable> uiFollowRefreshListeners = new ArrayList<>();
	
	// create new user with a unique user id, username, empty following + followers list, empty user feed
	// place new user in main root group
	public User(String userName){
		this.userID = userName;
	    this.userName = userName;
	    
	    // project 3 additions
	    this.creationTime = System.currentTimeMillis();
	    this.lastUpdateTime = 0;
	    
	    ID_Validator.registerID(this.userID);
	    // - end
	    
	    this.following = new ArrayList<>();
	    this.followers = new ArrayList<>();
	    this.currentGroup = UserGroup.getRoot();
	    this.userFeed = new UserFeed();
	    UserGroup.getRoot().addMember(this);
	    userRegistry.put(this.userID, this);
	}

	// oberver pattern
	@Override
	public void update(NewPost post) {
		userFeed.addPost(post);
		
		// project 3 additions
		this.lastUpdateTime = System.currentTimeMillis();
		// - end
		
		notifyUi();
	}

	@Override
	public String getID() {
		return this.userID;
	}

	@Override
	public String getName() {
		return this.userName;
	}
	
	public void setCurrentGroup(UserGroup group) {
		this.currentGroup = group;
	}
	
	public UserGroup getCurrentGroup() {
		return currentGroup;
	}
	
	public List<String> getFollowing() {
		return this.following;
	}
	
	public List<String> getFollowers() {
		return this.followers;
	}
	
	public UserFeed getUserFeed() {
		return this.userFeed;
	}
	
	public static User findUser(String userID) {
		return userRegistry.get(userID);
	}
	
	// project 3 additions
	public long getCreationTime() {
	    return creationTime;
	}
	
	public long getLastUpdateTime() {
	    return lastUpdateTime;
	}

	public void setLastUpdateTime(long time) {
	    this.lastUpdateTime = time;
	}
	// - end
	
	
	// visitor pattern; accepts visitor objects
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}
	
	// method for live refreshes
	public void addUiRefreshListener(Runnable listener) {
        this.uiRefreshListeners.add(listener);
    }
	
	// triggers all registered feed ui for refresh callbacks
	// called when user makes new post
	public void notifyUi() {
	    for (Runnable listener : uiRefreshListeners) {
	        SwingUtilities.invokeLater(listener);
	    }
	}
	
	// registers a UI callback to be triggered when this user's following list updates
	// used by UV_FollowingLogic to refresh the following list view automatically
	public void addUiFollowRefreshListener(Runnable listener) {
	    this.uiFollowRefreshListeners.add(listener);
	}
	
	// triggers all registered following ui refresh callbacks
	public void notifyFollowUi() {
	    for (Runnable listener : uiFollowRefreshListeners) {
	        SwingUtilities.invokeLater(listener);
	    }
	}
	
	@Override
	public String toString() {
	    return userName;
	}
}
