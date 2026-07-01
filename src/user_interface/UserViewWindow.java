/*
 * represents an individual User View window
 * 	> opens separate JFrame for each selected user
 * 	> assembles user view window and panel components
 */

package user_interface;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import user_functions.profile.User;

public class UserViewWindow extends JFrame {
    
    public UserViewWindow(User user) {
        setTitle(user.getName() + "'s View");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // only closes this window, not the whole app
        setLayout(new BorderLayout());
        
        UV_UserButtonsLogic buttonsLogic = new UV_UserButtonsLogic(user);
        UV_FollowingLogic followingLogic = new UV_FollowingLogic(user);
        UV_FeedLogic feedLogic = new UV_FeedLogic(user);
        
        UV_UserButtonsUI buttonsView = new UV_UserButtonsUI(followingLogic, feedLogic);
        UV_FollowingUI followingView = new UV_FollowingUI(followingLogic);
        UV_FeedUI feedView = new UV_FeedUI(feedLogic);
        
        add(buttonsView.render(), BorderLayout.NORTH);
        add(followingView.render(), BorderLayout.CENTER);
        add(feedView.render(), BorderLayout.SOUTH);
        
        followingLogic.setFollowingUI(followingView);
        feedLogic.setFeedUI(feedView);
        
        setVisible(true);
    }
}