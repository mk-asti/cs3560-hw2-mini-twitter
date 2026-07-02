/*
 * represents an individual User View window
 * 	> opens separate JFrame for each selected user
 * 	> assembles user view window and panel components
 */

package user_interface;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import user_functions.profile.User;


public class UserViewWindow extends JFrame {
    
    public UserViewWindow(User user) {
    	
    	// project 3 additions
        setTitle(user.getName() + "'s View");
        
        
        String lastUpdate = user.getLastUpdateTime() == 0 
        		? "no posts yet" : formatTime(user.getLastUpdateTime());

        System.out.println("opening view for: " + user.getName() + 
        		"\n\tuser created: " + formatTime(user.getCreationTime()));
        System.out.println("\tcurrent group: " + user.getCurrentGroup().getName() + 
        		"\n\tgroup created: " + formatTime(user.getCurrentGroup().getCreationTime()));
        
        if(user.getLastUpdateTime() == 0) {
        	System.out.println("\tlast updated: n/a (no posts yet)");
        }
        else
        	System.out.println("\tlast updated: " + formatTime(user.getLastUpdateTime()));
        // - end
        
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
    
    private String formatTime(long timeMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
        return sdf.format(new Date(timeMillis));
    }
}