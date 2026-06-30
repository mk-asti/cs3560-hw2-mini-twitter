package user_interface;

import javax.swing.*;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

public class UV_FollowingUI implements UIComponent {
    
    private final UV_FollowingLogic logic;
    private JList<String> list;
    
    public UV_FollowingUI(UV_FollowingLogic logic) {
        this.logic = logic;
    }

    @Override
    public JPanel render() {
        JPanel panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JComponent header = new HeaderComponent("following").render();
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(header);
        
        panel.add(Box.createVerticalStrut(5));    
        
        list = new JList<>(logic.getFollowingList().toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(list);
        
        scrollPane.setPreferredSize(new Dimension(440, 150));
        scrollPane.setMaximumSize(new Dimension(440, 150));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(scrollPane);
        
        return panel;
    }
    
    public void refresh() {
        list.setListData(logic.getFollowingList().toArray(new String[0]));
    }
}