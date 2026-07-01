/*
 * composite pattern (ui component for the user view action buttons)
 * 	> renders components for user view window
 */

package user_interface;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class UV_UserButtonsUI implements UIComponent {
    
    private final UV_FollowingLogic followingLogic;
    private final UV_FeedLogic feedLogic;
    
    public UV_UserButtonsUI(UV_FollowingLogic followingLogic, UV_FeedLogic feedLogic) {
        this.followingLogic = followingLogic;
        this.feedLogic = feedLogic;
    }

    @Override
    public JPanel render() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
        panel.setPreferredSize(new java.awt.Dimension(480, 100));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        
        panel.add(new InputComponent("follow user", 150, 30, 15, text -> followingLogic.followUser(text)).render());
        
        panel.add(new InputComponent("post tweet", 150, 30, 15, text -> feedLogic.postTweet(text)).render());
        
        return panel;
    }
}