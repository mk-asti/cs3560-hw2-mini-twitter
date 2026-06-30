package user_interface;

import user_functions.profile.User;
import user_functions.profile.UserAction;

public class UV_FollowingLogic {
    
    private final User user;
    private UV_FollowingUI followingUI;
    
    public UV_FollowingLogic(User user) {
        this.user = user;
        
        this.user.addUiFollowRefreshListener(() -> {
            if (followingUI != null) {
                followingUI.refresh();
            }
        });
    }
    
    public void setFollowingUI(UV_FollowingUI followingUI) {
        this.followingUI = followingUI;
    }
    
    public void followUser(String targetID) {
        if(targetID == null || targetID.isBlank()) {
        	return;
        }
        UserAction.followUser(user, targetID);
    }
    
    public java.util.List<String> getFollowingList() {
        return user.getFollowing();
    }
}