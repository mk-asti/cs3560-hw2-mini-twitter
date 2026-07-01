/*
 * composite pattern (ui component for the news feed list in user view)
 * 	> displays all posts visible to User (own posts, following posts)
 * 	> supports live refresh
 */

package user_interface;

import javax.swing.*;
import user_functions.feed.NewPost;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import java.util.stream.Collectors;

public class UV_FeedUI implements UIComponent {
    
    private final UV_FeedLogic logic;
    private JList<String> list;
    
    public UV_FeedUI(UV_FeedLogic logic) {
        this.logic = logic;
    }

    // build and returns feed panel
    @Override
    public JPanel render() {
        JPanel panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JComponent header = new HeaderComponent("twitter feed").render();
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(header);
        
        panel.add(Box.createVerticalStrut(5));
        
        list = new JList<>(getFeedStrings());
        JScrollPane scrollPane = new JScrollPane(list);
        
        scrollPane.setPreferredSize(new Dimension(440, 300));
        scrollPane.setMaximumSize(new Dimension(440, 300));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(scrollPane);
        
        return panel;
    }
    
    // converts posts into strings
    private String[] getFeedStrings() {
        List<NewPost> posts = logic.getFeed();
        return posts.stream()
            .map(p -> p.getUser().getName() + ": " + p.getText())
            .collect(Collectors.toList())
            .toArray(new String[0]);
    }
    
    // utility: live refresh
    public void refresh() {
        list.setListData(getFeedStrings());
    }
}